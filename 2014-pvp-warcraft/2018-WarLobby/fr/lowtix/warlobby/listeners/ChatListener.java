package fr.lowtix.warlobby.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		event.setCancelled(true);
		
		Player player = event.getPlayer();
		
		if(player.hasPermission("warlobby.admin")) {
			
			Bukkit.broadcastMessage("§8[§4A§8] §4"+player.getName()+" §8» §f"+event.getMessage());
		
		} else if(player.hasPermission("warlobby.resp")) {
			
			Bukkit.broadcastMessage("§8[§cR§8] §c"+player.getName()+" §8» §f"+event.getMessage());
		
		} else if(player.hasPermission("warlobby.mod")) {
			
			Bukkit.broadcastMessage("§8[§6M§8] §6"+player.getName()+" §8» §f"+event.getMessage());
		} else if(player.hasPermission("warlobby.staff")) {
			
			Bukkit.broadcastMessage("§8[§2S§8] §2"+player.getName()+" §8» §f"+event.getMessage());
		} else {
			
			Bukkit.broadcastMessage("§f"+player.getName()+" §8» §f"+event.getMessage());
			
		}
		
	}

}
