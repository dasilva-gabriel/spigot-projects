package fr.lowtix.warcore.listeners;

import java.util.HashMap;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.massivecraft.factions.entity.Faction;

import fr.lowtix.warbox.enums.Tags;
import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.enums.Levels;
import fr.lowtix.warcore.enums.Ranks;
import fr.lowtix.warcore.utils.PlayerUtils;

public class ChatListeners implements Listener {
	
	private HashMap<String, String> lastChat = new HashMap<String, String>();
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		if(!event.isCancelled()) {
			Player player = event.getPlayer();
			WarPlayer wPlayer = WarCore.getInstance().getUser(player);
			double time = (double) ((System.currentTimeMillis() - wPlayer.lastChat)/1000L);
			
			event.setCancelled(true);
			
			if(wPlayer.getRank().isHigher(Ranks.DIAMOND) && time < 1) {
				player.sendMessage("§6Modération §8» §cVous ne pouvez pas Spam/Flood le chat.");
				return;
			} else if (!wPlayer.getRank().isHigher(Ranks.DIAMOND) && time < 2.5) {
				player.sendMessage("§6Modération §8» §cVous ne pouvez pas Spam/Flood le chat.");
				return;
			}
			
			String message = event.getMessage();
			
			if(wPlayer.getRank().isHigher(Ranks.SILVER) && message.contains("[item]")) {
				message.replaceAll("[item]", player.getInventory().getItemInHand().getItemMeta().getDisplayName());
			}
			
			if (wPlayer.getRank().isHigher(Ranks.STAFF) && message.startsWith("$")) {
				return;
			}
			
			if(this.lastChat.containsKey(player.getName())) {
				String last = this.lastChat.get(player.getName());
				
				if(last.toLowerCase().equals(message.toLowerCase()) || message.toLowerCase().equals(last.toLowerCase())) {
					player.sendMessage("§6Modération §8» §cVotre message est similaire au message précédent.");
					return;
				}
			}
			
			String format = "";
			
			Faction faction = wPlayer.mplayer.getFaction();
			
			
			if(faction != null && !faction.getId().equals("warzone") && !faction.getId().equals("none") && !faction.getId().equals("safezone")) {
				format += "§8[§7"+faction.getName();
				
				if(!wPlayer.getLevel().equals(Levels.LEVEL_0)) {
					format += " §8| §3§l"+wPlayer.getLevel().getDisplayName();
				}
				format += "§8] §7";
			}
			
			if(!wPlayer.getPlayerStats().getTag().equals(Tags.NONE)) {
				format += wPlayer.getPlayerStats().getTag().getDisplay() + " §7";
			}
			
			Ranks rank = wPlayer.getRank();
			
			if(!rank.equals(Ranks.DEFAULT)) {
				format += "§7" +  rank.getPrefix() + " §7";
			}
			
			format += rank.getPrefixColor() + "" + player.getName();
			
			for(WarPlayer users : WarCore.getInstance().getUsers().values()) {
				
				Player all = users.getPlayer();
				
				message = rank.getChatColor() + message;
				
				if(message.contains(all.getName())) {
					message.replaceAll(all.getName(), "§d§l"+all.getName()+""+rank.getChatColor());
					if(!users.getPlayerStats().isAfk()) {
						PlayerUtils.sendActionBar(all, "§e§lVous avez été mentionné dans le chat!");
						all.playSound(all.getLocation(), Sound.NOTE_PIANO, 10.0F, 1.0F);
					}
				}
				
				all.sendMessage(format + " §8» " + rank.getChatColor() + event.getMessage());
				
			}
			
			if(!wPlayer.getRank().isHigher(Ranks.FAMOUS)) {
				lastChat.put(player.getName(), event.getMessage());
			}
			
			wPlayer.lastChat = System.currentTimeMillis();
		}
	}

}
