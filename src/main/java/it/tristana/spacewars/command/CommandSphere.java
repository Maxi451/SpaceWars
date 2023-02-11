package it.tristana.spacewars.command;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import it.tristana.commons.helper.CommonsHelper;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.config.SettingsCommands;

public class CommandSphere extends SpaceSubCommand {

	public CommandSphere(SpaceCommand main, String name, String permission, SettingsCommands settings) {
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

		arena.setSpherePowerup(pos);
		CommonsHelper.info(sender, settings.getSphereExecuted());
	}

	@Override
	protected boolean requiresPlayer() {
		return true;
	}

	@Override
	protected String getHelp() {
		return settings.getSphereHelp();
	}
}
