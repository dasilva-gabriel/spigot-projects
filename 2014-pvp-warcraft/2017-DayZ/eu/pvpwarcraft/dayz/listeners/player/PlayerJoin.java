package eu.pvpwarcraft.dayz.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import eu.pvpwarcraft.dayz.DayZ;
import eu.pvpwarcraft.dayz.events.UserJoinEvent;
import eu.pvpwarcraft.dayz.users.DayzPlayer;

public class PlayerJoin implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		event.setJoinMessage(null);
		DayZ.getInstance().joinedAPlayer(event.getPlayer());
	}
	
	@EventHandler
	public void onJoin(UserJoinEvent event){
		DayzPlayer dzp = event.getUser();
		dzp.setThirst(200);
		
		DayZ.getInstance().checkErrors();
		
		if(dzp.getPlayer().isOp() && DayZ.getInstance().errors.isEmpty()){
			dzp.getPlayer().sendMessage(DayZ.getInstance().prefix + " §aAucune erreurs à déclarer.");
		} else if (dzp.getPlayer().isOp() && !DayZ.getInstance().errors.isEmpty()){
			for(int i = 0; i < DayZ.getInstance().errors.size(); i++){
				dzp.getPlayer().sendMessage(DayZ.getInstance().errors.get(i));
			}
		}
	}

}
