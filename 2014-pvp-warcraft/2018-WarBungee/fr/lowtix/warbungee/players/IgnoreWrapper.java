package fr.lowtix.warbungee.players;

import java.util.ArrayList;

import fr.lowtix.warbungee.WarBungee;

public class IgnoreWrapper {
	
	private BungeeProfile profile;
	private ArrayList<String> ignoredPlayers;
	private boolean ignoreall;

	public IgnoreWrapper(BungeeProfile bungeeProfile) {
		ignoredPlayers = new ArrayList<String>();
		profile = bungeeProfile;
		ignoreall = false;
	}
	
	public BungeeProfile getProfile() {
		return profile;
	}
	
	public boolean isIgnoreall() {
		return ignoreall;
	}
	
	public void setIgnoreall(boolean ignoreall) {
		this.ignoreall = ignoreall;
	}
	
	public String getSerial() {
		String res = "";
		
		for(String s: ignoredPlayers) {
			res += s + "$";
		}
		
		return res;
		
	}

	public void loadBySerial(String serial) {
		
		String[] tab = serial.split("$");
		
		for(String s : tab) {
			if(s.length() > 1) {
				ignoredPlayers.add(s);
			}
		}
		
	}
	
	public boolean isIgnored(String s) {
		return ignoredPlayers.contains(s);
	}
	
	public void addIgnoredPlayer(String s) {
		if(!isIgnored(s)) {
			ignoredPlayers.add(s);
		}
	}
	
	public void removeIgnoredPlayer(String s) {
		if(isIgnored(s)) {
			ignoredPlayers.remove(s);
		}
	}
	
	public ArrayList<String> getIgnoredPlayers() {
		return ignoredPlayers;
	}
	
	public void load() {
		
		WarBungee.getInstance().getSqlManager().getIgnore().load(this);

	}
	
	public void save() {
		
		WarBungee.getInstance().getSqlManager().getIgnore().save(this);

	}
	
}
