package eu.pvpwarcraft.thetowers.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import eu.pvpwarcraft.thetowers.TheTowers;
import eu.pvpwarcraft.thetowers.handler.Step;
import eu.pvpwarcraft.thetowers.handler.Team.Teams;
import eu.pvpwarcraft.thetowers.handler.TowerPlayer;
import eu.pvpwarcraft.warcraftapi.WarCraftAPI;
import eu.pvpwarcraft.warcraftapi.configuration.Rank;
import eu.pvpwarcraft.warcraftapi.configuration.User;

public class PlayerChat implements Listener {

	@EventHandler
	public void OnJoin(AsyncPlayerChatEvent event) {

		Player player = event.getPlayer();
		
		User user = WarCraftAPI.getInstance().getUsers().get(player.getName());
		
		Rank rank = null;
		
		for(Rank ranks : Rank.values()){
			if (user.getRankPower() >= ranks.getMinPower() && user.getRankPower() < ranks.getMaxPower()){
				rank = ranks;
			}
		}
		
		TowerPlayer twpl = TheTowers.getPlayer(player);
		String message = event.getMessage();
		event.setCancelled(true);
		if(Step.getCurrentStep().equals(Step.IN_GAME)){
			if (!twpl.getTeam().equals(Teams.AUCUNE)) {
				if (message.startsWith("@")) {
					Bukkit.broadcastMessage("§8(§fTous§8) §f" + twpl.getTeam().getColor() + twpl.getTeam().getName() + " "+ player.getName() + " §8» §f" + message.toLowerCase().replaceFirst("@", ""));
				} else {
					for (Player all : Bukkit.getOnlinePlayers()) {
						TowerPlayer twall = TheTowers.getPlayer(all);
						if (twall.getTeam().equals(twpl.getTeam())) {
							twall.getPlayer()
									.sendMessage("§8(§fEquipe§8) §f" + twpl.getTeam().getColor() + twpl.getTeam().getName()
											+ " " + player.getName() + " §8» §f" + twpl.getTeam().getColor()
											+ message);
						}
					}
				}
			}else{
				if(user.getRankPower() >= 60){
					Bukkit.broadcastMessage(rank.getColor() + rank.getPrefix() + " " + rank.getColor() + player.getName() + " §8» " + rank.getColor() + message);
				}else{
					player.sendMessage("§6TheTowers §8» §cLe chat spectateur est désactivé.");
				}
			}
		}else{
			Bukkit.broadcastMessage("§8[§f§l"+user.getPoints()+"§8] "+ rank.getColor() + rank.getNickPrefix() +" §7"+ rank.getColor() + player.getName() + " §8» §7" + message.toLowerCase());
		}

	}

}
