package it.tristana.spacewars.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import it.tristana.commons.interfaces.arena.ArenasManager;
import it.tristana.commons.interfaces.arena.Status;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.arena.player.gun.Gun;

public class ShotListener implements Listener {

	private ArenasManager<SpaceArena, SpacePlayer> arenasManager;
	
	public ShotListener(ArenasManager<SpaceArena, SpacePlayer> arenasManager) {
		this.arenasManager = arenasManager;
	}
	
	@EventHandler
	public void on(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		SpaceArena arena = arenasManager.getArenaWithPlayer(player);
		if (arena == null || arena.getStatus() != Status.PLAYING) {
			return;
		}
		SpacePlayer spacePlayer = arena.getArenaPlayer(player);
		Gun gun = spacePlayer.getKit().getGun();
		if (gun.isThisItem(player.getInventory().getItemInMainHand()) && gun.tryToShoot(spacePlayer)) {
			arena.onShot(spacePlayer);
		}
	}
}
