package eu.pvpwarcraft.warfightapi.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import eu.pvpwarcraft.warfightapi.WarFightAPI;
import eu.pvpwarcraft.warfightapi.events.UserQuitEvent;
import eu.pvpwarcraft.warfightapi.managers.User;

public class PlayerQuit implements Listener {
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event){
		User user = WarFightAPI.getInstance().getUsers().get(event.getPlayer().getName());
		
		Bukkit.getPluginManager().callEvent(new UserQuitEvent(user));
		
		user.save();
	}

}
