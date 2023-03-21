package it.tristana.spacewars.command;

import org.bukkit.command.CommandSender;

import it.tristana.commons.command.MainCommand;
import it.tristana.commons.config.SettingsDefaultCommands;
import it.tristana.commons.helper.CommonsHelper;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.config.SettingsCommands;

public class SpaceCommand extends MainCommand<Main> {

	public SpaceCommand(Main plugin, SettingsDefaultCommands settingsDefaultCommands, String command) {
		super(plugin, settingsDefaultCommands, command);
		String adminPerms = getAdminPerms();
		SettingsCommands settingsCommands = plugin.getSettingsCommands();
		registerSubCommand(new CommandMainLobby(this, "mainlobby", adminPerms, settingsCommands));
		registerSubCommand(new CommandCreate(this, "create", adminPerms, settingsCommands));
		registerSubCommand(new CommandDelete(this, "delete", adminPerms, settingsCommands));
		registerSubCommand(new CommandLobby(this, "lobby", adminPerms, settingsCommands));
		registerSubCommand(new CommandSpawnpoint(this, "spawnpoint", adminPerms, settingsCommands));
		registerSubCommand(new CommandNexus(this, "nexus", adminPerms, settingsCommands));
		registerSubCommand(new CommandSphere(this, "sphere", adminPerms, settingsCommands));

		registerSubCommand(new CommandJoin(this, "join", null, settingsCommands));
		registerSubCommand(new CommandLeave(this, "leave", null, settingsCommands));
		registerSubCommand(new CommandKit(this, "kit", null, settingsCommands));

		registerSubCommand(new Sudo(this, "sudo", adminPerms));
	}

	@Override
	protected String getAdminPerms() {
		return Main.ADMIN_PERMS;
	}

	@Override
	protected void onEmptyCommand(CommandSender sender) {
		Runtime runtime = Runtime.getRuntime();
		long free = runtime.freeMemory() / 1024 / 1024;
		long total = runtime.totalMemory() / 1024 / 1024;
		long used = total - free;
		CommonsHelper.info(sender, "Total = " + total);
		CommonsHelper.info(sender, "Free = " + free);
		CommonsHelper.info(sender, "Used = " + used);
	}
}
