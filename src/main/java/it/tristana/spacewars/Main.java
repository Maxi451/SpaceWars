package it.tristana.spacewars;

import java.io.File;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import it.tristana.commons.config.ConfigDefaultCommands;
import it.tristana.commons.config.SettingsDefaultCommands;
import it.tristana.commons.database.BasicUsersManager;
import it.tristana.commons.database.DatabaseManager;
import it.tristana.commons.helper.CommonsHelper;
import it.tristana.commons.helper.PluginDraft;
import it.tristana.commons.interfaces.DatabaseHolder;
import it.tristana.commons.interfaces.Reloadable;
import it.tristana.commons.interfaces.chat.ChatManager;
import it.tristana.commons.interfaces.database.Database;
import it.tristana.commons.interfaces.database.UsersManager;
import it.tristana.commons.interfaces.gui.ClickedGuiManager;
import it.tristana.commons.listener.ChatListener;
import it.tristana.commons.listener.GuiListener;
import it.tristana.commons.listener.LoginQuitListener;
import it.tristana.spacewars.chat.SpaceChatManager;
import it.tristana.spacewars.command.SpaceCommand;
import it.tristana.spacewars.config.ConfigSpaceDatabase;
import it.tristana.spacewars.database.SpaceDatabase;
import it.tristana.spacewars.database.SpaceUser;
import it.tristana.spacewars.gui.SpaceClickedGuiManager;

public class Main extends PluginDraft implements Reloadable, DatabaseHolder {
	
	private static final String COMMAND = "spacewars";
	
	private File folder;
	private boolean isDisabled;

	private DatabaseManager<SpaceUser> database;
	private UsersManager<SpaceUser> usersManager;
	private SettingsDefaultCommands settingsDefaultCommands;
	private ChatManager chatManager;
	private ClickedGuiManager clickedGuiManager;
	
	@Override
	public void onEnable() {
		try {
			database = getDatabase();
			database.openConnection();
		} catch (Exception e) {
			CommonsHelper.consoleInfo("&cCould not open the database connection. Check the errors file");
			writeThrowableOnErrorsFile(e);
			isDisabled = true;
			return;
		}
		folder = getFolder();
		loadManagers();
		registerListeners();
		settingsDefaultCommands = new SettingsDefaultCommands(new ConfigDefaultCommands());
		Bukkit.getPluginCommand(COMMAND).setExecutor(new SpaceCommand(this, settingsDefaultCommands, "sw"));
	}
	
	@Override
	public void onDisable() {
		if (isDisabled) {
			return;
		}
		try {
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
		settingsDefaultCommands.reload();
	}
	
	private void loadManagers() {
		usersManager = new BasicUsersManager<>(database);
		chatManager = new SpaceChatManager();
		clickedGuiManager = new SpaceClickedGuiManager();
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
