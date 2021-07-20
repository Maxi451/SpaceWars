package it.tristana.spacewars.command;

import org.bukkit.entity.Player;

import it.tristana.commons.command.MainCommand;
import it.tristana.commons.helper.CommonsHelper;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.SpaceArena;

public class CommandJoin extends ArenaSubCommand {

	public CommandJoin(Main plugin, MainCommand main, String name, String permission) {
		super(plugin, main, name, permission);
	}

	@Override
	protected void execute(Player player, String[] args) {
		SpaceArena arena;
		if (args.length > 1) {
			arena = plugin.getArenaByName(args[1]);
		}
		else {
			arena = plugin.getFirstAvailableArena(player);
		}
		if (arena != null) {
			arena.onPlayerJoin(player);
			CommonsHelper.info(player, "&aJoined");
		}
		else {
			CommonsHelper.info(player, "&cArena not available");
		}
	}

	@Override
	protected String getAdditionalHelpParameters() {
		return "[arena]";
	}
	
	@Override
	protected String getHelp() {
		return "Join an arena";
	}
}
