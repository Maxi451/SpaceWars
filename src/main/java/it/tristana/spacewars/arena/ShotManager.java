package it.tristana.spacewars.arena;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import it.tristana.commons.math.AABB;
import it.tristana.commons.math.RayTrace;
import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.arena.player.gun.Gun;
import it.tristana.spacewars.arena.team.SpaceTeam;
import it.tristana.spacewars.event.SpacePlayerShotEvent;
import it.tristana.spacewars.helper.ParticlesHelper;

public class ShotManager {

	private static final Set<Material> transparentBlocks = new HashSet<Material>();
	static {
		transparentBlocks.add(Material.AIR);
	}
	
	public static void onShot(SpaceArena arena, SpacePlayer shooter) {
		Gun gun = shooter.getKit().getGun();
		double maxDistance = gun.isLongBarrel() ? 75 : 50;
		Player player = shooter.getPlayer();
		Location playerPos = player.getEyeLocation();
		Location maxDistanceLocation = playerPos.clone().add(playerPos.getDirection().multiply(maxDistance));
		if (!gun.isFmj()) {
			Location collisionPoint = RayTrace.firstCollisionPoint(playerPos, maxDistanceLocation, transparentBlocks, 0.05);
			if (collisionPoint != null) {
				maxDistanceLocation = collisionPoint;
				maxDistance = playerPos.distance(maxDistanceLocation);
			}
		}
		SpaceTeam team = shooter.getTeam();
		double minDistance = Double.MAX_VALUE;
		SpacePlayer targetFound = null;
		for (SpacePlayer target : arena.getPlayersWithoutCopy()) {
			if (team == target.getTeam()) {
				continue;
			}
			Player targetPlayer = target.getPlayer();
			boolean isGliding = targetPlayer.isGliding();
			if (RayTrace.canHit(player, targetPlayer, maxDistance, isGliding ? 0.25 : 0, isGliding)) {
				double distance = playerPos.distance(targetPlayer.getEyeLocation());
				if (distance < minDistance) {
					targetFound = target;
					minDistance = distance;
				}
			}
		}
		if (targetFound != null) {
			Player targetPlayer = targetFound.getPlayer();
			arena.onDamage(targetFound, shooter, gun.getDamage(shooter));
			maxDistanceLocation = targetPlayer.isGliding() ? AABB.getElytraPlayerEyesPos(targetPlayer) : targetPlayer.getEyeLocation();
		}
		SpacePlayerShotEvent event = new SpacePlayerShotEvent(shooter, targetFound);
		Bukkit.getPluginManager().callEvent(event);
		if (event.isCancelled()) {
			return;
		}
		ParticlesHelper.particlesLineColored(playerPos, maxDistanceLocation, 0.25, shooter.getTeam().getArmorColor(), 1);
		Sound sound = gun.getSound();
		if (sound != null) {
			arena.getWorld().playSound(player, sound, 1, 32);
		}
	}
}
