package it.tristana.spacewars.command;

import org.bukkit.World;
import org.bukkit.entity.Player;

import it.tristana.commons.command.MainCommand;
import it.tristana.commons.helper.CommonsHelper;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.SpaceArena;

public class CommandCreate extends ArenaSubCommand {

	public CommandCreate(Main plugin, MainCommand main, String name, String permission) {
		super(plugin, main, name, permission);
	}

	@Override
	protected void execute(Player player, String[] args) {
		if (args.length > 1) {
			SpaceArena arena = plugin.getArenaByName(args[1]);
			if (arena == null) {
				World world = player.getWorld();
				arena = plugin.getArenaByWorld(world);
				if (arena == null) {
					plugin.createArena(world, args[1]);
					CommonsHelper.info(player, "Arena " + args[1] + " created!");
				}
				else {
					CommonsHelper.info(player, "This world already has the arena \"" + arena.getName() + "\"!");
				}
			}
			else {
				CommonsHelper.info(player, "&cThat arena already exists!");
			}
		}
		else {
			CommonsHelper.info(player, getHelpMessage());
		}
	}

	@Override
	protected String getAdditionalHelpParameters() {
		return "<arena>";
	}
	
	@Override
	protected String getHelp() {
		return "Creates a new arena";
	}
}
