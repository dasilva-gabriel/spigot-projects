package fr.lowtix.cheatpatch.schedulers;

import org.bukkit.scheduler.BukkitRunnable;

import fr.lowtix.cheatpatch.CheatPatch;
import fr.lowtix.cheatpatch.PlayerWrapper;

public class TopScheduler extends BukkitRunnable {

	@Override
	public void run() {
		
		CheatPatch.getInstance().getTopManager().reloadTop();
		
		for(PlayerWrapper wp : CheatPatch.getInstance().getPlayers().values()) {
			wp.save();
		}
		
	}

}
