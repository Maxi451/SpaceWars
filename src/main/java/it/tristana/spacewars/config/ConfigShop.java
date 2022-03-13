package it.tristana.spacewars.config;

import java.io.File;

import it.tristana.commons.config.Config;

public class ConfigShop extends Config {

	public ConfigShop(File folder) {
		super(new File(folder, "shop.yml"));
	}

	@Override
	protected void createDefault() {
		
	}
}
