package fr.lowtix.warbungee.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class LobbyCommand extends Command {
	
	public LobbyCommand() {
		super("lobby", "", new String[] { "hub"});
	}

	@Override
	public void execute(CommandSender sender, String[] arg) {
		
		if(sender instanceof ProxiedPlayer) {
			
			ProxiedPlayer p = (ProxiedPlayer) sender;
			p.sendMessage(new TextComponent("§8[§3§lW§8] §6Envoi sur le serveur §bLobby§6..."));
			
			p.connect(ProxyServer.getInstance().getServerInfo("lobby"));
			
		}

	}

}
