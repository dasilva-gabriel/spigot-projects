package fr.lowtix.cheatpatch;

import fr.lowtix.cheatpatch.sql.CheatSQL;

public class PlayerWrapper {
	
	private final String name;
	private int kills;
	private int deaths;
	
	private boolean scoreboard;
	
	public PlayerWrapper(String name) {
		this.name = name;
		
		this.kills = 0;
		this.deaths = 0;
		this.scoreboard = true;
		
		CheatPatch.getInstance().addPlayer(this);
		CheatSQL.setupWrapper(this);
	}

	public void destruct() {
		save();
		CheatPatch.getInstance().removePlayer(this);
	}
	
	public void save() {
		CheatSQL.save(this);
	}
	
	public String getName() {
		return name;
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

	public void setScoreboard(boolean scoreboard) {
		this.scoreboard = scoreboard;
	}
	
	public boolean getScoreboard() {
		return this.scoreboard;
	}
}
