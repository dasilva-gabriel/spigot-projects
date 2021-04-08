package fr.lowtix.warbox.managers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;

import fr.lowtix.warbox.WarBox;
import fr.lowtix.warbox.players.PlayerStats;
import fr.lowtix.warbox.players.WarPlayer;

public class DailyDropsManager {
	
	public static final int timeForPick = 20;
	
	public boolean hasDailyDrop(WarPlayer wp) {
		
		int diff = getDiffHour(wp);
		return (diff >= timeForPick);
		
	}

	public int getDiffHour(WarPlayer wp) {
		int res = 999;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		try {
			Date d1 = dateFormat.parse(wp.getStats().getLastDailyPickUp());
			Date d2 = new Date();
			
			long diff = d2.getTime() - d1.getTime();
			
			long diffHours = diff / (60 * 60 * 1000) % 24;
			
			res = (int) diffHours;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
		
	}
	
	public void pickup(WarPlayer wp) {
		
		if(!WarBox.getInstance().getDailyDropsManager().hasDailyDrop(wp)) {
			wp.getPlayer().sendMessage("§8[§3§lW§fInfo§8] §cVotre arrivage n'a pas encore été livré...");
			return;
		}
		
		wp.getStats().setLastDailyPickUp(PlayerStats.dateFormat.format(new Date()));
		wp.getPlayer().sendMessage("§8[§3§lW§fInfo§8] §6Vous avez récupéré votre arrivage du jour!");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cr give to " + wp.getName() + " CrateSuper 1 offline");
	}
	
}
