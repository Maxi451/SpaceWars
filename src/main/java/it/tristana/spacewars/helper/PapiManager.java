package it.tristana.spacewars.helper;

import static java.lang.String.valueOf;

import org.bukkit.entity.Player;

import it.tristana.commons.helper.CommonsHelper;
import it.tristana.commons.interfaces.database.UsersManager;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.database.SpaceUser;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

/**
 * Hooks into PlaceholderAPI
 * @author TheSniper99
 *
 */

public final class PapiManager extends PlaceholderExpansion {

	/**
	 * A reference to the main class instance
	 */
	
	private Main plugin;
	private UsersManager<SpaceUser> usersManager;

	/**
	 * @param plugin The plugin instance
	 */

	public PapiManager(Main plugin, UsersManager<SpaceUser> usersManager) {
		this.plugin = plugin;
		this.usersManager = usersManager;
	}

	/**
	 * PlaceholderAPI complains if this isn't put here, so here it is
	 */

	@Override
	public boolean register() {
		return super.register();
	}

	/**
	 * The authors: Massimiliano Micol and wizzo!
	 */

	@Override
	public String getAuthor() {
		return CommonsHelper.playerListToString(plugin.getDescription().getAuthors(), "and", "nobody");
	}

	/**
	 * The plugin identifier
	 */

	@Override
	public String getIdentifier() {
		return "sw";
	}

	/**
	 * Gets the plugin version
	 */

	@Override
	public String getVersion() {
		return plugin.getDescription().getVersion();
	}

	/**
	 * ThePit registers its placeholders directly in the plugin
	 */
	
	@Override
    public boolean persist() {
        return true;
    }

	/**
	 * Available placeholders:
	 * <ul>
	 * 	<li>%sw_player%</li>
	 * 	<li>%sw_kills%</li>
	 * 	<li>%sw_deaths%</li>
	 * 	<li>%sw_wins%</li>
	 * </ul>
	 */
	
	@Override
	public String onPlaceholderRequest(final Player player, String identifier) {
		if (player == null) {
			return "*** PLAYER OFFLINE ***";
		}
		
		identifier = identifier.toLowerCase();
		SpaceUser user = usersManager.getUser(player);
		switch (identifier) {
		case "player":
			return valueOf(player.getName());
		case "kills":
			return valueOf(user.getKills());
		case "deaths":
			return valueOf(user.getDeaths());
		case "wins":
			return valueOf(user.getWins());
		default:
			break;
		}
		if (!identifier.startsWith("top_")) {
			return null;
		}
		identifier = identifier.substring("top_".length());
		return null;
	}
}