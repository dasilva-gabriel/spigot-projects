package fr.lowtix.warcore.modules;

import fr.lowtix.warcore.WarCore;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class GTPModule {
	
	
	
	public void teleport(ProxiedPlayer from, String toName) {
		
		if(!from.hasPermission("warcore.gtp")) {
			WarCore.getInstance().sendMessage(from, "Vous n'avez pas les privilèges nécéssaires.", ChatColor.RED);
			return;
		}
		
		if (ProxyServer.getInstance().getPlayer(toName) == null || !ProxyServer.getInstance().getPlayer(toName).isConnected()) {
			WarCore.getInstance().sendMessage(from, "Le joueur est actuellement hors-ligne.", ChatColor.RED);
			return;
		}
		
		ProxiedPlayer to = ProxyServer.getInstance().getPlayer(toName);

		if(from.getServer().getInfo().equals(to.getServer().getInfo())) {
			WarCore.getInstance().sendMessage(from, "Vous êtes déjà sur le serveur demandé.", ChatColor.RED);
			return;
		} else {
			BaseComponent msg1 = new TextComponent("Vous allez être téléporté sur le même serveur que ");
			BaseComponent msg2 = new TextComponent(""+to.getName());
			BaseComponent msg3 = new TextComponent("...");
			
			msg1.setColor(ChatColor.GREEN);
			msg2.setColor(ChatColor.YELLOW);
			msg3.setColor(ChatColor.GREEN);
			
			msg1.addExtra(msg2);
			msg1.addExtra(msg3);
	        
	        from.sendMessage((BaseComponent)msg1);
	        from.connect(to.getServer().getInfo());
		}
		
	}

}
