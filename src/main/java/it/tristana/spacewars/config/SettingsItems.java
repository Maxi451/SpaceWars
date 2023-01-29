package it.tristana.spacewars.config;

import java.io.File;

import it.tristana.commons.config.Settings;

public class SettingsItems extends Settings<ConfigItems> {

	public SettingsItems(File folder) {
		super(folder, ConfigItems.class);
	}

	@Override
	protected void reload(ConfigItems config) {
		
	}
}
