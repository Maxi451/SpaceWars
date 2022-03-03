package it.tristana.spacewars.config;

import java.io.File;

import it.tristana.commons.config.Config;

public class ConfigCommands extends Config {

	private static final String HELP = "help";
	private static final String EXECUTED = "executed";
	
	private static final String MAIN_LOBBY = "main-lobby.";
	public static final String MAIN_LOBBY_HELP = MAIN_LOBBY + HELP;
	public static final String MAIN_LOBBY_EXECUTED = MAIN_LOBBY + EXECUTED;
	public static final String MAIN_LOBBY_ARENA_HERE = MAIN_LOBBY + "arena-is-set-here";
	
	public ConfigCommands(File folder) {
		super(new File(folder, "commands.yml"));
	}

	@Override
	protected void createDefault() {
		set(MAIN_LOBBY_HELP, "Sets the main lobby, where players will spawn");
		set(MAIN_LOBBY_EXECUTED, "&aMain lobby set");
		set(MAIN_LOBBY_ARENA_HERE, "&cCan't set the main lobby here! In this world is located the arena {arena}");
	}
}
