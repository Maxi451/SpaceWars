package it.tristana.spacewars.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import it.tristana.commons.helper.CommonsHelper;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.config.SettingsCommands;

public class CommandLeave extends SpaceSubCommand {

	public CommandLeave(SpaceCommand main, String name, String permission, SettingsCommands settings) {
		super(main, name, permission, settings);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		Player player = (Player) sender;
		SpaceArena arena = plugin.getArenasManager().getArenaWithPlayer(player);
		if (arena == null) {
			CommonsHelper.info(sender, settings.getNotInArena());
			return;
		}
		arena.onPlayerLeave(player);
		CommonsHelper.info(sender, settings.getLeaveExecuted());
	}

	@Override
	protected String getHelp() {
		return settings.getLeaveHelp();
	}

	@Override
	protected boolean requiresPlayer() {
		return true;
	}
}
