package it.tristana.spacewars.config;

import java.io.File;

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
	private String kitChosen;
	private String notEnoughMoney;
	private String maxItemLevelReached;
	private String itemBought;

	public SettingsMessages(File folder) {
		super(folder, ConfigMessages.class);
	}

	@Override
	protected void reload(ConfigMessages config) {
		playerKilled = config.getString(ConfigMessages.PLAYER_KILLED);
		playerKilledSelf = config.getString(ConfigMessages.PLAYER_KILLED_SELF);
		playerEliminated = config.getString(ConfigMessages.PLAYER_ELIMINATED);
		playerLeft = config.getString(ConfigMessages.PLAYER_LEFT);
		teamEliminated = config.getString(ConfigMessages.TEAM_ELIMINATED);
		nexusDestroyed = config.getString(ConfigMessages.NEXUS_DESTROYED);
		moneyGained = config.getString(ConfigMessages.MONEY_GAINED);
		playerGotPowerup = config.getString(ConfigMessages.PLAYER_GOT_POWERUP);
		kitChosen = config.getString(ConfigMessages.KIT_CHOSEN);
		notEnoughMoney = config.getString(ConfigMessages.NOT_ENOUGH_MONEY);
		maxItemLevelReached = config.getString(ConfigMessages.MAX_ITEM_LEVEL_REACHED);
		itemBought = config.getString(ConfigMessages.ITEM_BOUGHT);
	}

	public String getNotEnoughMoney() {
		return notEnoughMoney;
	}

	public String getMaxItemLevelReached() {
		return maxItemLevelReached;
	}

	public String getItemBought() {
		return itemBought;
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

	public String getKitChosen() {
		return kitChosen;
	}
}
