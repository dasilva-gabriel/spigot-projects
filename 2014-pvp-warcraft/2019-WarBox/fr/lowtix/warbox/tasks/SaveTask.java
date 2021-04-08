package fr.lowtix.warbox.tasks;

import org.bukkit.scheduler.BukkitRunnable;

import fr.lowtix.warbox.WarBox;
import fr.lowtix.warbox.players.WarPlayer;

public class SaveTask extends BukkitRunnable {

	@Override
	public void run() {
		
		for(WarPlayer wp : WarBox.getInstance().getUsers().values()) {
			if(wp.canSave) {
				wp.save();
			}
		}
		
	}

}
