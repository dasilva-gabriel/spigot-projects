package fr.lowtix.warcore.commands;

import fr.lowtix.warcore.WarCore;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class SocialSpyCommand extends Command {

	public SocialSpyCommand(String name, String permission, String[] aliases) {
		super(name, permission, aliases);
	}

	@Override
	public void execute(CommandSender sender, String[] arg) {
		ProxiedPlayer p = (ProxiedPlayer)sender;
		if (arg.length == 1) {
			String serv = arg[0];
	        
	        WarCore.getInstance().getModules().getSocialspy().socialSpy(p, serv);
	        
		} else {
			BaseComponent msg1 = new TextComponent("Utilisation de la commande: ");
			BaseComponent msg2 = new TextComponent("/socialspy <serveur|all>");
			
			msg1.setColor(ChatColor.RED);
			msg2.setColor(ChatColor.YELLOW);
			
			msg1.addExtra(msg2);
	        
	        p.sendMessage((BaseComponent)msg1);
		}
	}

}
