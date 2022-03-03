package it.tristana.spacewars.command;

import it.tristana.commons.command.MainCommand;
import it.tristana.commons.config.SettingsDefaultCommands;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.config.SettingsCommands;

public class SpaceCommand extends MainCommand<Main> {

	public SpaceCommand(Main plugin, SettingsDefaultCommands settingsDefaultCommands, String command, SettingsCommands settingsCommands) {
		super(plugin, settingsDefaultCommands, command);
		String adminPerms = getAdminPerms();
		registerSubCommand(new CommandMainLobby(this, "mainlobby", adminPerms, settingsCommands));
	}
}
