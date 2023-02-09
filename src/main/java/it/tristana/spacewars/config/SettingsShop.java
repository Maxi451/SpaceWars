package it.tristana.spacewars.config;

import java.io.File;
import java.util.List;

import org.bukkit.Material;

import it.tristana.commons.config.Settings;
import it.tristana.commons.helper.CommonsHelper;

public class SettingsShop extends Settings<ConfigShop> {

	private String guiName;
	private String villagerShopName;
	private double pricePerLevelIncreasePercentage;

	private String armorName;
	private List<String> armorLore;
	private int armorPrice;
	private double armorBonusArmorPercentage;
	private int armorMaxLevel;
	private Material armorIcon;

	private String repairNexusName;
	private List<String> repairNexusLore;
	private int repairNexusPrice;
	private int repairNexusMaxLevel;
	private Material repairNexusIcon;

	public SettingsShop(File folder) {
		super(folder, ConfigShop.class);
	}

	@Override
	protected void reload(ConfigShop config) {
		guiName = config.getString(ConfigShop.GUI_NAME);
		villagerShopName = config.getString(ConfigShop.VILLAGER_SHOP_NAME);
		pricePerLevelIncreasePercentage = CommonsHelper.parseDoubleOrGetDefault(config.getString(ConfigShop.PRICE_PER_LEVEL_INCREASE_PERCENTAGE), 0.4);

		armorName = config.getString(ConfigShop.ARMOR_NAME);
		armorLore = config.getList(ConfigShop.ARMOR_LORE);
		armorPrice = CommonsHelper.parseIntOrGetDefault(config.getString(ConfigShop.ARMOR_PRICE), 500);
		armorBonusArmorPercentage = CommonsHelper.parseDoubleOrGetDefault(config.getString(ConfigShop.ARMOR_BONUS_ARMOR_PERCENTAGE), 0.25);
		armorMaxLevel = CommonsHelper.parseIntOrGetDefault(config.getString(ConfigShop.ARMOR_MAX_LEVEL), 5);
		armorIcon = CommonsHelper.parseMaterialOrGetDefault(config.getString(ConfigShop.ARMOR_ICON), Material.IRON_CHESTPLATE);

		repairNexusName = config.getString(ConfigShop.REPAIR_NEXUS_NAME);
		repairNexusLore = config.getList(ConfigShop.REPAIR_NEXUS_LORE);
		repairNexusPrice = CommonsHelper.parseIntOrGetDefault(config.getString(ConfigShop.REPAIR_NEXUS_PRICE), 1000);
		repairNexusMaxLevel = CommonsHelper.parseIntOrGetDefault(config.getString(ConfigShop.REPAIR_NEXUS_MAX_LEVEL), -1);
		repairNexusIcon = CommonsHelper.parseMaterialOrGetDefault(config.getString(ConfigShop.REPAIR_NEXUS_ICON), Material.OBSIDIAN);
	}

	public Material getArmorIcon() {
		return armorIcon;
	}

	public Material getRepairNexusIcon() {
		return repairNexusIcon;
	}

	public String getRepairNexusName() {
		return repairNexusName;
	}

	public List<String> getRepairNexusLore() {
		return repairNexusLore;
	}

	public int getRepairNexusPrice() {
		return repairNexusPrice;
	}

	public int getRepairNexusMaxLevel() {
		return repairNexusMaxLevel;
	}

	public double getPricePerLevelIncreasePercentage() {
		return pricePerLevelIncreasePercentage;
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
