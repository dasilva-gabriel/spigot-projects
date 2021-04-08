package eu.pvpwarcraft.meetup.listeners.players;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import eu.pvpwarcraft.meetup.handler.Step;
import eu.pvpwarcraft.meetup.managers.PlayersManager;

public class EntityDamageByEntity implements Listener {
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event){
		if(!(event.getEntity() instanceof Player)) return;
		if(!(event.getDamager() instanceof Player)) return;
		if(Step.isStep(Step.IN_GAME)){
			int damage = (int) event.getDamage();
			PlayersManager.addDG((Player) event.getDamager(), damage);
			PlayersManager.addDR((Player) event.getEntity(), damage);
		}
	}

}
