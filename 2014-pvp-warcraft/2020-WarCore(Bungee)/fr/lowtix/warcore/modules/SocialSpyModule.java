package fr.lowtix.warcore.modules;

import java.util.ArrayList;
import java.util.List;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.managers.PlayerWrapper;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class SocialSpyModule {
	
	private List<String> socialspy;
	
	public SocialSpyModule() {
		socialspy = new ArrayList<String>();
	}

	public void socialSpy(ProxiedPlayer from, String s) {
		
		PlayerWrapper wfrom = WarCore.getInstance().getWrapper(from);
		
		if(!from.hasPermission("warcore.socialspy")) {
			WarCore.getInstance().sendMessage(from, "Vous n'avez pas les privilèges nécéssaires.", ChatColor.RED);
			return;
		}
		
		if(s.equalsIgnoreCase("off")) {
			wfrom.disableSocialSpy();
			if(socialspy.contains(from.getName())) socialspy.remove(from.getName());
			WarCore.getInstance().sendMessage(from, "Vous avez désactivé le mode SocialSpy.", ChatColor.RED);
			return;
		}
		
		if(s.equalsIgnoreCase(wfrom.getSocialspy())) {
			wfrom.disableSocialSpy();
			if(socialspy.contains(from.getName())) socialspy.remove(from.getName());
			from.sendMessage(new TextComponent("§cVous avez désactivé le mode SocialSpy. §7(serveur: §7"+s.toLowerCase()+"§7)" ));
			return;
		} else {
			if(s.equalsIgnoreCase("all")) {
				wfrom.enableSocialspy("all");
				if(!socialspy.contains(from.getName())) socialspy.add(from.getName());
				WarCore.getInstance().sendMessage(from, "Vous avez activé le mode SocialSpy sur tout les serveurs.", ChatColor.GREEN);
				return;
			} 
			
			if(!ProxyServer.getInstance().getServers().keySet().contains(s.toLowerCase())) {
				WarCore.getInstance().sendMessage(from, "Ce serveur n'existe pas.", ChatColor.RED);
				return;
			}
			
			wfrom.enableSocialspy(s.toLowerCase());
			
			if(!socialspy.contains(from.getName())) socialspy.add(from.getName());
			
			WarCore.getInstance().sendMessage(from, "Vous avez activé le mode SocialSpy. §7(serveur: §7"+s.toLowerCase()+"§7)", ChatColor.GREEN);
			return;
		}
		
	}
	
	public void executeSocial(ProxiedPlayer from, String to, String message) {
		for(String s : socialspy) {
			if(WarCore.getInstance().containsWrapper(s) && ProxyServer.getInstance().getPlayer(s) != null && ProxyServer.getInstance().getPlayer(s).isConnected()) {
				ProxiedPlayer player = ProxyServer.getInstance().getPlayer(s);
				PlayerWrapper wplayer = WarCore.getInstance().getWrapper(player);
				String serv = from.getServer().getInfo().getName().toLowerCase();
				if(wplayer.isSocialSpy() && (wplayer.getSocialspy().equalsIgnoreCase(serv) || wplayer.getSocialspy().equalsIgnoreCase("all"))) {
					player.sendMessage(messageSyntaxSenderView(from.getName(), to, serv, message));
				}
			}
		}
	}
	
	public BaseComponent messageSyntaxSenderView(String from, String to, String server, String message) {
		BaseComponent base = new TextComponent();
		
		BaseComponent pref = new TextComponent("§7("+server+")");
		BaseComponent pseudo = new TextComponent("§8[§3"+from+" §8» §3"+to+"§8]");
		
		BaseComponent msg = new TextComponent(" " + message);
		msg.setColor(ChatColor.WHITE);
		
		base.addExtra(pref);
		base.addExtra(pseudo);
		base.addExtra(msg);
		
		return base;
	}
	
}
