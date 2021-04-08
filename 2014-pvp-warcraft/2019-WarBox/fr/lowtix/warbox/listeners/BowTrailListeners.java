package fr.lowtix.warbox.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

import fr.lowtix.warbox.WarBox;
import fr.lowtix.warbox.enums.BowTrail;
import fr.lowtix.warbox.players.WarPlayer;

public class BowTrailListeners implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityShootBow(EntityShootBowEvent e) {
		if ((e.getEntity() instanceof Player)) {
			Player player = (Player) e.getEntity();
			WarPlayer user = WarBox.getInstance().getUser(player);
			Entity proj = e.getProjectile();
			
			if(!user.getStats().getTrail().equals(BowTrail.NONE)) {
				WarBox.getInstance().getBowTrailsManager().addArrow(proj, user.getStats().getTrail().getEffect());
			}

		}
	}

}
