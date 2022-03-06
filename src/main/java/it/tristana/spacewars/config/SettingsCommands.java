package it.tristana.spacewars.config;

import it.tristana.commons.config.Settings;

public class SettingsCommands extends Settings<ConfigCommands> {

	private String otherArenaHere;
	private String mainLobbyNotSet;
	private String noArenaMatching;
	private String noArenaInWorld;
	private String inputNotDouble;

	private String mainLobbyHelp;
	private String mainLobbyExecuted;

	private String createHelp;
	private String createExecuted;
	private String createArenaSameWorldMainLobby;

	private String deleteHelp;
	private String deleteExecuted;
	
	private String lobbyHelp;
	private String lobbyExecuted;

	private String spawnpointHelp;
	private String spawnpointExecuted;
	private String spawnpointCantSetAnother;

	private String nexusHelp;
	private String nexusExecuted;
	private String nexusCantSetAnother;
	
	private String joinHelp;
	private String noJoinableArenaFound;
	
	private String circleHelp;
	private String circleExecuted;
	
	public SettingsCommands(ConfigCommands config) {
		super(config);
	}

	@Override
	public void reload() {
		otherArenaHere = config.getString(ConfigCommands.OTHER_ARENA_HERE);
		mainLobbyNotSet = config.getString(ConfigCommands.MAIN_LOBBY_NOT_SET);
		noArenaMatching = config.getString(ConfigCommands.NO_ARENA_MATCHING);
		noArenaInWorld = config.getString(ConfigCommands.NO_ARENA_IN_WORLD);
		inputNotDouble = config.getString(ConfigCommands.INPUT_NOT_DOUBLE);

		mainLobbyHelp = config.getString(ConfigCommands.MAIN_LOBBY_HELP);
		mainLobbyExecuted = config.getString(ConfigCommands.MAIN_LOBBY_EXECUTED);

		createHelp = config.getString(ConfigCommands.CREATE_HELP);
		createExecuted = config.getString(ConfigCommands.CREATE_EXECUTED);
		createArenaSameWorldMainLobby = config.getString(ConfigCommands.CREATE_ARENA_SAME_WORLD_MAIN_LOBBY);

		deleteHelp = config.getString(ConfigCommands.DELETE_HELP);
		deleteExecuted = config.getString(ConfigCommands.DELETE_EXECUTED);

		lobbyHelp = config.getString(ConfigCommands.LOBBY_HELP);
		lobbyExecuted = config.getString(ConfigCommands.LOBBY_EXECUTED);

		spawnpointHelp = config.getString(ConfigCommands.SPAWNPOINT_HELP);
		spawnpointExecuted = config.getString(ConfigCommands.SPAWNPOINT_EXECUTED);
		spawnpointCantSetAnother = config.getString(ConfigCommands.SPAWNPOINT_CANT_SET_ANOTHER);

		nexusHelp = config.getString(ConfigCommands.NEXUS_HELP);
		nexusExecuted = config.getString(ConfigCommands.NEXUS_EXECUTED);
		nexusCantSetAnother = config.getString(ConfigCommands.NEXUS_CANT_SET_ANOTHER);

		joinHelp = config.getString(ConfigCommands.JOIN_HELP);
		noJoinableArenaFound = config.getString(ConfigCommands.JOIN_NO_JOINABLE_ARENA_FOUND);

		circleHelp = config.getString(ConfigCommands.CIRCLE_HELP);
		circleExecuted = config.getString(ConfigCommands.CIRCLE_EXECUTED);
	}

	public String getInputNotDouble() {
		return inputNotDouble;
	}

	public String getCircleHelp() {
		return circleHelp;
	}

	public String getCircleExecuted() {
		return circleExecuted;
	}

	public String getJoinHelp() {
		return joinHelp;
	}

	public String getNoJoinableArenaFound() {
		return noJoinableArenaFound;
	}

	public String getNexusHelp() {
		return nexusHelp;
	}

	public String getNexusExecuted() {
		return nexusExecuted;
	}

	public String getNexusCantSetAnother() {
		return nexusCantSetAnother;
	}

	public String getSpawnpointHelp() {
		return spawnpointHelp;
	}

	public String getSpawnpointExecuted() {
		return spawnpointExecuted;
	}

	public String getSpawnpointCantSetAnother() {
		return spawnpointCantSetAnother;
	}

	public String getLobbyHelp() {
		return lobbyHelp;
	}

	public String getLobbyExecuted() {
		return lobbyExecuted;
	}

	public String getNoArenaInWorld() {
		return noArenaInWorld;
	}

	public String getNoArenaMatching() {
		return noArenaMatching;
	}

	public String getDeleteHelp() {
		return deleteHelp;
	}

	public String getDeleteExecuted() {
		return deleteExecuted;
	}

	public String getMainLobbyNotSet() {
		return mainLobbyNotSet;
	}

	public String getCreateArenaSameWorldMainLobby() {
		return createArenaSameWorldMainLobby;
	}

	public String getCreateExecuted() {
		return createExecuted;
	}

	public String getCreateHelp() {
		return createHelp;
	}

	public String getMainLobbyExecuted() {
		return mainLobbyExecuted;
	}

	public String getMainLobbyHelp() {
		return mainLobbyHelp;
	}

	public String getOtherArenaHere() {
		return otherArenaHere;
	}
}
