package it.tristana.spacewars;

import java.io.File;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.PluginManager;

import it.tristana.commons.arena.BasicArenasManager;
import it.tristana.commons.config.SettingsTeams;
import it.tristana.commons.database.BasicUsersManager;
import it.tristana.commons.database.DatabaseManager;
import it.tristana.commons.helper.BasicPartiesManager;
import it.tristana.commons.helper.CommonsHelper;
import it.tristana.commons.helper.PlayersManager;
import it.tristana.commons.helper.PluginDraft;
import it.tristana.commons.interfaces.DatabaseHolder;
import it.tristana.commons.interfaces.MainLobbyHolder;
import it.tristana.commons.interfaces.PartiesHolder;
import it.tristana.commons.interfaces.Reloadable;
import it.tristana.commons.interfaces.arena.ArenaLoader;
import it.tristana.commons.interfaces.arena.ArenasManager;
import it.tristana.commons.interfaces.arena.player.PartiesManager;
import it.tristana.commons.interfaces.chat.ChatManager;
import it.tristana.commons.interfaces.database.Database;
import it.tristana.commons.interfaces.database.UsersManager;
import it.tristana.commons.interfaces.gui.ClickedGuiManager;
import it.tristana.commons.listener.ChatListener;
import it.tristana.commons.listener.GuiListener;
import it.tristana.commons.listener.LoginQuitListener;
import it.tristana.commons.listener.VillagerShopListener;
import it.tristana.commons.scoreboard.ScoreboardManager;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.SpaceArenaLoader;
import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.arena.player.kit.KitsManager;
import it.tristana.spacewars.chat.SpaceChatManager;
import it.tristana.spacewars.command.SpaceCommand;
import it.tristana.spacewars.config.ConfigCommands;
import it.tristana.spacewars.config.ConfigSpaceDatabase;
import it.tristana.spacewars.config.SettingsArena;
import it.tristana.spacewars.config.SettingsCommands;
import it.tristana.spacewars.config.SettingsKits;
import it.tristana.spacewars.config.SettingsMessages;
import it.tristana.spacewars.config.SettingsPowerups;
import it.tristana.spacewars.config.SettingsScoreboard;
import it.tristana.spacewars.config.SettingsShop;
import it.tristana.spacewars.database.SpaceDatabase;
import it.tristana.spacewars.database.SpaceUser;
import it.tristana.spacewars.gui.SpaceClickedGuiManager;
import it.tristana.spacewars.gui.kit.GuiKit;
import it.tristana.spacewars.gui.shop.GuiShop;
import it.tristana.spacewars.helper.PapiManager;
import it.tristana.spacewars.helper.SpaceLoginAction;
import it.tristana.spacewars.helper.SpacePlayersManager;
import it.tristana.spacewars.helper.SpaceQuitAction;
import it.tristana.spacewars.listener.BlockListener;
import it.tristana.spacewars.listener.PlayerDamageListener;
import it.tristana.spacewars.listener.PlayerDeathListener;
import it.tristana.spacewars.listener.RemovedEventsListener;
import it.tristana.spacewars.listener.ShotListener;
import it.tristana.spacewars.listener.SpaceEventsListener;
import it.tristana.spacewars.scoreboard.SpacePersonalScoreboardManager;

public final class Main extends PluginDraft implements Reloadable, DatabaseHolder, PartiesHolder, MainLobbyHolder {
	
	public static final String ADMIN_PERMS = "spacewars.admin";

	private static final String COMMAND = "sw";

	private File folder;
	private boolean isDisabled;

	private SettingsKits settingsKits;
	private SettingsCommands settingsCommands;
	private SettingsPowerups settingsPowerups;
	private SettingsMessages settingsMessages;
	private SettingsTeams settingsTeams;
	private SettingsArena settingsArena;
	private SettingsScoreboard settingsScoreboard;
	private SettingsShop settingsShop;

	private DatabaseManager<SpaceUser> database;
	private UsersManager<SpaceUser> usersManager;
	private ChatManager chatManager;
	private ClickedGuiManager clickedGuiManager;
	private ArenasManager<SpaceArena, SpacePlayer> arenasManager;
	private ArenaLoader<SpaceArena> arenaLoader;
	private PlayersManager playersManager;
	private PartiesManager partiesManager;
	private ScoreboardManager<SpaceUser> lobbyScoreboardManager;
	private KitsManager kitsManager;

	private Location mainLobby;

	private boolean isPapiEnabled;

	@Override
	public void onEnable() {
		folder = getFolder();
		try {
			database = getDatabase();
			database.closeConnection(database.openConnection());
		} catch (Exception e) {
			selfDestroy(e, "&cCould not open the database connection. Did you configure the credentials? Check the errors file");
			return;
		}

		createSettings();
		checkDependencies();
		try {
			loadManagers();
		} catch (Exception e) {
			selfDestroy(e, "&cAn internal error occurred loading SpaceWars. This is a programming error! Please report the stacktrace found in your errors file");
			return;
		}

		registerListeners();
		loadArenas();
		registerCommand(this, SpaceCommand.class, COMMAND, ConfigCommands.FILE_NAME);
	}

	@Override
	public void onDisable() {
		if (isDisabled) {
			return;
		}

		closeArenas();
		saveArenas();
		usersManager.saveOnlineUsers();
	}

	@Override
	public Database getStorage() {
		return database;
	}

	@Override
	public void reload() {
		settingsKits.reload();
		settingsCommands.reload();
		settingsPowerups.reload();
		settingsMessages.reload();
		settingsTeams.reload();
		settingsArena.reload();
		settingsScoreboard.reload();
		settingsShop.reload();
		clickedGuiManager.clearGuis();
		registerGuis();
	}
	
	@Override
	public PartiesManager getPartiesManager() {
		return partiesManager;
	}

	@Override
	public Location getMainLobby() {
		return mainLobby == null ? null : mainLobby.clone();
	}

	@Override
	public void setMainLobby(Location mainLobby) {
		this.mainLobby = mainLobby;
	}

	public void addToMainScoreboard(SpaceUser user) {
		lobbyScoreboardManager.addUser(user);
	}

	public void removeFromMainScoreboard(SpaceUser user) {
		lobbyScoreboardManager.removeUser(user);
	}

	public ArenasManager<SpaceArena, SpacePlayer> getArenasManager() {
		return arenasManager;
	}

	public SettingsArena getSettingsArena() {
		return settingsArena;
	}

	public SettingsKits getSettingsKits() {
		return settingsKits;
	}

	public SettingsPowerups getSettingsPowerups() {
		return settingsPowerups;
	}

	public SettingsMessages getSettingsMessages() {
		return settingsMessages;
	}

	public SettingsTeams getSettingsTeams() {
		return settingsTeams;
	}

	public SettingsCommands getSettingsCommands() {
		return settingsCommands;
	}

	public SettingsScoreboard getSettingsScoreboard() {
		return settingsScoreboard;
	}

	public SettingsShop getSettingsShop() {
		return settingsShop;
	}

	public UsersManager<SpaceUser> getUsersManager() {
		return usersManager;
	}

	public ClickedGuiManager getClickedGuiManager() {
		return clickedGuiManager;
	}

	public KitsManager getKitsManager() {
		return kitsManager;
	}

	public PlayersManager getPlayersManager() {
		return playersManager;
	}

	public boolean isPapiEnabled() {
		return isPapiEnabled;
	}

	private void checkDependencies() {
		PluginManager pluginManager = Bukkit.getPluginManager();
		isPapiEnabled = pluginManager.getPlugin("PlaceholderAPI") != null;
	}

	private void registerGuis() {
		clickedGuiManager.registerGui(new GuiKit(settingsKits.getGuiName(), kitsManager));
		clickedGuiManager.registerGui(new GuiShop(settingsShop.getGuiName(), settingsShop));
	}

	private void selfDestroy(Throwable t, String message) {
		CommonsHelper.consoleInfo(message);
		writeThrowableOnErrorsFile(t);
		isDisabled = true;
		Bukkit.getPluginManager().disablePlugin(this);
	}

	private void closeArenas() {
		try {
			arenasManager.getArenas().forEach(SpaceArena::closeArena);
		} catch (Exception e) {
			writeThrowableOnErrorsFile(e);
		}
	}

	private void saveArenas() {
		if (mainLobby != null) {
			arenaLoader.saveMainLobby(mainLobby);
			arenaLoader.saveArenas(arenasManager.getArenas());
		}
	}

	private void loadArenas() {
		arenaLoader = new SpaceArenaLoader(folder, this);
		mainLobby = arenaLoader.getMainLobby();
		if (mainLobby != null) {
			arenaLoader.loadArenas().forEach(arenasManager::addArena);
		}
		arenasManager.startClock(this, 20);
	}

	private void createSettings() {
		settingsKits = new SettingsKits(folder);
		settingsCommands = new SettingsCommands(folder);
		settingsPowerups = new SettingsPowerups(folder);
		settingsMessages = new SettingsMessages(folder);
		settingsTeams = new SettingsTeams(folder);
		settingsArena = new SettingsArena(folder);
		settingsScoreboard = new SettingsScoreboard(folder);
		settingsShop = new SettingsShop(folder);
	}

	private void loadManagers() throws NoSuchMethodException {
		arenasManager = new BasicArenasManager<>();
		playersManager = new SpacePlayersManager(this, arenasManager);
		usersManager = new BasicUsersManager<>(database);
		chatManager = new SpaceChatManager();
		clickedGuiManager = new SpaceClickedGuiManager();
		partiesManager = new BasicPartiesManager();
		kitsManager = new KitsManager(this, settingsKits);
		lobbyScoreboardManager = new SpacePersonalScoreboardManager(this, settingsScoreboard::getLobbyName, settingsScoreboard::getLobbyLines);
		kitsManager.loadDefaultKits();
		if (isPapiEnabled) {
			new PapiManager(this, usersManager).register();
		}
		registerGuis();
	}

	private void registerListeners() {
		register(new LoginQuitListener<>(usersManager, database, this, new SpaceLoginAction(this, playersManager, lobbyScoreboardManager), new SpaceQuitAction(this, playersManager, lobbyScoreboardManager)));
		register(new ChatListener(chatManager));
		register(new GuiListener(clickedGuiManager));
		register(new ShotListener(arenasManager));
		register(new PlayerDeathListener(arenasManager));
		register(new BlockListener(arenasManager));
		register(new PlayerDamageListener(arenasManager, settingsArena));
		register(new RemovedEventsListener());
		register(new VillagerShopListener<>(arenasManager, clickedGuiManager, GuiShop.class));
		register(new SpaceEventsListener(arenasManager, settingsMessages));
	}

	private SpaceDatabase getDatabase() throws SQLException {
		ConfigSpaceDatabase config = new ConfigSpaceDatabase(folder);
		String host = config.getString(ConfigSpaceDatabase.HOST);
		String database = config.getString(ConfigSpaceDatabase.DATABASE);
		String username = config.getString(ConfigSpaceDatabase.USER);
		String password = config.getString(ConfigSpaceDatabase.PASSWORD);
		int port = Integer.parseInt(config.getString(ConfigSpaceDatabase.PORT));
		String tablePlayers = config.getString(ConfigSpaceDatabase.TABLE_PLAYERS);
		return new SpaceDatabase(host, database, username, password, port, this, tablePlayers);
	}
}
