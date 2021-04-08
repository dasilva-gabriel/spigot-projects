package fr.lowtix.warbox.managers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;

import fr.lowtix.warbox.players.WarPlayer;

public class LevelManager {
	
	public int getExpForLevel(int level) {
		return (int)( 1 + ((level+1) * 2.485));
	}
	
	public String getPrefix(int level) {
		
		if(level < 10) {
			return "§8[§7"+level+"§8]";
		} else if (level < 25) {
			return "§8[§f"+level+"§8]";
		} else if (level < 50) {
			return "§8[§a"+level+"§8]";
		} else if (level < 75) {
			return "§8[§2"+level+"§8]";
		} else if (level < 100) {
			return "§8[§e"+level+"§8]";
		} else if (level < 150) {
			return "§8[§6"+level+"§8]";
		} else if (level < 200) {
			return "§8[§d"+level+"§8]";
		} else if (level < 350) {
			return "§8[§5"+level+"§8]";
		} else if (level < 500) {
			return "§8[§9"+level+"§8]";
		} else if (level < 750) {
			return "§8[§1"+level+"§8]";
		} else if (level < 1000) {
			return "§8[§b"+level+"§8]";
		} else if (level < 1500) {
			return "§8[§3"+level+"§8]";
		} else if (level < 2500) {
			return "§8[§c"+level+"§8]";
		} else if (level >= 2500) {
			return "§8[§4"+level+"§8]";
		}
		
		return "§8[§7"+level+"§8]";
	}
	
	public void rankup(WarPlayer wp) {
		
		int level = wp.getLevel();
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		if(level%10 == 0) {
			boolean b = false;
			try {
				Date d1 = dateFormat.parse(wp.getStats().getLastRankUp());
				Date d2 = new Date();
				
				long diff = d2.getTime() - d1.getTime();
				
				long diffMinutes = diff / (60 * 1000) % 60;
				
				if(diffMinutes <= 20) {
					b= true;
					wp.getPlayer().sendMessage("§8[§3§lW§fInfo§8] §cPour rankup " + getPrefix(level) + " §f➟ " + getPrefix(level+1) + " §cveuillez attendre §e" + (20 - diffMinutes) +" minute(s)§c.");
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			if(b) {
				return;
			}
			
		}
		
		if(wp.getExp() < getExpForLevel(level+1)) {
			wp.getPlayer().sendMessage("§8[§3§lW§fInfo§8] §cIl vous faut encore §b" + (getExpForLevel(level+1) - wp.getExp()) + " §bexp §cpour rankup...");
			return;
		} else {
			wp.setLevel(level+1);
			wp.removeExp(getExpForLevel(level+1));
			wp.getStats().setLastRankUp(dateFormat.format(new Date()));
			wp.getPlayer().sendMessage("§8[§3§lW§fInfo§8] §6Rankup réussi, " + getPrefix(level) + " §f➟ " +getPrefix(level+1) + "§6.");
			
			if((level+1)%10 == 0) {
				Bukkit.broadcastMessage("§8[§6§l?§8] §eBravo §6" + wp.getName() + " §equi passe au niveau "+getPrefix(level+1));
			}
		}
		
	}

}
