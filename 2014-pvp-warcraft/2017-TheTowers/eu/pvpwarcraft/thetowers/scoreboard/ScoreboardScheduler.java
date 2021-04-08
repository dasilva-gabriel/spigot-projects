package eu.pvpwarcraft.thetowers.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import eu.pvpwarcraft.thetowers.TheTowers;
import eu.pvpwarcraft.thetowers.handler.Team.Teams;
import eu.pvpwarcraft.thetowers.handler.TowerPlayer;

public class ScoreboardScheduler {

	public static void updateScore() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			Scoreboard board = p.getScoreboard();

			TowerPlayer towerpl = TheTowers.getPlayer(p);

			if (board == null) {
				board = ScoreboardManager.setupScoreboard(p);
			}

			if (board.getTeam("stats").getSuffix() != "§a" + towerpl.getKills() + "§8/§c" + towerpl.getDeaths()) {
				board.getTeam("stats").setSuffix("§a" + towerpl.getKills() + "§8/§c" + towerpl.getDeaths());
			}

			if (board.getTeam("orange").getSuffix() != "§e§l" + TheTowers.getTeam(Teams.ORANGE).getPoints()) {

				board.getTeam("orange").setSuffix("§e§l" + TheTowers.getTeam(Teams.ORANGE).getPoints());

			}

			if (board.getTeam("cyan").getSuffix() != "§e§l" + TheTowers.getTeam(Teams.CYAN).getPoints()) {

				board.getTeam("cyan").setSuffix("§e§l" + TheTowers.getTeam(Teams.CYAN).getPoints());

			}

		}

	}

}
