package fr.lowtix.palatraining.handlers;

public class GameStatistics {

	private GamePlayer gplayer;
	private int kills;
	private int deaths;
	private int solo_game_count;
	private int team_game_count;
	private int solo_game_won;
	private int team_game_won;
	
	public GameStatistics(GamePlayer gplayer) {
		this.gplayer = gplayer;
		this.kills = 0;
		this.deaths = 0;
		this.solo_game_count = 0;
		this.team_game_count = 0;
		this.solo_game_won = 0;
		this.team_game_won = 0;
	}

	public GamePlayer getGplayer() {
		return gplayer;
	}

	public void setGplayer(GamePlayer gplayer) {
		this.gplayer = gplayer;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getSolo_game_count() {
		return solo_game_count;
	}

	public void setSolo_game_count(int solo_game_count) {
		this.solo_game_count = solo_game_count;
	}

	public int getTeam_game_count() {
		return team_game_count;
	}

	public void setTeam_game_count(int team_game_count) {
		this.team_game_count = team_game_count;
	}

	public int getSolo_game_won() {
		return solo_game_won;
	}

	public void setSolo_game_won(int solo_game_won) {
		this.solo_game_won = solo_game_won;
	}

	public int getTeam_game_won() {
		return team_game_won;
	}

	public void setTeam_game_won(int team_game_won) {
		this.team_game_won = team_game_won;
	}
	
}
