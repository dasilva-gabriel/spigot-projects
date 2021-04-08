package fr.lowtix.warbungee.listeners;

import fr.lowtix.warbungee.WarBungee;
import fr.lowtix.warbungee.players.BungeeProfile;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class InOutListeners implements Listener {
	
	@EventHandler
	public void onJoin(PostLoginEvent event) {
		
		ProxiedPlayer player = event.getPlayer();
		BungeeProfile prof_player = WarBungee.getInstance().getProfile(player.getName());
		
		prof_player.getIgnoreWrapper().load();
		
		TextComponent header = new TextComponent("§f §r\n§6§lPVP-WARCRAFT§r\n§7pvp-warcraft.net§r\n§f §r");
		TextComponent footer = new TextComponent("§f §r\n§fBesoin d'aide ? §3/help§r\n§f §r");
		player.setTabHeader(header, footer);
		
	}
	
	@EventHandler
	public void onQuit(PlayerDisconnectEvent event) {
		ProxiedPlayer player = event.getPlayer();
		BungeeProfile prof_player = WarBungee.getInstance().getProfile(player.getName());
		
		prof_player.getIgnoreWrapper().save();
	}

}
