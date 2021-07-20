package it.tristana.spacewars.config;

import java.io.File;

import it.tristana.commons.config.ConfigDatabase;

public class ConfigSpaceDatabase extends ConfigDatabase {

	public static final String TABLE = "table";
	
	public ConfigSpaceDatabase(File folder) {
		super(new File(folder, "database.yml"));
	}

	@Override
	public void createDefault() {
		super.createDefault();
		set(TABLE, "spacewars_players");
	}
}
