package it.tristana.spacewars.config;

import java.io.File;

import it.tristana.commons.config.Config;

public class ConfigArena extends Config {

	private static final String POWERUPS = "powerups.";
	public static final String POWERUPS_SPHERE_RADIUS = POWERUPS + "sphere-radius";
	public static final String POWERUPS_SPHERE_PARTICLES_DISTANCE = POWERUPS + "sphere-particles-distance";
	public static final String POWERUP_RECHARGE_TICKS = POWERUPS + "recharge-ticks";
	
	public static final String KIT_CHOOSE_GUI_NAME = "kit-choose-gui-name";
	
	public ConfigArena(File folder) {
		super(new File(folder, "arena.yml"));
	}
	
	@Override
	public void createDefault() {
		set(POWERUPS_SPHERE_RADIUS, "2");
		set(POWERUPS_SPHERE_PARTICLES_DISTANCE, "0.15");
		set(POWERUP_RECHARGE_TICKS, "8");
		
		set(KIT_CHOOSE_GUI_NAME, "&bChoose a kit!");
	}
}
