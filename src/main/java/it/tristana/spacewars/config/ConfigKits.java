package it.tristana.spacewars.config;

import java.io.File;

import it.tristana.commons.config.Config;

public class ConfigKits extends Config {

	public ConfigKits(File folder) {
		super(new File(folder, "kits.yml"));
	}

	@Override
	protected void createDefault() {
		
	}
}
