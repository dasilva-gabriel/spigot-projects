package fr.lowtix.cheatpatch.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.lowtix.cheatpatch.CheatPatch;

public class ScoreboardScheduler extends BukkitRunnable {

	@Override
	public void run() {
		
		CheatPatch.getInstance().getCheatScoreboard().top += 1;
		if(CheatPatch.getInstance().getCheatScoreboard().top >= 5) {
			CheatPatch.getInstance().getCheatScoreboard().top = 1;
		}
		
		for(Player all : Bukkit.getOnlinePlayers()) {
			CheatPatch.getInstance().getCheatScoreboard().setup(all, false);
		}
		
	}

}
