package it.tristana.spacewars.arena.team;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;

import it.tristana.commons.helper.CommonsHelper;
import it.tristana.commons.interfaces.Tickable;
import it.tristana.spacewars.helper.ParticlesHelper;

public class Nexus implements Tickable {

	public static final Material NEXUS_MATERIAL = Material.BEACON;
	public static final Material PILLAR_MATERIAL = Material.OBSIDIAN;

	private static final int DISTANCE_PILLAR_NEXUS = 3;
	private static final int DISTANCE_Y_PILLAR_NEXUS = -1;
	
	private SpaceTeam team;
	private Location location;
	private Pillar[] pillars;
	private boolean isBroken;
	
	private Location centeredLocation;
	private Location[] pillarsCenteredLocations;
	
	public Nexus(Location location) {
		this.location = location;
		centeredLocation = CommonsHelper.centerLocation(location).add(0, 0.5, 0);
		pillars = new Pillar[] {
				buildPillar(DISTANCE_PILLAR_NEXUS, 0),
				buildPillar(-DISTANCE_PILLAR_NEXUS, 0),
				buildPillar(0, DISTANCE_PILLAR_NEXUS),
				buildPillar(0, -DISTANCE_PILLAR_NEXUS)
		};
		pillarsCenteredLocations = new Location[pillars.length];
		for (int i = 0; i < pillarsCenteredLocations.length; i ++) {
			pillarsCenteredLocations[i] = CommonsHelper.centerLocation(pillars[i].getLocation()).add(0, 0.5, 0);
		}
	}

	@Override
	public void runTick() {
		if (isBroken) {
			return;
		}
		for (int i = 0; i < pillars.length; i ++) {
			if (pillars[i].isEnabled()) {
				ParticlesHelper.particlesLine(centeredLocation, pillarsCenteredLocations[i], 0.1, Particle.FLAME);
			}
		}
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
		location.getWorld().strikeLightningEffect(location);
		isBroken = true;
		return true;
	}

	public Location getLocation() {
		return location;
	}
	
	public boolean isBroken() {
		return isBroken;
	}
	
	public SpaceTeam getTeam() {
		return team;
	}
	
	void setTeam(SpaceTeam team) {
		this.team = team;
	}
	
	private Pillar buildPillar(int x, int z) {
		return new Pillar(location.clone().add(x, DISTANCE_Y_PILLAR_NEXUS, z));
	}
}
