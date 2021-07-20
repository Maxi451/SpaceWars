package it.tristana.spacewars.command;

import it.tristana.commons.command.MainCommand;
import it.tristana.spacewars.Main;

public class SpaceCommand extends MainCommand {
	
	public static final String ADMIN_PERMS = "spacewars.admin";
	public static final String PLAYER_PERMS = "spacewars.player";
	
	public SpaceCommand(Main plugin, String command) {
		super(command);
		registerSubCommand(new CommandLobby(plugin, this, "lobby", ADMIN_PERMS));
		registerSubCommand(new CommandReload(plugin, this, "reload", ADMIN_PERMS));
		registerSubCommand(new CommandPowerup(plugin, this, "powerup", ADMIN_PERMS));
		registerSubCommand(new CommandSpawnpoint(plugin, this, "spawnpoint", ADMIN_PERMS));
		registerSubCommand(new CommandStart(plugin, this, "start", ADMIN_PERMS));
		registerSubCommand(new CommandCreate(plugin, this, "create", ADMIN_PERMS));
		registerSubCommand(new CommandVardump(plugin, this, "vardump", ADMIN_PERMS));
		registerSubCommand(new CommandNexus(plugin, this, "nexus", ADMIN_PERMS));
		registerSubCommand(new CommandPos(plugin, this, "pos", ADMIN_PERMS));
		
		registerSubCommand(new CommandJoin(plugin, this, "join", PLAYER_PERMS));
		registerSubCommand(new CommandKit(plugin, this, "kit", PLAYER_PERMS));
	}
	
	@Override
	public String getAdminPerms() {
		return ADMIN_PERMS;
	}
}
