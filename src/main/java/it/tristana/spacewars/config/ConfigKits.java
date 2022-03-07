package it.tristana.spacewars.config;

import java.io.File;
import java.util.Arrays;

import it.tristana.commons.config.Config;

public class ConfigKits extends Config {

	private static final String NAME = "name";
	private static final String LORE = "lore";
	private static final String ARMOR = "armor";
	private static final String GUN = "gun.";
	private static final String RELOAD = "reload";
	private static final String DAMAGE = "damage";
	private static final String FMJ = "fmj";
	private static final String LONG_BARREL = "long-barrel";

	private static final String MINER = "miner.";
	public static final String MINER_NAME = MINER + NAME;
	public static final String MINER_LORE = MINER + LORE;
	public static final String MINER_ARMOR = MINER + ARMOR;
	public static final String MINER_GUN_RELOAD = MINER + GUN + RELOAD;
	public static final String MINER_GUN_DAMAGE = MINER + GUN + DAMAGE;
	public static final String MINER_GUN_FMJ = MINER + GUN + FMJ;
	public static final String MINER_GUN_LONG_BARREL = MINER + GUN + LONG_BARREL;

	private static final String SOLDIER = "soldier.";
	public static final String SOLDIER_NAME = SOLDIER + NAME;
	public static final String SOLDIER_LORE = SOLDIER + LORE;
	public static final String SOLDIER_ARMOR = SOLDIER + ARMOR;
	public static final String SOLDIER_GUN_RELOAD = SOLDIER + GUN + RELOAD;
	public static final String SOLDIER_GUN_DAMAGE = SOLDIER + GUN + DAMAGE;
	public static final String SOLDIER_GUN_FMJ = SOLDIER + GUN + FMJ;
	public static final String SOLDIER_GUN_LONG_BARREL = SOLDIER + GUN + LONG_BARREL;

	private static final String SNIPER = "sniper.";
	public static final String SNIPER_NAME = SNIPER + NAME;
	public static final String SNIPER_LORE = SNIPER + LORE;
	public static final String SNIPER_ARMOR = SNIPER + ARMOR;
	public static final String SNIPER_GUN_RELOAD = SNIPER + GUN + RELOAD;
	public static final String SNIPER_GUN_DAMAGE = SNIPER + GUN + DAMAGE;
	public static final String SNIPER_GUN_FMJ = SNIPER + GUN + FMJ;
	public static final String SNIPER_GUN_LONG_BARREL = SNIPER + GUN + LONG_BARREL;
	public static final String SNIPER_ENEMY_BONUS_ARMOR_IGNORED_PERCENTAGE = SNIPER + "enemy-bonus-armor-ignored-percentage";

	private static final String DEFENDER = "defender.";
	public static final String DEFENDER_NAME = DEFENDER + NAME;
	public static final String DEFENDER_LORE = DEFENDER + LORE;
	public static final String DEFENDER_ARMOR = DEFENDER + ARMOR;
	public static final String DEFENDER_GUN_RELOAD = DEFENDER + GUN + RELOAD;
	public static final String DEFENDER_GUN_DAMAGE = DEFENDER + GUN + DAMAGE;
	public static final String DEFENDER_GUN_FMJ = DEFENDER + GUN + FMJ;
	public static final String DEFENDER_GUN_LONG_BARREL = DEFENDER + GUN + LONG_BARREL;
	public static final String DEFENDER_BONUS_ARMOR_PERCENTAGE = DEFENDER + "bonus-armor-percentage";
	public static final String DEFENDER_BONUS_DAMAGE_PERCENTAGE = DEFENDER + "bonus-damage-percentage";
	public static final String DEFENDER_BONUS_RELOAD_PERCENTAGE = DEFENDER + "bonus-reload-percentage";
	public static final String DEFENDER_MAX_NEXUS_DISTANCE = DEFENDER + "max-nexus-distance";

	public ConfigKits(File folder) {
		super(new File(folder, "kits.yml"));
	}

	@Override
	protected void createDefault() {
		set(MINER_NAME, "Kit &bminer");
		set(MINER_LORE, Arrays.asList(
				"Starts with an Efficiency III pickaxe"
		));
		set(MINER_ARMOR, "33");
		set(MINER_GUN_RELOAD, "1500");
		set(MINER_GUN_DAMAGE, "3");
		set(MINER_GUN_FMJ, "false");
		set(MINER_GUN_LONG_BARREL, "false");
		
		set(SOLDIER_NAME, "Kit &bSoldier");
		set(SOLDIER_LORE, Arrays.asList(
				"&fThis kit",
				"&fis &6aggressive"
		));
		set(SOLDIER_ARMOR, "50");
		set(SOLDIER_GUN_RELOAD, "1000");
		set(SOLDIER_GUN_DAMAGE, "4");
		set(SOLDIER_GUN_FMJ, "false");
		set(SOLDIER_GUN_LONG_BARREL, "false");
		
		set(SNIPER_NAME, "Kit &bsniper");
		set(SNIPER_LORE, Arrays.asList(
				"Ignores 50% of the enemy's bonus armor and",
				"has the long barrel powerup always active"
		));
		set(SNIPER_ARMOR, "33");
		set(SNIPER_GUN_RELOAD, "2500");
		set(SNIPER_GUN_DAMAGE, "9");
		set(SNIPER_GUN_FMJ, "false");
		set(SNIPER_GUN_LONG_BARREL, "true");
		set(SNIPER_ENEMY_BONUS_ARMOR_IGNORED_PERCENTAGE, "50");
		
		set(DEFENDER_NAME, "Kit &bdefender");
		set(DEFENDER_LORE, Arrays.asList(
				"When near his nexus (< 24 blocks) gains these stats:",
				"+25% Armor",
				"+15% Damage",
				"-20% Reload time"
		));
		set(DEFENDER_ARMOR, "50");
		set(DEFENDER_GUN_RELOAD, "1500");
		set(DEFENDER_GUN_DAMAGE, "3");
		set(DEFENDER_GUN_FMJ, "false");
		set(DEFENDER_GUN_LONG_BARREL, "false");
		set(DEFENDER_BONUS_ARMOR_PERCENTAGE, "25");
		set(DEFENDER_BONUS_DAMAGE_PERCENTAGE, "15");
		set(DEFENDER_BONUS_RELOAD_PERCENTAGE, "20");
		set(DEFENDER_MAX_NEXUS_DISTANCE, "24");
	}
}
