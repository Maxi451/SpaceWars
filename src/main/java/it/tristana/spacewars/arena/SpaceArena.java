package it.tristana.spacewars.arena;

import org.bukkit.World;
import org.bukkit.entity.Player;

import it.tristana.commons.arena.BasicEnclosedArena;
import it.tristana.commons.interfaces.arena.Status;
import it.tristana.commons.interfaces.arena.player.PartiesManager;
import it.tristana.commons.interfaces.database.UsersManager;
import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.arena.team.SpaceTeam;
import it.tristana.spacewars.database.SpaceUser;

public class SpaceArena extends BasicEnclosedArena<SpaceTeam, SpacePlayer> {

	private UsersManager<SpaceUser> usersManager;
	
	public SpaceArena(World world, String name, PartiesManager partiesManager, UsersManager<SpaceUser> usersManager) {
		super(world, name, partiesManager);
		this.usersManager = usersManager;
	}
	
	@Override
	public void setStatus(Status status) {
		super.setStatus(status);
	}
	
	@Override
	protected SpacePlayer createArenaPlayer(Player player) {
		return new SpacePlayer(this, usersManager.getUser(player));
	}

	@Override
	protected SpaceTeam createTeam(int index) {
		return new SpaceTeam(this, "Team #" + index, "&c", null);
	}
}
