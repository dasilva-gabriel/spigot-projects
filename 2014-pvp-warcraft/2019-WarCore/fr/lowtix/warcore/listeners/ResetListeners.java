package fr.lowtix.warcore.listeners;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class ResetListeners implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		event.setJoinMessage(null);
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		event.setQuitMessage(null);
	}

	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event) {
		World world = event.getWorld();
		if ((!world.isThundering()) && (!world.hasStorm())) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onThunderChange(ThunderChangeEvent event) {
		if (event.toThunderState()) {
			event.setCancelled(true);
		}
	}

}
