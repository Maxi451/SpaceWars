package it.tristana.spacewars.command;

import org.bukkit.entity.Player;

import it.tristana.commons.command.MainCommand;
import it.tristana.commons.helper.CommonsHelper;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.SpaceArena;

public class CommandPowerup extends ArenaSubCommand {

	public CommandPowerup(Main plugin, MainCommand main, String name, String permission) {
		super(plugin, main, name, permission);
	}

	@Override
	protected void execute(Player player, String[] args) {
		SpaceArena arena = getArena(player);
		if (arena != null) {
			arena.addPowerup(player.getLocation());
			CommonsHelper.info(player, "&aPowerup added in arena " + arena.getName());
		}
		else {
			CommonsHelper.info(player, "&cThere is not an arena in this world!");
		}
	}
	
	@Override
	protected String getHelp() {
		return "Creates a powerup circle";
	}
}
