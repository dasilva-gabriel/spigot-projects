package eu.pvpwarcraft.thetowers.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardManager {
	
	public static Scoreboard setupScoreboard(Player p) {
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
    	Objective obj = board.registerNewObjective("towersscore", "dummy");
	    obj.setDisplayName("§6◈ §eTheTowers §6◈");
	    obj.setDisplaySlot(DisplaySlot.SIDEBAR);
	    
	    obj.getScore(" ").setScore(6);
	    
	    Team stats = board.registerNewTeam("stats");
	    stats.setPrefix("§e");
	    stats.addEntry(" §8» §7Stats: ");
	    obj.getScore(" §8» §7Stats: ").setScore(5);
	    
	    obj.getScore("   ").setScore(4);
	    
	    Team orange = board.registerNewTeam("orange");
	    orange.setPrefix("§e");
	    orange.addEntry("§8[§6●§8] §6Oranges: ");
	    obj.getScore("§8[§6●§8] §6Oranges: ").setScore(3);
	    
	    Team cyan = board.registerNewTeam("cyan");
	    cyan.setPrefix("§e");
	    cyan.addEntry("§8[§3●§8] §3Cyans: ");
	    obj.getScore("§8[§3●§8] §3Cyans: ").setScore(2);
	    
	    
	    obj.getScore("      ").setScore(1);
	    
	    obj.getScore("§dmc.pvp-warcraft.eu").setScore(0);
	    
	    p.setScoreboard(board);
		return board;
	  }

}
