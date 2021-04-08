package fr.lowtix.warbox.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitTask;

import fr.lowtix.warbox.WarBox;

public class SpawnListeners implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (WarBox.getInstance().getSpawnManager().tasks.containsKey(p) && (e.getFrom().getBlockX() != e.getTo().getBlockX()) || (e.getFrom().getBlockY() != e.getTo().getBlockY()) || (e.getFrom().getBlockZ() != e.getTo().getBlockZ())) {
			BukkitTask task = (BukkitTask) WarBox.getInstance().getSpawnManager().tasks.get(p);
			if (task != null) {
				task.cancel();
				WarBox.getInstance().getSpawnManager().tasks.remove(p);
				p.sendMessage("§8[§3§lW§fInfo§8] §cVous ne devez pas bouger pendant votre téléportation au spawn!");
			}
		}
	}

}
