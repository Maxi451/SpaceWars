package it.tristana.spacewars.arena;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import it.tristana.commons.arena.BasicEnclosedArena;
import it.tristana.commons.arena.BasicVillagerShop;
import it.tristana.commons.helper.CommonsHelper;
import it.tristana.commons.helper.TeamsColors;
import it.tristana.commons.interfaces.arena.player.PartiesManager;
import it.tristana.commons.interfaces.util.Status;
import it.tristana.commons.interfaces.util.VillagerShop;
import it.tristana.commons.math.CachedCircleEuclidean;
import it.tristana.commons.math.CircleEuclideanHelper;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.combact.HomingMissile;
import it.tristana.spacewars.arena.combact.MissilesManager;
import it.tristana.spacewars.arena.combact.ShotManager;
import it.tristana.spacewars.arena.kit.CombactClass;
import it.tristana.spacewars.arena.powerup.CirclePowerup;
import it.tristana.spacewars.config.ArenaValues;
import it.tristana.spacewars.helper.AabbHelper;

public class SpaceArena extends BasicEnclosedArena<SpaceTeam, SpacePlayer> {

	private static final int ARENA_TPS = Main.TICKS_PER_SECOND;
	private static final int CIRCLES_TPS = ARENA_TPS / 4;
	private static final int MISSILES_TPS = ARENA_TPS / 10;

	private static final double MAX_DISTANCE = 75;

	private ArenaValues arenaValues;

	private Main plugin;
	private ShotManager shotManager;

	private CircleEuclideanHelper circleEuclideanHelper;
	private MissilesManager missilesManager;
	private List<CirclePowerup> powerups;
	private List<Nexus> nexuses;
	private List<VillagerShop> shops;
	private int currentTick;

	public SpaceArena(Main plugin, ArenaValues arenaValues, World world, String name, PartiesManager partiesManager) {
		super(world, name, partiesManager);
		this.plugin = plugin;
		this.arenaValues = arenaValues;
		nexuses = new ArrayList<Nexus>();
		powerups = new ArrayList<CirclePowerup>();
		shops = new ArrayList<VillagerShop>();
		shotManager = new ShotManager(this);
		missilesManager = new MissilesManager(this);
		circleEuclideanHelper = new CircleEuclideanHelper(arenaValues.getPowerupsCircleRadius(), arenaValues.getPowerupsCircleParticles());
		ticksToStart = 30;
	}
	
	@Override
	public void playingPhase() {
		if (currentTick % ARENA_TPS == 0) {
			nexuses.forEach(nexus -> nexus.draw());
			checkPlayersInsideBorders();
			players.forEach(player -> player.runTick());
		}
		if (currentTick % CIRCLES_TPS == 0) {
			powerups.forEach(circle -> circle.draw());
		}
		if (currentTick % MISSILES_TPS == 0) {
			missilesManager.runTick();
		}
		checkPlayersInsidePowerups();
		currentTick ++;
	}

	@Override
	public void startGame() {
		super.startGame();
		int size = teams.size();
		for (int i = 0; i < size; i ++) {
			SpaceTeam team = teams.get(i);
			Nexus nexus = nexuses.get(i);
			team.setNexus(nexus);
			nexus.build();
		}
		shops.forEach(shop -> shop.spawnEntity());
		world.setGameRule(GameRule.NATURAL_REGENERATION, Boolean.FALSE);
		new ItemsBroker(plugin, this).giveItems();
	}

	@Override
	protected int getTeamsForNumPlayers(int players) {
		return Math.min(super.getTeamsForNumPlayers(players), nexuses.size());
	}

	@Override
	protected SpaceTeam createTeam(int index) {
		SpaceTeam team = new SpaceTeam(arenaValues.getTeamsNames().get(index), TeamsColors.TEAMS_COLOR[index], TeamsColors.COLORS_CODES[index]);
		team.setSpawnpoint(spawnpoints.get(index));
		return team;
	}

	private void checkPlayersInsidePowerups() {
		players.forEach(player -> checkPowerups(player));
	}
	
	private void checkPowerups(SpacePlayer player) {
		for (CirclePowerup powerup : powerups) {
			if (powerup.canBePicked()) {
				CachedCircleEuclidean circle = powerup.getCircle();
				if (circle.intersects(AabbHelper.getFor(player.getPlayer()))) {
					if (powerup.isReady()) {
						Bukkit.broadcastMessage("Positive");
					}
					else {
						Bukkit.broadcastMessage("Negative");
					}
					powerup.onPowerup();
					break;
				}
			}
		}
	}
	
	public void setNexus(Location location) {
		if (nexuses.size() < spawnpoints.size()) {
			nexuses.add(new Nexus(location));
		}
	}

	@Override
	protected SpacePlayer createArenaPlayer(Player player) {
		openKitMenu(player);
		return new SpacePlayer(this, player, plugin.getUser(player));
	}

	public void addPowerupCircle(Location location, double rotation) {
		powerups.add(new CirclePowerup(circleEuclideanHelper.getParticlesLocations(location, rotation), arenaValues.getPowerupRechargeTicks()));
	}

	@Override
	public boolean setSpawnpoint(Location location) {
		boolean result = spawnpoints.size() < TeamsColors.COLORS_CODES.length;
		if (result) {
			spawnpoints.add(location);
		}
		return result;
	}

	@Override
	public boolean isInsideBorders(Location location) {
		return super.isInsideBorders(location);
	}

	public boolean onRightClick(Player player) {
		boolean cancel = true;
		if (getStatus() == Status.PLAYING) {
			SpacePlayer spacePlayer = getArenaPlayer(player);
			if (spacePlayer != null) {
				ItemStack item = player.getInventory().getItemInMainHand();
				Items items = plugin.getItems();
				Material type = item.getType();
				if (type == items.getMissile().getType()) {
					missilesManager.addMissile(new HomingMissile(this, spacePlayer, players));
				}
				else if (type == items.getFuel().getType()) {
					cancel = false;
				}
				else {
					if (spacePlayer.canShoot()) {
						shotManager.onShot(spacePlayer, MAX_DISTANCE);
						spacePlayer.onShoot();
					}
				}
			}
		}
		return cancel;
	}

	public void openKitMenu(Player player) {
		CombactClass[] availableKits = plugin.getKits();
		Inventory inventory = Bukkit.createInventory(null, CommonsHelper.getGuiSizeFromNumOfElements(availableKits), arenaValues.getKitChooseGuiName());
		for (CombactClass kit : availableKits) {
			inventory.addItem(kit.getItemToShow());
		}
		player.openInventory(inventory);
	}

	public boolean forceStart() {
		boolean flag = (status == Status.WAITING || status == Status.STARTING) && players.size() > 0 && nexuses.size() > 1 && lowerPos != null && upperPos != null;
		if (flag) {
			launchGame();
		}
		return flag;
	}

	@Override
	public void closeArena() {
		Location mainLobby = plugin.getMainLobby();
		for (SpacePlayer player : players) {
			player.getPlayer().teleport(mainLobby);
		}
		super.closeArena();
	}

	public List<CirclePowerup> getPowerupsCircles() {
		return powerups;
	}

	public List<Location> getSpawnpoints() {
		return spawnpoints;
	}

	public List<Nexus> getNexuses() {
		return nexuses;
	}

	public void onKitChosen(SpacePlayer player, int slot) {
		CombactClass[] kits = plugin.getKits();
		if (slot >= 0 && slot < kits.length) {
			player.setKit(kits[slot]);
			CommonsHelper.info(player.getPlayer(), "Kit " + kits[slot].getName() + " selected");
		}
	}
	
	private void checkPlayersInsideBorders() {
		for (SpacePlayer player : players) {
			if (!isInsideBorders(player.getPlayer().getLocation())) {
				onPlayerDeath(player);
			}
		}
	}

	public void createDamagingExplosion(SpacePlayer shooter, Location location, double radius, double damage) {
		fakeExplosion(location);
		SpaceTeam team = shooter.getTeam();
		for (SpacePlayer player : players) {
			if (player == shooter || player.getTeam() != team) {
				onExplosionDamage(player, location, radius, damage);
			}
		}
	}

	private void onExplosionDamage(SpacePlayer spacePlayer, Location location, double radius, double damage) {
		Player player = spacePlayer.getPlayer();
		damage = Math.max(1 - player.getLocation().distance(location) / radius, 0);
		if (damage > 0) {
			player.damage(damage);
		}
	}

	private static void fakeExplosion(Location location) {
		location.getWorld().createExplosion(location.getX(), location.getY(), location.getZ(), 4, false, false);
	}

	public void onPlayerDeath(Player player) {
		onPlayerDeath(getArenaPlayer(player));
	}

	private void onPlayerDeath(SpacePlayer spacePlayer) {
		// TODO
		Player player = spacePlayer.getPlayer();
		player.setHealth(getHealthAttribute(player).getValue());
		player.setGameMode(GameMode.SPECTATOR);
		player.sendTitle("Sei morto", "suca", 20, 50, 10);
		spacePlayer.onDeath();
	}

	public boolean isGameRunning() {
		return status == Status.PLAYING || status == Status.ENDING;
	}
	
	public boolean isGameWaiting() {
		return status == Status.WAITING || status == Status.STARTING;
	}
	
	public void setShop(Location location) {
		shops.add(new BasicVillagerShop(location, plugin.getVillagerShopName()));
	}
	
	public List<VillagerShop> getShops() {
		return shops;
	}
	
	private static AttributeInstance getHealthAttribute(Player player) {
		return player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
	}
	
	public static void addBaseLife(Player player, int amount) {
		AttributeInstance attribute = getHealthAttribute(player);
		attribute.setBaseValue(attribute.getBaseValue() + amount);
	}
	
	public static void resetBaseLife(Player player) {
		AttributeInstance attribute = getHealthAttribute(player);
		attribute.setBaseValue(attribute.getDefaultValue());
	}
	
	public ItemStack getFuel() {
		return plugin.getItems().getFuel();
	}
}
