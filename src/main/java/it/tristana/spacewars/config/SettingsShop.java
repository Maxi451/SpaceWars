package it.tristana.spacewars.config;

import java.io.File;

import it.tristana.commons.config.Settings;

public class SettingsShop extends Settings<ConfigShop> {

	public SettingsShop(File folder) {
		super(folder, ConfigShop.class);
	}

	@Override
	protected void reload(ConfigShop config) {
		
	}
}
