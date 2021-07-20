package it.tristana.spacewars.database;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;

import it.tristana.commons.database.BasicUser;

public class SpaceUser extends BasicUser {
	
	private int wins;
	private int games;
	private int kills;
	private int deaths;
	private int nexuses;
	private int powerups;
	private int spent;
	
	public SpaceUser(@Nullable Player player) {
		super(player);
	}

	public SpaceUser(@Nullable Player player, int wins, int games, int kills, int deaths, int nexuses, int powerups, int spent) {
		super(player);
		this.wins = wins;
		this.games = games;
		this.kills = kills;
		this.deaths = deaths;
		this.nexuses = nexuses;
		this.powerups = powerups;
		this.spent = spent;
	}

	public void onWin() {
		wins ++;
	}
	
	public int getWins() {
		return wins;
	}
	
	public void onGame() {
		games ++;
	}

	public int getGames() {
		return games;
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

	public void onNexus() {
		nexuses ++;
	}
	
	public int getNexuses() {
		return nexuses;
	}

	public void onPowerup() {
		powerups ++;
	}
	
	public int getPowerups() {
		return powerups;
	}

	public void onSpent(int spent) {
		this.spent += spent;
	}
	
	public int getSpent() {
		return spent;
	}
}
