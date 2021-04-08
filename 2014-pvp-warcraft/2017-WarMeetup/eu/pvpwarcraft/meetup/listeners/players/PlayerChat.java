package eu.pvpwarcraft.meetup.listeners.players;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import eu.pvpwarcraft.meetup.managers.PlayersInGameManager;
import eu.pvpwarcraft.meetup.utils.UserManager;

public class PlayerChat implements Listener {
	
	@EventHandler
	public void onCheat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		if(UserManager.isFonda(p)){
			e.setCancelled(true);
			if(PlayersInGameManager.isInGame(p)){
				Bukkit.broadcastMessage("§8[§4F§8] §4"+p.getName()+" §8» §f"+e.getMessage());
			}else{
				Bukkit.broadcastMessage("§8[§7Spectateur §8| §4F§8] §4"+p.getName()+" §8» §7"+e.getMessage());
			}
			return;
		}
		if(UserManager.isAdmin(p)){
			e.setCancelled(true);
			if(PlayersInGameManager.isInGame(p)){
				Bukkit.broadcastMessage("§8[§cA§8] §c"+p.getName()+" §8» §f"+e.getMessage());
			}else{
				Bukkit.broadcastMessage("§8[§7Spectateur §8| §cA§8] §c"+p.getName()+" §8» §7"+e.getMessage());
			}
			return;
		}
		if(UserManager.isMod(p)){
			e.setCancelled(true);
			if(PlayersInGameManager.isInGame(p)){
				Bukkit.broadcastMessage("§8[§6Mod§8] §6"+p.getName()+" §8» §f"+e.getMessage());
			}else{
				Bukkit.broadcastMessage("§8[§7Spectateur §8| §6Mod§8] §6"+p.getName()+" §8» §7"+e.getMessage());
			}
			return;
		}
		if(UserManager.isStaff(p)){
			e.setCancelled(true);
			if(PlayersInGameManager.isInGame(p)){
				Bukkit.broadcastMessage("§8[§2S§8] §2"+p.getName()+" §8» §f"+e.getMessage());
			}else{
				Bukkit.broadcastMessage("§8[§7Spectateur §8| §2S§8] §2"+p.getName()+" §8» §7"+e.getMessage());
			}
			return;
		}
		if(UserManager.isFriends(p)){
			e.setCancelled(true);
			if(PlayersInGameManager.isInGame(p)){
				Bukkit.broadcastMessage("§8[§3A§8] §3"+p.getName()+" §8» §f"+e.getMessage());
			}else{
				Bukkit.broadcastMessage("§8[§7Spectateur §8| §3A§8] §3"+p.getName()+" §8» §7"+e.getMessage());
			}
			return;
		}
		if(UserManager.isPartner(p)){
			e.setCancelled(true);
			if(PlayersInGameManager.isInGame(p)){
				Bukkit.broadcastMessage("§8[§dFamous§8] §d"+p.getName()+" §8» §f"+e.getMessage());
			}else{
				Bukkit.broadcastMessage("§8[§7Spectateur §8| §dFamous§8] §d"+p.getName()+" §8» §7"+e.getMessage());
			}
			return;
		}
		if(UserManager.isPrem(p)){
			e.setCancelled(true);
			if(PlayersInGameManager.isInGame(p)){
				Bukkit.broadcastMessage("§8[§aPremium§8] §a"+p.getName()+" §8» §f"+e.getMessage());
			}else{
				Bukkit.broadcastMessage("§8[§7Spectateur §8| §aPremium§8] §a"+p.getName()+" §8» §7"+e.getMessage());
			}
			return;
		}
		if(UserManager.isDefault(p)){
			e.setCancelled(true);
			if(PlayersInGameManager.isInGame(p)){
				Bukkit.broadcastMessage(" §7"+p.getName()+" §8» §f"+e.getMessage());
			}else{
				Bukkit.broadcastMessage("§8[§7Spectateur§8] §7"+p.getName()+" §8» §7"+e.getMessage());
			}
			return;
		}
	}

}
