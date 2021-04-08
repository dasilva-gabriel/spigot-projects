package eu.pvpwarcraft.thetowers.managers;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import eu.pvpwarcraft.thetowers.TheTowers;
import eu.pvpwarcraft.thetowers.handler.HubHandler;
import eu.pvpwarcraft.thetowers.handler.Step;
import eu.pvpwarcraft.thetowers.handler.Team.Teams;
import eu.pvpwarcraft.thetowers.handler.TowerPlayer;
import eu.pvpwarcraft.thetowers.scoreboard.ScoreboardScheduler;
import eu.pvpwarcraft.thetowers.utils.PlayerUtils;

public class WinManager {
	
	public static void winByWinner(Teams winner){
		Step.setCurrentStep(Step.POST_GAME);
		Bukkit.broadcastMessage("§6TheTowers §8» §7L'équipe " + winner.getColor() + winner.getName() + " §7gagne la partie avec ses §b10 points §7!");
		for (Player all : Bukkit.getOnlinePlayers()) {
			TowerPlayer alltw = TheTowers.getPlayer(all);
			all.teleport(winner.getSpawn());
			stop();
			if (alltw.getTeam() != winner) {
				all.getInventory().clear();
				all.getInventory().setArmorContents(null);
				all.setGameMode(GameMode.SPECTATOR);
				PlayerUtils.sendTitle(all, "§c§lDEFAITE", "§7Votre équipe à perdue...", 0, 10, 5);
			}else{
				PlayerUtils.sendTitle(all, "§a§lVICTOIRE", "§7Votre équipe à gagnée !", 0, 10, 5);
				PlayerUtils.spawnFirework(all.getLocation(), FireworkEffect.Type.BALL_LARGE, 2, Color.ORANGE, Color.YELLOW, true, true);
				PlayerUtils.spawnFirework(all.getLocation(), FireworkEffect.Type.BALL_LARGE, 1, Color.AQUA, Color.BLUE, true, true);
			}
		}
	}
	
	public static void winByLoser(Teams loser){
		Step.setCurrentStep(Step.POST_GAME);
		Bukkit.broadcastMessage("§6TheTowers §8» §7L'équipe " + loser.getColor() + loser.getName() + " §7perd la partie car elle ne contient plus de joueurs §7!");
		for (Player all : Bukkit.getOnlinePlayers()) {
			TowerPlayer alltw = TheTowers.getPlayer(all);
			all.teleport(loser.getSpawn());
			stop();
			if (alltw.getTeam() == loser) {
				all.getInventory().clear();
				all.getInventory().setArmorContents(null);
				all.setGameMode(GameMode.SPECTATOR);
				PlayerUtils.sendTitle(all, "§c§lDEFAITE", "§7Votre équipe à perdue...", 0, 10, 5);
			}else{
				PlayerUtils.sendTitle(all, "§a§lVICTOIRE", "§7Votre équipe à gagnée !", 0, 10, 5);
				PlayerUtils.spawnFirework(all.getLocation(), FireworkEffect.Type.BALL_LARGE, 2, Color.ORANGE, Color.YELLOW, true, true);
				PlayerUtils.spawnFirework(all.getLocation(), FireworkEffect.Type.BALL_LARGE, 1, Color.AQUA, Color.BLUE, true, true);
			}
		}
	}
	
	private static void stop(){
		for(Player all : Bukkit.getOnlinePlayers()){
			HubHandler.sendToHub(all);
		}
		ScoreboardScheduler.updateScore();
		new BukkitRunnable() {
			
			@Override
			public void run() {
				Bukkit.getScheduler().cancelAllTasks();
	            Bukkit.spigot().restart();
				
			}
		}.runTaskLater(TheTowers.getInstance(), 200);
	}

}
