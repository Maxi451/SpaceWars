package it.tristana.spacewars.config;

import java.io.File;

import it.tristana.commons.config.Config;

public class ConfigPowerups extends Config {

	private static final String NAME = "name";
	private static final String CHANCE = "chance";
	
	private static final String _1_UP = "1up.";
	public static final String _1_UP_NAME = _1_UP + NAME;
	public static final String _1_UP_CHANCE = _1_UP + CHANCE;
	
	private static final String LONG_BARREL = "long-barrel.";
	public static final String LONG_BARREL_NAME = LONG_BARREL + NAME;
	public static final String LONG_BARREL_CHANCE = LONG_BARREL + CHANCE;
	
	public ConfigPowerups(File folder) {
		super(new File(folder, "powerups.yml"));
	}

	@Override
	protected void createDefault() {
		set(_1_UP_NAME, "1 up");
		set(_1_UP_CHANCE, "10");
		set(LONG_BARREL_NAME, "Long barrel");
		set(LONG_BARREL_CHANCE, "20");
	}
}
