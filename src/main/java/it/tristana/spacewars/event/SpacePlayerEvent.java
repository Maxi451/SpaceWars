package it.tristana.spacewars.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

import it.tristana.spacewars.arena.player.SpacePlayer;

public abstract class SpacePlayerEvent extends Event implements Cancellable {

	protected SpacePlayer player;
	protected boolean isCancelled;
	
	public SpacePlayerEvent(SpacePlayer player) {
		this.player = player;
	}

	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	@Override
	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}
	
	public SpacePlayer getPlayer() {
		return player;
	}
}
