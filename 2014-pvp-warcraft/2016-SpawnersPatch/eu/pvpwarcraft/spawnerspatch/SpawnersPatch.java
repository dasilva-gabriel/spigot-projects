package eu.pvpwarcraft.spawnerspatch;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SpawnersPatch extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBreak(BlockBreakEvent event) {
		if (event.getBlock().getType().equals(Material.MOB_SPAWNER)) {
			if (!event.getPlayer().isOp()) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBreak(BlockExplodeEvent event) {
		if (event.getBlock().getType().equals(Material.MOB_SPAWNER)) {
			event.setCancelled(true);

		}
	}

}
