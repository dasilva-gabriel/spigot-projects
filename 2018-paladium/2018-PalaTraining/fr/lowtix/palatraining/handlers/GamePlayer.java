package fr.lowtix.palatraining.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.lowtix.palatraining.PalaTraining;

public class GamePlayer {

	private Player player;
	private boolean inGame;
	private boolean inTeam;
	private Team team;
	private Game game;
	private GameStatistics stats;
	
	public GamePlayer(Player player) {
		this.player = player;
		this.inGame = false;
		this.inTeam = false;
		this.team = null;
		this.game = null;
		this.stats = new GameStatistics(this);
		
		PalaTraining.getInstance().addGamePlayer(this);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean isInGame() {
		return (inGame && (getGame()!=null));
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}

	public boolean isInTeam() {
		return inTeam;
	}

	public void setInTeam(boolean inTeam) {
		this.inTeam = inTeam;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		if(game == null ) {
			Bukkit.broadcastMessage(getPlayer().getName() + " GAME SET TO > NULL");
		} else {
			Bukkit.broadcastMessage(getPlayer().getName() + " GAME SET TO > " + game.getId());
		}
		this.game = game;
	}

	public GameStatistics getStats() {
		return stats;
	}

	public void setStats(GameStatistics stats) {
		this.stats = stats;
	}
	
	
	
}
