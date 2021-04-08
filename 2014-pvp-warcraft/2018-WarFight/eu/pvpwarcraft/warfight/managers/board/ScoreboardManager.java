package eu.pvpwarcraft.warfight.managers.board;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardManager {

	private Player player;

	private Scoreboard lobby_board;
	private Objective lobby_obj;

	private Scoreboard elo_board;
	private Objective elo_obj;

	public ScoreboardManager(Player player) {
		this.player = player;

		this.lobby_board = Bukkit.getScoreboardManager().getNewScoreboard();
		this.lobby_obj = this.lobby_board.registerNewObjective("warF_lobby", "dummy");
		loadLobbyBoard();

		this.elo_board = Bukkit.getScoreboardManager().getNewScoreboard();
		this.elo_obj = this.elo_board.registerNewObjective("warF_elo", "dummy");
		loadEloBoard();
	}

	public void loadLobbyBoard() {
		Scoreboard board = this.lobby_board;
		Objective obj = this.lobby_obj;
		obj.setDisplayName("§6§lW§e§lar§6§lF§e§light");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);

		obj.getScore("§8§m----------------§2").setScore(7);

		obj.getScore("§8» §7Compte: §f§o" + player.getName()).setScore(6);

		obj.getScore("§8§m----------------§1").setScore(5);

		Team players = board.registerNewTeam("players");
		players.setPrefix("§c");
		players.addEntry("§c   §7");
		obj.getScore("§8» §7Joueurs: §e0").setScore(4);

		Team in_queue = board.registerNewTeam("in_queue");
		in_queue.setPrefix("§c");
		in_queue.addEntry("§c   §7");
		obj.getScore("§8   ▪ §7En file: §b0").setScore(3);

		Team in_game = board.registerNewTeam("in_game");
		in_game.setPrefix("§c");
		in_game.addEntry("§c   §7");
		obj.getScore("§8   ▪ §7En jeu: §b0").setScore(2);

		obj.getScore("§8§m----------------§0").setScore(1);

		obj.getScore("§6mc.pvp-warcraft.eu").setScore(0);
	}
	
	public void loadBoardToPlayer(BoardType type){
		Scoreboard board = null;
		
		if(type.equals(BoardType.ELO)){
			board = this.elo_board;
		}
		if(type.equals(BoardType.LOBBY)){
			board = this.lobby_board;
		}
		
		player.setScoreboard(board);
	}

	public void loadEloBoard() {

		Objective obj = this.elo_obj;

		obj.getScore("§8§m----------------§2").setScore(8);

		obj.getScore("§8» §7Compte: §f§o" + player.getName()).setScore(7);

		obj.getScore("§8§m----------------§1").setScore(6);
		
		obj.getScore("§8  ▪ §aBuildUHC: §e§l1200").setScore(5);
		obj.getScore("§8  ▪ §aArcher: §e§l1200").setScore(4);
		obj.getScore("§8  ▪ §aNoDebuff: §e§l1200").setScore(3);
		obj.getScore("§8  ▪ §aCombo: §e§l1200").setScore(2);

		obj.getScore("§8§m----------------§0").setScore(1);

		obj.getScore("§6mc.pvp-warcraft.eu").setScore(0);
	}

}
