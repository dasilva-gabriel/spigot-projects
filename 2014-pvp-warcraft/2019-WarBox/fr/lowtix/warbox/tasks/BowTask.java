package fr.lowtix.warbox.tasks;

import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import fr.lowtix.warbox.WarBox;

public class BowTask extends BukkitRunnable {

	@Override
	public void run() {
		
		if(!WarBox.getInstance().getBowTrailsManager().arrows.isEmpty() && WarBox.getInstance().getBowTrailsManager().arrows.size() != 0) {
			for(Entity ents : WarBox.getInstance().getBowTrailsManager().arrows.keySet()) {
				
				if(ents == null || ents.isDead() || ents.isOnGround()) {
					WarBox.getInstance().getBowTrailsManager().removeArrow(ents);
					return;
				}
				
				if(ents.getTicksLived() > 0) {
					WarBox.getInstance().getBowTrailsManager().spawnEffect(ents.getLocation(), WarBox.getInstance().getBowTrailsManager().arrows.get(ents));
				}
				
			}
		}
		
	}

}
