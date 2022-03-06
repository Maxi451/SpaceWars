package it.tristana.spacewars.command;

import it.tristana.commons.command.SubCommand;
import it.tristana.commons.interfaces.arena.ArenasManager;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.config.SettingsCommands;

public abstract class SpaceSubCommand extends SubCommand {

	protected final Main plugin;
	protected final ArenasManager<SpaceArena> arenasManager;
	protected final SettingsCommands settings;
	
	public SpaceSubCommand(SpaceCommand main, String name, String permission, SettingsCommands settings) {
		super(main, name, permission);
		plugin = main.getPlugin();
		arenasManager = plugin.getArenasManager();
		this.settings = settings;
	}
}
