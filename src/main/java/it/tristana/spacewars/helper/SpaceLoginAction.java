package it.tristana.spacewars.helper;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import it.tristana.commons.helper.LoginAction;
import it.tristana.commons.helper.PlayersManager;
import it.tristana.commons.interfaces.arena.ArenasManager;
import it.tristana.commons.scoreboard.ScoreboardManager;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.database.SpaceUser;

public class SpaceLoginAction extends LoginAction<SpaceUser> {

	private final PlayersManager playersManager;
	private final ScoreboardManager<SpaceUser> scoreboardManager;

	public SpaceLoginAction(Main plugin, PlayersManager playersManager, ScoreboardManager<SpaceUser> scoreboardManager) {
		super(plugin, plugin.getArenasManager(), plugin::getMainLobby);
		this.playersManager = playersManager;
		this.scoreboardManager = scoreboardManager;
	}

	@Override
	public void accept(PlayerJoinEvent event, SpaceUser user) {
		super.accept(event, user);
		scoreboardManager.addUser(user);
	}

	@Override
	protected PlayersManager getPlayersManager(Plugin plugin, ArenasManager<?, ?> arenasManager) {
		return playersManager;
	}
}
