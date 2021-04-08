package fr.lowtix.cheatpatch.managers;

public class CheatTopResult {

	public String name;
	public int kills;

	public CheatTopResult(String name, int kills) {
		this.name = name;
		this.kills = kills;
	}

	public String getName() {
		return name;
	}

	public int getKills() {
		return kills;
	}
	
}
