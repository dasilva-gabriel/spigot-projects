package eu.pvpwarcraft.dayz.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import eu.pvpwarcraft.dayz.DayZ;
import eu.pvpwarcraft.dayz.sql.DayzSQL;

public class PlayerQuit implements Listener {
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event){
		event.setQuitMessage(null);
		DayzSQL.registerPlayer(DayZ.getPlayer(event.getPlayer()));
	}

}
