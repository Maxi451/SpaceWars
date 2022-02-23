package it.tristana.spacewars.arena;

import org.bukkit.World;
import org.bukkit.entity.Player;

import it.tristana.commons.arena.BasicEnclosedArena;
import it.tristana.commons.interfaces.arena.player.PartiesManager;
import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.arena.player.SpaceTeam;

public class SpaceArena extends BasicEnclosedArena<SpaceTeam, SpacePlayer> {

	public SpaceArena(World world, String name, PartiesManager partiesManager) {
		super(world, name, partiesManager);
	}

	@Override
	protected SpacePlayer createArenaPlayer(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected SpaceTeam createTeam(int index) {
		// TODO Auto-generated method stub
		return null;
	}
}
