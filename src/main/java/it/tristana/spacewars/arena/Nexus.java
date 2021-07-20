package it.tristana.spacewars.arena;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;

import it.tristana.commons.helper.ParticlesHelper;
import it.tristana.spacewars.interfaces.Drawable;

public class Nexus implements Drawable {

	public static final int BROKEN = 0;
	public static final int NORTH = 1;
	public static final int EAST = 2;
	public static final int SOUTH = 4;
	public static final int WEST = 8;
	
	private static final int PILLAR_Y_DISTANCE = 1;
	private static final int PILLAR_DISTANCE = 3;
	private static final double PARTICLES_DISTANCE = 0.2;
	
	private Location location;
	
	private int status;
	private boolean isBroken;
	
	public Nexus(Location location) {
		this.location = new Location(location.getWorld(), location.getBlockX() + 0.5, location.getBlockY() + 0.5, location.getBlockZ() + 0.5);
	}
	
	public void build() {
		World world = location.getWorld();
		int x = location.getBlockX();
		int y = location.getBlockY();
		int z = location.getBlockZ();
		world.getBlockAt(x, y, z).setType(Material.BEACON);
		getPillar(NORTH).setType(Material.OBSIDIAN);
		getPillar(EAST).setType(Material.OBSIDIAN);
		getPillar(SOUTH).setType(Material.OBSIDIAN);
		getPillar(WEST).setType(Material.OBSIDIAN);
		status = NORTH | EAST | SOUTH | WEST;
		isBroken = false;
	}
	
	private Block getPillar(int direction) {
		Block block;
		World world = location.getWorld();
		int x = location.getBlockX();
		int y = location.getBlockY();
		int z = location.getBlockZ();
		switch (direction) {
		case NORTH:
			block = world.getBlockAt(x, y - PILLAR_Y_DISTANCE, z - PILLAR_DISTANCE);
			break;
		case EAST:
			block = world.getBlockAt(x + PILLAR_DISTANCE, y - PILLAR_Y_DISTANCE, z);
			break;
		case SOUTH:
			block = world.getBlockAt(x, y - PILLAR_Y_DISTANCE, z + PILLAR_DISTANCE);
			break;
		case WEST:
			block = world.getBlockAt(x - PILLAR_DISTANCE, y - PILLAR_Y_DISTANCE, z);
			break;
		default:
			throw new IllegalArgumentException(String.format("Direction value must be NORTH (%d), EAST (%d), SOUTH (%d) or WEST (%d), but here arrived %d", NORTH, EAST, SOUTH, WEST, direction));
		}
		return block;
	}
	
	public boolean isBroken() {
		return isBroken;
	}
	
	public void setBroken() {
		isBroken = true;
	}
	
	public boolean canBeBroken() {
		return status == BROKEN;
	}
	
	public void breakPillar(int pillar) {
		if (checkPillar(pillar)) {
			status -= pillar;
		}
	}
	
	private boolean checkPillar(int pillar) {
		return (status & pillar) == pillar;
	}
	
	public int getStatus() {
		return status;
	}
	
	public Location getLocation() {
		return location;
	}
	
	@Override
	public void draw() {
		if (!canBeBroken()) {
			drawIfNeeded(NORTH);
			drawIfNeeded(EAST);
			drawIfNeeded(SOUTH);
			drawIfNeeded(WEST);
		}
	}
	
	private void drawIfNeeded(int pillar) {
		if (checkPillar(pillar)) {
			ParticlesHelper.particlesLine(location, getPillar(pillar).getLocation().add(0.5, 0.5, 0.5), PARTICLES_DISTANCE, Particle.FLAME);
		}
	}
}
