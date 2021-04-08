package fr.lowtix.cheatpatch.schedulers;

import org.bukkit.scheduler.BukkitRunnable;

import fr.lowtix.cheatpatch.CheatPatch;
import fr.lowtix.cheatpatch.guis.MinerGui;
import fr.lowtix.cheatpatch.managers.miner.PlayerMiner;
import fr.lowtix.cheatpatch.sql.MinerSQL;

public class MinerScheduler extends BukkitRunnable {

	private int timer = 0;
	private long last = System.currentTimeMillis();
	
	@Override
	public void run() {
		
		timer ++;
		
		for(PlayerMiner pm : CheatPatch.getInstance().getMinerManager().getMiners().values()) {
			pm.addTimer((int) ((System.currentTimeMillis() - last)/1000));
		}
		
		if(timer % 5 == 0) {
			for(PlayerMiner pm : CheatPatch.getInstance().getMinerManager().getMiners().values()) {
				MinerSQL.save(pm);
			}
		}
		
		if(timer >= 300) {
			
			System.out.println("TICK");
			timer = 0;
			
			for(PlayerMiner pm : CheatPatch.getInstance().getMinerManager().getMiners().values()) {
				pm.tick();
			}
			
		}
		
		for(MinerGui gui : MinerGui.openGuis) {
			gui.drawScreen();
		}
		
	}

}
