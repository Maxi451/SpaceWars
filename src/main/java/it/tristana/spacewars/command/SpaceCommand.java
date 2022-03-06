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
		registerSubCommand(new CommandCreate(this, "create", adminPerms, settingsCommands));
		registerSubCommand(new CommandDelete(this, "delete", adminPerms, settingsCommands));
		registerSubCommand(new CommandLobby(this, "lobby", adminPerms, settingsCommands));
		registerSubCommand(new CommandSpawnpoint(this, "spawnpoint", adminPerms, settingsCommands));
		registerSubCommand(new CommandNexus(this, "nexus", adminPerms, settingsCommands));
		registerSubCommand(new CommandCircle(this, "circle", adminPerms, settingsCommands));
		
		registerSubCommand(new CommandJoin(this, "join", null, settingsCommands));
	}

	@Override
	protected String getAdminPerms() {
		return Main.ADMIN_PERMS;
	}
}
