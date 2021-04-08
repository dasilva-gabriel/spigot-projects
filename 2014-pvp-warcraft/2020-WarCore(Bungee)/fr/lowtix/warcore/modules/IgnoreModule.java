package fr.lowtix.warcore.modules;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.managers.PlayerWrapper;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class IgnoreModule {

	public void ignore(ProxiedPlayer from, String target) {
		
		if(!from.hasPermission("warcore.ignore")) {
			WarCore.getInstance().sendMessage(from, "Vous n'avez pas les privilèges nécéssaires.", ChatColor.RED);
			return;
		}
		
		if(from.getName().equalsIgnoreCase(target)) {
			
			WarCore.getInstance().sendMessage(from, "Vous ne pouvez pas vous ignorer.", ChatColor.RED);
			return;
			
		}
		
		PlayerWrapper wfrom = WarCore.getInstance().getWrapper(from);
		
		if(target.equalsIgnoreCase("list")) {
			WarCore.getInstance().sendMessage(from, "------------------------------", ChatColor.DARK_GREEN, false, false, true);
			if(wfrom.getIgnored().size() == 0) {
				WarCore.getInstance().sendMessage(from, "Il n'y a personne d'ignoré.", ChatColor.RED);
			} else {
				WarCore.getInstance().sendMessage(from, "Personnes ignorés:", ChatColor.GOLD);
				String delim = ", ";
				String res = String.join(delim, wfrom.getIgnored());
				WarCore.getInstance().sendMessage(from, res, ChatColor.YELLOW);
			}
			WarCore.getInstance().sendMessage(from, "------------------------------", ChatColor.DARK_GREEN, false, false, true);
			return;
		}
		
		if(wfrom.getIgnored().contains(target.toLowerCase())) {
			
			wfrom.getIgnored().remove(target.toLowerCase());
			WarCore.getInstance().sendMessage(from, "Vous n'ignorez plus cette personne.", ChatColor.GREEN);
			return;
			
		} else {
			if(wfrom.getIgnored().size() >= 10) {
				WarCore.getInstance().sendMessage(from, "Vous ignorez déjà 10 personnes, faites /ignore list pour les voir.", ChatColor.RED);
				return;
			}
			wfrom.getIgnored().add(target.toLowerCase());
			WarCore.getInstance().sendMessage(from, "Ce joueur a été ajouté a votre liste de bloqués.", ChatColor.GREEN);
			return;
		}
		
	}
	
}
