package it.tristana.spacewars.helper;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import it.tristana.commons.helper.PlayersManager;
import it.tristana.commons.interfaces.arena.ArenasManager;

public class SpacePlayersManager extends PlayersManager {

	private Plugin plugin;

	public SpacePlayersManager(Plugin plugin, ArenasManager<?> arenasManager) {
		super(arenasManager);
		this.plugin = plugin;
	}
	
	@Override
	protected void hidePlayer(Player p1, Player p2) {
		if (p1 != p2) {
			p1.hidePlayer(plugin, p2);
			p2.hidePlayer(plugin, p1);
		}
	}

	@Override
	protected void showPlayer(Player p1, Player p2) {
		if (p1 != p2) {
			p1.showPlayer(plugin, p2);
			p2.showPlayer(plugin, p1);
		}
	}
	
	@Override
	protected void heal(Player player) {
		player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
	}
}
