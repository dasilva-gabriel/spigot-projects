package fr.lowtix.warcore.modules;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.managers.PlayerWrapper;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MessagingModule {

	public void reply(ProxiedPlayer from, String message) {
		PlayerWrapper wfrom = WarCore.getInstance().getWrapper(from);
		
		if(wfrom.getReply() == null || wfrom.getReply().length() <= 1) {
			WarCore.getInstance().sendMessage(from, "Vous n'avez personne a qui répondre.", ChatColor.RED);
			return;
		}
		
		message(from, wfrom.getReply(), message);
		
	}
	
	public void message(ProxiedPlayer from, String toName, String message) {
		
		if (ProxyServer.getInstance().getPlayer(toName) == null || !ProxyServer.getInstance().getPlayer(toName).isConnected()) {
			WarCore.getInstance().sendMessage(from, "Le joueur est actuellement hors-ligne.", ChatColor.RED);
			return;
		}
		
		if(from.getName().equalsIgnoreCase(toName)) {
			
			WarCore.getInstance().sendMessage(from, "Vous ne pouvez pas vous envoyer de message.", ChatColor.RED);
			return;
			
		}
		
		ProxiedPlayer to = ProxyServer.getInstance().getPlayer(toName);
		PlayerWrapper wfrom = WarCore.getInstance().getWrapper(from);
		PlayerWrapper wto = WarCore.getInstance().getWrapper(to);
		
		if(wfrom.getIgnored().contains(to.getName().toLowerCase())) {
			
			WarCore.getInstance().sendMessage(from, "Vous ignorez cette personne, impossible d'envoyer le message.", ChatColor.RED);
			return;
			
		}
		
		if(wto.getIgnored().contains(from.getName().toLowerCase())) {
			
			WarCore.getInstance().sendMessage(from, "Cette personne vous ignore, impossible d'envoyer le message.", ChatColor.RED);
			return;
			
		}
		
		if(!wfrom.isToggle()) {
			WarCore.getInstance().sendMessage(from, "Vous avez désactivé vos messages privés, impossible d'envoyer le message.", ChatColor.RED);
			return;
		}
		
		if(!wto.isToggle()) {
			WarCore.getInstance().sendMessage(from, "Cette personne a désactivé ses messages privés, impossible d'envoyer le message.", ChatColor.RED);
			return;
		}
		
		from.sendMessage(messageSyntaxSenderView(from.getName(), to.getName(), message));
		to.sendMessage(messageSyntax(from.getName(), to.getName(), message));
		
		wfrom.setReply(to.getName());
		wto.setReply(from.getName());
		
		WarCore.getInstance().getModules().getSocialspy().executeSocial(from, to.getName(), message);
		
	}
	
	public BaseComponent messageSyntax(String from, String to, String message) {
		BaseComponent base = new TextComponent();
		
		BaseComponent pseudo = new TextComponent("§8[§6"+from+" §8» §6Toi§8]");
		BaseComponent reponse = new TextComponent("§e[Répondre]");
		reponse.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "§eCliquez ici pour répondre§r\n§eau message de §6"+from ).create() ) );
		reponse.setClickEvent( new ClickEvent( ClickEvent.Action.SUGGEST_COMMAND, "/msg "+from+" " ) );
		
		BaseComponent msg = new TextComponent(" " + message + " ");
		msg.setColor(ChatColor.WHITE);
		
		base.addExtra(pseudo);
		base.addExtra(msg);
		base.addExtra(reponse);
		
		return base;
		
	}
	
	public BaseComponent messageSyntaxSenderView(String from, String to, String message) {
		BaseComponent base = new TextComponent();
		
		BaseComponent pseudo = new TextComponent("§8[§6Toi §8» §6"+to+"§8]");
		
		BaseComponent msg = new TextComponent(" " + message);
		msg.setColor(ChatColor.WHITE);
		
		base.addExtra(pseudo);
		base.addExtra(msg);
		
		return base;
	}
	
}
