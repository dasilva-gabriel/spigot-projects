package fr.lowtix.warbox.managers;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import fr.lowtix.warbox.WarBox;
import fr.lowtix.warbox.players.WarPlayer;

public class WelcomeManager {
	
	private String lastJoin = "";
	private ArrayList<String> yetWelcome = new ArrayList<String>();
	
	public boolean hasWelcomePlayer() {
		return lastJoin.equals("");
	}
	
	public void setWelcome(Player player) {
		yetWelcome.clear();
		lastJoin = player.getName();
	}
	
	public void welcome(Player player) {
		WarPlayer wp = WarBox.getInstance().getUser(player);
		
		if(hasWelcomePlayer()) {
			
			if(yetWelcome.contains(player.getName())) {
				player.sendMessage("§8[§3§lW§fInfo§8] §cVous avez déjà fais cela...");
				return;
			}
			
			yetWelcome.add(player.getName());
			player.sendMessage("§8[§3§lW§fInfo§8] §6Vous recevez §e5 coins §6pour votre sympathie. §8(§fBienvenue à §7" + lastJoin+"§8)");
			wp.addCoins(5);
			
		}
		
	}

}
