package it.tristana.spacewars.command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;

import it.tristana.commons.helper.CommonsHelper;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.config.SettingsCommands;

public class CommandDelete extends SpaceSubCommand {

	public CommandDelete(SpaceCommand main, String name, String permission, SettingsCommands settings) {
		super(main, name, permission, settings);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		SpaceArena arena = arenasManager.getArena(args[1]);
		if (arena == null) {
			CommonsHelper.info(sender, CommonsHelper.replaceAll(settings.getNoArenaMatching(), "{arena}", args[1]));
			return;
		}
		arena.closeArena();
		arenasManager.removeArena(arena);
		CommonsHelper.info(sender, settings.getDeleteExecuted());
	}

	@Override
	protected List<String> onTab(CommandSender sender, String[] args) {
		List<String> names = arenasManager.getArenas().stream().map(arena -> arena.getName()).collect(Collectors.toList());
		if (args.length < 2) {
			return names;
		}
		return StringUtil.copyPartialMatches(args[1], names, new ArrayList<>());
	}
	
	@Override
	protected boolean requiresPlayer() {
		return false;
	}

	@Override
	protected String getHelp() {
		return settings.getDeleteHelp();
	}
	
	@Override
	protected String getAdditionalHelpParameters() {
		return "<name>";
	}
	
	@Override
	protected int getMinRequiredParameters() {
		return 1;
	}
}
