package fr.lowtix.palatraining.tasks;

import org.bukkit.scheduler.BukkitRunnable;

import fr.lowtix.palatraining.PalaTraining;
import fr.lowtix.palatraining.handlers.Game;

public class GameTask extends BukkitRunnable {

	@Override
	public void run() {
		
		for(Game game : PalaTraining.getInstance().getGameManager().getGames().values()) {
			game.tickTimer();
		}
		
	}

}
