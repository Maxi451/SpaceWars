package it.tristana.spacewars.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import it.tristana.commons.database.DatabaseManager;
import it.tristana.commons.helper.CommonsHelper;

public class SpaceDatabase extends DatabaseManager<SpaceUser> {

	private String tableName;
	
	public SpaceDatabase(String host, String database, String username, String password, int port, String tableName) {
		super(host, database, username, password, port);
		this.tableName = tableName;
	}

	@Override
	public SpaceUser getUser(OfflinePlayer offlinePlayer) {
		SpaceUser user;
		try {
			ResultSet resultSet = executeQuery("SELECT wins, games, kills, deaths, nexuses, powerups, spent FROM " + tableName + " where uuid = '" + getUuid(offlinePlayer) + "';");
			Player player = offlinePlayer.getPlayer();
			if (resultSet.next()) {
				user = new SpaceUser(player, resultSet.getInt("wins"), resultSet.getInt("games"), resultSet.getInt("kills"), resultSet.getInt("deaths"), resultSet.getInt("nexuses"),
						resultSet.getInt("powerups"), resultSet.getInt("spent"));
			}
			else {
				user = new SpaceUser(player);
			}
		} catch (SQLException e) {
			user = null;
			CommonsHelper.consoleInfo("Error " + e.getErrorCode() + " while executing the query!");
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void saveUser(SpaceUser user) {
		try {
			executeUpdate(String.format("UPDATE %s SET wins = %d, games = %d, kills = %d, deaths = %d, nexuses = %d, powerups = %d, spent = %d WHERE uuid = '%s'",
					tableName, user.getWins(), user.getGames(), user.getKills(), user.getDeaths(), user.getNexuses(), user.getPowerups(), user.getSpent(), getUuid(user.getPlayer())));
		} catch (SQLException e) {
			CommonsHelper.consoleInfo("Error " + e.getErrorCode() + " while executing the query!");
			e.printStackTrace();
		}
	}

	@Override
	protected void createTables() throws SQLException {
		executeUpdate("CREATE TABLE IF NOT EXISTS " + tableName + " ("
				+ "	uuid VARCHAR(36) PRIMARY KEY,"
				+ "	wins INTEGER NOT NULL DEFAULT 0,"
				+ "	games INTEGER NOT NULL DEFAULT 0,"
				+ "	kills INTEGER NOT NULL DEFAULT 0,"
				+ "	deaths INTEGER NOT NULL DEFAULT 0,"
				+ "	nexuses INTEGER NOT NULL DEFAULT 0,"
				+ "	powerups INTEGER NOT NULL DEFAULT 0,"
				+ "	spent INTEGER NOT NULL DEFAULT 0"
				+ ");");
	}
}
