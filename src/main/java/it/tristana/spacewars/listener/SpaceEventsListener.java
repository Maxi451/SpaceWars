package it.tristana.spacewars.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import it.tristana.commons.helper.CommonsHelper;
import it.tristana.commons.interfaces.arena.ArenasManager;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.arena.player.kit.Kit;
import it.tristana.spacewars.config.SettingsMessages;
import it.tristana.spacewars.event.SpaceKitChosenEvent;

public class SpaceEventsListener implements Listener {

	private final ArenasManager<SpaceArena, SpacePlayer> arenasManager;
	private final SettingsMessages settings;

	public SpaceEventsListener(ArenasManager<SpaceArena, SpacePlayer> arenasManager, SettingsMessages settings) {
		this.arenasManager = arenasManager;
		this.settings = settings;
	}

	@EventHandler
	public void on(SpaceKitChosenEvent event) {
		Player player = event.getPlayer();
		SpacePlayer spacePlayer = arenasManager.getArenaPlayer(player);
		if (spacePlayer == null) {
			return;
		}

		Kit kit = event.getKit();
		spacePlayer.setKit(kit);
		CommonsHelper.info(player, CommonsHelper.replaceAll(settings.getKitChosen(), "{kit}", kit.getName()));
	}
}
