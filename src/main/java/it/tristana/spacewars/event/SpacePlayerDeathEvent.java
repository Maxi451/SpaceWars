package it.tristana.spacewars.event;

import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import it.tristana.spacewars.arena.player.SpacePlayer;

public class SpacePlayerDeathEvent extends PlayerEvent {
	
    private static final HandlerList handlers = new HandlerList();

	public SpacePlayerDeathEvent(SpacePlayer spacePlayer) {
		super(spacePlayer.getPlayer());
	}
	
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
