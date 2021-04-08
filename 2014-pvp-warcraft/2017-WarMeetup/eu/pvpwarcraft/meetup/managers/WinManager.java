package eu.pvpwarcraft.meetup.managers;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import eu.pvpwarcraft.meetup.Meetup;
import eu.pvpwarcraft.meetup.handler.Step;
import eu.pvpwarcraft.meetup.handler.servers.ServersManager;
import eu.pvpwarcraft.meetup.utils.PlayerUtils;
import eu.pvpwarcraft.warfightapi.WarFightAPI;
import eu.pvpwarcraft.warfightapi.managers.User;

public class WinManager {
	
    static int i = 0;
	
	public static void CheckWin(){
		if(PlayersInGameManager.getPlayersSize() == 1){
			Step.setCurrentStep(Step.POST_GAME);
			Player player = PlayersInGameManager.winPlayer();
			User user = WarFightAPI.getInstance().getUsers().get(player.getName());
			player.sendMessage("§2Gemmes §8» §7Vous recevez §e+10 gemmes §8(§bPartie remportée§8)");
			user.addGemmes(10);
			user.save();
			i = 0;
			Bukkit.broadcastMessage("§d§k|§a§k|§e§k|§2§k|§6§k|§5§k|§f§k|§r §6§lFélicitations §d§k|§a§k|§e§k|§2§k|§6§k|§5§k|§f§k|§r §eBravo à §6"+PlayersInGameManager.winPlayer().getName()+" §equi gagne la partie.");
			for(Player all : Bukkit.getOnlinePlayers()){
				PlayerUtils.sendTitle(all, "§7Partie terminée !", "§7Bravo §3"+player.getName(), 0, 500, 0);
			}
			new BukkitRunnable() {
				
				@Override
				public void run() {
					i++;
					for(Player all : Bukkit.getOnlinePlayers()){
						if(PlayersInGameManager.winPlayer().equals(all)){
							PlayerUtils.spawnFirework(all.getLocation(), FireworkEffect.Type.BALL_LARGE, 2, Color.YELLOW, Color.FUCHSIA, true, true);
							PlayerUtils.spawnFirework(all.getLocation(), FireworkEffect.Type.BALL_LARGE, 1, Color.WHITE, Color.GREEN, true, true);
						}
					}
					if(i == 30){
						for(Player all : Bukkit.getOnlinePlayers()){
							all.sendMessage("§6WarFight §8» §ePartie terminée, nous vous renvoyons au serveur principal.");
							ServersManager.sendToWarFightLobby(all);
						}
						new BukkitRunnable() {
							
							@Override
							public void run() {
								for(Player all : Bukkit.getOnlinePlayers()){
									PlayerUtils.sendTitle(all, "§7Partie terminée !", "§7Bravo §3"+player.getName(), 0, 1, 0);
								}
								Bukkit.spigot().restart();
								
							}
						}.runTaskLater(Meetup.getInstance(), 20);
					}
				}
			}.runTaskTimer(Meetup.getInstance(), 10, 10);
		}
	}

}
