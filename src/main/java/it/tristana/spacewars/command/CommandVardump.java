package it.tristana.spacewars.command;

import org.bukkit.entity.Player;

import it.tristana.commons.command.MainCommand;
import it.tristana.commons.helper.CommonsHelper;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.SpaceArena;

public class CommandVardump extends ArenaSubCommand {

	public CommandVardump(Main plugin, MainCommand main, String name, String permission) {
		super(plugin, main, name, permission);
	}

	@Override
	protected void execute(Player player, String[] args) {
		if (args.length > 1) {
			SpaceArena arena = plugin.getArenaByName(args[1]);
			if (arena != null) {
				CommonsHelper.info(player, "Name: " + arena.getName());
				CommonsHelper.info(player, "Status: " + arena.getStatus());
				CommonsHelper.info(player, "Max per team: " + arena.getMaxPerTeam());
				CommonsHelper.info(player, "Max players: " + arena.getMaxPlayers());
				CommonsHelper.info(player, "Players: " + arena.getPlayers().size());
			}
			else {
				CommonsHelper.info(player, "&cArena \"" + args[1] + "\" not found");
			}
		}
		else {
			CommonsHelper.info(player, "&cUse /" + main.getCommand() + " " + getName() + " <arena name>");
		}
	}

	@Override
	protected String getHelp() {
		return "Shows info about an arena";
	}
}
