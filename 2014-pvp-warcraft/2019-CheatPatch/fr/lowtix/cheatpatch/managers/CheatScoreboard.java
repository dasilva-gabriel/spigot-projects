package fr.lowtix.cheatpatch.managers;

import java.util.LinkedList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;

import fr.lowtix.cheatpatch.CheatPatch;
import fr.lowtix.cheatpatch.PlayerWrapper;
import fr.lowtix.cheatpatch.schedulers.ScoreboardScheduler;

public class CheatScoreboard {

	/*
	 * 1: Perso
	 * 2: Faction
	 * 3: TopKill
	 */
	public int top = 1;
	
	public CheatScoreboard() {
		new ScoreboardScheduler().runTaskTimerAsynchronously(CheatPatch.getInstance(), 100, 100);
	}
	
	@SuppressWarnings("deprecation")
	public void setup(Player player, boolean create) {
		
		PlayerWrapper wp = CheatPatch.getInstance().getPlayer(player.getName());
		
		Scoreboard board;
		if ((create) || (player.getScoreboard() == null)) {
			board = Bukkit.getScoreboardManager().getNewScoreboard();
		} else {
			board = player.getScoreboard();
			if (board.getObjective(DisplaySlot.SIDEBAR) != null) {
				board.getObjective(DisplaySlot.SIDEBAR).unregister();
			}
		}
		
		if(!wp.getScoreboard()) {
			return;
		}
		
		Objective obj = board.registerNewObjective("cheatboard", "dummy");
		
		obj.setDisplayName("§8» §6§lPvP-WarCraft §8«");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		double money = CheatPatch.getInstance().getEconomy().getBalance(player.getName());
		FPlayer fplayer = FPlayers.getInstance().getByPlayer(player);
		
		
		if (top == 1) {
			obj.getScore("   ").setScore(10);
			obj.getScore("§8» §7Infos joueurs:").setScore(9);
			obj.getScore(" §8■ §7Compte: §e"+player.getName()).setScore(8);
			obj.getScore(" §8■ §7Argent: §a"+(int) money+"$").setScore(7);
			obj.getScore(" §8■ §7Stats: §a"+wp.getKills()+"§f/§c"+wp.getDeaths()).setScore(6);
			obj.getScore("").setScore(5);
			obj.getScore("§7Power: §b"+ (int) fplayer.getPower()).setScore(4);
			obj.getScore("§7F-Fly: §f"+(fplayer.isFlying() ? "§aActif" : "§cInactif")).setScore(3);
			
			obj.getScore("  ").setScore(2);
			obj.getScore("§7Joueurs: §b"+ Bukkit.getOnlinePlayers().size()).setScore(1);
			obj.getScore("§6mc.pvp-warcraft.net").setScore(0);
			player.setScoreboard(board);
		
		} else if (top == 2 && (fplayer.getFaction() != null && !fplayer.getFactionId().equals("0"))) {
			Faction f = fplayer.getFaction();
			
			obj.getScore("   ").setScore(10);
			obj.getScore("§8» §7Infos factions:").setScore(9);
			obj.getScore(" §8■ §7Nom: §b"+f.getTag()).setScore(8);
			obj.getScore(" §8■ §7Membres: §a"+f.getSize()).setScore(7);
			obj.getScore(" §8■ §7Chef: §c"+f.getFPlayerLeader().getName()).setScore(6);
			obj.getScore(" §8■ §7Power: §e"+(int) f.getPower()+"§f/§6"+ (int) f.getPowerMax()).setScore(5);
			obj.getScore("").setScore(4);
			obj.getScore("§7Voir le top §e/f top").setScore(3);
			obj.getScore("  ").setScore(2);
			obj.getScore("§7Joueurs: §b"+ Bukkit.getOnlinePlayers().size()).setScore(1);
			obj.getScore("§6mc.pvp-warcraft.net").setScore(0);
			player.setScoreboard(board);
			
		} else if (top == 3) {
			
			LinkedList<CheatTopResult> top = CheatPatch.getInstance().getTopManager().getTop();
			if (top.isEmpty()) {
				obj.getScore("§0 §r").setScore(10);
				obj.getScore("§1 §r").setScore(9);
				obj.getScore("§2 §r").setScore(8);
				obj.getScore("§3 §r").setScore(7);
				obj.getScore("§cErreur dans le").setScore(6);
				obj.getScore("§cchargement du").setScore(5);
				obj.getScore("§cTopKill").setScore(4);
				obj.getScore("§4 §r").setScore(3);
				obj.getScore("§5 §r").setScore(2);
				obj.getScore("§6 §r").setScore(1);
				obj.getScore("§7 §r").setScore(0);
			} else {
				obj.getScore(getTopString(top, 0, "§c#1 §7")).setScore(10);
				obj.getScore(getTopString(top, 1, "§6#2 §7")).setScore(9);
				obj.getScore(getTopString(top, 2, "§e#3 §7")).setScore(8);
				obj.getScore(getTopString(top, 3, "§f#4 §7")).setScore(7);
				obj.getScore(getTopString(top, 4, "§f#5 §7")).setScore(6);
				obj.getScore(getTopString(top, 5, "§f#6 §7")).setScore(5);
				obj.getScore(getTopString(top, 6, "§f#7 §7")).setScore(4);
				obj.getScore(getTopString(top, 7, "§f#8 §7")).setScore(3);
				obj.getScore(getTopString(top, 8, "§f#9 §7")).setScore(2);
				obj.getScore(getTopString(top, 9, "§f#10 §7")).setScore(1);
				
				obj.getScore("§6mc.pvp-warcraft.net").setScore(0);
				
				player.setScoreboard(board);
			}
		} else {
			
			int i = CheatPatch.getInstance().getVoteManager().getVote();
			
			obj.getScore("   ").setScore(10);
			obj.getScore("§8» §7Votes:").setScore(9);
			obj.getScore(" §8■ §7Enregistrés: §b"+i).setScore(8);
			obj.getScore(" §8■ §7Gain:").setScore(7);
			obj.getScore("§r  §cx1 Clef BigBox").setScore(6);
			obj.getScore(" ").setScore(5);
			obj.getScore("§8» §7Clef §eBigBox §7dans:").setScore(4);
			obj.getScore(" §8■ §b"+ (CheatPatch.getInstance().getVoteManager().getMax() - i) + " votes").setScore(3);
			obj.getScore("  ").setScore(2);
			obj.getScore("§7Joueurs: §b"+ Bukkit.getOnlinePlayers().size()).setScore(1);
			obj.getScore("§6mc.pvp-warcraft.net").setScore(0);
			player.setScoreboard(board);
			
		}
		
	}
	
	private String getTopString(LinkedList<CheatTopResult> top, int index, String prefix) {
		CheatTopResult topres = top.get(index);
		return prefix+topres.getName() + " §f- §a"+topres.getKills();
	}

}
