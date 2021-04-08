package fr.lowtix.warbox.tasks;

import org.bukkit.scheduler.BukkitRunnable;

import fr.lowtix.warbox.WarBox;
import fr.lowtix.warbox.players.WarPlayer;

public class BoardTask extends BukkitRunnable {

	@Override
	public void run() {
		
		for(WarPlayer wp : WarBox.getInstance().getUsers().values()) {
			try {
				if(wp.canSave) {
					WarBox.getInstance().getBoardManager().updateScore(wp.getPlayer());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
