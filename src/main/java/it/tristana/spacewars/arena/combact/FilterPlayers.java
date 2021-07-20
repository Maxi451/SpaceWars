package it.tristana.spacewars.arena.combact;

import java.util.function.Predicate;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import it.tristana.commons.interfaces.arena.Arena;
import it.tristana.commons.interfaces.arena.player.TeamingPlayer;

public abstract class FilterPlayers<A extends Arena<P>, P extends TeamingPlayer<?, A>> implements Predicate<Entity> {

	protected A arena;
	protected P arenaPlayer;
	
	public FilterPlayers(A arena, P arenaPlayer) {
		this.arena = arena;
		this.arenaPlayer = arenaPlayer;
	}
	
	@Override
	public boolean test(Entity entity) {
		boolean result = entity instanceof Player;
		if (result) {
			P target = arena.getArenaPlayer((Player) entity);
			result = target != null && arenaPlayer.getTeam() != target.getTeam() && checkAdditionalConditions(target);
		}
		return result;
	}
	
	protected abstract boolean checkAdditionalConditions(P target);
}
