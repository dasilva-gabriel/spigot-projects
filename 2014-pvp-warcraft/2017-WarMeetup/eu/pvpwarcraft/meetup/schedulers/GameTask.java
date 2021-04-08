package eu.pvpwarcraft.meetup.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import eu.pvpwarcraft.meetup.Meetup;
import eu.pvpwarcraft.meetup.managers.PlayersInGameManager;
import eu.pvpwarcraft.meetup.managers.border.BorderManager;
import eu.pvpwarcraft.meetup.utils.PlayerUtils;

public class GameTask extends BukkitRunnable {

	public static BukkitTask task;
	public static int timer = 0;
	private Meetup main;

	public GameTask(Meetup instance) {
		this.main = instance;
	}

	@Override
	public void run() {
		if (main.players.size() <= 1) {
			cancel();
		}

		if(timer == 30){
			for(Player all : Bukkit.getOnlinePlayers()){
				PlayerUtils.playSound(all, Sound.ENDERDRAGON_WINGS);
			}
			Bukkit.broadcastMessage("§6WarFight §8» §7La §bBordure §7commencera à diminuée dans §c30 secondes§7.");
		}
		if(timer == 45){
			for(Player all : Bukkit.getOnlinePlayers()){
				PlayerUtils.playSound(all, Sound.ENDERDRAGON_WINGS);
			}
			Bukkit.broadcastMessage("§6WarFight §8» §7La §bBordure §7commencera à diminuée dans §c15 secondes§7.");
		}
		if(timer == 55){
			for(Player all : Bukkit.getOnlinePlayers()){
				PlayerUtils.playSound(all, Sound.ENDERDRAGON_WINGS);
			}
			Bukkit.broadcastMessage("§6WarFight §8» §7La §bBordure §7commencera à diminuée dans §c5 secondes§7.");
		}
		if(timer == 60){
			for(Player all : Bukkit.getOnlinePlayers()){
				PlayerUtils.playSound(all, Sound.BLAZE_BREATH);
			}
			Bukkit.broadcastMessage("§6WarFight §8» §7La §bBordure §7commencera à diminue désormais de §e1 block/seconde§7.");
		}
		if(timer > 90){
			BorderManager.decreaseBorder();
		}
		
		if(timer == 900){
			Bukkit.broadcastMessage("§6WarFight §8» §720 minutes se sont écoulés, un death match va être lancé dans §c30 secondes§7.");
		}
		
		if(timer == 915){
			Bukkit.broadcastMessage("§6WarFight §8» §720 minutes se sont écoulés, un death match va être lancé dans §c15 secondes§7.");
		}
		
		if(timer == 925){
			Bukkit.broadcastMessage("§6WarFight §8» §720 minutes se sont écoulés, un death match va être lancé dans §c5 secondes§7.");
		}
		
		if(timer == 927){
			Bukkit.broadcastMessage("§6WarFight §8» §720 minutes se sont écoulés, un death match va être lancé dans §c3 secondes§7.");
		}
		
		if(timer == 928){
			Bukkit.broadcastMessage("§6WarFight §8» §720 minutes se sont écoulés, un death match va être lancé dans §c3 secondes§7.");
		}
		
		if(timer == 929){
			Bukkit.broadcastMessage("§6WarFight §8» §720 minutes se sont écoulés, un death match va être lancé dans §c1 seconde§7.");
		}
		
		if(timer == 930){
			for(Player all : Bukkit.getOnlinePlayers()){
				if(PlayersInGameManager.isInGame(all)){
					all.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 3, 2));
				}
			}
		}
		
		if(timer == 940){
			for(Player all : Bukkit.getOnlinePlayers()){
				if(PlayersInGameManager.isInGame(all)){
					all.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 3, 3));
				}
			}
		}
		
		if(timer == 950){
			for(Player all : Bukkit.getOnlinePlayers()){
				if(PlayersInGameManager.isInGame(all)){
					all.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 10, 4));
				}
			}
		}
		
		if(timer > 970){
			for(Player all : Bukkit.getOnlinePlayers()){
				if(PlayersInGameManager.isInGame(all)){
					all.getLocation().getBlock().setType(Material.LAVA);
					all.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 5, 5));
				}
			}
		}

		timer++;

	}

}
