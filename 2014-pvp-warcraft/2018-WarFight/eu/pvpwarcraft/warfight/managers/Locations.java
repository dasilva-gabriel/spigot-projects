package eu.pvpwarcraft.warfight.managers;

public enum Locations {
	
	SPAWN("Spawn", "spawn", true),
	EDIT("Editeur de kits", "editing", true);
	
	private String name;
	private String path;
	private boolean isPlayer;
	
	private Locations(String name, String path, boolean isPlayer) {
		this.name = name;
		this.path = path;
		this.isPlayer = isPlayer;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public boolean isPlayer() {
		return isPlayer;
	}
	
	

}
