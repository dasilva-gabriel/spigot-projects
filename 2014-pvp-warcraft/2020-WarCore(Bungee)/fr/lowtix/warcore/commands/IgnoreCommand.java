package fr.lowtix.warcore.commands;

import java.util.HashSet;
import java.util.Set;

import fr.lowtix.warcore.WarCore;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

public class IgnoreCommand extends Command implements TabExecutor {

	public IgnoreCommand(String name, String permission, String[] aliases) {
		super(name, permission, aliases);
	}

	@Override
	public void execute(CommandSender sender, String[] arg) {
		ProxiedPlayer p = (ProxiedPlayer)sender;
		if (arg.length > 0) {
			String name = arg[0];
	        
	        WarCore.getInstance().getModules().getIgnore().ignore(p, name);
	        
		} else {
			BaseComponent msg1 = new TextComponent("Utilisation de la commande: ");
			BaseComponent msg2 = new TextComponent("/ignore <pseudo|list>");
			
			msg1.setColor(ChatColor.RED);
			msg2.setColor(ChatColor.YELLOW);
			
			msg1.addExtra(msg2);
	        
	        p.sendMessage((BaseComponent)msg1);
		}
	}

	@Override
	public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
		Set<String> matches = new HashSet<>();
	    String search = args[0].toLowerCase();
	    if (args.length == 1 && search.length() > 1) {
	    	for (ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
		        if (p.getName().toLowerCase().startsWith(search))
		          matches.add(p.getName()); 
		      }  
	    }
	      
	    return matches;
	}

}
