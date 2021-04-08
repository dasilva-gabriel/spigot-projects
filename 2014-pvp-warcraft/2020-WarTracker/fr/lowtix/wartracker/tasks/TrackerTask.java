package fr.lowtix.wartracker.tasks;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.lowtix.wartracker.WarTracker;

public class TrackerTask extends BukkitRunnable {

	@Override
	public void run() {

		HashMap<String, String> toChange = new HashMap<String, String>();

		for (String s : WarTracker.getInstance().getTrackers().keySet()) {

			Player p = Bukkit.getPlayer(s);
			if (!(p == null || !p.isOnline())) {

				if (p.getInventory().contains(Material.COMPASS)) {
					
					if(WarTracker.getInstance().getTime().get(s) < System.currentTimeMillis()) {
						p.sendMessage("§8[§6§l?§8] §7Les §f20 secondes §7de Track sont écoulés...");
						
						WarTracker.getInstance().getTime().remove(s);
						continue;
					}
					
					double range = getRangeByPerm(p);
					for (Entity en : p.getNearbyEntities(range, range/2, range)) {
						if (en instanceof Player) {

							Player p1 = (Player) en;
							if (p1.getGameMode().equals(GameMode.SURVIVAL) || p1.getGameMode().equals(GameMode.ADVENTURE)) {
								if (p.canSee(p1)) {
									if(!WarTracker.getInstance().getTrackers().get(s).equals(p1.getName())) {
										p.sendMessage("§8[§6§l?§8] §7Vous trackez "+p1.getName()+" §7qui est a §a" + ((int)p1.getLocation().distance(p.getLocation()))+ "§7. (§7§oRayon de recherche: §f§o"+range+"m§7)");
									}
									toChange.put(s, p1.getName());
								}
							}
						}
					}
				} else {
					p.sendMessage("§8[§6§l?§8] §cVous n'avez plus de boussole, votre mode track est désactivé.");
				}
			}

		}

		for (String s : WarTracker.getInstance().getTrackers().keySet()) {
			if(!toChange.containsKey(s)) {
				Player p = Bukkit.getPlayer(s);
				if (!(p == null || !p.isOnline())) {
					p.sendMessage("§8[§6§l?§8] §7Aucun joueur n'a été trouvé dans les environs...");
				}
			}
		}
		
		WarTracker.getInstance().getTrackers().clear();

		for (Map.Entry<String, String> e : toChange.entrySet()) {
			WarTracker.getInstance().getTrackers().put(e.getKey(), e.getValue());

		}

	}

	private double getRangeByPerm(Player p) {
		
		if(p.hasPermission("dayz.tracker.range5")) return 300.0D;
		else if(p.hasPermission("dayz.tracker.range4")) return 250.0D;
		else if(p.hasPermission("dayz.tracker.range3")) return 200.0D;
		else if(p.hasPermission("dayz.tracker.range2")) return 150.0D;
		else if(p.hasPermission("dayz.tracker.range1")) return 100.0D;
		
		return 50.0;
	}

}
