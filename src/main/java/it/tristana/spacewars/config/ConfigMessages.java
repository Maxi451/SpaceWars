package it.tristana.spacewars.config;

import java.io.File;

import it.tristana.commons.config.Config;

public class ConfigMessages extends Config {

	public ConfigMessages(File folder) {
		super(new File(folder, "messages.yml"));
	}

	@Override
	protected void createDefault() {
		
	}
}
