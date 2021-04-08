package fr.lowtix.wartracker.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.lowtix.wartracker.WarTracker;
import fr.lowtix.wartracker.utils.PlayerUtils;

public class CompassTask extends BukkitRunnable {

	@Override
	public void run() {
		for(String s : WarTracker.getInstance().getTrackers().keySet()) {
			Player p = Bukkit.getPlayer(s);
			Player track = Bukkit.getPlayer(WarTracker.getInstance().getTrackers().get(s));
			if(!(p == null || !p.isOnline()) && !(track == null || !track.isOnline()) && p.getWorld().equals(track.getWorld())) {
				if(p.getInventory().contains(Material.COMPASS)) {
					p.setCompassTarget(track.getLocation());
					PlayerUtils.sendActionBar(p, "§e§l" + track.getName() + " §7- §f§l" + track.getLocation().getBlock().getLocation().distance(p.getLocation().getBlock().getLocation()) + "m");
				}
			}
		}
	}

}
