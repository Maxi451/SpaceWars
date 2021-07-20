package it.tristana.spacewars.arena.combact;

import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.SpacePlayer;

public class SpaceFilterPlayers extends FilterPlayers<SpaceArena, SpacePlayer> {

	public SpaceFilterPlayers(SpaceArena arena, SpacePlayer arenaPlayer) {
		super(arena, arenaPlayer);
	}

	@Override
	protected boolean checkAdditionalConditions(SpacePlayer target) {
		return target.canBeHit() && (arenaPlayer.hasFmj() || arenaPlayer.getPlayer().hasLineOfSight(target.getPlayer()));
	}
}
