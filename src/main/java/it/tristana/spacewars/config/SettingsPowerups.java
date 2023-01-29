package it.tristana.spacewars.config;

import java.io.File;

import it.tristana.commons.config.Settings;
import it.tristana.commons.helper.CommonsHelper;

public class SettingsPowerups extends Settings<ConfigPowerups> {

	private String _1upName;
	private int _1upChance;
	
	private String longBarrelName;
	private int longBarrelChance;
	
	private String medicKitName;
	private int medicKitChance;
	
	private String fmjName;
	private int fmjChance;
	
	private String moneyName;
	private int moneyChance;
	private double moneyAmount;
	
	private String fuelName;
	private int fuelChance;
	private int fuelAmount;
	
	private String betterPickaxesName;
	private int betterPickaxesChance;
	
	public SettingsPowerups(File folder) {
		super(folder, ConfigPowerups.class);
	}

	@Override
	protected void reload(ConfigPowerups config) {
		_1upName = config.getString(ConfigPowerups._1_UP_NAME);
		_1upChance = CommonsHelper.parseIntOrGetDefault(config.getString(ConfigPowerups._1_UP_CHANCE), 10);
		
		longBarrelName = config.getString(ConfigPowerups.LONG_BARREL_NAME);
		longBarrelChance = CommonsHelper.parseIntOrGetDefault(config.getString(ConfigPowerups.LONG_BARREL_CHANCE), 20);
		
		medicKitName = config.getString(ConfigPowerups.MEDIC_KIT_NAME);
		medicKitChance = CommonsHelper.parseIntOrGetDefault(config.getString(ConfigPowerups.MEDIC_KIT_CHANCE), 20);
		
		fmjName = config.getString(ConfigPowerups.FMJ_NAME);
		fmjChance = CommonsHelper.parseIntOrGetDefault(config.getString(ConfigPowerups.FMJ_CHANCE), 15);
		
		moneyName = config.getString(ConfigPowerups.MONEY_NAME);
		moneyChance = CommonsHelper.parseIntOrGetDefault(config.getString(ConfigPowerups.MONEY_CHANCE), 10);
		moneyAmount = CommonsHelper.parseDoubleOrGetDefault(config.getString(ConfigPowerups.MONEY_AMOUNT), 100);
		
		fuelName = config.getString(ConfigPowerups.FUEL_NAME);
		fuelChance = CommonsHelper.parseIntOrGetDefault(config.getString(ConfigPowerups.FUEL_CHANCE), 20);
		fuelAmount = CommonsHelper.parseIntOrGetDefault(config.getString(ConfigPowerups.FUEL_AMOUNT), 3);
		
		betterPickaxesName = config.getString(ConfigPowerups.BETTER_PICKAXES_NAME);
		betterPickaxesChance = CommonsHelper.parseIntOrGetDefault(config.getString(ConfigPowerups.BETTER_PICKAXES_CHANCE), 15);
	}

	public String getBetterPickaxesName() {
		return betterPickaxesName;
	}

	public int getBetterPickaxesChance() {
		return betterPickaxesChance;
	}

	public String getFuelName() {
		return fuelName;
	}

	public int getFuelChance() {
		return fuelChance;
	}

	public int getFuelAmount() {
		return fuelAmount;
	}

	public String getMedicKitName() {
		return medicKitName;
	}

	public int getMedicKitChance() {
		return medicKitChance;
	}

	public String getFmjName() {
		return fmjName;
	}

	public int getFmjChance() {
		return fmjChance;
	}

	public String getMoneyName() {
		return moneyName;
	}

	public int getMoneyChance() {
		return moneyChance;
	}

	public double getMoneyAmount() {
		return moneyAmount;
	}

	public String get_1upName() {
		return _1upName;
	}

	public int get_1upChance() {
		return _1upChance;
	}

	public String getLongBarrelName() {
		return longBarrelName;
	}

	public int getLongBarrelChance() {
		return longBarrelChance;
	}
}
