package fr.lowtix.warbungee.sql;

public class SQLManager {
	
	private IgnoreTable ignore;
	
	public SQLManager() {
		ignore = new IgnoreTable();
	}

	public IgnoreTable getIgnore() {
		return ignore;
	}
	
}
