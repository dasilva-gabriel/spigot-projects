package eu.pvpwarcraft.meetup.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import eu.pvpwarcraft.meetup.Meetup;
import eu.pvpwarcraft.meetup.handler.Step;
import eu.pvpwarcraft.meetup.managers.kits.KitsManager;
import eu.pvpwarcraft.meetup.managers.npc.WaitManager;
import eu.pvpwarcraft.meetup.utils.PlayerUtils;

public class GamePreGame extends BukkitRunnable {

	public static BukkitTask task;
	public static int timer = 65;
	private Meetup main;

	public GamePreGame(Meetup instance) {
		this.main = instance;
	}

	// 30 -> PvP
	// 0 -> Border

	@Override
	public void run() {
		if (main.players.size() <= 1) {
			cancel();
		}

		if (timer > 60) {
			int i = timer - 60;
			for (Player all : Bukkit.getOnlinePlayers()) {
				WaitManager.teleportPlayer(all);
			}
			Bukkit.broadcastMessage("§6WarFight §8» §eLa partie commence dans §a" + i + " seconde(s)");
		}

		if (timer == 60) {
			Bukkit.broadcastMessage("§6WarFight §8» §7La Phase de PvP sera active dans quelques secondes.");
			Bukkit.broadcastMessage("§6WarFight §8» §7Faites §d§l/retry §7pour retenter d'avoir un meilleur Stuff !");
			KitsManager.init();
			KitsManager.moreCheat();
			for (Player all : Bukkit.getOnlinePlayers()) {
				WaitManager.teleportPlayer(all);
				all.getLocation().subtract(0, 1, 0).getBlock().setType(Material.AIR);
				PlayerUtils.playSound(all, Sound.BLAZE_DEATH);
			}
			for (Entity ents : Bukkit.getWorld("world").getEntities()) {
				if (!(ents instanceof Player)) {
					ents.remove();
				}
				
			}
		}

		if (timer == 50 || timer == 40 || timer == 35 || timer == 34 || timer == 33 || timer == 32 || timer == 31) {
			int i = timer - 30;
			Bukkit.broadcastMessage("§6WarFight §8» §7Le PvP sera actif dans §3" + i + " seconde(s)§7.");
			for (Player all : Bukkit.getOnlinePlayers()) {
				PlayerUtils.playSound(all, Sound.NOTE_SNARE_DRUM);
			}
		}
		if (timer == 30) {
			Step.setCurrentStep(Step.IN_GAME);
			Bukkit.broadcastMessage("§6WarFight §8» §aLe PvP est désormais actif!");
			for (Player all : Bukkit.getOnlinePlayers()) {
				PlayerUtils.playSound(all, Sound.ENDERDRAGON_HIT);
				PlayerUtils.sendTitle(all, "", "§cPvP actif !", 0, 3, 1);
			}
		}
		
		if(timer == 0){
			GameTask task = new GameTask(Meetup.getInstance());
			task.runTaskTimer(Meetup.getInstance(), 20, 20);
			cancel();
		}

		timer--;

	}

}
