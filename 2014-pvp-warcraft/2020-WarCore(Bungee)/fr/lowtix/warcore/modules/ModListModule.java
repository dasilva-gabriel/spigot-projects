package fr.lowtix.warcore.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.lowtix.warcore.WarCore;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class ModListModule {

	public void execute(ProxiedPlayer player) {
		
		if(!player.hasPermission("warcore.modlist")) {
			WarCore.getInstance().sendMessage(player, "Vous n'avez pas les privilèges nécéssaires.", ChatColor.RED);
			return;
		}
		
		HashMap<ServerInfo, List<String>> list = new HashMap<>();
		for(ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
			if(p.hasPermission("warcore.modlist")) {
				ServerInfo serv = p.getServer().getInfo();
				if(!list.containsKey(serv)) {
					list.put(serv, new ArrayList<String>());
				}
				list.get(serv).add(p.getName());
			}
		}
		
		String delim = ", ";
		
		for (Map.Entry<ServerInfo, List<String>> mapentry : list.entrySet()) {
			ServerInfo serv = mapentry.getKey();
			String players = String.join(delim, mapentry.getValue());
			player.sendMessage(messageSyntax(serv, players));
		}
		
	}
	
	public BaseComponent messageSyntax(ServerInfo serv, String players) {
		int mod = players.split(", ").length;
		int pl = serv.getPlayers().size();
		BaseComponent base = new TextComponent();
		
		BaseComponent pef = new TextComponent("§8| §6"+serv.getName()+" §8»");
		
		BaseComponent msg = new TextComponent(" " + players);
		msg.setColor(ChatColor.YELLOW);
		
		base.addExtra(pef);
		base.addExtra(msg);
		
		base.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( 
				"§8» §7Informations:§r\n" +
						"§8• §eConnectés: " + pl +"§r\n" +
						"§8• §eStaff: " + mod +"§r"
						).create() ) );
		
		return base;
	}
	
}
