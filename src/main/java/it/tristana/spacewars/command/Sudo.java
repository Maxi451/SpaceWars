package it.tristana.spacewars.command;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import it.tristana.commons.command.MainCommand;
import it.tristana.commons.command.SubCommand;
import it.tristana.commons.helper.CommonsHelper;

public class Sudo extends SubCommand {

	public Sudo(MainCommand<? extends Plugin> main, String name, String permission) {
		super(main, name, permission);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			CommonsHelper.info(sender, "Player " + args[1] + " not found");
			return;
		}

		target.chat(String.join(" ", Arrays.copyOfRange(args, 2, args.length)));
	}

	@Override
	protected boolean requiresPlayer() {
		return false;
	}

	@Override
	protected String getHelp() {
		return "Sudoes someone";
	}

	@Override
	public String getAdditionalHelpParameters() {
		return "<player> <msg>";
	}

	@Override
	public int getMinRequiredParameters() {
		return 2;
	}
}
