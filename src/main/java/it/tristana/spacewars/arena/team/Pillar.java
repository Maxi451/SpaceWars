package it.tristana.spacewars.arena.team;

import org.bukkit.Location;

public class Pillar {

	private Location location;
	private boolean isEnabled;
	
	public Pillar(Location location) {
		this.location = location;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}
	
	public void disable() {
		isEnabled = false;
	}
	
	public void enable() {
		location.getBlock().setType(Nexus.PILLAR_MATERIAL);
		isEnabled = true;
	}
}
