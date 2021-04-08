package fr.lowtix.warbungee.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ReportCommand extends Command {
	
	public ReportCommand() {
		super("report", "", new String[] { "reports"});
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		
		ProxiedPlayer player = (ProxiedPlayer) sender;
		
		if(args.length == 0) {
			player.sendMessage(new TextComponent("§8[§3§lW§fInfo§8] §6Pour report un joueur §e/report <joueur> <raison>"));
			return;
		} else if(args.length >= 2) {
			
			String name = args[0];
			
			if(ProxyServer.getInstance().getPlayer(name) == null || !ProxyServer.getInstance().getPlayer(name).isConnected()) {
				player.sendMessage(new TextComponent("§8[§3§lW§fInfo§8] §cLe joueur est actuellement hors-ligne."));
				return;
			}
			
			String reason = "";
			
			for(int i = 1; i < args.length; i++) {
				reason += args[i] + " "; 
			}

			ProxiedPlayer target = ProxyServer.getInstance().getPlayer(name);
			player.sendMessage(new TextComponent("§8[§3§lW§fInfo§8] §aLe rapport a été envoyé. §7("+target.getName()+" §8> §7"+reason+"§7)"));
			
			for(ProxiedPlayer pl : ProxyServer.getInstance().getPlayers()) {
				if(pl.hasPermission("warbungee.viewreports")) {
					
					pl.sendMessage(new TextComponent("§8[§4§l!§8] §4Rapport §8| §c"+target.getName()+" §8» §e"+reason+" §7(par §7" + player.getName()+"§7)"));
					
				}
			}
			
			
			return;
		}  else {
			
			for(String s : args) {
				player.sendMessage(new TextComponent(s));
			}
			
			player.sendMessage(new TextComponent("§8[§3§lW§fInfo§8] §6Pour report un joueur §e/report <joueur> <raison>"));
			return;
		}

	}

}
