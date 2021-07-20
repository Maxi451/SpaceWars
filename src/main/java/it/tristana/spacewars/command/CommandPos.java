package it.tristana.spacewars.command;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import it.tristana.commons.command.MainCommand;
import it.tristana.commons.helper.CommonsHelper;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.SpaceArena;

public class CommandPos extends ArenaSubCommand {

	public CommandPos(Main plugin, MainCommand main, String name, String permission) {
		super(plugin, main, name, permission);
	}

	@Override
	protected void execute(Player player, String[] args) {
		if (args.length > 1) {
			SpaceArena arena = getArena(player);
			if (arena != null) {
				Vector vector = player.getLocation().toVector();
				switch (args[1]) {
				case "1":
					arena.setLowerPos(vector);
					CommonsHelper.info(player, "First corner set");
					break;
				case "2":
					arena.setUpperPos(vector);
					CommonsHelper.info(player, "Second corner set");
					break;
				default:
					CommonsHelper.info(player, getHelpMessage());
					break;
				}
			}
			else {
				CommonsHelper.info(player, "&cThere is not an arena in this world!");
			}
		}
		else {
			CommonsHelper.info(player, getHelpMessage());
		}
	}

	@Override
	public String getAdditionalHelpParameters() {
		return "<'1' | '2'>";
	}
	
	@Override
	protected String getHelp() {
		return "Set the arena borders";
	}
}
