package fr.lowtix.warcore.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.utils.PlayerUtils;

public class BoardTask extends BukkitRunnable {

	@Override
	public void run() {
		
		int online = Bukkit.getOnlinePlayers().size();
		
		for(Player player : Bukkit.getOnlinePlayers()) {
			if(WarCore.getInstance().essentials.getVanishedPlayers().contains(player.getName())) {
				online -= 1;
			}
		}
		
		for(WarPlayer users : WarCore.getInstance().getUsers().values()) {
			
			if(!users.getPlayer().isOnline()) {
				WarCore.getInstance().deletePlayer(users);
				return;
			}
			
			users.board.updateScore(true);
			PlayerUtils.setupTabulation(users.getPlayer(), "§f §r\n§6§lPVP-WARCRAFT§r\n§7Besoin d'aide? §f/question§r\n§f §r", "§f §r\n§e"+online+" joueur(s) §7en ligne§r\n§f §r");
		}		
	}

}
