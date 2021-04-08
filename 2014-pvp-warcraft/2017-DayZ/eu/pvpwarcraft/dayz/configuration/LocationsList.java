package eu.pvpwarcraft.dayz.configuration;

public enum LocationsList {
	
	SPAWN("Spawn"), FIRST_JOIN("First_Join");
	
	private String name;

	private LocationsList(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	

}
