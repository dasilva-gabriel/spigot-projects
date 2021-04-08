package eu.pvpwarcraft.meetup.managers.board;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;

import eu.pvpwarcraft.meetup.Meetup;
import eu.pvpwarcraft.meetup.managers.PlayersManager;

public class ScoreboardScheduler extends BukkitRunnable {

	@Override
	public void run() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			Scoreboard board = p.getScoreboard();

			if (board == null) {
				board = ScoreboardManager.setupScoreboard(p);
			}

			if (board.getTeam("players").getSuffix() != "§e" + Meetup.getInstance().players.size() + "§8/§e20") {
				board.getTeam("players").setSuffix("§e" + Meetup.getInstance().players.size() + "§8/§e20");
			}
			if (board.getTeam("kills").getSuffix() != "§a"+PlayersManager.getKills(p)) {
				board.getTeam("kills").setSuffix("§a"+PlayersManager.getKills(p));
			}
			if (board.getTeam("percent").getSuffix() != "§d"+PlayersManager.getPercent(p)+"%") {
				board.getTeam("percent").setSuffix("§d"+PlayersManager.getPercent(p)+"%");
			}

		}

	}

}
