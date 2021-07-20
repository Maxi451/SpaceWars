package it.tristana.spacewars.arena.helper;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import it.tristana.commons.math.AABB;
import it.tristana.commons.math.Ray;
import it.tristana.commons.math.RayTrace;
import it.tristana.commons.math.TargetResult;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.SpacePlayer;
import it.tristana.spacewars.arena.SpaceTeam;

public class Target {
	
	public static TargetResult getTargetPlayer(SpaceArena arena, Player player, double max) {
		List<Player> possible = new ArrayList<Player>();
		SpacePlayer arenaPlayer =  arena.getArenaPlayer(player);
		boolean hasFMJ = arenaPlayer.hasFmj();
		SpaceTeam team = arenaPlayer.getTeam();
		for (Entity entity : player.getNearbyEntities(max, max, max)) {
			if (entity instanceof Player) {
				Player target = (Player) entity;
				SpacePlayer targetPlayer = arena.getArenaPlayer(target);
				if (targetPlayer != null && targetPlayer.canBeHit() && team != targetPlayer.getTeam() && (player.hasLineOfSight(target) || hasFMJ) && RayTrace.canHit(player, target, max)) {
					possible.add(target);
				}
			}
		}
		Ray ray = Ray.from(player);
		Ray ray2 = Ray.from2(player);
		double d = -1;
		Player closest = null;
		for (Player player1 : possible) {
			if (!arena.hasPlayer(player1) || arena.areInSameTeam(player, player1)) {
				continue;
			}
			double dis = AABB.from(player1).collidesD(ray, 0, max);
			if (dis != -1) {
				if (dis < d || d == -1) {
					d = dis;
					closest = player1;
				}
			}
			double dis2 = AABB.from(player1).collidesD(ray2, 0, max);
			if (dis2 != -1) {
				if (dis2 < d || d == -1) {
					d = dis;
					closest = player1;
				}
			}
		}
		return new TargetResult(closest, d);
	}
}
