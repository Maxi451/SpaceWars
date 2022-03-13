package it.tristana.spacewars.arena;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import it.tristana.commons.interfaces.Tickable;
import it.tristana.commons.math.CachedCircleEuclidean;
import it.tristana.commons.math.CircleEuclideanHelper;
import it.tristana.commons.math.Ray;
import it.tristana.spacewars.helper.ParticlesHelper;

public class CirclePowerup implements Tickable {
	
	public static final double DIAMETER = 5;

	private static final int TICKS_TO_RECHARGE = 8;
	private static final CircleEuclideanHelper circleEuclidean = new CircleEuclideanHelper(DIAMETER, 64);
	
	private final Location location;
	private final CachedCircleEuclidean circle;
	private final double rotation;
	
	private int ticksToRecharge;
	
	public CirclePowerup(Location location, double rotation) {
		this.location = location;
		circle = circleEuclidean.getParticlesLocations(location, rotation);
		this.rotation = rotation;
	}

	@Override
	public void runTick() {
		Vector[] points = circle.getPoints();
		if (ticksToRecharge <= 0) {
			for (int i = 0; i < points.length; i ++) {
				ParticlesHelper.particle(points[i].toLocation(location.getWorld()), Particle.VILLAGER_HAPPY);
			}
			return;
		}
		int percentage = (int) ((double) ticksToRecharge / TICKS_TO_RECHARGE * points.length);
		for (int i = 0; i < percentage; i ++) {
			ParticlesHelper.particle(points[i].toLocation(location.getWorld()), Color.RED);
		}
		for (int i = percentage; i < points.length; i ++) {
			ParticlesHelper.particle(points[i].toLocation(location.getWorld()), Color.YELLOW);
		}
		if (ticksToRecharge > 0) {
			ticksToRecharge --;
		}
	}
	
	public Location getLocation() {
		return location;
	}
	
	public double getRotation() {
		return rotation;
	}
	
	public boolean tryToPickUp() {
		boolean flag = ticksToRecharge == 0;
		if (flag) {
			ticksToRecharge = TICKS_TO_RECHARGE;
		}
		return flag;
	}
	
	public boolean intersects(Player player, double offset) {
		return circle.intersects(player, offset, player.isGliding());
	}
	
	public Ray[] getRays() {
		return circle.getRays();
	}
}
