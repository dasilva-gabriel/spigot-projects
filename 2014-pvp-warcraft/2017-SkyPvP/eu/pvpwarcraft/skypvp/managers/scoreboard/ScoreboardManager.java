package eu.pvpwarcraft.skypvp.managers.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardManager {
	
	public static Scoreboard setupScoreboard(Player p) {
	    Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

	    Objective obj = board.registerNewObjective("skypvp", "dummy");
	    obj.setDisplayName("§8• §3§lSkyPvP §8•");
	    obj.setDisplaySlot(DisplaySlot.SIDEBAR);

	    obj.getScore("§b ").setScore(11);
	    
	    Team coins = board.registerNewTeam("coins");
	    coins.setPrefix("§1");
	    coins.addEntry(" §8▶ §7");
	    obj.getScore(" §8▶ §7").setScore(10);
	    
	    obj.getScore("§3 ").setScore(9);


	    Team kills = board.registerNewTeam("kills");
	    kills.setPrefix("§0");
	    kills.addEntry(" §8▶ §7Tués: ");
	    obj.getScore(" §8▶ §7Tués: ").setScore(8);


	    Team deaths = board.registerNewTeam("deaths");
	    deaths.setPrefix("§5");
	    deaths.addEntry(" §8▶ §7Morts: §e");
	    obj.getScore(" §8▶ §7Morts: §e").setScore(7);
	    
	    obj.getScore("§4 ").setScore(6);
	    
	    Team first = board.registerNewTeam("first");
	    first.setPrefix("§l");
	    first.addEntry("§6 ➀  ");
	    obj.getScore("§6 ➀ §7Actuellement").setScore(5);
	    
	    Team second = board.registerNewTeam("second");
	    second.setPrefix("§f");
	    second.addEntry("§7 ➁ ");
	    obj.getScore("§7 ➁ §7en dév").setScore(4);
	    
	    Team third = board.registerNewTeam("third");
	    third.setPrefix("§3");
	    third.addEntry("§c ➂");
	    obj.getScore("§c ➂ §7#Soon").setScore(3);
	    
	    obj.getScore("§7 Faites §d/top").setScore(2);

	    obj.getScore("§8 ").setScore(1);

	    obj.getScore("§6mc.pvp-warcraft.eu").setScore(0);

	    p.setScoreboard(board);
	    return board;
	  }

}
