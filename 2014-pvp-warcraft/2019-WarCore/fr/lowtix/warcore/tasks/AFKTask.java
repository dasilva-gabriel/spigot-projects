package fr.lowtix.warcore.tasks;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.enums.Ranks;

public class AFKTask extends BukkitRunnable {

	@Override
	public void run() {
		
		for(WarPlayer warPlayer : WarCore.getInstance().getUsers().values()) {
			
			Player player = warPlayer.getPlayer();
			
			if(!warPlayer.getRank().isHigher(Ranks.TRIAL_MOD)) {
				if(warPlayer.afkPoints > 15) {
					warPlayer.afkToKick = true;
					warPlayer.reloadAfkMsg();
					player.sendMessage("§c§lVous êtes considéré comme AFK.");
				} else if(warPlayer.afkPoints == 14) {
					player.sendMessage("§8[§e§l!§8] §cAttention, vous serez expulsé dans §e5 minutes §cpour inactivité.");
					player.sendMessage("§8[§e§l!§8] §7Si vous n'êtes pas AFK, une commande vous sera demandée d'être exécuté pour le prouver.");
				} else if(warPlayer.afkPoints == 15) {
					player.sendMessage("§8[§e§l!§8] §cAttention, vous serez expulsé dans §e1 minute §cpour inactivité.");
					player.sendMessage("§8[§e§l!§8] §7Si vous n'êtes pas AFK faites §b/noafk "+warPlayer.afkMsg+"§7.");
					warPlayer.afkMustCommand = true;
				}
			}
			
			if(player.getLocation().distance(warPlayer.lastAFKLoc) <= 10) {
				warPlayer.afkPoints++;
			} else {
				warPlayer.afkPoints = 0;
			}
			
			warPlayer.lastAFKLoc = player.getLocation();
		}
		
	}

}
