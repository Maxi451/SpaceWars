package it.tristana.spacewars.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import it.tristana.commons.interfaces.arena.ArenasManager;
import it.tristana.commons.interfaces.arena.Status;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.SpaceArena;

public class BlockListener implements Listener {

	private ArenasManager<SpaceArena> arenasManager;
	
	public BlockListener(ArenasManager<SpaceArena> arenasManager) {
		this.arenasManager = arenasManager;
	}
	
	@EventHandler
	public void on(BlockBreakEvent event) {
		Player player = event.getPlayer();
		SpaceArena arena = arenasManager.getArenaWithPlayer(player);
		if (arena == null) {
			if (!player.hasPermission(Main.ADMIN_PERMS)) {
				event.setCancelled(true);
			}
			return;
		}
		if (arena.getStatus() != Status.PLAYING || !arena.onBlockBroken(player, event.getBlock())) {
			event.setCancelled(true);
		}
	}
}
