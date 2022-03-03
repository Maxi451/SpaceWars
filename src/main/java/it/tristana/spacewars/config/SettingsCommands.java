package it.tristana.spacewars.config;

import it.tristana.commons.config.Settings;

public class SettingsCommands extends Settings<ConfigCommands> {

	private String mainLobbyExecuted;
	private String mainLobbyHelp;
	private String mainLobbyArenaHere;
	
	public SettingsCommands(ConfigCommands config) {
		super(config);
	}

	@Override
	public void reload() {
		mainLobbyExecuted = config.getString(ConfigCommands.MAIN_LOBBY_EXECUTED);
		mainLobbyHelp = config.getString(ConfigCommands.MAIN_LOBBY_HELP);
		mainLobbyArenaHere = config.getString(ConfigCommands.MAIN_LOBBY_ARENA_HERE);
	}

	public String getMainLobbyExecuted() {
		return mainLobbyExecuted;
	}

	public String getMainLobbyHelp() {
		return mainLobbyHelp;
	}

	public String getMainLobbyArenaHere() {
		return mainLobbyArenaHere;
	}
}
