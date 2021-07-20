package it.tristana.spacewars.config;

import java.io.File;
import java.util.Arrays;

import it.tristana.commons.config.Config;

public class ConfigItems extends Config {

	private static final String NAME = "name";
	private static final String LORE = "lore";

	private static final String ELYTRA = "elytra.";
	public static final String ELYTRA_NAME = ELYTRA + NAME;
	public static final String ELYTRA_LORE = ELYTRA + LORE;

	private static final String PICKAXE = "pickaxe.";
	public static final String PICKAXE_NAME = PICKAXE + NAME;
	public static final String PICKAXE_LORE = PICKAXE + LORE;

	private static final String FUEL = "fuel.";
	public static final String FUEL_NAME = FUEL + NAME;
	public static final String FUEL_LORE = FUEL + LORE;

	private static final String EMP = "emp.";
	public static final String EMP_NAME = EMP + NAME;
	public static final String EMP_LORE = EMP + LORE;

	private static final String MISSILE = "missile.";
	public static final String MISSILE_NAME = MISSILE + NAME;
	public static final String MISSILE_LORE = MISSILE + LORE;

	public ConfigItems(File folder) {
		super(new File(folder, "items.yml"));
	}

	@Override
	protected void createDefault() {
		set(ELYTRA_NAME, "&a&lHycarus's Wings");
		set(ELYTRA_LORE, Arrays.asList(
				"&e&oFaster than light!"
		));

		set(PICKAXE_NAME, "&b&lThe Drill");
		set(PICKAXE_LORE, Arrays.asList(
				"&e&oCan only mine pillars and nexuses"
		));

		set(FUEL_NAME, "&9&lFuel");
		set(FUEL_LORE, Arrays.asList(
				"&e&oLiquid propane"
		));

		set(EMP_NAME, "&5&lEMP");
		set(EMP_LORE, Arrays.asList(
				"&e&oBlinds near enemies and",
				"&e&oremoves their fuel"
		));

		set(MISSILE_NAME, "&4&lMissile");
		set(MISSILE_LORE, Arrays.asList(
				"&e&oFollows the enemies for a BOOM!"
		));
	}
}
