package it.tristana.spacewars.helper;

import org.bukkit.plugin.Plugin;

import it.tristana.commons.helper.LoginAction;
import it.tristana.commons.helper.PlayersManager;
import it.tristana.commons.interfaces.arena.ArenasManager;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.database.SpaceUser;

public class SpaceLoginAction extends LoginAction<SpaceUser> {

	public SpaceLoginAction(Main plugin) {
		super(plugin, plugin.getArenasManager(), plugin::getMainLobby);
	}

	@Override
	protected PlayersManager getPlayersManager(Plugin plugin, ArenasManager<?> arenasManager) {
		return new SpacePlayersManager(plugin, arenasManager);
	}
}
