package it.tristana.spacewars.config;

import java.io.File;
import java.util.List;

import it.tristana.commons.config.Settings;
import it.tristana.commons.helper.CommonsHelper;

public class SettingsShop extends Settings<ConfigShop> {

	private String armorName;
	private List<String> armorLore;
	private int armorPrice;
	private double armorBonusArmorPercentage;
	
	public SettingsShop(File folder) {
		super(folder, ConfigShop.class);
	}

	@Override
	protected void reload(ConfigShop config) {
		armorName = config.getString(ConfigShop.ARMOR_NAME);
		armorLore = config.getList(ConfigShop.ARMOR_LORE);
		armorPrice = CommonsHelper.parseIntOrGetDefault(config.getString(ConfigShop.ARMOR_PRICE), 500);
		armorBonusArmorPercentage = CommonsHelper.parseDoubleOrGetDefault(config.getString(ConfigShop.ARMOR_BONUS_ARMOR_PERCENTAGE), 0.25);
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
