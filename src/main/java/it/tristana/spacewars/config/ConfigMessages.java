package it.tristana.spacewars.config;

import java.io.File;

import it.tristana.commons.config.Config;

public class ConfigMessages extends Config {

	private static final String MISC = "misc.";
	public static final String YES_WORD = MISC + "yes-word";
	public static final String NO_WORD = MISC + "no-word";
	public static final String VILLAGER_SHOP_NAME = MISC + "villager-shop-name";
	
	public ConfigMessages(File folder) {
		super(new File(folder, "messages.yml"));
	}

	@Override
	protected void createDefault() {
		set(YES_WORD, "yes");
		set(NO_WORD, "no");
		set(VILLAGER_SHOP_NAME, "&aShop");
	}
}
