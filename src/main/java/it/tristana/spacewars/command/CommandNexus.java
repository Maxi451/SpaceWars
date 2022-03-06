package it.tristana.spacewars.command;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import it.tristana.commons.helper.CommonsHelper;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.config.SettingsCommands;

public class CommandNexus extends SpaceSubCommand {

	public CommandNexus(SpaceCommand main, String name, String permission, SettingsCommands settings) {
		super(main, name, permission, settings);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		Location pos = ((Player) sender).getLocation();
		SpaceArena arena = arenasManager.getArenaInWorld(pos.getWorld());
		if (arena == null) {
			CommonsHelper.info(sender, settings.getNoArenaInWorld());
			return;
		}
		if (arena.setNexusLocation(pos)) {
			CommonsHelper.info(sender, CommonsHelper.replaceAll(settings.getNexusExecuted(), "{team name}", arena.getTeamName(arena.getNexusAmount() - 1)));
		} else {
			CommonsHelper.info(sender, settings.getNexusCantSetAnother());
		}
	}

	@Override
	protected boolean requiresPlayer() {
		return true;
	}

	@Override
	protected String getHelp() {
		return settings.getNexusHelp();
	}
}
