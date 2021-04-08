package fr.lowtix.warbox.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.lowtix.warbox.WarBox;
import fr.lowtix.warbox.events.UserFirstJoinEvent;
import fr.lowtix.warbox.events.UserJoinEvent;
import fr.lowtix.warbox.players.WarPlayer;
import fr.lowtix.warbox.utils.PlayerUtils;

public class InOutListeners implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		event.setJoinMessage(null);
		
		event.getPlayer().sendMessage("§8[§3§lW§8] §6Chargement de votre profil §bPvPBox§6, §f" + event.getPlayer().getName() + "§r §e§kUuU§6...");
		event.getPlayer().teleport(WarBox.getInstance().getSpawn());
		
		WarPlayer wp = WarBox.getInstance().getUser(event.getPlayer());
		
		PlayerUtils.sendTitle(event.getPlayer(), "§6§lPvP-Box", "§eby §f§npvp-warcraft.net", 0, 30, 10);
		wp.reloadTabulation();
		
		WarBox.getInstance().getBoardManager().setupBoard(event.getPlayer());
		
		event.getPlayer().setMaxHealth(40);
		event.getPlayer().setHealth(40);
		
	}
	
	@EventHandler
	public void onUserJoin(UserJoinEvent event) {
		WarPlayer wp = event.getUser();
		Player player = wp.getPlayer();
		
		player.sendMessage("§8[§3§lW§8] §6Votre profil §bPvPBox §6est chargé!");
	}
	
	@EventHandler
	public void onFirstJoin(UserFirstJoinEvent event) {
		
		Bukkit.broadcastMessage("§8» §eBienvenue §6"+event.getPlayer().getName()+" §esur le serveur §bPvPBox §e!");
		Bukkit.broadcastMessage("§8» §7§oSouhaitez lui la bienvenue et recevez §e§o5 coins§7§o...");
		
		WarBox.getInstance().getWelcomeManager().setWelcome(event.getPlayer());
		
		event.getPlayer().teleport(WarBox.getInstance().getSpawn());
		
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		
		event.setQuitMessage(null);
		
		Player player = event.getPlayer();
		WarPlayer wp = WarBox.getInstance().getUser(player);
		
		wp.save();
		WarBox.getInstance().deletePlayer(wp);
		
	}
	
}
