package it.tristana.spacewars.config;

import java.io.File;

import it.tristana.commons.config.Config;

public class ConfigItems extends Config {

	public ConfigItems(File folder) {
		super(new File(folder, "items.yml"));
	}

	@Override
	protected void createDefault() {
		
	}
}
