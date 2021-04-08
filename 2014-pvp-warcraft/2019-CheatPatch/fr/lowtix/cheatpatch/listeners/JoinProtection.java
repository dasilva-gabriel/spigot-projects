package fr.lowtix.cheatpatch.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinProtection implements Listener {
	
	public ArrayList<String> players = new ArrayList<String>();
	
	public JoinProtection() {
		players.add("lowtix_");
		players.add("xtempete");
		players.add("energykev");
		players.add("uneglacepassion");
	}
	
	public void onJoin(PlayerJoinEvent event) {
		boolean b = checkHack(event.getPlayer());
		if(b || event.getPlayer().getAddress().getAddress().getHostAddress().contains("0.0.0") || event.getPlayer().getAddress().getAddress().getHostAddress().contains("127.0.0") || event.getPlayer().getAddress().getAddress().getHostAddress().startsWith("0.")) {
			
			event.getPlayer().kickPlayer("&4&lVous ne pouvez pas rejoindre (Hack)");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ipban " + event.getPlayer().getName() + " Tentative de Hack");
			Bukkit.banIP(event.getPlayer().getAddress().getAddress().getHostAddress());
			
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage("§8[§4§l!§8] §cAttention, §e"+event.getPlayer().getName()+" §ca tenté d'entrer sur le serveur (HACK)");
			Bukkit.broadcastMessage("");
		}
		
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event) {
		boolean b = checkHack(event.getPlayer());
		
		if(b) {
			event.setCancelled(true);
		}
		
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		boolean b = checkHack(event.getPlayer());
		
		if(b) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onFood(FoodLevelChangeEvent event) {
		Player p = (Player) event.getEntity();
		boolean b = checkHack(p);
		
		if(b) {
			event.setCancelled(true);
		}
	}
	
	public boolean checkHack(Player player) {
		
		if(!players.contains(player.getName().toLowerCase())) {
			if(player.isOp() || player.hasPermission("*")) {
				player.kickPlayer("&4&lVous ne pouvez pas rejoindre (Hack)");
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ipban " + player.getName() + " Tentative de Hack");
				Bukkit.banIP(player.getAddress().getAddress().getHostAddress());
				return true;
			}
		}
		
		return false;
		
	}

}
