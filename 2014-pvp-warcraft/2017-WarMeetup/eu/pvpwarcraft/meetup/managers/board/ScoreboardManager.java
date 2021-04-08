package eu.pvpwarcraft.meetup.managers.board;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardManager {
	
	public static Scoreboard setupScoreboard(Player p) {
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
    	Objective obj = board.registerNewObjective("lobby_scoreboard", "dummy");
	    obj.setDisplayName("§6§lW§e§lar§6§lF§e§light");
	    obj.setDisplaySlot(DisplaySlot.SIDEBAR);
	    
	    obj.getScore("   ").setScore(9);
	    
	    obj.getScore("§8» §7Jeu: §bUHCMeetup").setScore(8);
	    
	    obj.getScore("    ").setScore(7);
	    
	    Team players = board.registerNewTeam("players");
	    players.setPrefix("§e");
	    players.addEntry("§8» §7Joueurs: ");
	    obj.getScore("§8» §7Joueurs: ").setScore(6);
	    
	    Team kills = board.registerNewTeam("kills");
	    kills.setPrefix("§e");
	    kills.addEntry("§8» §7Kills: ");
	    obj.getScore("§8» §7Kills: ").setScore(5);
	    
	    Team percent = board.registerNewTeam("percent");
	    percent.setPrefix("§e");
	    percent.addEntry("§8» §7Force: ");
	    obj.getScore("§8» §7Force: ").setScore(4);
	    
	    obj.getScore(" ").setScore(3);
	    
	    obj.getScore("  §8• §7Teams: §c✘").setScore(2);
	    
	    obj.getScore("  §8• §7Camp: §c✘").setScore(1);
	    
	    obj.getScore("      ").setScore(0);
	    
	    obj.getScore("§6mc.pvp-warcraft.eu").setScore(-1);
	    
	    p.setScoreboard(board);
		return board;

	    
	  }

}
