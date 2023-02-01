package it.tristana.spacewars.arena;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import it.tristana.commons.arena.BasicEnclosedArena;
import it.tristana.commons.arena.powerup.BasicPowerupsManager;
import it.tristana.commons.config.SettingsTeams;
import it.tristana.commons.helper.BasicTickablesManager;
import it.tristana.commons.helper.CommonsHelper;
import it.tristana.commons.helper.PlayersManager;
import it.tristana.commons.interfaces.Reloadable;
import it.tristana.commons.interfaces.arena.ShopArena;
import it.tristana.commons.interfaces.arena.Status;
import it.tristana.commons.interfaces.database.UsersManager;
import it.tristana.commons.interfaces.gui.ClickedGuiManager;
import it.tristana.commons.interfaces.util.Powerup;
import it.tristana.commons.interfaces.util.PowerupsManager;
import it.tristana.commons.interfaces.util.TickablesManager;
import it.tristana.commons.scoreboard.ScoreboardManager;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.arena.player.kit.Kit;
import it.tristana.spacewars.arena.player.kit.KitsManager;
import it.tristana.spacewars.arena.powerup.PowerupsBuilder;
import it.tristana.spacewars.arena.shop.SpaceVillagerShop;
import it.tristana.spacewars.arena.team.ColorsHelper;
import it.tristana.spacewars.arena.team.Nexus;
import it.tristana.spacewars.arena.team.SpaceTeam;
import it.tristana.spacewars.config.SettingsArena;
import it.tristana.spacewars.config.SettingsMessages;
import it.tristana.spacewars.config.SettingsPowerups;
import it.tristana.spacewars.config.SettingsScoreboard;
import it.tristana.spacewars.database.SpaceUser;
import it.tristana.spacewars.gui.kit.GuiKit;
import it.tristana.spacewars.scoreboard.SpacePersonalScoreboardManager;
import it.tristana.spacewars.scoreboard.SpaceTeamableScoreboardManager;

public class SpaceArena extends BasicEnclosedArena<SpaceTeam, SpacePlayer> implements Reloadable, ShopArena<SpacePlayer, SpaceVillagerShop> {

	public static final int TPS = 4;

	private final Main plugin;
	private final UsersManager<SpaceUser> usersManager;
	private final List<Location> nexusLocations;
	private final Collection<SpherePowerup> spheres;
	private final Collection<SpaceVillagerShop> shops;
	private final ClickedGuiManager guiManager;
	private final PowerupsManager<SpacePlayer> powerupsManager;
	private final ScoreboardManager<SpaceUser> preGameScoreboardManager;
	private final ScoreboardManager<SpaceUser> gameScoreboardManager;
	private final PlayersManager playersManager;
	private final KitsManager kitsManager;

	private final SettingsArena settingsArena;
	private final SettingsPowerups settingsPowerups;
	private final SettingsMessages settingsMessages;
	private final SettingsTeams settingsTeams;

	private TickablesManager playersPositionManager;

	public SpaceArena(World world, String name, Main plugin) {
		super(world, name, plugin.getPartiesManager());
		this.plugin = plugin;
		this.usersManager = plugin.getUsersManager();
		this.playersManager = plugin.getPlayersManager();
		this.kitsManager = plugin.getKitsManager();
		this.guiManager = plugin.getClickedGuiManager();
		this.settingsArena = plugin.getSettingsArena();
		this.settingsPowerups = plugin.getSettingsPowerups();
		SettingsScoreboard settingsScoreboard = plugin.getSettingsScoreboard();
		this.preGameScoreboardManager = new SpacePersonalScoreboardManager(plugin, settingsScoreboard::getPreGameName, settingsScoreboard::getPreGameLines);
		this.gameScoreboardManager = new SpaceTeamableScoreboardManager(this, settingsScoreboard);
		this.settingsMessages = plugin.getSettingsMessages();
		this.settingsTeams = plugin.getSettingsTeams();
		this.nexusLocations = new ArrayList<>();
		this.spheres = new HashSet<>();
		this.shops = new HashSet<>();
		this.powerupsManager = new BasicPowerupsManager<>(PowerupsBuilder.createPowerups(settingsPowerups));
		reset();
	}

	@Override
	public void reload() {
		closeArena();
	}

	@Override
	public int getTps() {
		return SpaceArena.TPS;
	}

	@Override
	public void startGame() {
		super.startGame();

		int minPlayers = Integer.MAX_VALUE;
		for (SpaceTeam team : teams) {
			int players = team.getPlayers().size();
			if (players < minPlayers) {
				minPlayers = players;
			}
		}
		int startingLives = minPlayers * settingsArena.getStartingLivesPerPlayer();

		teams.forEach(team -> team.setLives(startingLives));
		players.forEach(player -> {
			if (player.getKit() == null) {
				player.setKit(kitsManager.getRandom());
			}
			player.getPlayer().getInventory().clear();
			player.giveDefaultItems();
			player.setPlayingGameMode();
			SpaceUser user = player.getUser();
			preGameScoreboardManager.removeUser(user);
			gameScoreboardManager.addUser(user);
		});
		buildNexuses();
		world.setGameRule(GameRule.NATURAL_REGENERATION, false);
	}

	@Override
	public void setStatus(Status status) {
		super.setStatus(status);
	}

	@Override
	public boolean onPlayerJoin(Player player) {
		boolean onJoin = super.onPlayerJoin(player);
		if (onJoin) {
			openGuiMenu(player);
			playersManager.fixHiddenPlayers(player);
			preGameScoreboardManager.addUser(usersManager.getUser(player));
		}
		return onJoin;
	}

	@Override
	public void closeArena() {
		while (players.size() > 0) {
			exit(players.get(0));
		}

		reset();
	}

	@Override
	public boolean setSpawnpoint(Location location) {
		boolean flag = spawnpoints.size() < Math.min(settingsTeams.getTeams().length, ColorsHelper.MAX_SUPPORTED_TEAMS);
		if (flag) {
			flag = super.setSpawnpoint(location);
		}
		return flag;
	}

	@Override
	public void onPlayerLeave(Player player) {
		exit(getArenaPlayer(player));
	}

	@Override
	protected void playingPhase() {
		if (!isFullTick()) {
			return;
		}

		if (checkWinningConditions()) {
			setStatus(Status.ENDING);
			CommonsHelper.broadcast("Game ended");
			return;
		}

		teams.forEach(SpaceTeam::runTick);
		players.forEach(SpacePlayer::runTick);
		spheres.forEach(SpherePowerup::runTick);
		gameScoreboardManager.runTick();
	}

	@Override
	protected SpacePlayer createArenaPlayer(Player player) {
		return new SpacePlayer(this, usersManager.getUser(player));
	}

	@Override
	protected SpaceTeam createTeam(int index) {
		return new SpaceTeam(this, spawnpoints.get(index), getTeamName(index), getColorCode(index), new Nexus(nexusLocations.get(index)), ColorsHelper.getColor(index));
	}

	@Override
	protected int getTeamsForNumPlayers(int players) {
		return Math.min(ColorsHelper.MAX_SUPPORTED_TEAMS, Math.min(spawnpoints.size(), nexusLocations.size()));
	}

	@Override
	protected void reset() {
		super.reset();
		if (plugin == null) {
			// super class initialization called this
			// method before the plugin reference was set
			return;
		}

		if (playersPositionManager != null) {
			playersPositionManager.stopClock();
		}

		playersPositionManager = new BasicTickablesManager();
		spheres.forEach(playersPositionManager::registerTickable);
		playersPositionManager.startClock(plugin, 1);
	}

	@Override
	public Collection<SpaceVillagerShop> getShops() {
		return shops;
	}

	@Override
	public void addShop(SpaceVillagerShop shop) {
		shops.add(shop);
	}

	public void openGuiMenu(Player player) {
		guiManager.getGui(GuiKit.class).open(player);
	}

	public void onPillarBroken(SpacePlayer player, Nexus target) {
		broadcast("Pillar broken");
	}

	public void onNexusBroken(SpacePlayer player, Nexus target) {
		SpaceTeam targetTeam = target.getTeam();
		targetTeam.getPlayers().forEach(targetPlayer -> {
			targetPlayer.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 999999, 0, false, false));
		});
		broadcast("Nexus broken");
	}

	public void onShot(SpacePlayer shooter) {
		ShotManager.onShot(this, shooter);
	}

	public void onDamage(SpacePlayer player, SpacePlayer damager, double damage) {
		Kit kit = player.getKit();
		double totalArmor = kit.getArmor(player);
		if (damager != null) {
			double baseArmor = kit.getBaseArmor();
			double bonusArmor = totalArmor - baseArmor;
			totalArmor -= damager.getKit().getGun().getTargetArmorReduced(baseArmor, bonusArmor);
		}
		onTrueDamage(player, damager, 100d / Math.max(1, 100 + totalArmor) * damage);
	}

	public void onTrueDamage(SpacePlayer player, SpacePlayer damager, double damage) {
		if (damager != null) {
			player.setLastAttacker(damager);
		}
		player.getPlayer().damage(damage);
	}

	public void onPlayerDeath(SpacePlayer spacePlayer) {
		SpacePlayer killer = spacePlayer.getLastAttacker();
		SpaceTeam team = spacePlayer.getTeam();
		if (killer == null) {
			broadcast(CommonsHelper.replaceAll(settingsMessages.getPlayerKilledSelf(), new String[] { "{player color}", "{player}"}, new String[] { team.getColorCode(), spacePlayer.getPlayer().getName() }));
		} else {
			broadcast(CommonsHelper.replaceAll(settingsMessages.getPlayerKilled(),
					new String[] { "{player color}", "{player}", "{killer color}", "{killer}" },
					new String[] { team.getColorCode(), spacePlayer.getPlayer().getName(), killer.getTeam().getColorCode(), killer.getPlayer().getName() }));
		}
		spacePlayer.onDeath();
		if (team.getLives() <= 0) {
			exit(spacePlayer);
		}
	}

	public int getCurrentTick() {
		return currentTick;
	}

	public void giveRandomPowerup(SpacePlayer player) {
		givePowerup(player, powerupsManager.getRandomPowerup());
	}

	public void givePowerup(SpacePlayer player, Powerup<SpacePlayer> powerup) {
		if (powerup.doAction(player)) {
			broadcast(CommonsHelper.replaceAll(settingsMessages.getPlayerGotPowerup(), new String[] { "{player color}", "{player}", "{powerup}" }, new String[] { player.getTeam().getColorCode(), player.getPlayer().getName(), powerup.getName() }));
		}
	}

	public boolean setNexusLocation(Location location) {
		boolean flag = nexusLocations.size() < spawnpoints.size();
		if (flag) {
			flag = nexusLocations.add(location);
		}
		return flag;
	}

	public List<Location> getNexusLocations() {
		return new ArrayList<>(nexusLocations);
	}

	public int getNexusAmount() {
		return nexusLocations.size();
	}

	public String getTeamName(int teamIndex) {
		String[] teams = settingsTeams.getTeams();
		return teamIndex >= 0 && teamIndex < teams.length ? teams[teamIndex] : null;
	}

	public String getColorCode(int teamIndex) {
		String[] colors = settingsTeams.getColors();
		return teamIndex >= 0 && teamIndex < colors.length ? colors[teamIndex] : null;
	}

	public void broadcast(String message) {
		players.forEach(player -> CommonsHelper.info(player.getPlayer(), message));
		spectators.forEach(player ->  CommonsHelper.info(player, message));
	}

	public void setSpherePowerup(Location location) {
		spheres.add(new SpherePowerup(this, location));
	}

	public Collection<SpherePowerup> getSpheres() {
		return new HashSet<>(spheres);
	}

	public static void heal(Player player) {
		player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
	}

	public static void addHealth(Player player, double health) {
		AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
		attribute.setBaseValue(attribute.getBaseValue() + health);
	}

	List<SpacePlayer> getPlayersWithoutCopy() {
		return players;
	}

	private void buildNexuses() {
		teams.forEach(team -> team.getNexus().build());
	}

	private void exit(SpacePlayer spacePlayer) {
		Player player = spacePlayer.getPlayer();
		for (PotionEffect effect : player.getActivePotionEffects()) {
			player.removePotionEffect(effect.getType());
		}
		gameScoreboardManager.removeUser(usersManager.getUser(player));
		players.remove(spacePlayer);
		SpaceTeam team = spacePlayer.getTeam();
		boolean hasTeam = team != null;
		String[] lookingFor = new String[] { "{player color}", "{player}" };
		String[] replacements = new String[] { hasTeam ? team.getColorCode() : settingsTeams.getDefaultColor(), player.getName() };
		String msg = team.getLives() > 0 ? settingsMessages.getPlayerLeft() : settingsMessages.getPlayerEliminated();
		broadcast(CommonsHelper.replaceAll(msg, lookingFor, replacements));
		playersManager.resetPlayer(player, plugin.getMainLobby());
		if (!hasTeam) {
			return;
		}

		team.removePlayer(spacePlayer);
		if (team.getPlayers().size() == 0) {
			broadcast(CommonsHelper.replaceAll(settingsMessages.getTeamEliminated(), new String[] { "{team color}", "{team}" }, new String[] { team.getColorCode(), team.getName() }));
		}
	}

	private boolean checkWinningConditions() {
		int teamsAlive = 0;
		for (SpaceTeam team : teams) {
			if (team.getPlayers().size() > 0) {
				teamsAlive ++;
			}
		}
		return teamsAlive < 2;
	}
}
