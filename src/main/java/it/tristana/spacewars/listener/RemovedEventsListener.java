package it.tristana.spacewars.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.WorldLoadEvent;

public class RemovedEventsListener implements Listener {

	@EventHandler
	public void on(FoodLevelChangeEvent event) {
		event.setCancelled(true);
		event.getEntity().setSaturation(2.4f);
	}
	
	@EventHandler
	public void on(WeatherChangeEvent event) {
		if (event.getWorld().isClearWeather()) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void on(WorldLoadEvent event) {
		event.getWorld().setSpawnFlags(false, false);
	}
}
