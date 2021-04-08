package fr.lowtix.warcore.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;

public class KillListeners implements Listener {
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		WarPlayer wPlayer = WarCore.getInstance().getUser(player);
		
		wPlayer.setDeaths(wPlayer.getDeaths() + 1);
		
		if(player.getKiller() != null) {
			Player attacker = player.getKiller();
			WarPlayer wAtck = WarCore.getInstance().getUser(attacker);
			
			wAtck.setKills(wAtck.getKills() + 1);

			double base = 15;
			double gain = WarCore.getInstance().booster.getKillBoost(base);
			
			gain = Math.floor(gain*100)/100;
			
			wAtck.addPoints(base);
			wAtck.addPoints(gain);
			attacker.sendMessage("§8[§e§l!§8] §7Vous avez reçu §f"+base+" ✴ §8(§7§oBoost de §e§o"+gain+" points§8)");
		}
	}

}
