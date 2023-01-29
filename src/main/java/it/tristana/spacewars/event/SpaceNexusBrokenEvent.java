package it.tristana.spacewars.event;

import org.bukkit.event.HandlerList;

import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.arena.team.Nexus;

public class SpaceNexusBrokenEvent extends SpacePlayerEvent {
	
    protected static final HandlerList handlers = new HandlerList();
    
    private Nexus nexus;

	public SpaceNexusBrokenEvent(SpacePlayer player, Nexus nexus) {
		super(player);
		this.nexus = nexus;
	}

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public Nexus getNexus() {
    	return nexus;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
