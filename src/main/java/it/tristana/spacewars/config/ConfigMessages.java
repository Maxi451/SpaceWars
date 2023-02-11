package it.tristana.spacewars.config;

import java.io.File;

import it.tristana.commons.config.Config;

public class ConfigMessages extends Config {

	public static final String PLAYER_KILLED = "player-killed";
	public static final String PLAYER_KILLED_SELF = "player-killed-self";
	public static final String PLAYER_ELIMINATED = "player-eliminated";
	public static final String PLAYER_LEFT = "player-left";
	public static final String TEAM_ELIMINATED = "team-eliminated";
	public static final String NEXUS_DESTROYED = "nexus-destroyed";
	public static final String MONEY_GAINED = "money-gained";
	public static final String PLAYER_GOT_POWERUP = "player-got-powerup";
	public static final String KIT_CHOSEN = "kit-chosen";
	public static final String NOT_ENOUGH_MONEY = "not-enough-money";
	public static final String MAX_ITEM_LEVEL_REACHED = "max-item-level-reached";
	public static final String ITEM_BOUGHT = "item-bought";
	public static final String CANT_BUY_THIS_NOW = "cant-buy-this-now";

	public ConfigMessages(File folder) {
		super(new File(folder, "messages.yml"));
	}

	@Override
	protected void createDefault() {
		set(PLAYER_KILLED, "{player color}{player} has been killed by {killer color}{killer}&f!");
		set(PLAYER_KILLED_SELF, "{player color}{player} &fhas killed himself");
		set(PLAYER_ELIMINATED, "{player color}{player} &fhas been eliminated!");
		set(PLAYER_LEFT, "{player color}{player} &fhas left the game");
		set(TEAM_ELIMINATED, "{team color}{team} &fhas been eliminated!");
		set(NEXUS_DESTROYED, "{team color}{team}&f's nexus has been &cdestroyed &fby {player color}{player}&f!");
		set(MONEY_GAINED, "You gained &6{money} &fmoney");
		set(PLAYER_GOT_POWERUP, "{player color}{player} picked up &6{powerup}");
		set(KIT_CHOSEN, "You have chosen the kit {kit}&r! Use '&b/sw kit&r' to change it again");
		set(NOT_ENOUGH_MONEY, "&cYou need {money} more money!");
		set(MAX_ITEM_LEVEL_REACHED, "&cYou already reached the maximum level for {item}!");
		set(ITEM_BOUGHT, "You bought &6{item}");
		set(CANT_BUY_THIS_NOW, "&cYou can't buy this now!");
	}
}
