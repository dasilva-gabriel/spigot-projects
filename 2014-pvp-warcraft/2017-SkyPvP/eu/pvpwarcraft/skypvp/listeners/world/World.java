package eu.pvpwarcraft.skypvp.listeners.world;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class World implements Listener {

	@EventHandler
	public void onWheatherChange(WeatherChangeEvent e) {
		if ((!e.getWorld().hasStorm()) || (!e.getWorld().isThundering()))
			e.setCancelled(true);
	}

	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}

}
