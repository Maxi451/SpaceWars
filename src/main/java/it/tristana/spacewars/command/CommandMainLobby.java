package it.tristana.spacewars.command;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import it.tristana.commons.helper.CommonsHelper;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.config.SettingsCommands;

public class CommandMainLobby extends SpaceSubCommand {
	
	public CommandMainLobby(SpaceCommand main, String name, String permission, SettingsCommands settings) {
		super(main, name, permission, settings);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		Location playerPos = ((Player) sender).getLocation();
		SpaceArena arena = plugin.getArenasManager().getArenaInWorld(playerPos.getWorld());
		if (arena == null) {
			plugin.setMainLobby(playerPos);
			CommonsHelper.info(sender, settings.getMainLobbyExecuted());
		} else {
			CommonsHelper.info(sender, CommonsHelper.replaceAll(settings.getOtherArenaHere(), "{arena}", arena.getName()));
		}
	}

	@Override
	protected String getHelp() {
		return settings.getMainLobbyHelp();
	}

	@Override
	protected boolean requiresPlayer() {
		return true;
	}
}
