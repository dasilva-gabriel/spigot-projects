package fr.lowtix.palatraining.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class ResetListeners implements Listener {

	@EventHandler
	public void onPlace(BlockPlaceEvent event) {

		Player player = event.getPlayer();

		if (!player.getGameMode().equals(GameMode.CREATIVE) && !player.isOp()) {
			event.setCancelled(true);
		}

	}

	@EventHandler
	public void onBreak(BlockBreakEvent event) {

		Player player = event.getPlayer();

		if (!player.getGameMode().equals(GameMode.CREATIVE) && !player.isOp()) {
			event.setCancelled(true);
		}

	}

	@EventHandler
	public void onFood(FoodLevelChangeEvent event) {

		event.setCancelled(true);
		((Player) event.getEntity()).setFoodLevel(20);

	}

	@EventHandler
	public void onWeatherChange(WeatherChangeEvent e) {
		e.setCancelled(e.toWeatherState());
	}

}
