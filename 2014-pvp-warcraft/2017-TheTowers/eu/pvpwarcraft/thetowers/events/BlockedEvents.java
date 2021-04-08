package eu.pvpwarcraft.thetowers.events;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import eu.pvpwarcraft.thetowers.handler.Step;
import eu.pvpwarcraft.thetowers.handler.Team;

public class BlockedEvents implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		if(event.getBlock().getType().toString().equals("SPONGE")){
			event.setCancelled(true);
			player.sendMessage("§6TheTowers §8» §cVous ne pouvez pas casser de Blocks ici.");
		}
		for (Team.Teams team : Team.Teams.values()) {
			if (event.getBlock().getLocation().distance(team.getSpawn()) < 4) {
				event.setCancelled(true);
				player.sendMessage("§6TheTowers §8» §cVous ne pouvez pas casser de Blocks ici.");
			}
		}
		if (!Step.isStep(Step.IN_GAME)) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		if (!Step.isStep(Step.IN_GAME)) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPistonMoveBlock(BlockPistonExtendEvent event) {
		for (Team.Teams team : Team.Teams.values()) {
			if (event.getBlock().getLocation().distance(team.getSpawn()) < 4) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onBlockFromTo(BlockFromToEvent event) {
		for (Team.Teams team : Team.Teams.values()) {
			if (event.getBlock().getLocation().distance(team.getSpawn()) < 4) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		for (Team.Teams team : Team.Teams.values()) {
			if (event.getBlock().getLocation().distance(team.getSpawn()) < 4) {
				event.setCancelled(true);
				player.sendMessage("§6TheTowers §8» §cVous ne pouvez pas placer de Blocks ici.");
			}
		}
		if (!Step.isStep(Step.IN_GAME)) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event) {
		World world = event.getWorld();
		if ((!world.isThundering()) && (!world.hasStorm()))
			event.setCancelled(true);
	}

	@EventHandler
	public void onThunderChange(ThunderChangeEvent event) {
		if (event.toThunderState())
			event.setCancelled(true);
	}

}
