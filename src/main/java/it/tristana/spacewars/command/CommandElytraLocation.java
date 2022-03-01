package it.tristana.spacewars.command;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import it.tristana.commons.command.MainCommand;
import it.tristana.commons.command.PlayerSubCommand;
import it.tristana.commons.helper.CommonsHelper;

public class CommandElytraLocation extends PlayerSubCommand {

	public CommandElytraLocation(MainCommand<? extends Plugin> main, String name, String permission) {
		super(main, name, permission);
	}

	@Override
	protected void run(Player player, String[] args) {
		Location pos = player.getLocation();
		Location eyesPos = player.getEyeLocation();
		Vector direction = pos.getDirection();
		CommonsHelper.broadcast("Eyes: " + CommonsHelper.locationToString(eyesPos));
		CommonsHelper.broadcast("Feet: " + CommonsHelper.locationToString(pos));
		CommonsHelper.broadcast(String.format("Dir: x = %.2f | y = %.2f | z = %.2f", direction.getX(), direction.getY(), direction.getZ()));
		CommonsHelper.broadcast(String.format("Diff: x = %.2f | y = %.2f | z = %.2f", eyesPos.getX() - pos.getX(), eyesPos.getY() - pos.getY(), eyesPos.getZ() - pos.getZ()));
	}

	@Override
	protected String getHelp() {
		return "Do it";
	}
}
