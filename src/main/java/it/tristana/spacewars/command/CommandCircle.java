package it.tristana.spacewars.command;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import it.tristana.commons.helper.CommonsHelper;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.config.SettingsCommands;

public class CommandCircle extends SpaceSubCommand {

	public CommandCircle(SpaceCommand main, String name, String permission, SettingsCommands settings) {
		super(main, name, permission, settings);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		Location pos = ((Player) sender).getLocation();
		SpaceArena arena = arenasManager.getArenaInWorld(pos.getWorld());
		if (arena == null) {
			CommonsHelper.info(sender, settings.getNoArenaInWorld());
			return;
		}
		double rotation;
		try {
			rotation = Double.parseDouble(args[1]);
		} catch (NumberFormatException e) {
			CommonsHelper.info(sender, CommonsHelper.replaceAll(settings.getInputNotDouble(), "{input}", args[1]));
			return;
		}
		arena.setCirclePowerup(pos, rotation);
		CommonsHelper.info(sender, settings.getCircleExecuted());
	}

	@Override
	protected String getAdditionalHelpParameters() {
		return "<rotation>";
	}
	
	@Override
	protected int getMinRequiredParameters() {
		return 1;
	}
	
	@Override
	protected boolean requiresPlayer() {
		return true;
	}

	@Override
	protected String getHelp() {
		return settings.getCircleHelp();
	}
}
