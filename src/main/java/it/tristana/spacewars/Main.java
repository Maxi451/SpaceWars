package it.tristana.spacewars;

import java.io.File;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import it.tristana.commons.arena.BasicArenasManager;
import it.tristana.commons.config.ConfigDefaultCommands;
import it.tristana.commons.config.SettingsDefaultCommands;
import it.tristana.commons.database.BasicUsersManager;
import it.tristana.commons.database.DatabaseManager;
import it.tristana.commons.helper.CommonsHelper;
import it.tristana.commons.helper.PluginDraft;
import it.tristana.commons.interfaces.DatabaseHolder;
import it.tristana.commons.interfaces.Reloadable;
import it.tristana.commons.interfaces.arena.ArenaLoader;
import it.tristana.commons.interfaces.arena.ArenasManager;
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
import it.tristana.spacewars.config.ConfigKits;
import it.tristana.spacewars.config.ConfigSpaceDatabase;
import it.tristana.spacewars.config.SettingsKits;
import it.tristana.spacewars.database.SpaceDatabase;
import it.tristana.spacewars.database.SpaceUser;
import it.tristana.spacewars.gui.GuiKit;
import it.tristana.spacewars.gui.SpaceClickedGuiManager;

public class Main extends PluginDraft implements Reloadable, DatabaseHolder {
	
	public static final String ADMIN_PERMS = "spacewars.admin";
	
	private static final String COMMAND = "spacewars";
	
	private File folder;
	private boolean isDisabled;

	private SettingsDefaultCommands settingsDefaultCommands;
	private SettingsKits settingsKits;

	private DatabaseManager<SpaceUser> database;
	private UsersManager<SpaceUser> usersManager;
	private ChatManager chatManager;
	private ClickedGuiManager clickedGuiManager;
	private ArenasManager<SpaceArena> arenasManager;
	private ArenaLoader<SpaceArena> arenaLoader;
	private KitsManager kitsManager;
	
	@Override
	public void onEnable() {
		folder = getFolder();
		try {
			database = getDatabase();
			database.openConnection();
		} catch (Exception e) {
			selfDestroy(e, "&cCould not open the database connection. Check the errors file");
			return;
		}
		settingsDefaultCommands = new SettingsDefaultCommands(new ConfigDefaultCommands());
		settingsKits = new SettingsKits(new ConfigKits(folder));
		loadArenas();
		try {
			loadManagers();
		} catch (Exception e) {
			selfDestroy(e, "&cAn internal error occurred loading SpaceWars. This is a programming error! Please report the stacktrace found in your errors file");
			return;
		}
		registerListeners();
		Bukkit.getPluginCommand(COMMAND).setExecutor(new SpaceCommand(this, settingsDefaultCommands, "sw"));
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
		clickedGuiManager.clearGuis();
		registerGuis();
	}
	
	private void registerGuis() {
		clickedGuiManager.registerGui(new GuiKit("Kits", kitsManager));
	}
	
	private void selfDestroy(Throwable t, String message) {
		CommonsHelper.consoleInfo(message);
		writeThrowableOnErrorsFile(t);
		isDisabled = true;
		Bukkit.getPluginManager().disablePlugin(this);
	}
	
	private void closeArenas() {
		arenasManager.getArenas().forEach(arena -> arena.closeArena());
	}
	
	private void saveArenas() {
		arenaLoader.saveArenas(arenasManager.getArenas());
	}
	
	private void loadArenas() {
		arenaLoader = new SpaceArenaLoader(folder);
		arenasManager = new BasicArenasManager<>();
		arenaLoader.loadArenas().forEach(arena -> arenasManager.addArena(arena));
		arenasManager.startClock(this, 20);
	}
	
	private void loadManagers() throws NoSuchMethodException {
		usersManager = new BasicUsersManager<>(database);
		chatManager = new SpaceChatManager();
		clickedGuiManager = new SpaceClickedGuiManager();
		kitsManager = new KitsManager(this, settingsKits);
		kitsManager.loadDefaultKits();
		registerGuis();
	}
	
	private void registerListeners() {
		register(new LoginQuitListener<>(usersManager, database));
		register(new ChatListener(chatManager));
		register(new GuiListener(clickedGuiManager));
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
