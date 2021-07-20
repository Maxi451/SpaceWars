package it.tristana.spacewars.config;

import java.io.File;
import java.util.Arrays;

import it.tristana.commons.config.Config;

public class ConfigUpgrade extends Config {

	private static final String INFO = "info.";
	public static final String UPGRADE_LEVEL = INFO + "level";
	public static final String UPGRADE_COST = INFO + "price";
	public static final String UPGRADE_ALL_TEAM = INFO + "description";

	private static final String NAME = "name";
	private static final String VALUE = "value";
	private static final String MAX_LEVEL = "max-level";
	private static final String COST = "cost";

	private static final String DAMAGE = "damage.";
	public static final String UPGRADE_DAMAGE_NAME = DAMAGE + NAME;
	public static final String UPGRADE_DAMAGE_VALUE = DAMAGE + VALUE;
	public static final String UPGRADE_DAMAGE_MAX_LEVEL = DAMAGE + MAX_LEVEL;
	public static final String UPGRADE_DAMAGE_COST = DAMAGE + COST;

	private static final String DEFENSE = "defense.";
	public static final String UPGRADE_DEFENSE_NAME = DEFENSE + NAME;
	public static final String UPGRADE_DEFENSE_VALUE = DEFENSE + VALUE;
	public static final String UPGRADE_DEFENSE_MAX_LEVEL = DEFENSE + MAX_LEVEL;
	public static final String UPGRADE_DEFENSE_COST = DEFENSE + COST;

	private static final String FIRE_RATIO = "fire-ratio.";
	public static final String UPGRADE_FIRE_RATIO_NAME = FIRE_RATIO + NAME;
	public static final String UPGRADE_FIRE_RATIO_VALUE = FIRE_RATIO + VALUE;
	public static final String UPGRADE_FIRE_RATIO_MAX_LEVEL = FIRE_RATIO + MAX_LEVEL;
	public static final String UPGRADE_FIRE_RATIO_COST = FIRE_RATIO + COST;

	private static final String FUEL = "fuel.";
	public static final String UPGRADE_FUEL_NAME = FUEL + NAME;
	public static final String UPGRADE_FUEL_VALUE = FUEL + VALUE;
	public static final String UPGRADE_FUEL_MAX_LEVEL = FUEL + MAX_LEVEL;
	public static final String UPGRADE_FUEL_COST = FUEL + COST;

	private static final String HEALTH = "health.";
	public static final String UPGRADE_HEALTH_NAME = HEALTH + NAME;
	public static final String UPGRADE_HEALTH_VALUE = HEALTH + VALUE;
	public static final String UPGRADE_HEALTH_MAX_LEVEL = HEALTH + MAX_LEVEL;
	public static final String UPGRADE_HEALTH_COST = HEALTH + COST;

	private static final String OBSIDIAN = "obsidian.";
	public static final String UPGRADE_OBSIDIAN_NAME = OBSIDIAN + NAME;
	public static final String UPGRADE_OBSIDIAN_MAX_LEVEL = OBSIDIAN + MAX_LEVEL;
	public static final String UPGRADE_OBSIDIAN_COST = OBSIDIAN + COST;

	public ConfigUpgrade(File folder) {
		super(new File(folder, "upgrade.yml"));
	}
	
	@Override
	protected void createDefault() {
		set(UPGRADE_LEVEL, "&e&oLevel: &a&o{current level}&f&o/&6&o{max level}");
		set(UPGRADE_COST, "&e&oCost: &6&o{current cost}{coin symbol}");
		set(UPGRADE_ALL_TEAM, Arrays.asList(
				"&a&oBy enhancing this object",
				"&a&othe whole team will benefit"
				));

		set(UPGRADE_DAMAGE_NAME, "+7.5% base damage");
		set(UPGRADE_DAMAGE_VALUE, "7.5");
		set(UPGRADE_DAMAGE_MAX_LEVEL, "4");
		set(UPGRADE_DAMAGE_COST, "450");

		set(UPGRADE_DEFENSE_NAME, "+7.5% damage resistance");
		set(UPGRADE_DEFENSE_VALUE, "7.5");
		set(UPGRADE_DEFENSE_MAX_LEVEL, "4");
		set(UPGRADE_DEFENSE_COST, "450");

		set(UPGRADE_FIRE_RATIO_NAME, "+7.5% fire ratio");
		set(UPGRADE_FIRE_RATIO_VALUE, "7.5");
		set(UPGRADE_FIRE_RATIO_MAX_LEVEL, "4");
		set(UPGRADE_FIRE_RATIO_COST, "450");

		set(UPGRADE_FUEL_NAME, "+2 fuel");
		set(UPGRADE_FUEL_VALUE, "2");
		set(UPGRADE_FUEL_MAX_LEVEL, "-1");
		set(UPGRADE_FUEL_COST, "30");

		set(UPGRADE_HEALTH_NAME, "+4 max HP (2 hearts)");
		set(UPGRADE_HEALTH_VALUE, "4");
		set(UPGRADE_HEALTH_MAX_LEVEL, "4");
		set(UPGRADE_HEALTH_COST, "450");

		set(UPGRADE_OBSIDIAN_NAME, "Repair nexus protection");
		set(UPGRADE_OBSIDIAN_MAX_LEVEL, "-1");
		set(UPGRADE_OBSIDIAN_COST, "1000");
	}
}
