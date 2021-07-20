package it.tristana.spacewars.command;

import org.bukkit.entity.Player;

import it.tristana.commons.command.MainCommand;
import it.tristana.commons.helper.CommonsHelper;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.SpaceArena;

public class CommandLobby extends ArenaSubCommand {

	public CommandLobby(Main plugin, MainCommand main, String name, String permission) {
		super(plugin, main, name, permission);
	}

	@Override
	public void execute(Player player, String[] args) {
		SpaceArena arena = getArena(player);
		if (arena != null) {
			arena.setLobby(player.getLocation());
			CommonsHelper.info(player, "&aLobby set");
		}
		else {
			CommonsHelper.info(player, "&cThere is not an arena in this world!");
		}
	}

	@Override
	protected String getHelp() {
		return "Set the arena lobby";
	}
}
