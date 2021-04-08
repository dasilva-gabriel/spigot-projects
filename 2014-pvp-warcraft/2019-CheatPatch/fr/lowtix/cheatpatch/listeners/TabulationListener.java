package fr.lowtix.cheatpatch.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.lowtix.cheatpatch.utils.PlayerUtils;

public class TabulationListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		PlayerUtils.setupTabulation(event.getPlayer(), "§6- §ePvP§6-§eWarCraft§6.§eNET §7[§c1.8/1.13§7] §6-\n"
				+ "§6 §r", "§f §r\n§eComment faire de l'argent §6? §cFarming, PvP, ..."
				+ "§r\n§6- §eLe site §6WEB §e: §chttps://pvp-warcraft.net/ §6-");
	}
	
}
