package it.tristana.spacewars.arena.player;

import it.tristana.commons.arena.player.BasicArenaPlayer;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.player.kit.Kit;
import it.tristana.spacewars.arena.team.SpaceTeam;
import it.tristana.spacewars.database.SpaceUser;

public class SpacePlayer extends BasicArenaPlayer<SpaceTeam, SpaceArena> {

	private SpaceUser user;
	private Kit kit;
	
	public SpacePlayer(SpaceArena arena, SpaceUser user) {
		super(arena, user.getPlayer());
		this.user = user;
	}
	
	public SpaceUser getUser() {
		return user;
	}
	
	public void setKit(Kit kit) {
		this.kit = kit;
	}
	
	public Kit getKit() {
		return kit;
	}
}
