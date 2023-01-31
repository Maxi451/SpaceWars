package it.tristana.spacewars.config;

import java.io.File;
import java.util.Arrays;

import it.tristana.commons.config.Config;

public class ConfigScoreboard extends Config {

	public static final String TEAMS_PLACEHOLDER = "{teams}";

	private static final String NAME = "name";
	private static final String LINES = "lines";

	public static final String IS_ENABLED = "is-enabled";

	private static final String LOBBY = "lobby.";
	public static final String LOBBY_NAME = LOBBY + NAME;
	public static final String LOBBY_LINES = LOBBY + LINES;

	private static final String PRE_GAME = "pre-game.";
	public static final String PRE_GAME_NAME = PRE_GAME + NAME;
	public static final String PRE_GAME_LINES = PRE_GAME + LINES;

	private static final String GAME = "game.";
	public static final String GAME_NAME = GAME + NAME;
	public static final String GAME_LINES = GAME + LINES;

	public ConfigScoreboard(File folder) {
		super(new File(folder, "scoreboard.yml"));
	}

	@Override
	protected void createDefault() {
		set(IS_ENABLED, "true");

		set(LOBBY_NAME, "SpaceWars Lobby");
		set(LOBBY_LINES, Arrays.asList("line1", "line", "line3"));

		set(PRE_GAME_NAME, "SpaceWars Lobby");
		set(PRE_GAME_LINES, Arrays.asList("line1", "line", "line3"));

		set(GAME_NAME, "SpaceWars Lobby");
		set(GAME_LINES, Arrays.asList("line1", "line", "line3"));
	}
}
