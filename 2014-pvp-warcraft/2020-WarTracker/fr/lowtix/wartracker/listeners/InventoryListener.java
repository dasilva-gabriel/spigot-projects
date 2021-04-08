package fr.lowtix.wartracker.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import fr.lowtix.wartracker.WarTracker;

public class InventoryListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onTrack(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack it = p.getItemInHand();
		if (it!= null && it.getType().equals(Material.COMPASS)) {
			e.setCancelled(true);
			if(!WarTracker.getInstance().getTrackers().containsKey(p.getName())) {
				
				if(WarTracker.getInstance().getCooldown().containsKey(p.getName())) {
					long l = System.currentTimeMillis() - WarTracker.getInstance().getCooldown().get(p.getName());
					if(l/1000 < getTimeByPerm(p)) {
						p.sendMessage("§8[§6§l?§8] §7Vous devez patienter §c" + ((int) getTimeByPerm(p) - (l/1000)) + "s §7pour pouvoir réutiliser cela.");
						return;
					}
					
				}
				
				WarTracker.getInstance().getTrackers().put(p.getName(), p.getName());
				p.sendMessage("§8[§6§l?§8] §7Recherche d'un joueur...");
				WarTracker.getInstance().getCooldown().put(p.getName(), System.currentTimeMillis());
				WarTracker.getInstance().getTime().put(p.getName(), System.currentTimeMillis() + (20*1000));
			}
		}
	}
	
	private int getTimeByPerm(Player p) {
		if(p.hasPermission("dayz.tracker.time3")) return 30;
		else if(p.hasPermission("dayz.tracker.time2")) return 60;
		else if(p.hasPermission("dayz.tracker.time1")) return 90;
		
		return 120;
	}

}
