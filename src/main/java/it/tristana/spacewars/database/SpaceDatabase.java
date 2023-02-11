package it.tristana.spacewars.database;

import java.sql.SQLException;

import org.bukkit.OfflinePlayer;

import it.tristana.commons.database.DatabaseManager;
import it.tristana.spacewars.Main;

public class SpaceDatabase extends DatabaseManager<SpaceUser> {

	private Main plugin;
	private String tablePlayers;

	public SpaceDatabase(String host, String database, String username, String password, int port, Main plugin, String tablePlayers) throws SQLException {
		super(host, database, username, password , port);
		this.plugin = plugin;
		this.tablePlayers = tablePlayers;
		createTables();
	}

	@Override
	public SpaceUser getUser(OfflinePlayer player) {
		try {
			return executeQuery(String.format("SELECT wins, kills, deaths, games FROM %s WHERE uuid = '%s';", tablePlayers, getUuid(player)), resultSet -> {
				try {
					if (resultSet.next()) {
						return new SpaceUser(player.getPlayer(), resultSet.getInt("wins"), resultSet.getInt("kills"), resultSet.getInt("deaths"), resultSet.getInt("games"));
					}
				} catch (SQLException e) {
					plugin.writeThrowableOnErrorsFile(e);
				}
				return new SpaceUser(player.getPlayer());
			});
		} catch (SQLException e) {
			plugin.writeThrowableOnErrorsFile(e);
		}
		return new SpaceUser(player.getPlayer());
	}

	@Override
	public void saveUser(SpaceUser user) {
		String uuid = getUuid(user.getPlayer());
		try {
			executeUpdate(String.format("REPLACE INTO %s (uuid, wins, kills, deaths, games) VALUES ('%s', %d, %d, %d, %d);", tablePlayers, uuid, user.getWins(), user.getKills(), user.getDeaths(), user.getGames()));
		} catch (SQLException e) {
			plugin.writeThrowableOnErrorsFile(e);
		}
	}

	@Override
	public void createTables() throws SQLException {
		executeUpdate("CREATE TABLE IF NOT EXISTS " + tablePlayers + "("
				+ "uuid CHAR(36) PRIMARY KEY,"
				+ "wins INTEGER NOT NULL DEFAULT 0,"
				+ "kills INTEGER NOT NULL DEFAULT 0,"
				+ "deaths INTEGER NOT NULL DEFAULT 0,"
				+ "games INTEGER NOT NULL DEFAULT 0"
				+ ");"
				);
	}
}
