package fr.lowtix.warcore.tasks;

import org.bukkit.scheduler.BukkitRunnable;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;

public class TimeTask extends BukkitRunnable {

	@Override
	public void run() {
		
		WarCore.getInstance().booster.checkBooster();
		
		for(WarPlayer all : WarCore.getInstance().getUsers().values()) {
			all.getPlayerStats().timePlayed ++;
			
		}
		
	}

}
