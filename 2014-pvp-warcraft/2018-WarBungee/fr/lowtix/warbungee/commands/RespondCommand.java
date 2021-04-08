package fr.lowtix.warbungee.commands;

import fr.lowtix.warbungee.WarBungee;
import fr.lowtix.warbungee.players.BungeeProfile;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class RespondCommand extends Command {

	public RespondCommand() {
		super("respond", "", new String[] { "r", "er","reply","ereply"});
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		
		ProxiedPlayer player = (ProxiedPlayer) sender;
		BungeeProfile prof_player = WarBungee.getInstance().getProfile(player.getName());
		
		if (args.length >= 1) {
			
			if(prof_player.getLastPrivateMessageUser() == null || prof_player.getLastPrivateMessageUser().equals("")) {
				player.sendMessage(new TextComponent("§8[§3§lW§fInfo§8] §cVous n'avez personne dans vos messages précédent."));
				return;
			}
			
			String name = prof_player.getLastPrivateMessageUser();
			String message = "";
			
			for (int i = 0; i < args.length; i++) {
				message = message + args[i] + " §f§o";
			}
			
			
			if(ProxyServer.getInstance().getPlayer(name) == null || !ProxyServer.getInstance().getPlayer(name).isConnected()) {
				player.sendMessage(new TextComponent("§8[§3§lW§fInfo§8] §cLe joueur est actuellement hors-ligne."));
				return;
			}
			
			if(prof_player.timeElapsesLastPrivateMessage() <= 1.5) {
				player.sendMessage(new TextComponent("§8[§3§lW§fInfo§8] §cVous avez déjà envoyé un message privé il y a peu..."));
				return;
			}
			
			if(prof_player.getLastPrivateMessage().equalsIgnoreCase(message)) {
				player.sendMessage(new TextComponent("§8[§3§lW§fInfo§8] §cVous ne pouvez pas spammer en message privé."));
				return;
			}
			
			prof_player.setLastPrivateMessage(message);
			prof_player.setLastPrivateMessageTime(System.currentTimeMillis());
			
			ProxiedPlayer target = ProxyServer.getInstance().getPlayer(name);
			BungeeProfile prof_target = WarBungee.getInstance().getProfile(target.getName());
			
			if(prof_player.getIgnoreWrapper().getIgnoredPlayers().contains(target.getName())) {
				player.sendMessage(new TextComponent("§8[§3§lW§fInfo§8] §cVous avez bloqué ce joueur§c, vous ne pouvez pas §clui envoyer de messages."));
				return;
			}
			
			if(prof_target.getIgnoreWrapper().getIgnoredPlayers().contains(player.getName())) {
				player.sendMessage(new TextComponent("§8[§3§lW§fInfo§8] §cCe joueur vous a bloqué!"));
				return;
			}
			
			prof_target.setLastPrivateMessageUser(player.getName());
			prof_player.setLastPrivateMessageUser(target.getName());
			
			message.replaceAll("", "§e");
			
			TextComponent msg = new TextComponent("§8[§6"+player.getName()+" §7» §6Moi§8] §e(R) §f§o" + message);
			msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§8» §6Cliquez pour répondre").create()));
			msg.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + player.getName() + " "));
			
			target.sendMessage(msg);
			
			msg = new TextComponent("§8[§6Moi §7» §6"+target.getName()+"§8] §f§o" + message);
			msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§8» §6Cliquez pour renvoyer un message").create()));
			msg.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + target.getName() + " "));
			player.sendMessage(msg);
			
		} else {
			player.sendMessage(new TextComponent("§8[§3§lW§fInfo§8] §6Pour envoyer un message §e/respond <message>§6."));
		}

	}

}
