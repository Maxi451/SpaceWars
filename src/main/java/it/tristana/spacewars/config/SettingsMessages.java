package it.tristana.spacewars.config;

import it.tristana.commons.config.Settings;

public class SettingsMessages extends Settings<ConfigMessages> {

	private String playerKilled;
	private String playerKilledSelf;
	private String playerEliminated;
	private String playerLeft;
	private String teamEliminated;
	private String nexusDestroyed;
	private String moneyGained;
	private String playerGotPowerup;
	
	public SettingsMessages(ConfigMessages config) {
		super(config);
	}

	@Override
	public void reload() {
		playerKilled = config.getString(ConfigMessages.PLAYER_KILLED);
		playerKilledSelf = config.getString(ConfigMessages.PLAYER_KILLED_SELF);
		playerEliminated = config.getString(ConfigMessages.PLAYER_ELIMINATED);
		playerLeft = config.getString(ConfigMessages.PLAYER_LEFT);
		teamEliminated = config.getString(ConfigMessages.TEAM_ELIMINATED);
		nexusDestroyed = config.getString(ConfigMessages.NEXUS_DESTROYED);
		moneyGained = config.getString(ConfigMessages.MONEY_GAINED);
		playerGotPowerup = config.getString(ConfigMessages.PLAYER_GOT_POWERUP);
	}

	public String getPlayerGotPowerup() {
		return playerGotPowerup;
	}

	public String getPlayerKilled() {
		return playerKilled;
	}

	public String getPlayerKilledSelf() {
		return playerKilledSelf;
	}

	public String getPlayerEliminated() {
		return playerEliminated;
	}

	public String getPlayerLeft() {
		return playerLeft;
	}

	public String getTeamEliminated() {
		return teamEliminated;
	}

	public String getNexusDestroyed() {
		return nexusDestroyed;
	}

	public String getMoneyGained() {
		return moneyGained;
	}
}
