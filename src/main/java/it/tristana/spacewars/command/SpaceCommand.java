package it.tristana.spacewars.command;

import it.tristana.commons.command.MainCommand;
import it.tristana.commons.config.SettingsDefaultCommands;
import it.tristana.spacewars.Main;

public class SpaceCommand extends MainCommand<Main> {

	public SpaceCommand(Main plugin, SettingsDefaultCommands settings, String command) {
		super(plugin, settings, command);
	}
}
