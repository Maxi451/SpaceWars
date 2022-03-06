package it.tristana.spacewars.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import it.tristana.commons.helper.CommonsHelper;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.config.SettingsCommands;

public class CommandJoin extends SpaceSubCommand {

	public CommandJoin(SpaceCommand main, String name, String permission, SettingsCommands settings) {
		super(main, name, permission, settings);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		Player player = ((Player) sender);
		SpaceArena arena = arenasManager.getFirstArenaAvailable(player);
		if (arena == null) {
			CommonsHelper.info(sender, settings.getNoJoinableArenaFound());
			return;
		}
		arena.onPlayerJoin(player);
	}

	@Override
	protected boolean requiresPlayer() {
		return true;
	}

	@Override
	protected String getHelp() {
		return settings.getJoinHelp();
	}
}
