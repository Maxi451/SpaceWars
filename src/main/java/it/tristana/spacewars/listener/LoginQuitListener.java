package it.tristana.spacewars.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import it.tristana.spacewars.Main;

public class LoginQuitListener implements Listener {

	private Main plugin;
	
	public LoginQuitListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		plugin.onPlayerJoin(event.getPlayer());
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		plugin.onPlayerQuit(event.getPlayer());
	}
}
