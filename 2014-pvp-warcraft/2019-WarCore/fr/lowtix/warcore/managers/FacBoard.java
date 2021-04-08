package fr.lowtix.warcore.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;

public class FacBoard {
	
	private Player player;
	private WarPlayer wPlayer;
	public boolean moderation = false;
	
	public FacBoard(WarPlayer facUser) {
		this.wPlayer = facUser;
		this.player = facUser.getPlayer();
		setupScoreboard(false);
	}

	public Scoreboard setupScoreboard(boolean moderationBoard) {
		
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = board.registerNewObjective("trainboard", "dummy");
		obj.setDisplayName("§6§lFACTIONS");

		obj.setDisplaySlot(DisplaySlot.SIDEBAR);

		this.moderation = moderationBoard;
		
		if(!moderationBoard) {
			
			obj.getScore("§3 ").setScore(8);
			
			Team stats = board.registerNewTeam("stats");
			stats.setPrefix("§e");
			stats.addEntry("§7K/D: ");
			obj.getScore("§7K/D: ").setScore(7);

			Team streak = board.registerNewTeam("streak");
			streak.setPrefix("§c");
			streak.addEntry("§7KS: ");
			obj.getScore("§7KS: ").setScore(6);
			
			obj.getScore("§2").setScore(5);
			
			Team level = board.registerNewTeam("level");
			level.setPrefix("§c");
			level.addEntry("§7Niveau: ");
			obj.getScore("§7Niveau: ").setScore(4);
			
			Team coins = board.registerNewTeam("coins");
			coins.setPrefix("§c");
			coins.addEntry("§7Coins: ");
			obj.getScore("§7Coins: ").setScore(3);
			
			Team points = board.registerNewTeam("points");
			points.setPrefix("§c");
			points.addEntry("§7Points: ");
			obj.getScore("§7Points: ").setScore(2);
		} else {
			
			obj.getScore("§2 ").setScore(6);
			
			obj.getScore("§cMode modération").setScore(5);
			
			obj.getScore("§4 ").setScore(4);
			
			Team track = board.registerNewTeam("track");
			track.setPrefix("§e");
			track.addEntry("§7Track: ");
			obj.getScore("§7Track: ").setScore(3);
			
			Team vanish = board.registerNewTeam("vanish");
			vanish.setPrefix("§e");
			vanish.addEntry("§7Vanish: ");
			obj.getScore("§7Vanish: ").setScore(2);
			
		}

		
		
		obj.getScore("§1").setScore(1);
		obj.getScore("§emc.pvp-warcraft.fr").setScore(0);

		player.setScoreboard(board);
		
		updateScore(false);
		
		return board;
	}

	public void updateScore(boolean turn) {

		try {
			Scoreboard board = player.getScoreboard();
			
			boolean resetup = false;
			boolean moderationMod = false;

			if (turn && board == null) {
				setupScoreboard(false);
			}
			
			if(!this.moderation) {
				if (board.getTeam("stats").getSuffix() != null && (board.getTeam("stats").getSuffix() != "§a"+wPlayer.getKills()+"§8/§c"+wPlayer.getDeaths())) {
					board.getTeam("stats").setSuffix("§a"+wPlayer.getKills()+"§8/§c"+wPlayer.getDeaths());
				}
				
				if (board.getTeam("streak").getSuffix() != null && (board.getTeam("streak").getSuffix() != "§d"+wPlayer.getStreak())) {
					board.getTeam("streak").setSuffix("§d"+wPlayer.getStreak());
				}
				
				if (board.getTeam("streak").getSuffix() != null && (board.getTeam("level").getSuffix() != "§b§l"+wPlayer.getLevel().getDisplayName())) {
					board.getTeam("level").setSuffix("§b§l"+wPlayer.getLevel().getDisplayName());
				}
				
				if (board.getTeam("coins").getSuffix() != null && (board.getTeam("coins").getSuffix() != "§e"+(int) wPlayer.getCoins()+" ⛃")) {
					board.getTeam("coins").setSuffix("§e"+(int) wPlayer.getCoins()+ " ⛃");
				}
				
				if (board.getTeam("points").getSuffix() != null && (board.getTeam("points").getSuffix() != "§f"+(int) wPlayer.getPoints()+" ✴")) {
					board.getTeam("points").setSuffix("§f"+(int) wPlayer.getPoints()+" ✴");
				}
			} else {
				if(wPlayer.getModPlayer().isTrack()) {
					String track = wPlayer.getModPlayer().getTrack();
					if(track.length() >= 13) {
						track = track.substring(0, 13);
						track += ".";
					}
					if (board.getTeam("track").getSuffix() != null && (board.getTeam("track").getSuffix() != "§e"+track)) {
						board.getTeam("track").setSuffix("§e"+track);
					}
				} else {
					if (board.getTeam("track").getSuffix() != null && (board.getTeam("track").getSuffix() != "§e-")) {
						board.getTeam("track").setSuffix("§e-");
					}
				}
				
				if(WarCore.getInstance().essentials.getVanishedPlayers().contains(wPlayer.getPlayer().getName())) {
					if (board.getTeam("vanish").getSuffix() != null && (board.getTeam("vanish").getSuffix() != "§2✔")) {
						board.getTeam("vanish").setSuffix("§2✔");
					}
				} else {
					if (board.getTeam("vanish").getSuffix() != null && (board.getTeam("vanish").getSuffix() != "§4✕")) {
						board.getTeam("vanish").setSuffix("§4✕");
					}
				}
			}
			
			if(this.moderation && !wPlayer.getModPlayer().isMod()) {
				moderationMod = false;
				resetup = true;
			} else if(!this.moderation && wPlayer.getModPlayer().isMod()) {
				moderationMod = true;
				resetup = true;
			}
			
			if(turn && resetup) {
				setupScoreboard(moderationMod);
			}
		} catch (Exception e) {
			setupScoreboard(false);
		}
	}

}
