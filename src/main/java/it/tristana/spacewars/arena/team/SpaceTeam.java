package it.tristana.spacewars.arena.team;

import it.tristana.commons.arena.player.BasicTeam;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.player.SpacePlayer;

public class SpaceTeam extends BasicTeam<SpacePlayer, SpaceArena> {

	private Nexus nexus;
	
	public SpaceTeam(SpaceArena arena, String name, String colorCode, Nexus nexus) {
		super(arena, name, colorCode);
		this.nexus = nexus;
	}
	
	public Nexus getNexus() {
		return nexus;
	}
}
