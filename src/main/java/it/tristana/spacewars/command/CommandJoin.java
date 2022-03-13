package it.tristana.spacewars.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

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
	protected List<String> onTab(CommandSender sender, String[] args) {
		List<String> names = arenasManager.getArenas().stream().map(arena -> arena.getName()).toList();
		if (args.length < 2) {
			return names;
		}
		return StringUtil.copyPartialMatches(args[1], names, new ArrayList<>());
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
