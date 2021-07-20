package it.tristana.spacewars.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import it.tristana.commons.command.MainCommand;
import it.tristana.commons.command.SubCommand;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.SpaceArena;

public abstract class ArenaSubCommand extends SubCommand {

	protected final Main plugin;
	
	public ArenaSubCommand(Main plugin, MainCommand main, String name, String permission) {
		super(main, name, permission);
		this.plugin = plugin;
	}
	
	@Override
	public final void execute(CommandSender sender, String[] args) {
		execute((Player) sender, args);
	}
	
	@Override
	public final boolean requiresPlayer() {
		return true;
	}
	
	/**
	 * Returns the arena in the player's world
	 * @param player The player in
	 * @return The arena in the world, or {@code null}<br>
	 * if no arena is set in the world
	 */
	
	protected final SpaceArena getArena(Player player) {
		return plugin.getArenaByWorld(player.getWorld());
	}
	
	protected abstract void execute(Player player, String[] args);
}
