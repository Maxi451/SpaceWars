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
	
	private double powerupsSphereRadius;
	private double powerupsSphereParticlesDistance;
	private int powerupRechargeTicks;
	
	private String kitChooseGuiName;
	
	public ArenaValues(ConfigTeams configTeams, ConfigArena configArena) {
		this.configTeams = configTeams;
		this.configArena = configArena;
	}
	
	@Override
	public void setup() {
		teamsNames = configTeams.getList(ConfigTeams.TEAMS);
		
		powerupsSphereRadius = parseDoubleOrGetDefault(configArena.getString(ConfigArena.POWERUPS_SPHERE_RADIUS), 2);
		powerupsSphereParticlesDistance = parseDoubleOrGetDefault(configArena.getString(ConfigArena.POWERUPS_SPHERE_PARTICLES_DISTANCE), 0.15);
		powerupRechargeTicks = parseIntOrGetDefault(configArena.getString(ConfigArena.POWERUP_RECHARGE_TICKS), 8);
		
		kitChooseGuiName = configArena.getString(ConfigArena.KIT_CHOOSE_GUI_NAME);
	}
		
	public List<String> getTeamsNames() {
		return teamsNames;
	}
	
	public double getPowerupsSphereRadius() {
		return powerupsSphereRadius;
	}

	public double getPowerupsSphereParticlesDistance() {
		return powerupsSphereParticlesDistance;
	}

	public int getPowerupRechargeTicks() {
		return powerupRechargeTicks;
	}
	
	public String getKitChooseGuiName() {
		return kitChooseGuiName;
	}
}
