package it.tristana.spacewars.arena.combact;

import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import it.tristana.commons.helper.ParticlesHelper;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.SpacePlayer;

public class ShotManager {

	private static final double PARTICLES_DISTANCE = 0.3;
	private static final int PARTICLES_PER_SHOT_STEP = 1;
	private static final float PITCH = 48;

	private SpaceArena arena;

	public ShotManager(SpaceArena arena) {
		this.arena = arena;
	}

	public void onShot(SpacePlayer spacePlayer, double maxDistance) {
		Player player = spacePlayer.getPlayer();
		World world = player.getWorld();
		Location eyeLocation = player.getEyeLocation();
		Vector direction = eyeLocation.getDirection();
		world.playSound(eyeLocation, Sound.BLOCK_BEACON_DEACTIVATE, 1, PITCH);
		RayTraceResult rayTraceResult = world.rayTraceEntities(eyeLocation, direction, maxDistance, 1, new SpaceFilterPlayers(arena, spacePlayer));
		Location targetLocation;
		Player target = null;
		Entity targetEntity;
		if (rayTraceResult != null && (targetEntity = rayTraceResult.getHitEntity()) instanceof Player) {
			target = (Player) targetEntity;
		}
		if (target != null) {
			target.damage(spacePlayer.getBaseDamage());
		}
		else if (!spacePlayer.hasFmj()) {
			rayTraceResult = world.rayTraceBlocks(eyeLocation, direction, maxDistance, FluidCollisionMode.NEVER, true);
		}
		if (rayTraceResult == null) {
			targetLocation = eyeLocation.clone().add(direction.clone().normalize().multiply(maxDistance));
		}
		else {
			targetLocation = rayTraceResult.getHitPosition().toLocation(world);
		}
		ParticlesHelper.particlesLineColored(eyeLocation, targetLocation, PARTICLES_DISTANCE, spacePlayer.getTeam().getColor(), PARTICLES_PER_SHOT_STEP);
	}
}
