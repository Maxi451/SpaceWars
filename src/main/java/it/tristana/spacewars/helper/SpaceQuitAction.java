package it.tristana.spacewars.helper;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import it.tristana.commons.helper.PlayersManager;
import it.tristana.commons.helper.QuitAction;
import it.tristana.commons.interfaces.arena.Arena;
import it.tristana.commons.interfaces.arena.ArenasManager;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.database.SpaceUser;

public class SpaceQuitAction extends QuitAction<SpaceUser> {

	private PlayersManager playersManager;

	public SpaceQuitAction(Main plugin, PlayersManager playersManager) {
		super(plugin, plugin.getArenasManager(), plugin::getMainLobby);
		this.playersManager = playersManager;
	}

	@Override
	public void accept(PlayerQuitEvent event, SpaceUser user) {
		Player player = event.getPlayer();
		Arena<?> arena = arenasManager.getArenaWithPlayer(player);
		if (arena != null) {
			arena.onPlayerLeave(player);
		}
		super.accept(event, user);
	}

	@Override
	protected PlayersManager getPlayersManager(Plugin plugin, ArenasManager<?, ?> arenasManager) {
		return playersManager;
	}
}
