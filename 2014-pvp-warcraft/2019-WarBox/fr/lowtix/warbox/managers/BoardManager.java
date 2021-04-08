package fr.lowtix.warbox.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import fr.lowtix.warbox.WarBox;
import fr.lowtix.warbox.players.WarPlayer;

public class BoardManager {
	
	public void setupBoard(Player player) {
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = board.registerNewObjective("boxboard", "dummy");
		obj.setDisplayName("§6§lPVP-BOX");

		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		obj.getScore("§3 ").setScore(10);
		obj.getScore("§r §e§lPROFIL").setScore(9);
		
		Team level = board.registerNewTeam("wlevel");
		level.setPrefix("§1");
		level.addEntry("§r §fNiveau: ");
		obj.getScore("§r §fNiveau: ").setScore(8);

		Team coins = board.registerNewTeam("wcoins");
		coins.setPrefix("§2");
		coins.addEntry("§r §fCoins: ");
		obj.getScore("§r §fCoins: ").setScore(7);
		
		Team exp = board.registerNewTeam("wexp");
		exp.setPrefix("§5");
		exp.addEntry("§r §fExp: ");
		obj.getScore("§r §fExp: ").setScore(6);
		
		obj.getScore("§4 ").setScore(5);
		obj.getScore("§r §e§lSTATS").setScore(4);
		
		Team kills = board.registerNewTeam("wkills");
		kills.setPrefix("§6");
		kills.addEntry("§r §fKills: ");
		obj.getScore("§r §fKills: ").setScore(3);
		
		Team ks = board.registerNewTeam("wks");
		ks.setPrefix("§7");
		ks.addEntry("§r §fSérie: ");
		obj.getScore("§r §fSérie: ").setScore(2);
		
		obj.getScore("§8 ").setScore(1);
		obj.getScore("§r §6pvp-warcraft.net").setScore(0);
		
		player.setScoreboard(board);
		
		updateScore(player);
	}

	public void updateScore(Player player) {
		
		Scoreboard board = player.getScoreboard();
		WarPlayer wp = WarBox.getInstance().getUser(player);
		
		if (board.getTeam("wlevel").getSuffix() != null && (board.getTeam("wlevel").getSuffix() != WarBox.getInstance().getLevelManager().getPrefix(wp.getLevel()))) {
			board.getTeam("wlevel").setSuffix(WarBox.getInstance().getLevelManager().getPrefix(wp.getLevel()));
		}
		
		if (board.getTeam("wcoins").getSuffix() != null && (board.getTeam("wcoins").getSuffix() != "§e" + wp.getCoins())) {
			board.getTeam("wcoins").setSuffix("§e" + wp.getCoins());
		}
		
		if (board.getTeam("wexp").getSuffix() != null && (board.getTeam("wexp").getSuffix() != "§b" + wp.getExp())) {
			board.getTeam("wexp").setSuffix("§b" + wp.getExp());
		}
		
		if (board.getTeam("wkills").getSuffix() != null && (board.getTeam("wkills").getSuffix() != "§c" + wp.getStats().getKills())) {
			board.getTeam("wkills").setSuffix("§c" + wp.getStats().getKills());
		}
		
		if (board.getTeam("wks").getSuffix() != null && (board.getTeam("wks").getSuffix() != "§a" + wp.getKillstreak())) {
			board.getTeam("wks").setSuffix("§a" + wp.getKillstreak());
		}
		
	}

}
