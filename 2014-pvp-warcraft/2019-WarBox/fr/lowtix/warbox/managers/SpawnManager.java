package fr.lowtix.warbox.managers;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import fr.lowtix.warbox.WarBox;

public class SpawnManager {

	public Map<Player, BukkitTask> tasks = new HashMap<>();

	public void teleportToSpawn(Player player) {
		
		if(player.isOp()) {
			player.teleport(WarBox.getInstance().getSpawn());
			player.sendMessage("§8[§3§lW§fInfo§8] §6Vous êtes avez été téléporté au spawn.");
		} else {
			player.sendMessage("§8[§3§lW§fInfo§8] §6Vous allez être téléporté au spawn dans §b4 secondes§6...");
			if (!this.tasks.containsKey(player)) {
				this.tasks.put(player, new BukkitRunnable() {
					public void run() {
						player.teleport(WarBox.getInstance().getSpawn());
						player.sendMessage("§8[§3§lW§fInfo§8] §6Vous avez été téléporté au spawn.");
						WarBox.getInstance().getSpawnManager().tasks.remove(player);
					}
				}.runTaskLater(WarBox.getInstance(), 20L * 4));
			}
		}
	}

}
