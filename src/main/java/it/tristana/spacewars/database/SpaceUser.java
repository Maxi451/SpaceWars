package it.tristana.spacewars.database;

import org.bukkit.OfflinePlayer;

import it.tristana.commons.database.BasicUser;

public class SpaceUser extends BasicUser {

	private int wins;
	private int kills;
	private int deaths;
	private int games;

	SpaceUser(OfflinePlayer player) {
		super(player);
	}

	public void setup(int wins, int kills, int deaths, int games) {
		this.wins = wins;
		this.kills = kills;
		this.deaths = deaths;
		this.games = games;
	}

	public void onWin() {
		wins ++;
	}

	public int getWins() {
		return wins;
	}

	public void onKill() {
		kills ++;
	}

	public int getKills() {
		return kills;
	}

	public void onDeath() {
		deaths ++;
	}

	public int getDeaths() {
		return deaths;
	}

	public void onGame() {
		games ++;
	}

	public int getGames() {
		return games;
	}
}
