package fr.lowtix.warbox.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.lowtix.warbox.WarBox;

public class WorldTask extends BukkitRunnable {

	@Override
	public void run() {
		
		for(Player p : Bukkit.getWorld("world").getPlayers()) {
			p.teleport(WarBox.getInstance().getSpawn());
		}
		
	}

}
