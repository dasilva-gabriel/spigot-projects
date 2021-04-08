package eu.pvpwarcraft.thetowers.schedulers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import eu.pvpwarcraft.thetowers.TheTowers;
import eu.pvpwarcraft.thetowers.handler.Step;
import eu.pvpwarcraft.thetowers.handler.Team;
import eu.pvpwarcraft.thetowers.handler.Team.Teams;
import eu.pvpwarcraft.thetowers.handler.TowerPlayer;
import eu.pvpwarcraft.thetowers.managers.PlayerManager;
import eu.pvpwarcraft.thetowers.managers.TeamManager;
import eu.pvpwarcraft.thetowers.scoreboard.ScoreboardManager;
import eu.pvpwarcraft.thetowers.utils.PlayerUtils;

public class StartScheduler extends BukkitRunnable {
	
	private TheTowers main;
	private BossBar bossbarWait;
	private BossBar bossbarTimer;
	private int bbState = 1;
	private String title = "§eEvent TheTowers - By PvP-Warcraft";
	private boolean ascending = true;
	private int secondsState = 0;
	public static int timer = 120;
	private int minPlayers = 4;

	public StartScheduler(TheTowers main) {
		this.main = main;
	}

	@Override
	public void run() {
		
		if(bossbarWait == null){
			bossbarWait = Bukkit.createBossBar("§eEvent TheTowers §f- §7By PvP-Warcraft", BarColor.WHITE, BarStyle.SOLID, new BarFlag[0]);
		}
		
		if(bossbarTimer == null){
			bossbarTimer = Bukkit.createBossBar("§f§l* §aDébut dans §e120 secondes §f§l*", BarColor.GREEN, BarStyle.SEGMENTED_20, new BarFlag[0]);
		}
		
		if(main.getPlayers().size() == 8 && timer > 90){
			timer = 90;
		}
		if(main.getPlayers().size() == 10 && timer > 60){
			timer = 60;
		}
		
		if(main.getPlayers().size() == 14 && timer > 30){
			timer = 30;
		}
		
		if(secondsState >= 10){
			secondsState = 0;
		}else{
			secondsState++;
		}
		
		
		if(main.getPlayers().size() < minPlayers){
			
			if(timer != 120){
				timer = 120;
			}
			
			if(!bossbarTimer.getPlayers().isEmpty()){
				bossbarTimer.removeAll();
			}

			bossbarWait.setTitle(getTitle(bbState));

			if((bbState < title.length()) && ascending){
				if(!ascending){
					ascending = true;
				}
				bbState++;
			}else{
				if(ascending){
					ascending = false;
				}
				if(bbState == 3){
					ascending = true;
				}
				bbState--;
			}
			
			bossbarWait.setProgress(makePEC(bbState, title.length(), 1.0));
			
			for(Player all : Bukkit.getOnlinePlayers()){
				PlayerUtils.sendActionBar(all, "§6❱ §7En attente de §e"+ (minPlayers - main.getPlayers().size()) + " joueur(s) §6❰");
				if(!bossbarWait.getPlayers().contains(all)){
					bossbarWait.addPlayer(all);
				}
			}
		} else if (main.getPlayers().size() >= minPlayers) {
			
			if(((timer % 10 == 0)) && secondsState == 1 && (timer == 120 || timer == 60 || timer == 90)){
				for(Player all : Bukkit.getOnlinePlayers()){
					PlayerUtils.playSound(all, Sound.BLOCK_NOTE_PLING);
				}
				Bukkit.broadcastMessage("§6TheTowers §8» §7Début de la partie dans §b" + getTime().replace(":", " minute ") +  getSecond() + " §7!");
			}
			if((((timer <= 30) && (((timer % 10 == 0)) && secondsState == 1)) || timer <= 10) && secondsState == 1){
				for(Player all : Bukkit.getOnlinePlayers()){
					PlayerUtils.playSound(all, Sound.BLOCK_NOTE_PLING);
				}
				Bukkit.broadcastMessage("§6TheTowers §8» §7Début de la partie dans §b" + timer + getSecond() + " §7!");
			}
			
			if(secondsState == 10){
				timer--;
			}
			
			if(!bossbarWait.getPlayers().isEmpty()){
				bossbarWait.removeAll();
			}
			
			bossbarTimer.setTitle(getPoints(secondsState) +" §aDébut dans §e" + getTime().replace(":", " min ") + " " + getSecond() + " " + getPoints(secondsState));
			bossbarTimer.setProgress(makePEC(timer, 120, 1.0));
			
			for(Player all : Bukkit.getOnlinePlayers()){
				if(!bossbarTimer.getPlayers().contains(all)){
					bossbarTimer.addPlayer(all);
				}
			}
			
		}
		if(timer <= 0){
			for (Team.Teams team : Team.Teams.values()) {
				TheTowers.getTeam(team).setPoints(0);
			}
			for(Player all : TheTowers.getInstance().getPlayers()){
				ScoreboardManager.setupScoreboard(all);
			}
			GameScheduler game = new GameScheduler(TheTowers.getInstance());
			game.runTaskTimer(TheTowers.getInstance(), 5, 5);
			bossbarWait.removeAll();
			bossbarTimer.removeAll();
			cancel();
			Bukkit.broadcastMessage("§6TheTowers §8» §7La partie commence... Téléportation des joueurs en cours !");
			Step.setCurrentStep(Step.IN_GAME);
			for(Player all : TheTowers.getInstance().getPlayers()){				
				TowerPlayer allt = TheTowers.getPlayer(all);
				PlayerUtils.playSound(all, Sound.ENTITY_WITHER_SPAWN);
				
				all.sendMessage("§8§m--+----------------------------------------+--");
				all.sendMessage(" §8» §7Bienvenue chez les "+allt.getTeam().getColor() + allt.getTeam().getName());
				all.sendMessage(" §8» §7Vous êtes avec "+TheTowers.getTeam(allt.getTeam()).getPlayersList(allt.getTeam().getColor()));
				all.sendMessage("");
				all.sendMessage("  §8[§d§l?§8] §7Pour parler à tous le monde introduisez un §f'§b@§f' §7au début de votre message.");
				all.sendMessage("§8§m--+----------------------------------------+--");
				
				if (allt.getTeam().equals(Teams.AUCUNE)){
					Teams rteam = TeamManager.randomTeam();
					Bukkit.broadcastMessage("§6TheTowers §8» §7Vous n'avez pas sélectionné d'équipe. Vous rejoignez l'équipe " + rteam.getColor() + rteam.getName());
					allt.setTeam(TeamManager.randomTeam());
				}
				all.teleport(TheTowers.getTeam(allt.getTeam()).getSpawn());
				PlayerManager.respawnPlayer(allt);
			}
			
			return;
		}
		
	}
	
	private String getPoints(int step){
		String result = "§f";
		
		if(step == 1 || step == 2){
			result = "§c";
		}
		if(step == 3 || step == 4){
			result = "§3";
		}
		if(step == 5 || step == 6){
			result = "§d";
		}
		if(step == 7 || step == 8){
			result = "§e";
		}
		if(step == 9 || step == 10){
			result = "§2";
		}
		return result + "•";		
	}
	
	private String getTitle(int step){
		
		String first = title.substring(0, step);
		String second = title.substring(step, title.length());
		
		return first + "§6" + second;
	}
	
	private double makePEC(double arg0, double arg1, double arg3){
		double result = 0.0;
		
		result = (arg0*arg3)/arg1;
		
		return result;
	}
	
	private String getSecond(){
        return timer <= 1 ? " seconde" : " secondes";
    }
	
	private String getTime(){
		return new SimpleDateFormat("mm:ss").format(new Date(timer * 1000));
	}

}
