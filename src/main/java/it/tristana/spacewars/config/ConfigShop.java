package it.tristana.spacewars.config;

import java.io.File;
import java.util.Arrays;

import it.tristana.commons.config.Config;

public class ConfigShop extends Config {

	public static final String GUI_NAME = "gui-name";
	public static final String VILLAGER_SHOP_NAME = "villager-shop-name";
	public static final String PRICE_PER_LEVEL_INCREASE_PERCENTAGE = "price-per-level-increase-percentage";

	private static final String NAME = "name";
	private static final String LORE = "lore";
	private static final String PRICE = "price";
	private static final String MAX_LEVEL = "max-level";
	private static final String ICON = "icon";

	private static final String ARMOR = "armor.";
	public static final String ARMOR_NAME = ARMOR + NAME;
	public static final String ARMOR_LORE = ARMOR + LORE;
	public static final String ARMOR_PRICE = ARMOR + PRICE;
	public static final String ARMOR_BONUS_ARMOR_PERCENTAGE = ARMOR + "bonus-armor-percentage";
	public static final String ARMOR_MAX_LEVEL = ARMOR + MAX_LEVEL;
	public static final String ARMOR_ICON = ARMOR + ICON;

	private static final String REPAIR_NEXUS = "repair-nexus.";
	public static final String REPAIR_NEXUS_NAME = REPAIR_NEXUS + NAME;
	public static final String REPAIR_NEXUS_LORE = REPAIR_NEXUS + LORE;
	public static final String REPAIR_NEXUS_PRICE = REPAIR_NEXUS + PRICE;
	public static final String REPAIR_NEXUS_MAX_LEVEL = REPAIR_NEXUS + MAX_LEVEL;
	public static final String REPAIR_NEXUS_ICON = REPAIR_NEXUS + ICON;

	public ConfigShop(File folder) {
		super(new File(folder, "shop.yml"));
	}

	@Override
	protected void createDefault() {
		set(GUI_NAME, "&bShop");
		set(VILLAGER_SHOP_NAME, "&b&lSHOP");
		set(PRICE_PER_LEVEL_INCREASE_PERCENTAGE, "0.4");

		set(ARMOR_NAME, "Bonus armor");
		set(ARMOR_LORE, Arrays.asList("Increases armor by 25%"));
		set(ARMOR_PRICE, "500");
		set(ARMOR_BONUS_ARMOR_PERCENTAGE, "0.25");
		set(ARMOR_MAX_LEVEL, "5");
		set(ARMOR_ICON, "IRON_CHESTPLATE");

		set(REPAIR_NEXUS_NAME, "Nexus repair kit");
		set(REPAIR_NEXUS_LORE, Arrays.asList("Repairs the nexus's pillars,", "if the core is not destroyed"));
		set(REPAIR_NEXUS_PRICE, "1000");
		set(REPAIR_NEXUS_MAX_LEVEL, "-1");
		set(REPAIR_NEXUS_ICON, "OBSIDIAN");
	}
}
