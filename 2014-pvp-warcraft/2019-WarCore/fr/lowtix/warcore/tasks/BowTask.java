package fr.lowtix.warcore.tasks;

import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import fr.lowtix.warcore.WarCore;

public class BowTask extends BukkitRunnable {

	@Override
	public void run() {
		
		if(!WarCore.getInstance().bowManager.arrows.isEmpty() && WarCore.getInstance().bowManager.arrows.size() != 0) {
			for(Entity ents : WarCore.getInstance().bowManager.arrows.keySet()) {
				if(ents == null || ents.isDead() || ents.isOnGround()) {
					WarCore.getInstance().bowManager.removeArrow(ents);
					return;
				}
				if(ents.getTicksLived() > 0) {
					WarCore.getInstance().bowManager.spawnEffect(ents.getLocation(), WarCore.getInstance().bowManager.arrows.get(ents));
				}
				
			}
		}
		
	}

}
