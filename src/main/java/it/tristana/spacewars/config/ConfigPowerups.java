package it.tristana.spacewars.config;

import java.io.File;

import it.tristana.commons.config.Config;

public class ConfigPowerups extends Config {

	/**
	 *  Messages from Powerup1UP
	 */
	
	private static final String POWERUP_1UP_PATH = "Powerup-1UP.";
	public static final String POWERUP_1UP_NAME = POWERUP_1UP_PATH + "Powerup-1UP-name";
	public static final String POWERUP_1UP_SHORT_NAME = POWERUP_1UP_PATH + "Powerup-1UP-short-name";
	public static final String POWERUP_1UP_SPAWN_CHANCE = POWERUP_1UP_PATH + "Powerup-1UP-spawn-chance";

	/**
	 *  Messages from PowerupBetterPickaxe
	 */

	private static final String POWERUP_PICKAXE_PATH = "Powerup-pickaxe.";
	public static final String POWERUP_PICKAXE_NAME = POWERUP_PICKAXE_PATH + "Powerup-upgraded-pickaxe-name";
	public static final String POWERUP_PICKAXE_SHORT_NAME = POWERUP_PICKAXE_PATH + "Powerup-upgraded-pickaxe-short-name";
	public static final String POWERUP_PICKAXE_SPAWN_CHANCE = POWERUP_PICKAXE_PATH + "Powerup-pickaxe-spawn-chance";

	/**
	 *  Messages from PowerupEMP
	 */

	private static final String POWERUP_EMP_PATH = "EMP.";
	public static final String POWERUP_EMP_NAME = POWERUP_EMP_PATH + "Powerup-EMP-name";
	public static final String POWERUP_EMP_SHORT_NAME = POWERUP_EMP_PATH + "Powerup-EMP-short-name";
	public static final String POWERUP_EMP_SPAWN_CHANCE = POWERUP_EMP_PATH + "Powerup-EMP-spawn-chance";
	
	/**
	 *  Messages from PowerupFMJ
	 */

	private static final String POWERUP_FMJ_PATH = "Powerup-FMJ.";
	public static final String POWERUP_FMJ_NAME = POWERUP_FMJ_PATH + "Powerup-FMJ-name";
	public static final String POWERUP_FMJ_SHORT_NAME = POWERUP_FMJ_PATH + "Powerup-FMJ-short-name";
	public static final String POWERUP_FMJ_SPAWN_CHANCE = POWERUP_FMJ_PATH + "Powerup-FMJ-spawn-chance";

	/**
	 *  Messages from PowerupFuel
	 */

	private static final String POWERUP_FUEL_PATH = "Powerup-fuel.";
	public static final String POWERUP_FUEL_NAME = POWERUP_FUEL_PATH + "Powerup-fuel-name";
	public static final String POWERUP_FUEL_SHORT_NAME = POWERUP_FUEL_PATH + "Powerup-fuel-short-name";
	public static final String POWERUP_FUEL_SPAWN_CHANCE = POWERUP_FUEL_PATH + "Powerup-fuel-spawn-chance";

	/**
	 *  Messages from PowerupLongShot
	 */

	private static final String POWERUP_LONG_SHOT_PATH = "Powerup-long-barrel.";
	public static final String POWERUP_LONG_SHOT_NAME = POWERUP_LONG_SHOT_PATH + "Powerup-long-barrel-name";
	public static final String POWERUP_LONG_SHOT_SHORT_NAME = POWERUP_LONG_SHOT_PATH + "Powerup-long-barrel-short-name";
	public static final String POWERUP_LONG_SHOT_SPAWN_CHANCE = POWERUP_LONG_SHOT_PATH + "Powerup-long-barrel-spawn-chance";
	
	/**
	 *  Messages from PowerupMedicKit
	 */

	private static final String POWERUP_MEDIC_KIT_PATH = "Powerup-medic-kit.";
	public static final String POWERUP_MEDIC_KIT_NAME = POWERUP_MEDIC_KIT_PATH + "Powerup-medic-kit-name";
	public static final String POWERUP_MEDIC_KIT_SHORT_NAME = POWERUP_MEDIC_KIT_PATH + "Powerup-medic-kit-short-name";
	public static final String POWERUP_MEDIC_KIT_SPAWN_CHANCE = POWERUP_MEDIC_KIT_PATH + "Powerup-medic-kit-spawn-chance";

	/**
	 *  Messages from PowerupOvergrowth
	 */

	private static final String POWERUP_OVERGROWTH_PATH = "Powerup-overgrowth.";
	public static final String POWERUP_OVERGROWTH_NAME = POWERUP_OVERGROWTH_PATH + "Powerup-overgrowth-name";
	public static final String POWERUP_OVERGROWTH_SHORT_NAME = POWERUP_OVERGROWTH_PATH + "Powerup-overgrowth-short-name";
	public static final String POWERUP_OVERGROWTH_SPAWN_CHANCE = POWERUP_OVERGROWTH_PATH + "Powerup-overgrowth-spawn-chance";

	/**
	 *  Messages from PowerupShield
	 */

	private static final String POWERUP_SHIELD_PATH = "Powerup-shield.";
	public static final String POWERUP_SHIELD_NAME = POWERUP_SHIELD_PATH + "Powerup-shield-name";
	public static final String POWERUP_SHIELD_SHORT_NAME = POWERUP_SHIELD_PATH + "Powerup-shield-short-name";
	public static final String POWERUP_SHIELD_SPAWN_CHANCE = POWERUP_SHIELD_PATH + "Powerup-shield-spawn-chance";
	
	/**
	 *  Messages from PowerupStealth
	 */

	private static final String POWERUP_STEALTH_PATH = "Powerup-stealth.";
	public static final String POWERUP_STEALTH_NAME = POWERUP_STEALTH_PATH + "Powerup-stealth-name";
	public static final String POWERUP_STEALTH_SHORT_NAME = POWERUP_STEALTH_PATH + "Powerup-stealth-short-name";
	public static final String POWERUP_STEALTH_SPAWN_CHANCE = POWERUP_STEALTH_PATH + "Powerup-stealth-spawn-chance";

	/**
	 *  Messages from PowerupBlindness
	 */

	private static final String POWERUP_BLINDNESS_PATH = "Powerup-blindness.";
	public static final String POWERUP_BLINDNESS_NAME = POWERUP_BLINDNESS_PATH + "Powerup-blindness-name";
	public static final String POWERUP_BLINDNESS_SHORT_NAME = POWERUP_BLINDNESS_PATH + "Powerup-blindness-short-name";
	public static final String POWERUP_BLINDNESS_SPAWN_CHANCE = POWERUP_BLINDNESS_PATH + "Powerup-blindness-spawn-chance";
	
	/**
	 *  Messages from PowerupIgnite
	 */

	private static final String POWERUP_IGNITE_PATH = "Powerup-ignite.";
	public static final String POWERUP_IGNITE_NAME = POWERUP_IGNITE_PATH + "Powerup-ignite-name";
	public static final String POWERUP_IGNITE_SHORT_NAME = POWERUP_IGNITE_PATH + "Powerup-ignite-short-name";
	public static final String POWERUP_IGNITE_SPAWN_CHANCE = POWERUP_IGNITE_PATH + "Powerup-ignite-spawn-chance";
	
	/**
	 *  Messages from PowerupIncomingMissile
	 */

	private static final String POWERUP_INCOMING_MISSILE_PATH = "Powerup-incoming-missile.";
	public static final String POWERUP_INCOMING_MISSILE_NAME = POWERUP_INCOMING_MISSILE_PATH + "Powerup-incoming-missile-name";
	public static final String POWERUP_INCOMING_MISSILE_SHORT_NAME = POWERUP_INCOMING_MISSILE_PATH + "Powerup-incoming-missile-short-name";
	public static final String POWERUP_INCOMING_MISSILE_SPAWN_CHANCE = POWERUP_INCOMING_MISSILE_PATH + "Powerup-incoming-missile-spawn-chance";
	
	/**
	 *  Messages from PowerupTeleport
	 */

	private static final String POWERUP_TELEPORT_PATH = "Powerup-teleport.";
	public static final String POWERUP_TELEPORT_NAME = POWERUP_TELEPORT_PATH + "Powerup-teleport-name";
	public static final String POWERUP_TELEPORT_SHORT_NAME = POWERUP_TELEPORT_PATH + "Powerup-teleport-short-name";
	public static final String POWERUP_TELEPORT_SPAWN_CHANCE = POWERUP_TELEPORT_PATH + "Powerup-teleport-spawn-chance";
	
	public ConfigPowerups(File folder) {
		super(new File(folder, "powerups.yml"));
	}
	
	@Override
	protected void createDefault() {
		set(POWERUP_1UP_NAME, "1UP");
		set(POWERUP_1UP_SHORT_NAME, "1UP");
		set(POWERUP_1UP_SPAWN_CHANCE, "8");
		
		set(POWERUP_PICKAXE_NAME, "Better drill");
		set(POWERUP_PICKAXE_SHORT_NAME, "Drill");
		set(POWERUP_PICKAXE_SPAWN_CHANCE, "11");
		
		set(POWERUP_EMP_NAME, "EMP");
		set(POWERUP_EMP_SHORT_NAME, "EMP");
		set(POWERUP_EMP_SPAWN_CHANCE, "5");
		
		set(POWERUP_FMJ_NAME, "FMJ bullets");
		set(POWERUP_FMJ_SHORT_NAME, "FMJ");
		set(POWERUP_FMJ_SPAWN_CHANCE, "12");
		
		set(POWERUP_FUEL_NAME, "Fuel");
		set(POWERUP_FUEL_SHORT_NAME, "Fuel");
		set(POWERUP_FUEL_SPAWN_CHANCE, "12");
		
		set(POWERUP_LONG_SHOT_NAME, "Long barrel");
		set(POWERUP_LONG_SHOT_SHORT_NAME, "Barrel");
		set(POWERUP_LONG_SHOT_SPAWN_CHANCE, "12");
		
		set(POWERUP_MEDIC_KIT_NAME, "Medic kit");
		set(POWERUP_MEDIC_KIT_SHORT_NAME, "Medic");
		set(POWERUP_MEDIC_KIT_SPAWN_CHANCE, "15");
		
		set(POWERUP_OVERGROWTH_NAME, "Overgrowth");
		set(POWERUP_OVERGROWTH_SHORT_NAME, "Overgrowth");
		set(POWERUP_OVERGROWTH_SPAWN_CHANCE, "11");
		
		set(POWERUP_SHIELD_NAME, "Shield");
		set(POWERUP_SHIELD_SHORT_NAME, "Shield");
		set(POWERUP_SHIELD_SPAWN_CHANCE, "14");
		
		set(POWERUP_STEALTH_NAME, "Stealth");
		set(POWERUP_STEALTH_SHORT_NAME, "Stealth");
		set(POWERUP_STEALTH_SPAWN_CHANCE, "5");
		
		set(POWERUP_BLINDNESS_NAME, "Blindness");
		set(POWERUP_BLINDNESS_SHORT_NAME, "Blindness");
		set(POWERUP_BLINDNESS_SPAWN_CHANCE, "30");

		set(POWERUP_IGNITE_NAME, "Ignite");
		set(POWERUP_IGNITE_SHORT_NAME, "Ignite");
		set(POWERUP_IGNITE_SPAWN_CHANCE, "30");
		
		set(POWERUP_INCOMING_MISSILE_NAME, "Incoming missile");
		set(POWERUP_INCOMING_MISSILE_SHORT_NAME, "Incoming");
		set(POWERUP_INCOMING_MISSILE_SPAWN_CHANCE, "25");
		
		set(POWERUP_TELEPORT_NAME, "Teleport");
		set(POWERUP_TELEPORT_SHORT_NAME, "Teleport");
		set(POWERUP_TELEPORT_SPAWN_CHANCE, "15");
	}
}
