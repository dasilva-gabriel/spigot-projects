package fr.lowtix.warbungee.commands;

import fr.lowtix.warbungee.WarBungee;
import fr.lowtix.warbungee.players.BungeeProfile;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class IgnoreCommand extends Command {
	
	public IgnoreCommand() {
		super("ignore", "", new String[] { "eignore","unignore","eunignore","delignore","edelignore","remignore","eremignore","rmignore","ermignore"});
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		
		ProxiedPlayer player = (ProxiedPlayer) sender;
		BungeeProfile prof_player = WarBungee.getInstance().getProfile(player.getName());
		
		if(args.length == 1 ) {
			
			String name = args[0];
			
			if(name.equalsIgnoreCase(player.getName())) {
				player.sendMessage(new TextComponent("§8[§3§lW§fInfo§8] §cVous ne pouvez pas vous bloquer..."));
				return;
			}
			
			if(ProxyServer.getInstance().getPlayer(name) == null || !ProxyServer.getInstance().getPlayer(name).isConnected()) {
				player.sendMessage(new TextComponent("§8[§3§lW§fInfo§8] §cLe joueur est actuellement hors-ligne."));
				return;
			}
			
			ProxiedPlayer target = ProxyServer.getInstance().getPlayer(name);
			
			if(prof_player.getIgnoreWrapper().getIgnoredPlayers().contains(target.getName())) {
				
				player.sendMessage(new TextComponent("§8[§3§lW§fInfo§8] §6Vous avez §adébloqué §6le joueur §e"+target.getName()+"§6."));
				
				prof_player.getIgnoreWrapper().removeIgnoredPlayer(target.getName());
				
			} else {
				
				player.sendMessage(new TextComponent("§8[§3§lW§fInfo§8] §6Vous avez §cbloqué §6le joueur §e"+target.getName()+"§6."));
				
				prof_player.getIgnoreWrapper().addIgnoredPlayer(target.getName());
			}
			
		} else {
			player.sendMessage(new TextComponent("§8[§3§lW§fInfo§8] §6Pour bloquer/débloquer quelqu'un §e/ignore <joueur>§6."));
		}

	}

}
