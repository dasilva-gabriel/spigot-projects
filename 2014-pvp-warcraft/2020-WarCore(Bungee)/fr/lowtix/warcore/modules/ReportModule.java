package fr.lowtix.warcore.modules;

import java.util.HashMap;

import fr.lowtix.warcore.WarCore;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class ReportModule {

	private HashMap<String, Long> cooldown = new HashMap<>();

	public void report(ProxiedPlayer from, String toName, String reason) {

		if (ProxyServer.getInstance().getPlayer(toName) == null
				|| !ProxyServer.getInstance().getPlayer(toName).isConnected()) {
			WarCore.getInstance().sendMessage(from, "Le joueur est actuellement hors-ligne.", ChatColor.RED);
			return;
		}

		if (from.getName().equalsIgnoreCase(toName)) {

			WarCore.getInstance().sendMessage(from, "Vous ne pouvez pas vous report vous-même.", ChatColor.RED);
			return;

		}

		ProxiedPlayer to = ProxyServer.getInstance().getPlayer(toName);

		if (to.hasPermission("warcore.bypassreport")) {
			WarCore.getInstance().sendMessage(from, "Cette personne est protégée, impossible d'envoyer le report.", ChatColor.RED);
			return;
		}
		
		if(reason.length() > 16) {
			
			BaseComponent msg1 = new TextComponent("Vous ne pouvez pas envoyer un report avec une raison contenant plus de ");
			BaseComponent msg2 = new TextComponent("16 caractères");
			BaseComponent msg3 = new TextComponent(".");
			
			msg1.setColor(ChatColor.RED);
			msg2.setColor(ChatColor.YELLOW);
			msg3.setColor(ChatColor.RED);
			
			msg1.addExtra(msg2);
			msg1.addExtra(msg3);
	        
	        from.sendMessage((BaseComponent)msg1);
	        return;
		}
		
		if (!this.cooldown.containsKey(from.getName()) || System.currentTimeMillis() - 120000L >= ((Long)this.cooldown.get(from.getName())).longValue()) {
			
			this.cooldown.put(from.getName(), Long.valueOf(System.currentTimeMillis()));
			
			for (ProxiedPlayer pplayer : ProxyServer.getInstance().getPlayers()) {
		          if (pplayer.hasPermission("warcore.seereports")) {
		        	  pplayer.sendMessage(messageSyntax(from.getName(), to.getName(), to.getServer().getInfo().getName().toLowerCase(), reason));
		          }
			}
			
			WarCore.getInstance().sendMessage(from, "Votre report a été envoyé avec succès a l'équipe de modération. Il sera traité dans les plus brefs délais.", ChatColor.GREEN);
		} else {
			long time = ((Long)this.cooldown.get(from.getName())).longValue() - System.currentTimeMillis() + 120000L;
			BaseComponent msg1 = new TextComponent("Vous devez patienter ");
			BaseComponent msg2 = new TextComponent(""+millisToTime(time));
			BaseComponent msg3 = new TextComponent(".");
			
			msg1.setColor(ChatColor.RED);
			msg2.setColor(ChatColor.YELLOW);
			msg3.setColor(ChatColor.RED);
			
			msg1.addExtra(msg2);
			msg1.addExtra(msg3);
	        
	        from.sendMessage((BaseComponent)msg1);
		}

	}

	public static String millisToTime(long millis) {
		long seconds = millis / 1000L;
		return String.valueOf(seconds % 60L) + " secondes";
	}
	
	public BaseComponent messageSyntax(String from, String to, String server, String message) {
		BaseComponent base = new TextComponent();
		
		BaseComponent pref = new TextComponent("§7("+server+")");
		BaseComponent pseudo = new TextComponent("§8[§4Mod §8| §cReport§8] ");
		BaseComponent names = new TextComponent(""+to);
		names.setColor(ChatColor.YELLOW);
		BaseComponent tick = new TextComponent(" » ");
		tick.setColor(ChatColor.DARK_GRAY);
		
		BaseComponent msg = new TextComponent(" " + message + " ");
		msg.setColor(ChatColor.RED);
		
		BaseComponent suf = new TextComponent("(par: " + from + ")");
		suf.setColor(ChatColor.WHITE);
		
		base.addExtra(pref);
		base.addExtra(pseudo);
		base.addExtra(names);
		base.addExtra(tick);
		base.addExtra(msg);
		base.addExtra(suf);
		
		return base;
	}

}
