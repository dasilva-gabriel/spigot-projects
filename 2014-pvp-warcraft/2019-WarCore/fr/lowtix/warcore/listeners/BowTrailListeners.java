package fr.lowtix.warcore.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

import fr.lowtix.warbox.enums.BowTrail;
import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;

public class BowTrailListeners implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityShootBow(EntityShootBowEvent e) {
		if ((e.getEntity() instanceof Player)) {
			Player player = (Player) e.getEntity();
			WarPlayer user = WarCore.getInstance().getUser(player);
			Entity proj = e.getProjectile();
			
			if(!user.getPlayerStats().trail.equals(BowTrail.NONE)) {
				WarCore.getInstance().bowManager.addArrow(proj, user.getPlayerStats().trail.getEffect());
			}

		}
	}

}
