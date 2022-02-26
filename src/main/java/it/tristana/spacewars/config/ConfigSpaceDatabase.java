package it.tristana.spacewars.config;

import java.io.File;

import it.tristana.commons.config.ConfigDatabase;

public class ConfigSpaceDatabase extends ConfigDatabase {

	public static final String TABLE_PLAYERS = "table-players";
	
	public ConfigSpaceDatabase(File folder) {
		super(new File(folder, "database.yml"));
	}
	
	@Override
	protected void createDefault() {
		super.createDefault();
		set(TABLE_PLAYERS, "sw_players");
	}
}
