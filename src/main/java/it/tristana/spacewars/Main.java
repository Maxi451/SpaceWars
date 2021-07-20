package it.tristana.spacewars;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import it.tristana.commons.arena.Clock;
import it.tristana.commons.config.ConfigDatabase;
import it.tristana.commons.config.ConfigTeams;
import it.tristana.commons.helper.BasicPartiesManager;
import it.tristana.commons.helper.CommonsHelper;
import it.tristana.commons.interfaces.Tickable;
import it.tristana.commons.interfaces.arena.ArenaLoader;
import it.tristana.commons.interfaces.arena.player.PartiesManager;
import it.tristana.spacewars.arena.Items;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.kit.CombactClass;
import it.tristana.spacewars.arena.kit.CombactClassDefender;
import it.tristana.spacewars.arena.kit.CombactClassDestroyer;
import it.tristana.spacewars.arena.kit.CombactClassMiner;
import it.tristana.spacewars.arena.kit.CombactClassPyromaniac;
import it.tristana.spacewars.arena.kit.CombactClassSniper;
import it.tristana.spacewars.arena.kit.CombactClassSoldier;
import it.tristana.spacewars.arena.kit.CombactClassStormtrooper;
import it.tristana.spacewars.arena.kit.CombactClassTank;
import it.tristana.spacewars.arena.kit.CombactClassTraceur;
import it.tristana.spacewars.arena.upgrade.Upgrade;
import it.tristana.spacewars.command.SpaceCommand;
import it.tristana.spacewars.config.ArenaValues;
import it.tristana.spacewars.config.CombactClassesValues;
import it.tristana.spacewars.config.ConfigArena;
import it.tristana.spacewars.config.ConfigClasses;
import it.tristana.spacewars.config.ConfigItems;
import it.tristana.spacewars.config.ConfigMessages;
import it.tristana.spacewars.config.ConfigSpaceDatabase;
import it.tristana.spacewars.config.ConfigUpgrade;
import it.tristana.spacewars.config.ItemsValues;
import it.tristana.spacewars.config.UpgradeValues;
import it.tristana.spacewars.database.SpaceDatabase;
import it.tristana.spacewars.database.SpaceUser;
import it.tristana.spacewars.helper.SpaceArenaLoader;
import it.tristana.spacewars.interfaces.Configurable;
import it.tristana.spacewars.listener.LoginQuitListener;
import it.tristana.spacewars.listener.SpaceListener;

public final class Main extends JavaPlugin {

	public static final int TICKS_PER_SECOND = 20;
	private static final String MAIN_COMMAND = "sw";

	private final File folder = getDataFolder();
	private PartiesManager partiesManager;
	private ArenaLoader<SpaceArena> arenasLoader;
	private Clock clock;

	private List<SpaceArena> arenas;
	private Configurable[] configurables;
	private ConfigMessages configMessages;
	private ConfigClasses configClasses;
	private ConfigTeams configTeams;
	private ConfigArena configArena;
	private ConfigUpgrade configUpgrade;
	private ConfigItems configItems;
	private ArenaValues arenaValues;
	private ItemsValues itemsValues;
	private CombactClassesValues combactClassesValues;
	private UpgradeValues upgradeValues;
	private Items items;
	private String villagerShopName;

	private ConfigSpaceDatabase configDatabase;
	private SpaceDatabase database;
	private List<SpaceUser> users;
	private boolean isUsingDatabase;

	private Location mainLobby;

	@Override
	public void onEnable() {
		checkFolder(folder);
		loadConfigs();
		reload();
		isUsingDatabase = openDbConnection();
		if (!isUsingDatabase) {
			CommonsHelper.consoleInfo("&eSpaceWars will not use the database, data will be only kept as long as the player is online!");
		}
		for (Player player : Bukkit.getOnlinePlayers()) {
			onPlayerJoin(player);
		}
		partiesManager = new BasicPartiesManager();
		arenasLoader = new SpaceArenaLoader(this, arenaValues, new ArrayList<SpaceArena>(), new File(folder, "maps.yml"), partiesManager);
		arenas = arenasLoader.loadArenas();
		users = new LinkedList<SpaceUser>();
		clock = new Clock();
		for (SpaceArena arena : arenas) {
			addTickable(arena);
		}
		clock.schedule(this, 1);
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new SpaceListener(this), this);
		pluginManager.registerEvents(new LoginQuitListener(this), this);
		Bukkit.getPluginCommand(MAIN_COMMAND).setExecutor(new SpaceCommand(this, MAIN_COMMAND));
	}

	@Override
	public void onDisable() {
		clock.cancel();
		arenasLoader.saveArenas();
		if (isUsingDatabase) {
			for (SpaceUser user : users) {
				database.saveUser(user);
			}
			try {
				database.closeConnection();
			} catch (SQLException e) {
				CommonsHelper.consoleInfo("&eError " + e.getErrorCode() + " while closing the database connection!");
				e.printStackTrace();
			}
		}
	}

	private static void checkFolder(File folder) {
		folder.mkdirs();
	}

	private boolean openDbConnection() {
		boolean result = false;
		try {
			String host = configDatabase.getString(ConfigDatabase.HOST);
			String database = configDatabase.getString(ConfigDatabase.DATABASE);
			String username = configDatabase.getString(ConfigDatabase.USER);
			String password = configDatabase.getString(ConfigDatabase.PASSWORD);
			int port = Integer.parseInt(configDatabase.getString(ConfigDatabase.PORT));
			String table = configDatabase.getString(ConfigSpaceDatabase.TABLE);
			this.database = new SpaceDatabase(host, database, username, password, port, table);
			this.database.openConnection();
			result = true;
		} catch (SQLException e) {
			CommonsHelper.consoleInfo("&cSQL Error " + e.getErrorCode() + " while opening the connection with the database!");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			CommonsHelper.consoleInfo("Error while opening the connection with the database, the port is not a valid integer!");
		} catch (Exception e) {
			CommonsHelper.consoleInfo("&cGeneric error while opening the connection with the database!");
			e.printStackTrace();
		}
		return result;
	}

	private void loadConfigs() {
		configMessages = new ConfigMessages(folder);
		configClasses = new ConfigClasses(folder);
		configDatabase = new ConfigSpaceDatabase(folder);
		configTeams = new ConfigTeams(folder);
		configArena = new ConfigArena(folder);
		configUpgrade = new ConfigUpgrade(folder);
		configItems = new ConfigItems(folder);
		arenaValues = new ArenaValues(configTeams, configArena);
		itemsValues = new ItemsValues(configItems);
		combactClassesValues = new CombactClassesValues(configMessages, configClasses);
		upgradeValues = new UpgradeValues(configUpgrade);
		items = new Items(itemsValues);
		configurables = new Configurable[] {
				arenaValues,
				itemsValues,
				combactClassesValues,
				upgradeValues,
				items
		};
	}
	
	public void reload() {
		if (arenas != null) {
			arenas.forEach(arena -> arena.closeArena());
		}
		for (Configurable configurable : configurables) {
			configurable.setup();
		}
		villagerShopName = configMessages.getString(ConfigMessages.VILLAGER_SHOP_NAME);
		Upgrade.setup(combactClassesValues, upgradeValues);
	}

	public ConfigTeams getConfigTeams() {
		return configTeams;
	}

	public ArenaValues getArenaValues() {
		return arenaValues;
	}

	public CombactClassesValues getCombactClassesValues() {
		return combactClassesValues;
	}
	
	public Items getItems() {
		return items;
	}
	
	public SpaceArena getArenaByWorld(World world) {
		return getArena(arena -> arena.getWorld() == world);
	}

	public SpaceArena getFirstAvailableArena(Player player) {
		return getArena(arena -> arena.testPlayerJoin(player));
	}

	public SpaceArena getArenaByName(String name) {
		return getArena(arena -> arena.getName().equalsIgnoreCase(name));
	}

	private SpaceArena getArena(Function<SpaceArena, Boolean> function) {
		SpaceArena result = null;
		for (SpaceArena arena : arenas) {
			if (function.apply(arena).booleanValue()) {
				result = arena;
				break;
			}
		}
		return result;
	}

	public void setMainLobby(Location mainLobby) {
		this.mainLobby = mainLobby;
	}

	public Location getMainLobby() {
		return mainLobby;
	}

	public void addTickable(Tickable tickable) {
		clock.add(tickable);
	}

	public void createArena(World world, String name) {
		SpaceArena arena = new SpaceArena(this, arenaValues, world, name, partiesManager);
		arenas.add(arena);
		addTickable(arena);
	}

	public void onPlayerJoin(Player player) {
		player.getInventory().clear();
		SpaceUser user;
		if (isUsingDatabase) {
			user = database.getUser(player);
		}
		else {
			user = new SpaceUser(player);
		}
		users.add(user);
	}

	public void onPlayerQuit(Player player) {
		SpaceUser user = getUser(player, true);
		if (isUsingDatabase) {
			database.saveUser(user);
		}
	}

	public SpaceUser getUser(Player player) {
		return getUser(player, false);
	}
	
	public SpaceUser getUser(Player player, boolean remove) {
		Iterator<SpaceUser> iterator = users.iterator();
		SpaceUser result = null;
		while (iterator.hasNext()) {
			SpaceUser user = iterator.next();
			if (user.getPlayer() == player) {
				result = user;
				if (remove) {
					iterator.remove();
				}
			}
		}
		return result;
	}

	@SuppressWarnings("deprecation")
	public SpaceUser getUser(String name) {
		SpaceUser result = null;
		Player player = Bukkit.getPlayerExact(name);
		if (player == null) {
			if (isUsingDatabase) {
				result = database.getUser(Bukkit.getOfflinePlayer(name));
			}
		}
		else {
			result = getUser(player, false);
		}
		return result;
	}
	
	public static Main getInstance() {
		return getPlugin(Main.class);
	}
	
	public String getVillagerShopName() {
		return villagerShopName;
	}
	
	public CombactClass[] getKits() {
		return new CombactClass[] {
				new CombactClassDefender(),
				new CombactClassDestroyer(),
				new CombactClassMiner(),
				new CombactClassPyromaniac(),
				new CombactClassSniper(),
				new CombactClassSoldier(),
				new CombactClassStormtrooper(),
				new CombactClassTank(),
				new CombactClassTraceur()
		};
	}
}
