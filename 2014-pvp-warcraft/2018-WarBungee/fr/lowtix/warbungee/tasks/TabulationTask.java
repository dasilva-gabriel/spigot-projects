package fr.lowtix.warbungee.tasks;

import java.util.Collection;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class TabulationTask implements Runnable {

	@Override
	public void run() {
		Collection<ProxiedPlayer> players = ProxyServer.getInstance().getPlayers();
		
		System.out.println("sch");
		
		for(ProxiedPlayer all : players) {
			
			TextComponent header = new TextComponent("§f §r\n§6§lPVP-WARCRAFT§r\n§7pvp-warcraft.net§r\n§f §r");
			TextComponent footer = new TextComponent("§f §r\n§fBesoin d'aide ? §3/help§r\n§b"+players.size()+"§f/150 §8(§7"+all.getServer().getInfo().getPlayers().size()+" sur ce serveur§8)§r\n§f §r");
			all.setTabHeader(header, footer);
		}

	}

}
