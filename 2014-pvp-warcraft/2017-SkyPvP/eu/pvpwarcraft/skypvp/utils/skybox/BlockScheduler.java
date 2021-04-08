package eu.pvpwarcraft.skypvp.utils.skybox;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockScheduler extends BukkitRunnable {

	@Override
	public void run() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.getItemOnCursor().getType() == Material.SEA_LANTERN) {
				p.getItemOnCursor().setType(Material.AIR);
			}
			if (p.getItemInHand().getType() == Material.SEA_LANTERN) {
				p.getItemInHand().setType(Material.AIR);
			}
			if(p.hasPermission("group.default")){
				p.setPlayerListName("§7 "+p.getName());
			}
			if(p.hasPermission("group.skyknight")){
				p.setPlayerListName("§3⚔ §b"+p.getName());
			}
			if(p.hasPermission("group.skyking")){
				p.setPlayerListName("§b✩ §3"+p.getName());
			}
			if(p.hasPermission("group.mod")){
				p.setPlayerListName("§8» §2"+p.getName());
			}
			if(p.hasPermission("group.admin")){
				p.setPlayerListName("§8» §c"+p.getName());
			}
			if(p.hasPermission("group.fonda")){
				p.setPlayerListName("§8» §4"+p.getName());
			}
		}
		for (Entity all : Bukkit.getWorld("world").getEntities()) {
			if (all instanceof ArmorStand) {
				all.remove();
			}
		}
		SkyBoxSpawn.spawn();

	}

}
