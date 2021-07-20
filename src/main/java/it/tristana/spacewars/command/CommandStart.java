package it.tristana.spacewars.command;

import org.bukkit.entity.Player;

import it.tristana.commons.command.MainCommand;
import it.tristana.commons.helper.CommonsHelper;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.SpaceArena;

public class CommandStart extends ArenaSubCommand {

	public CommandStart(Main plugin, MainCommand main, String name, String permission) {
		super(plugin, main, name, permission);
	}

	@Override
	protected void execute(Player player, String[] args) {
		SpaceArena arena = getArena(player);
		if (arena != null) {
			if (arena.forceStart()) {
				CommonsHelper.info(player, "&aArena force started");
			}
			else {
				CommonsHelper.info(player, "&cYou can't start the arena now");
			}
		}
		else {
			CommonsHelper.info(player, "&cYou're not in an arena!");
		}
	}

	@Override
	protected String getHelp() {
		return "Starts the game";
	}
}
