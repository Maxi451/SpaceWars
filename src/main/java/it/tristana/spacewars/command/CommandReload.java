package it.tristana.spacewars.command;

import org.bukkit.command.CommandSender;

import it.tristana.commons.command.MainCommand;
import it.tristana.commons.command.SubCommand;
import it.tristana.commons.helper.CommonsHelper;
import it.tristana.spacewars.Main;

public class CommandReload extends SubCommand {

	private Main plugin;
	
	public CommandReload(Main plugin, MainCommand main, String name, String permission) {
		super(main, name, permission);
		this.plugin = plugin;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		plugin.reloadConfig();
		CommonsHelper.info(sender, "&aPlugin reloaded");
	}

	@Override
	protected boolean requiresPlayer() {
		return false;
	}

	@Override
	protected String getHelp() {
		return "Reloads the plugin";
	}
}
