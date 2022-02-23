package it.tristana.spacewars;

import java.io.File;

import it.tristana.commons.database.BasicUsersManager;
import it.tristana.commons.database.DatabaseManager;
import it.tristana.commons.helper.PluginDraft;
import it.tristana.commons.interfaces.DatabaseHolder;
import it.tristana.commons.interfaces.Reloadable;
import it.tristana.commons.interfaces.database.Database;
import it.tristana.commons.interfaces.database.UsersManager;
import it.tristana.spacewars.config.ConfigSpaceDatabase;
import it.tristana.spacewars.database.SpaceDatabase;
import it.tristana.spacewars.database.SpaceUser;

public class Main extends PluginDraft implements Reloadable, DatabaseHolder {
	
	private File folder;
	private boolean isDisabled;

	private DatabaseManager<SpaceUser> database;
	private UsersManager<SpaceUser> usersManager;
	
	@Override
	public void onEnable() {
		folder = getFolder();
		try {
			database = getDatabase();
			database.openConnection();
		} catch (Exception e) {
			writeThrowableOnErrorsFile(e);
			isDisabled = true;
			return;
		}
		usersManager = new BasicUsersManager<>(database);
	}
	
	@Override
	public void onDisable() {
		if (isDisabled) {
			return;
		}
	}

	@Override
	public Database getStorage() {
		return database;
	}
	
	@Override
	public void reload() {
		
	}
	
	private SpaceDatabase getDatabase() {
		ConfigSpaceDatabase config = new ConfigSpaceDatabase(folder);
		String host = config.getString(ConfigSpaceDatabase.HOST);
		String database = config.getString(ConfigSpaceDatabase.HOST);
		String username = config.getString(ConfigSpaceDatabase.HOST);
		String password = config.getString(ConfigSpaceDatabase.HOST);
		int port = Integer.parseInt(config.getString(ConfigSpaceDatabase.HOST));
		String tablePlayers = config.getString(ConfigSpaceDatabase.TABLE_PLAYERS);
		return new SpaceDatabase(host, database, username, password, port, this, tablePlayers);
	}
}
