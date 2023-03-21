package it.tristana.spacewars.config;

import java.io.File;
import java.util.List;

import it.tristana.commons.config.Settings;
import it.tristana.commons.helper.CommonsHelper;

public class SettingsScoreboard extends Settings<ConfigScoreboard> {

	private boolean isEnabled;
	private String statsLoading;

	private String lobbyName;
	private List<String> lobbyLines;

	private String preGameName;
	private List<String> preGameLines;

	private String gameName;
	private List<String> gameLines;
	private String gameTeam;

	public SettingsScoreboard(File folder) {
		super(folder, ConfigScoreboard.class);
	}

	@Override
	protected void reload(ConfigScoreboard config) {
		isEnabled = CommonsHelper.parseBoolean(config.getString(ConfigScoreboard.IS_ENABLED));
		statsLoading = config.getString(ConfigScoreboard.STATS_LOADING);

		lobbyName = config.getString(ConfigScoreboard.LOBBY_NAME);
		lobbyLines = config.getList(ConfigScoreboard.LOBBY_LINES);

		preGameName = config.getString(ConfigScoreboard.PRE_GAME_NAME);
		preGameLines = config.getList(ConfigScoreboard.PRE_GAME_LINES);

		gameName = config.getString(ConfigScoreboard.GAME_NAME);
		gameLines = config.getList(ConfigScoreboard.GAME_LINES);
		gameTeam = config.getString(ConfigScoreboard.GAME_TEAM);
	}

	public String getStatsLoading() {
		return statsLoading;
	}

	public String getGameTeam() {
		return gameTeam;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public String getLobbyName() {
		return lobbyName;
	}

	public List<String> getLobbyLines() {
		return lobbyLines;
	}

	public String getPreGameName() {
		return preGameName;
	}

	public List<String> getPreGameLines() {
		return preGameLines;
	}

	public String getGameName() {
		return gameName;
	}

	public List<String> getGameLines() {
		return gameLines;
	}
}