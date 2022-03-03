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

	public ConfigKits(File folder) {
		super(new File(folder, "kits.yml"));
	}

	@Override
	protected void createDefault() {
		set(MINER_NAME, "Kit &bminer");
		set(MINER_LORE, Arrays.asList(
				"&fThis kit",
				"&fis &6diggy"
		));
		set(MINER_ARMOR, "33");
		set(MINER_GUN_RELOAD, "2000");
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
	}
}
