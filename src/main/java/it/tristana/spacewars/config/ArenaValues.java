package it.tristana.spacewars.config;

import static it.tristana.spacewars.arena.helper.Utility.parseDoubleOrGetDefault;
import static it.tristana.spacewars.arena.helper.Utility.parseIntOrGetDefault;

import java.util.List;

import it.tristana.commons.config.ConfigTeams;
import it.tristana.spacewars.interfaces.Configurable;

public class ArenaValues implements Configurable {
	
	private ConfigTeams configTeams;
	private ConfigArena configArena;
	
	private List<String> teamsNames;
	
	private double powerupsCircleRadius;
	private int powerupsCircleParticles;
	private int powerupRechargeTicks;
	
	private String kitChooseGuiName;
	
	public ArenaValues(ConfigTeams configTeams, ConfigArena configArena) {
		this.configTeams = configTeams;
		this.configArena = configArena;
	}
	
	@Override
	public void setup() {
		teamsNames = configTeams.getList(ConfigTeams.TEAMS);
		
		powerupsCircleRadius = parseDoubleOrGetDefault(configArena.getString(ConfigArena.POWERUPS_CIRCLE_RADIUS), 3.5);
		powerupsCircleParticles = parseIntOrGetDefault(configArena.getString(ConfigArena.POWERUPS_CIRCLE_PARTICLES), 64);
		powerupRechargeTicks = parseIntOrGetDefault(configArena.getString(ConfigArena.POWERUP_RECHARGE_TICKS), 8);
		
		kitChooseGuiName = configArena.getString(ConfigArena.KIT_CHOOSE_GUI_NAME);
	}
		
	public List<String> getTeamsNames() {
		return teamsNames;
	}

	public double getPowerupsCircleRadius() {
		return powerupsCircleRadius;
	}

	public int getPowerupsCircleParticles() {
		return powerupsCircleParticles;
	}
	
	public int getPowerupRechargeTicks() {
		return powerupRechargeTicks;
	}
	
	public String getKitChooseGuiName() {
		return kitChooseGuiName;
	}
}
