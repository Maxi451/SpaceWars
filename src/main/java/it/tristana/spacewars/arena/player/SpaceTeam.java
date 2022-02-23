package it.tristana.spacewars.arena.player;

import it.tristana.commons.arena.player.BasicTeam;
import it.tristana.spacewars.arena.SpaceArena;

public class SpaceTeam extends BasicTeam<SpacePlayer, SpaceArena> {

	public SpaceTeam(SpaceArena arena, String name, String colorCode) {
		super(arena, name, colorCode);
	}
}
