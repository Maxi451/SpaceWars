package it.tristana.spacewars.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import it.tristana.commons.helper.CommonsHelper;
import it.tristana.commons.interfaces.arena.Status;
import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.config.SettingsCommands;
import it.tristana.spacewars.gui.kit.GuiKit;

public class CommandKit extends SpaceSubCommand {

	public CommandKit(SpaceCommand main, String name, String permission, SettingsCommands settings) {
		super(main, name, permission, settings);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		Player player = (Player) sender;
		SpacePlayer spacePlayer = arenasManager.getArenaPlayer(player);
		if (spacePlayer == null) {
			CommonsHelper.info(sender, settings.getNotInArena());
			return;
		}

		Status status = spacePlayer.getArena().getStatus();
		if (status != Status.WAITING && status != Status.STARTING) {
			CommonsHelper.info(sender, settings.getCantChooseKitNow());
			return;
		}

		plugin.getClickedGuiManager().getGui(GuiKit.class).open((Player) sender);
	}

	@Override
	protected boolean requiresPlayer() {
		return true;
	}

	@Override
	protected String getHelp() {
		return settings.getKitHelp();
	}
}
