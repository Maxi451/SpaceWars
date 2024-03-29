package it.tristana.spacewars.event;

import org.bukkit.event.HandlerList;

import it.tristana.spacewars.arena.player.SpacePlayer;

public class SpacePlayerDeathEvent extends SpacePlayerEvent {
	
    private static final HandlerList handlers = new HandlerList();

	public SpacePlayerDeathEvent(SpacePlayer player) {
		super(player);
	}
	
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
