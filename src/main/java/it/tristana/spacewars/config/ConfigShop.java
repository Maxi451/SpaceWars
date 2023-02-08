package it.tristana.spacewars.config;

import java.io.File;

import it.tristana.commons.config.Config;

public class ConfigShop extends Config {

	public static final String GUI_NAME = "gui-name";
	public static final String VILLAGER_SHOP_NAME = "villager-shop-name";
	
	private static final String NAME = "name";
	private static final String LORE = "lore";
	private static final String PRICE = "price";
	private static final String MAX_LEVEL = "max-level";

	private static final String ARMOR = "armor.";
	public static final String ARMOR_NAME = ARMOR + NAME;
	public static final String ARMOR_LORE = ARMOR + LORE;
	public static final String ARMOR_PRICE = ARMOR + PRICE;
	public static final String ARMOR_BONUS_ARMOR_PERCENTAGE = ARMOR + "bonus-armor-percentage";
	public static final String ARMOR_MAX_LEVEL = ARMOR + MAX_LEVEL;

	public ConfigShop(File folder) {
		super(new File(folder, "shop.yml"));
	}

	@Override
	protected void createDefault() {
		set(GUI_NAME, "&bShop");
		set(VILLAGER_SHOP_NAME, "&b&lSHOP");
		
		set(ARMOR_NAME, "Bonus armor");
		set(ARMOR_LORE, "Increases armor by 25%");
		set(ARMOR_PRICE, "500");
		set(ARMOR_BONUS_ARMOR_PERCENTAGE, "0.25");
		set(ARMOR_MAX_LEVEL, "5");
	}
}
