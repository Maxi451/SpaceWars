package it.tristana.spacewars.arena;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import it.tristana.commons.arena.BasicEnclosedArena;
import it.tristana.commons.arena.powerup.BasicPowerupsManager;
import it.tristana.commons.config.SettingsTeams;
import it.tristana.commons.helper.BasicTickablesManager;
import it.tristana.commons.helper.CommonsHelper;
import it.tristana.commons.interfaces.Reloadable;
import it.tristana.commons.interfaces.arena.Status;
import it.tristana.commons.interfaces.database.UsersManager;
import it.tristana.commons.interfaces.gui.ClickedGuiManager;
import it.tristana.commons.interfaces.util.Powerup;
import it.tristana.commons.interfaces.util.PowerupsManager;
import it.tristana.commons.interfaces.util.TickablesManager;
import it.tristana.commons.math.AABB;
import it.tristana.commons.math.Ray;
import it.tristana.commons.math.RayTrace;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.arena.player.gun.Gun;
import it.tristana.spacewars.arena.player.kit.KitsManager;
import it.tristana.spacewars.arena.powerup.PowerupsBuilder;
import it.tristana.spacewars.arena.team.ColorsHelper;
import it.tristana.spacewars.arena.team.Nexus;
import it.tristana.spacewars.arena.team.SpaceTeam;
import it.tristana.spacewars.config.SettingsMessages;
import it.tristana.spacewars.config.SettingsPowerups;
import it.tristana.spacewars.database.SpaceUser;
import it.tristana.spacewars.event.SpacePlayerShotEvent;
import it.tristana.spacewars.gui.GuiKit;
import it.tristana.spacewars.helper.ParticlesHelper;

public class SpaceArena extends BasicEnclosedArena<SpaceTeam, SpacePlayer> implements Reloadable {

	private static final Set<Material> transparentBlocks = new HashSet<Material>();
	static {
		transparentBlocks.add(Material.AIR);
	}
	
	private final Main plugin;
	private final UsersManager<SpaceUser> usersManager;
	private final List<Location> nexusLocations;
	private final List<CirclePowerup> circles;
	private final ClickedGuiManager guiManager;
	private final PowerupsManager<SpacePlayer> powerupsManager;
	private final KitsManager kitsManager;

	private final SettingsPowerups settingsPowerups;
	private final SettingsMessages settingsMessages;
	private final SettingsTeams settingsTeams;
	
	private TickablesManager playersPositionManager;

	private int currentTick;

	public SpaceArena(World world, String name, Main plugin) {
		super(world, name, plugin.getPartiesManager());
		this.plugin = plugin;
		this.usersManager = plugin.getUsersManager();
		this.kitsManager = plugin.getKitsManager();
		this.guiManager = plugin.getClickedGuiManager();
		this.settingsPowerups = plugin.getSettingsPowerups();
		this.settingsMessages = plugin.getSettingsMessages();
		this.settingsTeams = plugin.getSettingsTeams();
		nexusLocations = new ArrayList<>();
		circles = new ArrayList<>();
		powerupsManager = new BasicPowerupsManager<>(new PowerupsBuilder(settingsPowerups).createPowerups());
		reset();
	}

	@Override
	public void reload() {
		closeArena();
	}

	@Override
	public void startGame() {
		super.startGame();
		selectRandomKitsIfNeeded();
		clearInventories();
		giveStartingItems();
		changeGameModes();
		buildNexuses();
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
		}
		return onJoin;
	}

	@Override
	public void closeArena() {
		players.forEach(player -> {
			exit(player);
		});
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
		SpacePlayer spacePlayer = getArenaPlayer(player);
		if (spacePlayer == null) {
			return;
		}
		exit(spacePlayer);
	}

	@Override
	protected void playingPhase() {
		currentTick ++;
		if (checkWinningConditions()) {
			setStatus(Status.ENDING);
			return;
		}
		teams.forEach(team -> team.runTick());
		players.forEach(player -> player.runTick());
		circles.forEach(circle -> circle.runTick());
		circles.forEach(circle -> {
			Ray[] rays = circle.getRays();
			for (int i = 0; i < rays.length; i ++) {
				ParticlesHelper.particlesLine(rays[i].getOrigin().clone().toLocation(world), rays[i].getOrigin().clone().add(rays[i].getDirection().multiply(CirclePowerup.RADIUS)).toLocation(world), 0.5, Particle.FLAME);
			}
		});
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
		playersPositionManager.registerTickable(new CirclesIntersectionManager(this));
		playersPositionManager.startClock(plugin, 4);
	}

	public void openGuiMenu(Player player) {
		GuiKit gui = guiManager.getGui(GuiKit.class);
		if (gui != null) {
			gui.open(player);
		}
	}

	public boolean onBlockBroken(Player player, Block block) {
		SpaceTeam playerTeam = getArenaPlayer(player).getTeam();
		Material type = block.getType();
		boolean isNexus = type == Nexus.NEXUS_MATERIAL;
		if (!isNexus && type != Nexus.PILLAR_MATERIAL) {
			return false;
		}
		Function<SpaceTeam, Boolean> tester; 
		if (isNexus) {
			tester = team -> { return team.getNexus().breakNexus(); };
		} else {
			tester = team -> { return team.getNexus().breakPillar(block.getLocation()); };
		}
		for (SpaceTeam team : teams) {
			if (tester.apply(team)) {
				return team != playerTeam;
			}
		}
		return false;
	}

	public void onShot(SpacePlayer shooter) {
		Gun gun = shooter.getKit().getGun();
		double maxDistance = gun.isLongBarrel() ? 75 : 50;
		Player player = shooter.getPlayer();
		Location playerPos = player.getEyeLocation();
		Location maxDistanceLocation = playerPos.clone().add(playerPos.getDirection().multiply(maxDistance));
		if (!gun.isFmj()) {
			Location collisionPoint = RayTrace.firstCollisionPoint(playerPos, maxDistanceLocation, transparentBlocks, 0.05);
			if (collisionPoint != null) {
				maxDistanceLocation = collisionPoint;
				maxDistance = playerPos.distance(maxDistanceLocation);
			}
		}
		SpaceTeam team = shooter.getTeam();
		double minDistance = Double.MAX_VALUE;
		SpacePlayer targetFound = null;
		for (SpacePlayer target : players) {
			if (team == target.getTeam()) {
				continue;
			}
			Player targetPlayer = target.getPlayer();
			boolean isGliding = targetPlayer.isGliding();
			if (RayTrace.canHit(player, targetPlayer, maxDistance, isGliding ? 0.25 : 0, isGliding)) {
				double distance = playerPos.distance(targetPlayer.getEyeLocation());
				if (distance < minDistance) {
					targetFound = target;
					minDistance = distance;
				}
			}
		}
		if (targetFound != null) {
			Player targetPlayer = targetFound.getPlayer();
			onDamage(targetFound, shooter, gun.getDamage());
			maxDistanceLocation = targetPlayer.isGliding() ? AABB.getElytraPlayerEyesPos(targetPlayer) : targetPlayer.getEyeLocation();
		}
		SpacePlayerShotEvent event = new SpacePlayerShotEvent(shooter, targetFound);
		Bukkit.getPluginManager().callEvent(event);
		if (event.isCancelled()) {
			return;
		}
		ParticlesHelper.particlesLineColored(playerPos, maxDistanceLocation, 0.2, shooter.getTeam().getArmorColor(), 1);
		world.playSound(player, gun.getSound(), 1, 32);
	}

	public void onDamage(SpacePlayer player, SpacePlayer damager, double damage) {
		onTrueDamage(player, damager, 100d / Math.max(1, 100 + player.getKit().getArmor()) * damage);
	}

	public void onTrueDamage(SpacePlayer player, SpacePlayer damager, double damage) {
		if (damager != null) {
			player.setLastAttacker(damager);
		}
		player.getPlayer().damage(damage);
	}

	public void onPlayerDeath(SpacePlayer spacePlayer) {
		SpacePlayer killer = spacePlayer.getLastAttacker();
		if (killer == null) {
			broadcast(CommonsHelper.replaceAll(settingsMessages.getPlayerKilledSelf(), new String[] {"{player color}", "{player}"}, new String[] {spacePlayer.getTeam().getColorCode(), spacePlayer.getPlayer().getName()}));
		} else {
			broadcast(CommonsHelper.replaceAll(settingsMessages.getPlayerKilled(),
					new String[] {"{player color}", "{player}", "{killer color}", "{killer}"},
					new String[] {spacePlayer.getTeam().getColorCode(), spacePlayer.getPlayer().getName(), killer.getTeam().getColorCode(), killer.getPlayer().getName()}));
		}
		spacePlayer.onDeath();
		if (spacePlayer.getLives() <= 0) {
			exit(spacePlayer);
		}
	}

	public int getCurrentTick() {
		return currentTick;
	}

	public Powerup<SpacePlayer> getRandomPowerup() {
		return powerupsManager.getRandomPowerup();
	}
	
	public void giveRandomPowerup(SpacePlayer player) {
		givePowerup(player, getRandomPowerup());
	}
	
	public void givePowerup(SpacePlayer player, Powerup<SpacePlayer> powerup) {
		if (powerup.doAction(player)) {
			broadcast(CommonsHelper.replaceAll(settingsMessages.getPlayerGotPowerup(), new String[] {"{player color}", "{player}", "{powerup}"}, new String[] {player.getTeam().getColorCode(), player.getPlayer().getName(), powerup.getName()}));
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

	public void setCirclePowerup(Location location, double rotation) {
		circles.add(new CirclePowerup(location, rotation));
	}
	
	public List<CirclePowerup> getCircles() {
		return new ArrayList<>(circles);
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

	List<CirclePowerup> getCirclesWithoutCopy() {
		return circles;
	}
	
	private void buildNexuses() {
		teams.forEach(team -> team.getNexus().build());
	}
	
	private void exit(SpacePlayer player) {
		players.remove(player);
		String[] lookingFor = new String[] {"{player color}", "{player}"};
		String[] replacements = new String[] {player.getTeam().getColorCode(), player.getPlayer().getName()};
		if (player.getLives() == 0) {
			broadcast(CommonsHelper.replaceAll(settingsMessages.getPlayerEliminated(), lookingFor, replacements));
		} else {
			broadcast(CommonsHelper.replaceAll(settingsMessages.getPlayerLeft(), lookingFor, replacements));
		}
		SpaceTeam team = player.getTeam();
		team.removePlayer(player);
		if (team.getPlayers().size() == 0) {
			broadcast(CommonsHelper.replaceAll(settingsMessages.getTeamEliminated(), new String[] {"{team color}", "{team}"}, new String[] {team.getColorCode(), team.getName()}));
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

	private void selectRandomKitsIfNeeded() {
		players.forEach(player -> {
			if (player.getKit() == null) {
				player.setKit(kitsManager.getRandomKit());
			}
		});
	}

	private void changeGameModes() {
		players.forEach(player -> player.setPlayingGameMode());
	}

	private void giveStartingItems() {
		players.forEach(player -> player.giveDefaultItems());
	}
	
	private void clearInventories() {
		players.forEach(player -> player.getPlayer().getInventory().clear());
	}
}
