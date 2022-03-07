package it.tristana.spacewars.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import it.tristana.commons.interfaces.arena.ArenasManager;
import it.tristana.commons.interfaces.arena.Status;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.player.SpacePlayer;

public class BlockListener implements Listener {

	private ArenasManager<SpaceArena> arenasManager;
	
	public BlockListener(ArenasManager<SpaceArena> arenasManager) {
		this.arenasManager = arenasManager;
	}
	
	@EventHandler
	public void on(BlockBreakEvent event) {
		Player player = event.getPlayer();
		SpaceArena arena = arenasManager.getArenaInWorld(player.getWorld());
		if (arena == null) {
			if (!player.hasPermission(Main.ADMIN_PERMS)) {
				event.setCancelled(true);
			}
			return;
		}
		if (arena.getStatus() != Status.PLAYING) {
			return;
		}
		event.setCancelled(true);
		SpacePlayer spacePlayer = arena.getArenaPlayer(player);
		if (spacePlayer == null) {
			return;
		}
		Block block = event.getBlock();
		if (arena.onBlockBroken(spacePlayer, block)) {
			block.setType(Material.AIR);
		}
	}
}
