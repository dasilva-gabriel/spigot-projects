package fr.lowtix.cheatpatch.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.lowtix.cheatpatch.CheatPatch;
import fr.lowtix.cheatpatch.PlayerWrapper;

public class KillListener implements Listener {
	
	@EventHandler
	public void onKill(PlayerDeathEvent event) {
	
		PlayerWrapper pw = CheatPatch.getInstance().getPlayer(event.getEntity().getName());
		pw.setDeaths(pw.getDeaths()+1);
		
		if(event.getEntity().getKiller() != null) {
			PlayerWrapper pw1 = CheatPatch.getInstance().getPlayer(event.getEntity().getKiller().getName());
			pw1.setKills(pw1.getKills()+1);
		}
		
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDamage(EntityDamageByEntityEvent event) {
		if(!event.isCancelled() && event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
			((Player) event.getDamager()).performCommand("mc off");
			((Player) event.getEntity()).performCommand("mc off");
		}
	}
}
