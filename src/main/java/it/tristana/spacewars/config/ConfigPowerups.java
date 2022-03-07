package it.tristana.spacewars.config;

import java.io.File;

import it.tristana.commons.config.Config;

public class ConfigPowerups extends Config {

	private static final String NAME = "name";
	private static final String CHANCE = "chance";
	private static final String AMOUNT = "amount";
	
	private static final String _1_UP = "1up.";
	public static final String _1_UP_NAME = _1_UP + NAME;
	public static final String _1_UP_CHANCE = _1_UP + CHANCE;
	
	private static final String LONG_BARREL = "long-barrel.";
	public static final String LONG_BARREL_NAME = LONG_BARREL + NAME;
	public static final String LONG_BARREL_CHANCE = LONG_BARREL + CHANCE;
	
	private static final String MEDIC_KIT = "medic-kit.";
	public static final String MEDIC_KIT_NAME = MEDIC_KIT + NAME;
	public static final String MEDIC_KIT_CHANCE = MEDIC_KIT + CHANCE;
	
	private static final String FMJ = "fmj.";
	public static final String FMJ_NAME = FMJ + NAME;
	public static final String FMJ_CHANCE = FMJ + CHANCE;
	
	private static final String MONEY = "money.";
	public static final String MONEY_NAME = MONEY + NAME;
	public static final String MONEY_CHANCE = MONEY + CHANCE;
	public static final String MONEY_AMOUNT = MONEY + AMOUNT;
	
	private static final String FUEL = "fuel.";
	public static final String FUEL_NAME = FUEL + NAME;
	public static final String FUEL_CHANCE = FUEL + CHANCE;
	public static final String FUEL_AMOUNT = FUEL + AMOUNT;
	
	private static final String BETTER_PICKAXES = "better-pickaxes.";
	public static final String BETTER_PICKAXES_NAME = BETTER_PICKAXES + NAME;
	public static final String BETTER_PICKAXES_CHANCE = BETTER_PICKAXES + CHANCE;
	
	public ConfigPowerups(File folder) {
		super(new File(folder, "powerups.yml"));
	}

	@Override
	protected void createDefault() {
		set(_1_UP_NAME, "1 UP");
		set(_1_UP_CHANCE, "10");
		
		set(LONG_BARREL_NAME, "Long barrel");
		set(LONG_BARREL_CHANCE, "20");
		
		set(MEDIC_KIT_NAME, "Medic kit");
		set(MEDIC_KIT_CHANCE, "20");
		
		set(FMJ_NAME, "FMJ");
		set(FMJ_CHANCE, "15");
		
		set(MONEY_NAME, "+100 Money");
		set(MONEY_CHANCE, "10");
		set(MONEY_AMOUNT, "100");
		
		set(FUEL_NAME, "Fuel");
		set(FUEL_CHANCE, "20");
		set(FUEL_AMOUNT, "3");
		
		set(BETTER_PICKAXES_NAME, "Better pickaxes");
		set(BETTER_PICKAXES_CHANCE, "15");
	}
}
