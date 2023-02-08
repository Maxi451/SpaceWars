package it.tristana.spacewars.config;

import java.io.File;
import java.util.List;

import it.tristana.commons.config.Settings;
import it.tristana.commons.helper.CommonsHelper;

public class SettingsShop extends Settings<ConfigShop> {

	private String guiName;
	private String villagerShopName;

	private String armorName;
	private List<String> armorLore;
	private int armorPrice;
	private double armorBonusArmorPercentage;
	private int armorMaxLevel;

	public SettingsShop(File folder) {
		super(folder, ConfigShop.class);
	}

	@Override
	protected void reload(ConfigShop config) {
		guiName = config.getString(ConfigShop.GUI_NAME);
		villagerShopName = config.getString(ConfigShop.VILLAGER_SHOP_NAME);

		armorName = config.getString(ConfigShop.ARMOR_NAME);
		armorLore = config.getList(ConfigShop.ARMOR_LORE);
		armorPrice = CommonsHelper.parseIntOrGetDefault(config.getString(ConfigShop.ARMOR_PRICE), 500);
		armorBonusArmorPercentage = CommonsHelper.parseDoubleOrGetDefault(config.getString(ConfigShop.ARMOR_BONUS_ARMOR_PERCENTAGE), 0.25);
		armorMaxLevel = CommonsHelper.parseIntOrGetDefault(config.getString(ConfigShop.ARMOR_MAX_LEVEL), 5);
	}

	public String getVillagerShopName() {
		return villagerShopName;
	}

	public String getGuiName() {
		return guiName;
	}

	public int getArmorMaxLevel() {
		return armorMaxLevel;
	}

	public double getArmorBonusArmorPercentage() {
		return armorBonusArmorPercentage;
	}

	public String getArmorName() {
		return armorName;
	}

	public List<String> getArmorLore() {
		return armorLore;
	}

	public int getArmorPrice() {
		return armorPrice;
	}
}
