package it.tristana.spacewars.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import it.tristana.commons.helper.CommonsHelper;
import it.tristana.commons.interfaces.arena.ArenasManager;
import it.tristana.commons.interfaces.arena.Status;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.arena.team.Nexus;
import it.tristana.spacewars.arena.team.SpaceTeam;

public class BlockListener implements Listener {

	private ArenasManager<SpaceArena, SpacePlayer> arenasManager;

	public BlockListener(ArenasManager<SpaceArena, SpacePlayer> arenasManager) {
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

		SpaceTeam playerTeam = spacePlayer.getTeam();
		Block block = event.getBlock();
		Material type = block.getType();
		Location blockPos = block.getLocation();

		for (SpaceTeam team : arena.getTeams()) {
			if (team == playerTeam) {
				continue;
			}

			Nexus nexus = team.getNexus();
			if (type == Nexus.NEXUS_MATERIAL && CommonsHelper.samePosInt(blockPos, blockPos) && nexus.breakNexus()) {
				block.setType(Material.AIR);
				block.getWorld().strikeLightningEffect(blockPos);
				arena.onNexusBroken(spacePlayer, nexus);
				return;
			}

			if (nexus.breakPillar(blockPos)) {
				block.setType(Material.AIR);
				arena.onPillarBroken(spacePlayer, nexus);
			}
		}
	}
}
