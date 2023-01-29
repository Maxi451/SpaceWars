package it.tristana.spacewars.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import it.tristana.spacewars.arena.player.kit.Kit;

public class SpaceKitChosenEvent extends PlayerEvent {
	
    private static final HandlerList handlers = new HandlerList();

    private Kit kit;
    
	public SpaceKitChosenEvent(Player player, Kit kit) {
		super(player);
		this.kit = kit;
	}
	
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public Kit getKit() {
    	return kit;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
