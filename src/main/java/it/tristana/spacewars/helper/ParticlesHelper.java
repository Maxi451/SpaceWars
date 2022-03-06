package it.tristana.spacewars.helper;

import java.util.List;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
//import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.util.Vector;

import it.tristana.commons.math.RayTrace;

public class ParticlesHelper {
	
	private ParticlesHelper() {}

	public static void particle(Location pos, Color color) {
		particle(pos, Particle.REDSTONE, new Particle.DustOptions(color, 1));
	}

	public static void particle(Location pos, Particle particle) {
		particle(pos, particle, null);
	}

	private static void particle(Location pos, Particle particle, Particle.DustOptions dust) {
		pos.getWorld().spawnParticle(particle, pos.getX(), pos.getY(), pos.getZ(), 1, 0, 0, 0, 0, dust, true);
	}
	
	public static void particlesLineColored(Location pos1, Location pos2, double distanceBetweenParticles, Color color) {
		particlesLineColored(pos1, pos2, distanceBetweenParticles, color, 1);
	}

	public static void particlesLineColored(Location pos1, Location pos2, double distanceBetweenParticles, Color color, int amount) {
		particlesLine(pos1, pos2, distanceBetweenParticles, Particle.REDSTONE, new Particle.DustOptions(color, amount));
	}

	public static void particlesLine(Location pos1, Location pos2, double distanceBetweenParticles, Particle particle) {
		particlesLine(pos1, pos2, distanceBetweenParticles, particle, null);
	}

	private static void particlesLine(Location pos1, Location pos2, double distanceBetweenParticles, Particle particle, Particle.DustOptions dust) {
		List<Vector> vectors = RayTrace.traverse(pos1.toVector(), pos2.toVector(), distanceBetweenParticles);
		World world = pos1.getWorld();
		for (Vector v1 : vectors) {
			world.spawnParticle(particle, v1.getX(), v1.getY(), v1.getZ(), 1, 0, 0, 0, 0, dust, true);
		}
	}
}