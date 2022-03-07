package it.tristana.spacewars.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import it.tristana.commons.interfaces.arena.ArenasManager;
import it.tristana.commons.interfaces.arena.Status;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.config.SettingsArena;

public class PlayerDamageListener implements Listener {

	private ArenasManager<SpaceArena> arenasManager;
	private SettingsArena settings;

	public PlayerDamageListener(ArenasManager<SpaceArena> arenasManager, SettingsArena settings) {
		this.arenasManager = arenasManager;
		this.settings = settings;
	}

	@EventHandler
	public void on(EntityDamageEvent event) {
		Entity entity = event.getEntity();
		if (!(entity instanceof Player)) {
			return;
		}
		Player player = (Player) entity;
		SpaceArena arena = arenasManager.getArenaWithPlayer(player);
		if (arena == null || arena.getStatus() != Status.PLAYING) {
			event.setCancelled(true);
			return;
		}
		DamageCause cause = event.getCause();
		switch (cause) {
		case FLY_INTO_WALL:
		case FALL:
			event.setDamage(event.getDamage() * (1 - settings.getFlyIntoWallDamageReductionPercentage()));
			break;
		case BLOCK_EXPLOSION:
		case ENTITY_EXPLOSION:
		case ENTITY_ATTACK:
			event.setCancelled(true);
			break;
		default:
			break;
		}
	}
}
