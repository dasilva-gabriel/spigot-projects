package fr.lowtix.warcore.managers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.enums.Ranks;

public class StaffChatManager implements Listener {

	public void on(WarPlayer wPlayer) {
		wPlayer.getModPlayer().setChatActive(true);
		wPlayer.getPlayer().sendMessage("§6Modération §8» §7Chat de modération §aactivé§7.");
	}

	public void off(WarPlayer wPlayer) {
		wPlayer.getModPlayer().setChatActive(false);
		wPlayer.getPlayer().sendMessage("§6Modération §8» §7Chat de modération §cdésactivé§7.");
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		WarPlayer wPlayer = WarCore.getInstance().getUser(player);

		if (wPlayer.getRank().isHigher(Ranks.STAFF)) {
			if (event.getMessage().length() > 1 && event.getMessage().startsWith("$")) {
				event.setCancelled(true);
				String message = event.getMessage().replace("$", "");

				if (message.equalsIgnoreCase("on")) {
					on(wPlayer);
					return;
				}

				if (message.equalsIgnoreCase("off")) {
					off(wPlayer);
					return;
				}
				
				if (!wPlayer.getModPlayer().isChatActive()) {
					player.sendMessage("§6Modération §8» §7Votre chat de modération est désactivé.");
					return;
				}

				sendModMessage(Ranks.STAFF, "§e" + wPlayer.getRank().getPrefixColor() + player.getName() + " §8» §b" + message);

			}
		}
	}

	public void sendModMessage(Ranks rank, String message) {
		for (WarPlayer players : WarCore.getInstance().getUsers().values()) {
			if (players.getRank().isHigher(rank) && players.getModPlayer().isChatActive()) {
				players.getPlayer().sendMessage("§f(§cModération§f) §e" + message);
			}
		}
	}
	
	public void sendShieldMessage(Ranks rank, String message) {
		for (WarPlayer players : WarCore.getInstance().getUsers().values()) {
			if (players.getRank().isHigher(rank) && players.getModPlayer().isChatActive()) {
				players.getPlayer().sendMessage("§f(§2WarShield§f) §e" + message);
			}
		}
	}
	
}
