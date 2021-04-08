package eu.pvpwarcraft.warfight.managers.arenas;

public enum ArenaStates {
	
	MISCONFIGURED("Configuration interminée"),
	CONFIGURED("Configuration terminée"),
	IN_USE("Utilisation ");
	
	private String name;

	private ArenaStates(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
