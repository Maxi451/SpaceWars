package it.tristana.spacewars.arena;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import it.tristana.commons.interfaces.Tickable;
import it.tristana.commons.math.CachedSphere;
import it.tristana.commons.math.SphereHelper;
import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.helper.ParticlesHelper;

public class SpherePowerup implements Tickable {

	public static final double RADIUS = 3;
	public static final double RADIUS_SQUARED = RADIUS * RADIUS;
	public static final int SAMPLES = 100;
	public static final int TICKS_TO_RECHARGE = 8 * SpaceArena.TPS;

	private final SpaceArena arena;
	private final CachedSphere sphere;
	private final Vector[] points;
	private final World world;

	private int ticksToRecharge;

	public SpherePowerup(SpaceArena arena, Location center) {
		this.arena = arena;
		this.sphere = SphereHelper.getSphere2(center, RADIUS, SAMPLES);
		this.points = this.sphere.getPoints().toArray(new Vector[0]);
		this.world = center.getWorld();
	}

	@Override
	public void runTick() {
		if (ticksToRecharge > 0) {
			int percentage = (int) ((double) ticksToRecharge / TICKS_TO_RECHARGE * points.length);
			for (int i = 0; i < percentage; i ++) {
				ParticlesHelper.particle(points[i].toLocation(world), Color.RED);
			}

			for (int i = percentage; i < points.length; i ++) {
				ParticlesHelper.particle(points[i].toLocation(world), Color.YELLOW);
			}
		} else {
			for (int i = 0; i < points.length; i ++) {
				ParticlesHelper.particle(points[i].toLocation(world), Particle.VILLAGER_HAPPY);
			}
		}

		if (ticksToRecharge -- > 0) {
			return;
		}

		for (SpacePlayer spacePlayer : arena.getPlayers()) {
			Player player = spacePlayer.getPlayer();
			if (isPlayerInside(player)) {
				ticksToRecharge = TICKS_TO_RECHARGE;
				arena.giveRandomPowerup(spacePlayer);
				Location eyes = player.getEyeLocation();
				double radius = sphere.getRadius();
				Vector eyesVector = eyes.toVector();
				Vector offset = eyesVector.clone().normalize().multiply(radius * 2);
				double distance = distanceLineDot(eyesVector.clone().subtract(offset), eyesVector.clone().add(offset), sphere.getCenter().toVector());
				player.setVelocity(player.getVelocity().multiply(1 + 1d / (radius - distance)));
				break;
			}
		}
	}
	
	public Location getLocation() {
		return sphere.getCenter();
	}

	private boolean isPlayerInside(Player player) {
		return player.getLocation().distanceSquared(sphere.getCenter()) < RADIUS_SQUARED;
	}

	private static double distanceLineDot(Vector min, Vector max, Vector point) {
		Vector norm = max.clone().subtract(min).normalize();
		double dot = point.clone().subtract(min).dot(norm);
		return min.clone().add(norm.multiply(dot)).distance(point);
	}
}
