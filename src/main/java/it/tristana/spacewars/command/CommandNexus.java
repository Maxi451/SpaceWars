package it.tristana.spacewars.command;

import org.bukkit.entity.Player;

import it.tristana.commons.command.MainCommand;
import it.tristana.commons.helper.CommonsHelper;
import it.tristana.commons.helper.TeamsColors;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.SpaceArena;

public class CommandNexus extends ArenaSubCommand {

	public CommandNexus(Main plugin, MainCommand main, String name, String permission) {
		super(plugin, main, name, permission);
	}

	@Override
	protected void execute(Player player, String[] args) {
		SpaceArena arena = getArena(player);
		if (arena != null) {
			arena.setNexus(player.getLocation());
			int index = arena.getNexuses().size() - 1;
			CommonsHelper.info(player, "&aAdded the nexus for team " + TeamsColors.COLORS_CODES[index] + plugin.getArenaValues().getTeamsNames().get(index) + "&a!");
		}
		else {
			CommonsHelper.info(player, "&cThere is not an arena in this world!");
		}
	}

	@Override
	protected String getHelp() {
		return "Adds a team nexus in the arena";
	}
}
