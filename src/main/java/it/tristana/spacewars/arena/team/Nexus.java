package it.tristana.spacewars.arena.team;

import org.bukkit.Location;
import org.bukkit.Material;

import it.tristana.commons.helper.CommonsHelper;

public class Nexus {

	public static final Material NEXUS_MATERIAL = Material.BEACON;
	public static final Material PILLAR_MATERIAL = Material.OBSIDIAN;

	private static final int DISTANCE_PILLAR_NEXUS = 2;
	private static final int DISTANCE_Y_PILLAR_NEXUS = 1;
	
	private Location location;
	private Pillar[] pillars;
	private boolean isBroken;
	
	public Nexus(Location location) {
		this.location = location;
		pillars = new Pillar[] {
				buildPillar(DISTANCE_PILLAR_NEXUS, 0),
				buildPillar(-DISTANCE_PILLAR_NEXUS, 0),
				buildPillar(0, DISTANCE_PILLAR_NEXUS),
				buildPillar(0, -DISTANCE_PILLAR_NEXUS)
		};
	}
	
	public void build() {
		location.getBlock().setType(NEXUS_MATERIAL);
		for (int i = 0; i < pillars.length; i ++) {
			pillars[i].enable();
		}
	}

	public boolean breakPillar(Location location) {
		for (int i = 0; i < pillars.length; i ++) {
			if (CommonsHelper.samePosInt(pillars[i].getLocation(), location)) {
				boolean canBreak = pillars[i].isEnabled();
				if (canBreak) {
					pillars[i].disable();
				}
				return canBreak;
			}
		}
		return false;
	}
	
	public boolean breakNexus() {
		for (int i = 0; i < pillars.length; i ++) {
			if (pillars[i].isEnabled()) {
				return false;
			}
		}
		isBroken = true;
		return true;
	}

	public Location getLocation() {
		return location;
	}
	
	public boolean isBroken() {
		return isBroken;
	}
	
	private Pillar buildPillar(int x, int z) {
		return new Pillar(location.clone().add(x, -DISTANCE_Y_PILLAR_NEXUS, z));
	}
}
