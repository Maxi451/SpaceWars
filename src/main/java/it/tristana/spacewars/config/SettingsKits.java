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
	
	private String sniperName;
	private List<String> sniperLore;
	private double sniperArmor;
	private long sniperGunReload;
	private double sniperGunDamage;
	private boolean sniperGunFmj;
	private boolean sniperGunLongBarrel;
	private double sniperEnemyBonusArmorIgnoredPercentage;
	
	private String defenderName;
	private List<String> defenderLore;
	private double defenderArmor;
	private long defenderGunReload;
	private double defenderGunDamage;
	private boolean defenderGunFmj;
	private boolean defenderGunLongBarrel;
	private double defenderBonusArmorPercentage;
	private double defenderBonusDamagePercentage;
	private double defenderBonusReloadPercentage;
	private double defenderMaxNexusDistance;
	
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

		sniperName = config.getString(ConfigKits.SNIPER_NAME);
		sniperLore = config.getList(ConfigKits.SNIPER_LORE);
		sniperArmor = CommonsHelper.parseDoubleOrGetDefault(config.getString(ConfigKits.SNIPER_ARMOR), 50);
		sniperGunReload = CommonsHelper.parseIntOrGetDefault(config.getString(ConfigKits.SNIPER_GUN_RELOAD), 1000);
		sniperGunDamage = CommonsHelper.parseDoubleOrGetDefault(config.getString(ConfigKits.SNIPER_GUN_DAMAGE), 4);
		sniperGunFmj = CommonsHelper.parseBoolean(config.getString(ConfigKits.SNIPER_GUN_FMJ));
		sniperGunLongBarrel = CommonsHelper.parseBoolean(config.getString(ConfigKits.SNIPER_GUN_LONG_BARREL));
		sniperEnemyBonusArmorIgnoredPercentage = CommonsHelper.parseDoubleOrGetDefault(config.getString(ConfigKits.SNIPER_ENEMY_BONUS_ARMOR_IGNORED_PERCENTAGE), 50) / 100;

		defenderName = config.getString(ConfigKits.DEFENDER_NAME);
		defenderLore = config.getList(ConfigKits.DEFENDER_LORE);
		defenderArmor = CommonsHelper.parseDoubleOrGetDefault(config.getString(ConfigKits.DEFENDER_ARMOR), 50);
		defenderGunReload = CommonsHelper.parseIntOrGetDefault(config.getString(ConfigKits.DEFENDER_GUN_RELOAD), 1000);
		defenderGunDamage = CommonsHelper.parseDoubleOrGetDefault(config.getString(ConfigKits.DEFENDER_GUN_DAMAGE), 4);
		defenderGunFmj = CommonsHelper.parseBoolean(config.getString(ConfigKits.DEFENDER_GUN_FMJ));
		defenderGunLongBarrel = CommonsHelper.parseBoolean(config.getString(ConfigKits.DEFENDER_GUN_LONG_BARREL));
		defenderBonusArmorPercentage = CommonsHelper.parseDoubleOrGetDefault(config.getString(ConfigKits.DEFENDER_BONUS_ARMOR_PERCENTAGE), 25) / 100;
		defenderBonusDamagePercentage = CommonsHelper.parseDoubleOrGetDefault(config.getString(ConfigKits.DEFENDER_BONUS_DAMAGE_PERCENTAGE), 15) / 100;
		defenderBonusReloadPercentage = CommonsHelper.parseDoubleOrGetDefault(config.getString(ConfigKits.DEFENDER_BONUS_RELOAD_PERCENTAGE), 20) / 100;
		defenderMaxNexusDistance = CommonsHelper.parseDoubleOrGetDefault(config.getString(ConfigKits.DEFENDER_MAX_NEXUS_DISTANCE), 24);
	}

	public String getSniperName() {
		return sniperName;
	}

	public List<String> getSniperLore() {
		return sniperLore;
	}

	public double getSniperArmor() {
		return sniperArmor;
	}

	public long getSniperGunReload() {
		return sniperGunReload;
	}

	public double getSniperGunDamage() {
		return sniperGunDamage;
	}

	public boolean isSniperGunFmj() {
		return sniperGunFmj;
	}

	public boolean isSniperGunLongBarrel() {
		return sniperGunLongBarrel;
	}

	public double getSniperEnemyBonusArmorIgnoredPercentage() {
		return sniperEnemyBonusArmorIgnoredPercentage;
	}

	public String getDefenderName() {
		return defenderName;
	}

	public List<String> getDefenderLore() {
		return defenderLore;
	}

	public double getDefenderArmor() {
		return defenderArmor;
	}

	public long getDefenderGunReload() {
		return defenderGunReload;
	}

	public double getDefenderGunDamage() {
		return defenderGunDamage;
	}

	public boolean isDefenderGunFmj() {
		return defenderGunFmj;
	}

	public boolean isDefenderGunLongBarrel() {
		return defenderGunLongBarrel;
	}

	public double getDefenderBonusArmorPercentage() {
		return defenderBonusArmorPercentage;
	}

	public double getDefenderBonusDamagePercentage() {
		return defenderBonusDamagePercentage;
	}

	public double getDefenderBonusReloadPercentage() {
		return defenderBonusReloadPercentage;
	}

	public double getDefenderMaxNexusDistance() {
		return defenderMaxNexusDistance;
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
