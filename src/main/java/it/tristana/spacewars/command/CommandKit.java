package it.tristana.spacewars.command;

import org.bukkit.entity.Player;

import it.tristana.commons.command.MainCommand;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.SpaceArena;

public class CommandKit extends ArenaSubCommand {

	public CommandKit(Main plugin, MainCommand main, String name, String permission) {
		super(plugin, main, name, permission);
	}

	@Override
	protected void execute(Player player, String[] args) {
		SpaceArena arena = getArena(player);
		if (arena != null && arena.isGameWaiting() && arena.getArenaPlayer(player) != null) {
			arena.openKitMenu(player);
		}
	}

	@Override
	protected String getHelp() {
		return "Allows to choose a game kit";
	}
}
