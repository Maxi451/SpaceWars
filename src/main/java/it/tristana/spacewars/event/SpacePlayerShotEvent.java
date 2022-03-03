package it.tristana.spacewars.event;

import org.bukkit.event.HandlerList;

import it.tristana.spacewars.arena.Shootable;
import it.tristana.spacewars.arena.player.SpacePlayer;

public class SpacePlayerShotEvent extends SpacePlayerEvent {
	
    private static final HandlerList handlers = new HandlerList();

    private final Shootable target;
    
	public SpacePlayerShotEvent(SpacePlayer player, Shootable target) {
		super(player);
		this.target = target;
	}
	
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public Shootable getTarget() {
    	return target;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
