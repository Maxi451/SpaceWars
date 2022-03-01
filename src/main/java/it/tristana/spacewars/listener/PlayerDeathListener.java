package it.tristana.spacewars.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import it.tristana.commons.interfaces.arena.ArenasManager;
import it.tristana.spacewars.arena.SpaceArena;

public class PlayerDeathListener implements Listener {

	private ArenasManager<SpaceArena> arenasManager;
	
	public PlayerDeathListener(ArenasManager<SpaceArena> arenasManager) {
		this.arenasManager = arenasManager;
	}
	
	@EventHandler
	public void on(EntityDamageEvent event) {
		Entity entity = event.getEntity();
		if (!(entity instanceof Player)) {
			return;
		}
		Player player = (Player) entity;
		SpaceArena arena = arenasManager.getArenaWithPlayer(player);
		if (arena == null) {
			event.setCancelled(true);
			return;
		}
		if (player.getHealth() - event.getDamage() <= 0) {
			event.setCancelled(true);
			arena.onPlayerDeath(arena.getArenaPlayer(player));
		}
	}
}
