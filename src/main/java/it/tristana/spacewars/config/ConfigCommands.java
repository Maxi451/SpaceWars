package it.tristana.spacewars.config;

import java.io.File;

import it.tristana.commons.config.Config;

public class ConfigCommands extends Config {

	private static final String HELP = "help";
	private static final String EXECUTED = "executed";
	
	private static final String GENERAL = "general.";
	public static final String OTHER_ARENA_HERE = GENERAL + "other-arena-here";
	public static final String MAIN_LOBBY_NOT_SET = GENERAL + "main-lobby-not-set";
	public static final String NO_ARENA_MATCHING = GENERAL + "no-arena-matching";
	public static final String NO_ARENA_IN_WORLD = GENERAL + "no-arena-in-world";
	public static final String INPUT_NOT_DOUBLE = GENERAL + "input-not-double";
	
	private static final String MAIN_LOBBY = "main-lobby.";
	public static final String MAIN_LOBBY_HELP = MAIN_LOBBY + HELP;
	public static final String MAIN_LOBBY_EXECUTED = MAIN_LOBBY + EXECUTED;
	
	private static final String CREATE = "create.";
	public static final String CREATE_HELP = CREATE + HELP;
	public static final String CREATE_EXECUTED = CREATE + EXECUTED;
	public static final String CREATE_ARENA_SAME_WORLD_MAIN_LOBBY = CREATE + "same-world-main-lobby";
	
	private static final String DELETE = "delete.";
	public static final String DELETE_HELP = DELETE + HELP;
	public static final String DELETE_EXECUTED = DELETE + EXECUTED;
	
	private static final String LOBBY = "lobby.";
	public static final String LOBBY_HELP = LOBBY + HELP;
	public static final String LOBBY_EXECUTED = LOBBY + EXECUTED;
	
	private static final String SPAWNPOINT = "spawnpoint.";
	public static final String SPAWNPOINT_HELP = SPAWNPOINT + HELP;
	public static final String SPAWNPOINT_EXECUTED = SPAWNPOINT + EXECUTED;
	public static final String SPAWNPOINT_CANT_SET_ANOTHER = SPAWNPOINT + "cant-set-another";
	
	private static final String NEXUS = "spawnpoint.";
	public static final String NEXUS_HELP = NEXUS + HELP;
	public static final String NEXUS_EXECUTED = NEXUS + EXECUTED;
	public static final String NEXUS_CANT_SET_ANOTHER = NEXUS + "cant-set-another";
	
	private static final String JOIN = "join.";
	public static final String JOIN_HELP = JOIN + HELP;
	public static final String JOIN_NO_JOINABLE_ARENA_FOUND = JOIN + "no-joinable-arena-found";
	
	private static final String CIRCLE = "circle.";
	public static final String CIRCLE_HELP = CIRCLE + HELP;
	public static final String CIRCLE_EXECUTED = CIRCLE + EXECUTED;
	
	public ConfigCommands(File folder) {
		super(new File(folder, "commands.yml"));
	}

	@Override
	protected void createDefault() {
		set(OTHER_ARENA_HERE, "&cIn this world is already located the arena {arena}");
		set(MAIN_LOBBY_NOT_SET, "&cYou have to set the main lobby first");
		set(NO_ARENA_MATCHING, "&cThere isn't an arena with the name {arena}!");
		set(NO_ARENA_IN_WORLD, "&cThere isn't an arena in this world!");
		set(INPUT_NOT_DOUBLE, "&c{input} is not a valid decimal number (use the '.' character)");
		
		set(MAIN_LOBBY_HELP, "Sets the main lobby, where players will spawn");
		set(MAIN_LOBBY_EXECUTED, "&aMain lobby set");

		set(CREATE_HELP, "Creates a new arena in the world where you stand");
		set(CREATE_EXECUTED, "&aNew arena created!");
		set(CREATE_ARENA_SAME_WORLD_MAIN_LOBBY, "&cYou can't create an arena in the same world of the main lobby!");

		set(DELETE_HELP, "Deletes the arena with the given name");
		set(DELETE_EXECUTED, "Arena deleted");
		
		set(LOBBY_HELP, "Sets the lobby for the arena in the world where you stand");
		set(LOBBY_EXECUTED, "Lobby set to your current position");

		set(SPAWNPOINT_HELP, "Sets a spawnpoint that will be used as a team's spawnpoint");
		set(SPAWNPOINT_EXECUTED, "Spawnpoint for team {team name} set");
		set(SPAWNPOINT_CANT_SET_ANOTHER, "&cYou have set the maximum amount of spawnpoints");

		set(NEXUS_HELP, "Sets a spawnpoint that will be used as a team's spawnpoint");
		set(NEXUS_EXECUTED, "Nexus for team {team name} set");
		set(NEXUS_CANT_SET_ANOTHER, "&cYou must set another spawnpoint first");
		
		set(JOIN_HELP, "Joins an arena");
		set(JOIN_NO_JOINABLE_ARENA_FOUND, "&cNo free arena was found. Sorry!");

		set(CIRCLE_HELP, "Sets a circle powerup spawner in which player can fly");
		set(CIRCLE_EXECUTED, "Circle powerup set");
	}
}
