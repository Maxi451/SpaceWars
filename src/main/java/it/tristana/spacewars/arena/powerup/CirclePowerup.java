package it.tristana.spacewars.arena.powerup;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.util.Vector;

import it.tristana.commons.interfaces.Tickable;
import it.tristana.commons.math.CachedCircleEuclidean;
import it.tristana.spacewars.interfaces.Drawable;

public class CirclePowerup implements Tickable, Drawable {

	private static final int TICKS_TO_REPICK = 1;
	
	private final World world;
	private final CachedCircleEuclidean circle;
	private final int rechargeTicks;
	private int ticksToRecharge;
	private int ticksToBePicked;
	
	public CirclePowerup(CachedCircleEuclidean circle, int rechargeTicks) {
		this.circle = circle;
		this.rechargeTicks = rechargeTicks;
		world = circle.getCenter().getWorld();
	}
	
	@Override
	public void runTick() {
		if (ticksToRecharge > 0) {
			ticksToRecharge --;
		}
		if (ticksToBePicked > 0) {
			ticksToBePicked --;
		}
		draw();
	}
	
	public boolean canBePicked() {
		return ticksToBePicked == 0;
	}
	
	public boolean isReady() {
		return ticksToRecharge == 0 && canBePicked();
	}
	
	public void onPowerup() {
		if (isReady()) {
			ticksToRecharge = rechargeTicks;
			ticksToBePicked = TICKS_TO_REPICK;
		}
	}
	
	public CachedCircleEuclidean getCircle() {
		return circle;
	}
	
	@Override
	public void draw() {
		double ratio = ticksToRecharge == 0 ? 1 : 1 - ticksToRecharge / (double) rechargeTicks;
		Vector[] points = circle.getPoints();
		for (int i = 0; i < points.length; i ++) {
			double x = points[i].getX();
			double y = points[i].getY();
			double z = points[i].getZ();
			if (ratio == 1) {                 
				particle(world, Particle.VILLAGER_HAPPY, x, y, z, null);
			}
			else if (i / (double) points.length <= ratio) {
				particle(Color.YELLOW, x, y, z);
			}
			else {
				particle(Color.RED, x, y, z);
			}
		}
	}
	
	private void particle(Color color, double x, double y, double z) {
		particle(world, Particle.REDSTONE, x, y, z, new Particle.DustOptions(color, 1));
	}
	
	private static void particle(World world, Particle particle, double x, double y, double z, Particle.DustOptions dust) {
		world.spawnParticle(particle, x, y, z, 1, 0, 0, 0, 0, dust, true);
	}
}
