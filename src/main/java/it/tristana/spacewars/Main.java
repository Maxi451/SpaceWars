package it.tristana.spacewars;

import java.io.File;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.PluginCommand;

import it.tristana.commons.arena.BasicArenasManager;
import it.tristana.commons.config.ConfigDefaultCommands;
import it.tristana.commons.config.ConfigTeams;
import it.tristana.commons.config.SettingsDefaultCommands;
import it.tristana.commons.config.SettingsTeams;
import it.tristana.commons.database.BasicUsersManager;
import it.tristana.commons.database.DatabaseManager;
import it.tristana.commons.helper.BasicPartiesManager;
import it.tristana.commons.helper.CommonsHelper;
import it.tristana.commons.helper.PluginDraft;
import it.tristana.commons.interfaces.DatabaseHolder;
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
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.SpaceArenaLoader;
import it.tristana.spacewars.arena.player.kit.KitsManager;
import it.tristana.spacewars.chat.SpaceChatManager;
import it.tristana.spacewars.command.SpaceCommand;
import it.tristana.spacewars.config.ConfigArena;
import it.tristana.spacewars.config.ConfigCommands;
import it.tristana.spacewars.config.ConfigKits;
import it.tristana.spacewars.config.ConfigMessages;
import it.tristana.spacewars.config.ConfigPowerups;
import it.tristana.spacewars.config.ConfigSpaceDatabase;
import it.tristana.spacewars.config.SettingsArena;
import it.tristana.spacewars.config.SettingsCommands;
import it.tristana.spacewars.config.SettingsKits;
import it.tristana.spacewars.config.SettingsMessages;
import it.tristana.spacewars.config.SettingsPowerups;
import it.tristana.spacewars.database.SpaceDatabase;
import it.tristana.spacewars.database.SpaceUser;
import it.tristana.spacewars.gui.SpaceClickedGuiManager;
import it.tristana.spacewars.gui.kit.GuiKit;
import it.tristana.spacewars.helper.SpaceLoginAction;
import it.tristana.spacewars.helper.SpaceQuitAction;
import it.tristana.spacewars.listener.BlockListener;
import it.tristana.spacewars.listener.PlayerDamageListener;
import it.tristana.spacewars.listener.PlayerDeathListener;
import it.tristana.spacewars.listener.RemovedEventsListener;
import it.tristana.spacewars.listener.ShotListener;

public class Main extends PluginDraft implements Reloadable, DatabaseHolder, PartiesHolder {
	
	public static final String ADMIN_PERMS = "spacewars.admin";
	
	private static final String COMMAND = "spacewars";
	
	private File folder;
	private boolean isDisabled;

	private SettingsDefaultCommands settingsDefaultCommands;
	private SettingsKits settingsKits;
	private SettingsCommands settingsCommands;
	private SettingsPowerups settingsPowerups;
	private SettingsMessages settingsMessages;
	private SettingsTeams settingsTeams;
	private SettingsArena settingsArena;
	
	private DatabaseManager<SpaceUser> database;
	private UsersManager<SpaceUser> usersManager;
	private ChatManager chatManager;
	private ClickedGuiManager clickedGuiManager;
	private ArenasManager<SpaceArena> arenasManager;
	private ArenaLoader<SpaceArena> arenaLoader;
	private PartiesManager partiesManager;
	private KitsManager kitsManager;
	
	private Location mainLobby;
	
	@Override
	public void onEnable() {
		folder = getFolder();
		try {
			database = getDatabase();
			database.openConnection();
		} catch (Exception e) {
			selfDestroy(e, "&cCould not open the database connection. Did you configure the credentials? Check the errors file");
			return;
		}
		createSettings();
		try {
			loadManagers();
		} catch (Exception e) {
			selfDestroy(e, "&cAn internal error occurred loading SpaceWars. This is a programming error! Please report the stacktrace found in your errors file");
			return;
		}
		registerListeners();
		loadArenas();
		PluginCommand cmd = Bukkit.getPluginCommand(COMMAND);
		SpaceCommand spaceCommand = new SpaceCommand(this, settingsDefaultCommands, "sw", settingsCommands);
		cmd.setTabCompleter(spaceCommand);
		cmd.setExecutor(spaceCommand);
	}
	
	@Override
	public void onDisable() {
		if (isDisabled) {
			return;
		}
		closeArenas();
		saveArenas();
		try {
			usersManager.saveOnlineUsers();
			database.closeConnection();
		} catch (SQLException e) {
			writeThrowableOnErrorsFile(e);
		}
	}
	
	@Override
	public Database getStorage() {
		return database;
	}
	
	@Override
	public void reload() {
		settingsDefaultCommands.setConfig(new ConfigDefaultCommands());
		settingsKits.setConfig(new ConfigKits(folder));
		settingsCommands.setConfig(new ConfigCommands(folder));
		settingsPowerups.setConfig(new ConfigPowerups(folder));
		settingsMessages.setConfig(new ConfigMessages(folder));
		settingsTeams.setConfig(new ConfigTeams(folder));
		settingsArena.setConfig(new ConfigArena(folder));
		clickedGuiManager.clearGuis();
		registerGuis();
	}
	
	@Override
	public PartiesManager getPartiesManager() {
		return partiesManager;
	}
	
	public ArenasManager<SpaceArena> getArenasManager() {
		return arenasManager;
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

	public UsersManager<SpaceUser> getUsersManager() {
		return usersManager;
	}

	public ClickedGuiManager getClickedGuiManager() {
		return clickedGuiManager;
	}

	public KitsManager getKitsManager() {
		return kitsManager;
	}

	public Location getMainLobby() {
		return mainLobby == null ? null : mainLobby.clone();
	}
	
	public void setMainLobby(Location mainLobby) {
		this.mainLobby = mainLobby;
	}
	
	private void registerGuis() {
		clickedGuiManager.registerGui(new GuiKit(CommonsHelper.toChatColors("&6&lKits"), kitsManager));
	}
	
	private void selfDestroy(Throwable t, String message) {
		CommonsHelper.consoleInfo(message);
		writeThrowableOnErrorsFile(t);
		isDisabled = true;
		Bukkit.getPluginManager().disablePlugin(this);
	}
	
	private void closeArenas() {
		for (SpaceArena arena : arenasManager.getArenas()) {
			try {
				arena.closeArena();
			} catch (Exception e) {
				writeThrowableOnErrorsFile(e);
			}
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
			arenaLoader.loadArenas().forEach(arena -> arenasManager.addArena(arena));
		}
		arenasManager.startClock(this, 20);
	}
	
	private void createSettings() {
		settingsDefaultCommands = new SettingsDefaultCommands(new ConfigDefaultCommands());
		settingsKits = new SettingsKits(new ConfigKits(folder));
		settingsCommands = new SettingsCommands(new ConfigCommands(folder));
		settingsPowerups = new SettingsPowerups(new ConfigPowerups(folder));
		settingsMessages = new SettingsMessages(new ConfigMessages(folder));
		settingsTeams = new SettingsTeams(new ConfigTeams(folder));
		settingsArena = new SettingsArena(new ConfigArena(folder));
	}
	
	private void loadManagers() throws NoSuchMethodException {
		arenasManager = new BasicArenasManager<>();
		usersManager = new BasicUsersManager<>(database);
		chatManager = new SpaceChatManager();
		clickedGuiManager = new SpaceClickedGuiManager();
		partiesManager = new BasicPartiesManager();
		kitsManager = new KitsManager(this, settingsKits);
		kitsManager.loadDefaultKits();
		registerGuis();
	}
	
	private void registerListeners() {
		register(new LoginQuitListener<>(usersManager, database, new SpaceLoginAction(this), new SpaceQuitAction(this)));
		register(new ChatListener(chatManager));
		register(new GuiListener(clickedGuiManager));
		register(new ShotListener(arenasManager));
		register(new PlayerDeathListener(arenasManager));
		register(new BlockListener(arenasManager));
		register(new PlayerDamageListener(arenasManager, settingsArena));
		register(new RemovedEventsListener());
	}
	
	private SpaceDatabase getDatabase() {
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
