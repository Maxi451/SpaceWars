package it.tristana.spacewars.command;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import it.tristana.commons.helper.CommonsHelper;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.config.SettingsCommands;

public class CommandCreate extends SpaceSubCommand {
	
	public CommandCreate(SpaceCommand main, String name, String permission, SettingsCommands settings) {
		super(main, name, permission, settings);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		World world = ((Player) sender).getWorld();
		Location mainLobby = plugin.getMainLobby();
		if (mainLobby == null) {
			CommonsHelper.info(sender, settings.getMainLobbyNotSet());
			return;
		}
		if (mainLobby.getWorld() == world) {
			CommonsHelper.info(sender, settings.getCreateArenaSameWorldMainLobby());
			return;
		}
		SpaceArena otherArena = arenasManager.getArenaInWorld(world);
		if (otherArena != null) {
			CommonsHelper.info(sender, CommonsHelper.replaceAll(settings.getOtherArenaHere(), "{arena}", otherArena.getName()));
			return;
		}
		arenasManager.addArena(new SpaceArena(world, args[1], plugin));
		CommonsHelper.info(sender, settings.getCreateExecuted());
	}

	@Override
	protected boolean requiresPlayer() {
		return true;
	}

	@Override
	protected String getHelp() {
		return settings.getCreateHelp();
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
