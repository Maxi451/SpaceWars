package it.tristana.spacewars.config;

import java.io.File;

import it.tristana.commons.config.Config;

public class ConfigShop extends Config {

	private static final String NAME = "name";
	private static final String LORE = "lore";
	private static final String PRICE = "price";

	private static final String ARMOR = "armor.";
	public static final String ARMOR_NAME = ARMOR + NAME;
	public static final String ARMOR_LORE = ARMOR + LORE;
	public static final String ARMOR_PRICE = ARMOR + PRICE;
	public static final String ARMOR_BONUS_ARMOR_PERCENTAGE = ARMOR + "bonus-armor-percentage";

	public ConfigShop(File folder) {
		super(new File(folder, "shop.yml"));
	}

	@Override
	protected void createDefault() {
		set(ARMOR_NAME, "Bonus armor");
		set(ARMOR_LORE, "Increases armor by 5%");
		set(ARMOR_PRICE, "500");
		set(ARMOR_BONUS_ARMOR_PERCENTAGE, "0.25");
	}
}
