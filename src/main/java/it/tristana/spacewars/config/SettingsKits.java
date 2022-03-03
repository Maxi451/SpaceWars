package it.tristana.spacewars.config;

import java.util.List;

import it.tristana.commons.config.Settings;
import it.tristana.commons.helper.CommonsHelper;

public class SettingsKits extends Settings<ConfigKits> {

	private String minerName;
	private List<String> minerLore;
	private double minerArmor;
	private long minerGunReload;
	private double minerGunDamage;
	private boolean minerGunFmj;
	private boolean minerGunLongBarrel;
	
	private String soldierName;
	private List<String> soldierLore;
	private double soldierArmor;
	private long soldierGunReload;
	private double soldierGunDamage;
	private boolean soldierGunFmj;
	private boolean soldierGunLongBarrel;
	
	public SettingsKits(ConfigKits config) {
		super(config);
	}
	
	@Override
	public void reload() {
		minerName = config.getString(ConfigKits.MINER_NAME);
		minerLore = config.getList(ConfigKits.MINER_LORE);
		minerArmor = CommonsHelper.parseDoubleOrGetDefault(config.getString(ConfigKits.MINER_ARMOR), 33);
		minerGunReload = CommonsHelper.parseIntOrGetDefault(config.getString(ConfigKits.MINER_GUN_RELOAD), 2000);
		minerGunDamage = CommonsHelper.parseDoubleOrGetDefault(config.getString(ConfigKits.MINER_GUN_DAMAGE), 3);
		minerGunFmj = CommonsHelper.parseBoolean(config.getString(ConfigKits.MINER_GUN_FMJ));
		minerGunLongBarrel = CommonsHelper.parseBoolean(config.getString(ConfigKits.MINER_GUN_LONG_BARREL));

		soldierName = config.getString(ConfigKits.SOLDIER_NAME);
		soldierLore = config.getList(ConfigKits.SOLDIER_LORE);
		soldierArmor = CommonsHelper.parseDoubleOrGetDefault(config.getString(ConfigKits.SOLDIER_ARMOR), 50);
		soldierGunReload = CommonsHelper.parseIntOrGetDefault(config.getString(ConfigKits.SOLDIER_GUN_RELOAD), 1000);
		soldierGunDamage = CommonsHelper.parseDoubleOrGetDefault(config.getString(ConfigKits.SOLDIER_GUN_DAMAGE), 4);
		soldierGunFmj = CommonsHelper.parseBoolean(config.getString(ConfigKits.SOLDIER_GUN_FMJ));
		soldierGunLongBarrel = CommonsHelper.parseBoolean(config.getString(ConfigKits.SOLDIER_GUN_LONG_BARREL));
	}

	public String getMinerName() {
		return minerName;
	}

	public List<String> getMinerLore() {
		return minerLore;
	}

	public double getMinerArmor() {
		return minerArmor;
	}

	public long getMinerGunReload() {
		return minerGunReload;
	}

	public double getMinerGunDamage() {
		return minerGunDamage;
	}

	public boolean isMinerGunFmj() {
		return minerGunFmj;
	}

	public boolean isMinerGunLongBarrel() {
		return minerGunLongBarrel;
	}

	public String getSoldierName() {
		return soldierName;
	}

	public List<String> getSoldierLore() {
		return soldierLore;
	}

	public double getSoldierArmor() {
		return soldierArmor;
	}

	public long getSoldierGunReload() {
		return soldierGunReload;
	}

	public double getSoldierGunDamage() {
		return soldierGunDamage;
	}

	public boolean isSoldierGunFmj() {
		return soldierGunFmj;
	}

	public boolean isSoldierGunLongBarrel() {
		return soldierGunLongBarrel;
	}
}
