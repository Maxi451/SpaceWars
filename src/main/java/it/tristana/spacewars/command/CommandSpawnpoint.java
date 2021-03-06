package it.tristana.spacewars.command;

import org.bukkit.entity.Player;

import it.tristana.commons.command.MainCommand;
import it.tristana.commons.helper.CommonsHelper;
import it.tristana.commons.helper.TeamsColors;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.SpaceArena;

public class CommandSpawnpoint extends ArenaSubCommand {

	public CommandSpawnpoint(Main plugin, MainCommand main, String name, String permission) {
		super(plugin, main, name, permission);
	}

	@Override
	public void execute(Player player, String[] args) {
		SpaceArena arena = getArena(player);
		if (arena != null) {
			if (arena.setSpawnpoint(player.getLocation())) {
				int index = arena.getSpawnpoints().size() - 1;
				CommonsHelper.info(player, TeamsColors.COLORS_CODES[index] + plugin.getArenaValues().getTeamsNames().get(index) + " Team&f's spawnpoint set in arena " + arena.getName());
			}
			else {
				CommonsHelper.info(player, "&cAn arena can have a maximum of " + TeamsColors.COLORS_CODES.length + " teams!");
			}
		}
		else {
			CommonsHelper.info(player, "&cThere is not an arena in this world!");
		}
	}

	@Override
	protected String getHelp() {
		return "Sets the spawnpoint of the current team";
	}
}
