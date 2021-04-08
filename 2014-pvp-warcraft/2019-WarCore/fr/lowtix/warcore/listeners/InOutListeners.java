package fr.lowtix.warcore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.events.UserJoinEvent;

public class InOutListeners implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		event.setJoinMessage(null);
		
		Player player = event.getPlayer();
		
		if(!player.isOp() && WarCore.getInstance().error) {
			player.kickPlayer("§4§lERREUR DU SERVEUR§r\n§cContactez au plus vite un Administrateur,§r\n§cet prévenez le d'une erreur Serveur!");
			return;
		}
		
		if(player.isOp() && WarCore.getInstance().error) {
			player.sendMessage("§4§lERREUR! §cUne erreur Serveur (WarCore) les joueurs seront expulsés.");
			return;
		}
		
		player.sendMessage("§bInfo §8» §7Chargement de votre profil en cours §e§koOo§7...");
		
		WarPlayer wp = WarCore.getInstance().getUser(player);
		wp.setPlayer(player);
	}
	
	@EventHandler
	public void onJoin(UserJoinEvent event) {
		WarPlayer wp = event.getUser();
		Player player = wp.getPlayer();
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user "+player.getName()+" group set "+wp.getRank().getDisplayName());
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user "+player.getName()+" group add "+wp.getLevel().name().toLowerCase());
		
		wp.getTabCustom().reload();
		
		wp.afkToKick = false;
		
		if(wp.getPlayerStats().joinMessage) {
			Bukkit.broadcastMessage("§8» "+wp.getRank().getPrefixColor()+player.getName()+" §7§oa rejoint le serveur.");
		}
		
		player.sendMessage("§bInfo §8» §aVotre profil a été chargé avec succès!");
		
		player.sendMessage("§8§l«§6-§8§l»§8§m------------------------------------------§8§l«§6-§8§l»");
		player.sendMessage("  §7Bienvenue §b"+player.getName()+" §7sur le serveur §aPvP-Warcraft§7.");
		player.sendMessage("  §7Nous sommes en période §f§lALPHA §7des bugs sont a prévoir. Nous");
		player.sendMessage("  §7comptons sur vous pour nous prévenir si vous en trouvez un.");
		player.sendMessage("§8§l«§6-§8§l»§8§m------------------------------------------§8§l«§6-§8§l»");
		
		if(wp.getPlayerStats().isAfk()) {
			player.sendMessage("§8[§a§l!§8] §7Attention vous êtes encore en §6Mode AFK§7. Pour le désactiver faites §b/afk§7.");
		}
		
	}
	
	@EventHandler
	public void onQUit(PlayerQuitEvent event) {
		
		WarPlayer wp = WarCore.getInstance().getUser(event.getPlayer());
		wp.save();
		WarCore.getInstance().deletePlayer(wp);
		
		if(wp.getModPlayer().isMod()) {
			WarCore.getInstance().staffModManager.desactiveMod(wp);
		}
	}


}
