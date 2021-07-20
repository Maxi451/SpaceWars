package it.tristana.spacewars.config;

import java.io.File;

import it.tristana.commons.config.Config;

public class ConfigArena extends Config {

	private static final String POWERUPS = "powerups.";
	public static final String POWERUPS_CIRCLE_RADIUS = POWERUPS + "circle-radius";
	public static final String POWERUPS_CIRCLE_PARTICLES = POWERUPS + "circle-particles";
	public static final String POWERUP_RECHARGE_TICKS = POWERUPS + "recharge-ticks";
	
	public static final String KIT_CHOOSE_GUI_NAME = "kit-choose-gui-name";
	
	public ConfigArena(File folder) {
		super(new File(folder, "arena.yml"));
	}
	
	@Override
	public void createDefault() {
		set(POWERUPS_CIRCLE_RADIUS, "5");
		set(POWERUPS_CIRCLE_PARTICLES, "64");
		set(POWERUP_RECHARGE_TICKS, "8");
		
		set(KIT_CHOOSE_GUI_NAME, "&bChoose a kit!");
	}
}
