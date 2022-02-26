package it.tristana.spacewars.event;

import org.bukkit.event.HandlerList;

import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.arena.team.Nexus;
import it.tristana.spacewars.arena.team.Pillar;

public class PillarBrokenEvent extends NexusBrokenEvent {

	private Pillar pillar;
	
	public PillarBrokenEvent(SpacePlayer player, Nexus nexus, Pillar pillar) {
		super(player, nexus);
		this.pillar = pillar;
	}

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

	public Pillar getPillar() {
		return pillar;
	}
    
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
