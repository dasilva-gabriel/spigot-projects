package eu.pvpwarcraft.meetup.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import eu.pvpwarcraft.meetup.Meetup;
import eu.pvpwarcraft.meetup.handler.Step;
import eu.pvpwarcraft.meetup.managers.PlayersInGameManager;
import eu.pvpwarcraft.meetup.managers.border.BorderManager;
import eu.pvpwarcraft.meetup.managers.kits.KitsManager;
import eu.pvpwarcraft.meetup.managers.npc.WaitManager;
import eu.pvpwarcraft.meetup.managers.types.Types;
import eu.pvpwarcraft.meetup.managers.types.TypesManager;
import eu.pvpwarcraft.meetup.utils.PlayerUtils;
import eu.pvpwarcraft.warfightapi.WarFightAPI;
import eu.pvpwarcraft.warfightapi.managers.User;

public class GameStart extends BukkitRunnable{
	
	public static BukkitTask task;
	public static int timer = 180;
	private Meetup main;
	
	public GameStart(Meetup instance) {
		this.main = instance;
	}

	@Override
	public void run() {
		
		for(Player all : Bukkit.getOnlinePlayers()){
			all.setLevel(timer);
		}
		
		if(main.players.size() > 5 && timer > 120){
			timer = 120;
		}
		
		if(main.players.size() > 12 && timer > 90){
			timer = 90;
		}
		
		if(main.players.size() > 18 && timer > 60){
			timer = 60;
		}
		
		if(main.players.size() > 19 && timer > 15){
			timer = 15;
		}
		
		if(main.players.size() < 2){
			timer = 60;
			Bukkit.broadcastMessage("§6WarFight §8» §cIl n'y a plus assez de joueurs.");
			cancel();
			return;
		}
		
		if(timer == 60 || timer == 30 || timer == 15 || timer == 10){
			Bukkit.broadcastMessage("§6WarFight §8» §7Téléportation dans l'arène dans §b"+timer+" seconde(s)");
			for(Player all : Bukkit.getOnlinePlayers()){
				PlayerUtils.playSound(all, Sound.NOTE_BASS_GUITAR);
				PlayerUtils.sendTitle(all, "§b§l"+timer, "§7Début dans...", 0, 5, 1);
			}
		}
		
		if(timer == 5 || timer == 4){
			Bukkit.broadcastMessage("§6WarFight §8» §7Téléportation dans l'arène dans §e"+timer+" seconde(s)");
			for(Player all : Bukkit.getOnlinePlayers()){
				PlayerUtils.playSound(all, Sound.NOTE_BASS);
				PlayerUtils.sendTitle(all, "§e§l"+timer, "§7Début dans...", 0, 5, 1);
			}
		}
		
		if(timer == 3 || timer == 2 || timer == 1){
			Bukkit.broadcastMessage("§6WarFight §8» §7Téléportation dans l'arène dans §c"+timer+" seconde(s)");
			for(Player all : Bukkit.getOnlinePlayers()){
				PlayerUtils.playSound(all, Sound.NOTE_PLING);
				PlayerUtils.sendTitle(all, "§c§l"+timer, "§7Début dans...", 0, 5, 1);
			}
		}
		if(timer <= 0){
			Bukkit.broadcastMessage("§6WarFight §8» §dVotes terminés, vous avez décidés le type §e"+Types.getName(TypesManager.getTypesWinner()));
			BorderManager.setBorder(25 * PlayersInGameManager.getPlayersSize());
			Step.setCurrentStep(Step.PRE_GAME);
			KitsManager.init();
			for(Player all : Bukkit.getOnlinePlayers()){
				User user = WarFightAPI.getInstance().getUsers().get(all.getName());
				if(PlayersInGameManager.isInGame(all)){
					user.getStats().setMeetUpParties(Integer.valueOf(user.getStats().getMeetUpParties() + 1));
				}
				all.getInventory().clear();
				all.setLevel(0);
				all.getInventory().setArmorContents(null);
				KitsManager.giveItems(all);
				WaitManager.teleportPlayer(all);
				all.getLocation().getChunk().load();
				Meetup.clearSpawnBox();
				all.getLocation().subtract(0, 1, 0).getBlock().setType(Material.STAINED_GLASS);
			}
			GamePreGame task = new GamePreGame(Meetup.getInstance());
			task.runTaskTimer(Meetup.getInstance(), 20, 20);
			cancel();
		}
		
		
		timer--;
		
	}

}
