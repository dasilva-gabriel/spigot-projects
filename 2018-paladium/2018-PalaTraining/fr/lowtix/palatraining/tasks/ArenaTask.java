package fr.lowtix.palatraining.tasks;

import org.bukkit.scheduler.BukkitRunnable;

import fr.lowtix.palatraining.PalaTraining;
import fr.lowtix.palatraining.manager.GameArenasManager;

public class ArenaTask extends BukkitRunnable {

	@Override
	public void run() {
		
		GameArenasManager gam = PalaTraining.getInstance().getGameArenasManager();
		
		if(gam.getReadyArenas(true).size() <= 2) {
			gam.createArena(true);
		}
		
		if(gam.getReadyArenas(false).size() <= 4) {
			gam.createArena(false);
		}
		
	}

}
