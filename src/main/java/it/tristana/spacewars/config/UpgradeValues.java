package it.tristana.spacewars.config;

import java.util.List;

import it.tristana.spacewars.interfaces.Configurable;

public class UpgradeValues implements Configurable {

	private ConfigUpgrade configUpgrade;
	
	private List<String> upgradeAllTeam;
	private String upgradeLevel;
	private String upgradeCost;
	
	private String upgradeDamageName;
	private String upgradeDamageValue;
	private String upgradeDamageMaxLevel;
	private String upgradeDamageCost;
	
	private String upgradeDefenseName;
	private String upgradeDefenseValue;
	private String upgradeDefenseMaxLevel;
	private String upgradeDefenseCost;
	
	private String upgradeFireRatioName;
	private String upgradeFireRatioValue;
	private String upgradeFireRatioMaxLevel;
	private String upgradeFireRatioCost;
	
	private String upgradeFuelName;
	private String upgradeFuelValue;
	private String upgradeFuelMaxLevel;
	private String upgradeFuelCost;
	
	private String upgradeHealthName;
	private String upgradeHealthValue;
	private String upgradeHealthMaxLevel;
	private String upgradeHealthCost;
	
	private String upgradeObsidianName;
	private String upgradeObsidianMaxLevel;
	private String upgradeObsidianCost;
	
	public UpgradeValues(ConfigUpgrade configUpgrade) {
		this.configUpgrade = configUpgrade;
	}
	
	@Override
	public void setup() {
		upgradeAllTeam = configUpgrade.getList(ConfigUpgrade.UPGRADE_ALL_TEAM);
		upgradeLevel = configUpgrade.getString(ConfigUpgrade.UPGRADE_LEVEL);
		upgradeCost = configUpgrade.getString(ConfigUpgrade.UPGRADE_COST);
		
		upgradeDamageName = configUpgrade.getString(ConfigUpgrade.UPGRADE_DAMAGE_NAME);
		upgradeDamageValue = configUpgrade.getString(ConfigUpgrade.UPGRADE_DAMAGE_VALUE);
		upgradeDamageMaxLevel = configUpgrade.getString(ConfigUpgrade.UPGRADE_DAMAGE_MAX_LEVEL);
		upgradeDamageCost = configUpgrade.getString(ConfigUpgrade.UPGRADE_DAMAGE_COST);
		
		upgradeDefenseName = configUpgrade.getString(ConfigUpgrade.UPGRADE_DEFENSE_NAME);
		upgradeDefenseValue = configUpgrade.getString(ConfigUpgrade.UPGRADE_DEFENSE_VALUE);
		upgradeDefenseMaxLevel = configUpgrade.getString(ConfigUpgrade.UPGRADE_DEFENSE_MAX_LEVEL);
		upgradeDefenseCost = configUpgrade.getString(ConfigUpgrade.UPGRADE_DEFENSE_COST);
		
		upgradeFireRatioName = configUpgrade.getString(ConfigUpgrade.UPGRADE_FIRE_RATIO_NAME);
		upgradeFireRatioValue = configUpgrade.getString(ConfigUpgrade.UPGRADE_FIRE_RATIO_VALUE);
		upgradeFireRatioMaxLevel = configUpgrade.getString(ConfigUpgrade.UPGRADE_FIRE_RATIO_MAX_LEVEL);
		upgradeFireRatioCost = configUpgrade.getString(ConfigUpgrade.UPGRADE_FIRE_RATIO_COST);
		
		upgradeFuelName = configUpgrade.getString(ConfigUpgrade.UPGRADE_FUEL_NAME);
		upgradeFuelValue = configUpgrade.getString(ConfigUpgrade.UPGRADE_FUEL_VALUE);
		upgradeFuelMaxLevel = configUpgrade.getString(ConfigUpgrade.UPGRADE_FUEL_MAX_LEVEL);
		upgradeFuelCost = configUpgrade.getString(ConfigUpgrade.UPGRADE_FUEL_COST);
		
		upgradeHealthName = configUpgrade.getString(ConfigUpgrade.UPGRADE_HEALTH_NAME);
		upgradeHealthValue = configUpgrade.getString(ConfigUpgrade.UPGRADE_HEALTH_VALUE);
		upgradeHealthMaxLevel = configUpgrade.getString(ConfigUpgrade.UPGRADE_HEALTH_MAX_LEVEL);
		upgradeHealthCost = configUpgrade.getString(ConfigUpgrade.UPGRADE_HEALTH_COST);
		
		upgradeObsidianName = configUpgrade.getString(ConfigUpgrade.UPGRADE_OBSIDIAN_NAME);
		upgradeObsidianMaxLevel = configUpgrade.getString(ConfigUpgrade.UPGRADE_OBSIDIAN_MAX_LEVEL);
		upgradeObsidianCost = configUpgrade.getString(ConfigUpgrade.UPGRADE_OBSIDIAN_COST);
	}

	public List<String> getUpgradeAllTeam() {
		return upgradeAllTeam;
	}

	public String getUpgradeLevel() {
		return upgradeLevel;
	}

	public String getUpgradeCost() {
		return upgradeCost;
	}

	public String getUpgradeDamageName() {
		return upgradeDamageName;
	}

	public String getUpgradeDamageValue() {
		return upgradeDamageValue;
	}

	public String getUpgradeDamageMaxLevel() {
		return upgradeDamageMaxLevel;
	}

	public String getUpgradeDamageCost() {
		return upgradeDamageCost;
	}

	public String getUpgradeDefenseName() {
		return upgradeDefenseName;
	}

	public String getUpgradeDefenseValue() {
		return upgradeDefenseValue;
	}

	public String getUpgradeDefenseMaxLevel() {
		return upgradeDefenseMaxLevel;
	}

	public String getUpgradeDefenseCost() {
		return upgradeDefenseCost;
	}

	public String getUpgradeFireRatioName() {
		return upgradeFireRatioName;
	}

	public String getUpgradeFireRatioValue() {
		return upgradeFireRatioValue;
	}

	public String getUpgradeFireRatioMaxLevel() {
		return upgradeFireRatioMaxLevel;
	}

	public String getUpgradeFireRatioCost() {
		return upgradeFireRatioCost;
	}

	public String getUpgradeFuelName() {
		return upgradeFuelName;
	}

	public String getUpgradeFuelValue() {
		return upgradeFuelValue;
	}

	public String getUpgradeFuelMaxLevel() {
		return upgradeFuelMaxLevel;
	}

	public String getUpgradeFuelCost() {
		return upgradeFuelCost;
	}

	public String getUpgradeHealthName() {
		return upgradeHealthName;
	}

	public String getUpgradeHealthValue() {
		return upgradeHealthValue;
	}

	public String getUpgradeHealthMaxLevel() {
		return upgradeHealthMaxLevel;
	}

	public String getUpgradeHealthCost() {
		return upgradeHealthCost;
	}

	public String getUpgradeObsidianName() {
		return upgradeObsidianName;
	}

	public String getUpgradeObsidianMaxLevel() {
		return upgradeObsidianMaxLevel;
	}

	public String getUpgradeObsidianCost() {
		return upgradeObsidianCost;
	}
}