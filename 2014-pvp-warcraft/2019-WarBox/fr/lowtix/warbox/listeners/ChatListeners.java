package fr.lowtix.warbox.listeners;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.lowtix.warbox.WarBox;
import fr.lowtix.warbox.enums.Groups;
import fr.lowtix.warbox.enums.Tags;
import fr.lowtix.warbox.players.WarPlayer;

public class ChatListeners implements Listener {
	
	private HashMap<String, String> lastChatMessage = new HashMap<String, String>();
	private HashMap<String, Long> lastChatTime = new HashMap<String, Long>();
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		
		if(!event.isCancelled()) {
			event.setCancelled(true);
			
			Player player = event.getPlayer();
			WarPlayer wPlayer = WarBox.getInstance().getUser(player);
			
			if(lastChatMessage.containsKey(player.getName())) {
				if(lastChatMessage.get(player.getName()).equalsIgnoreCase(event.getMessage())) {
					player.sendMessage("§8[§3§lW§4Mod§8] §6Le spam n'est pas toléré sur le serveur! §8(§fMême message que le précédent§8)");
					return;
				}
			}
			lastChatMessage.put(player.getName(), event.getMessage());
			
			if(lastChatTime.containsKey(player.getName())) {
				double time = (double) ((System.currentTimeMillis() - lastChatTime.get(player.getName()))/1000L);
				if(!wPlayer.getGroup().isHigherOrEquals(Groups.MODERATOR) && time < 2) {
					player.sendMessage("§8[§3§lW§4Mod§8] §6Le flood n'est pas toléré sur le serveur!");
					return;
				}
			}
			lastChatTime.put(player.getName(), System.currentTimeMillis());
			
			String format = "";
			
			format += WarBox.getInstance().getLevelManager().getPrefix(wPlayer.getLevel());
			format += wPlayer.getGroup().getPrefix();
			format += "§r ";
			
			if(!wPlayer.getStats().getActive_tag().equals(Tags.NONE)) {
				format += wPlayer.getStats().getActive_tag().getDisplay() + " §r";
			}
			
			format += wPlayer.getGroup().getGroupColor();
			
			format += player.getName();
			
			Bukkit.broadcastMessage(format + " §8» " + wPlayer.getGroup().getChatColor() + event.getMessage());
			
			if(event.getMessage().toLowerCase().contains("bienvenu") || event.getMessage().toLowerCase().contains("bvn")) {
				WarBox.getInstance().getWelcomeManager().welcome(player);
			}
			
		}
		
	}

}
