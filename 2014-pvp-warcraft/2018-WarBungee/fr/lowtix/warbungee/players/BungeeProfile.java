package fr.lowtix.warbungee.players;

import java.util.LinkedList;

import fr.lowtix.warbungee.WarBungee;

public class BungeeProfile {
	
	private String name;

	private LinkedList<String> lastMessages;
	
	private String lastPrivateMessageUser;
	private String lastPrivateMessage;
	private long lastPrivateMessageTime;
	
	private IgnoreWrapper ignoreWrapper;
	
	public BungeeProfile(String name) {
		this.name = name;
		this.lastMessages = new LinkedList<String>();
		this.lastPrivateMessage = "";
		this.lastPrivateMessageTime = 0;
		this.lastPrivateMessageUser = "";
		
		this.ignoreWrapper = new IgnoreWrapper(this);
		
		WarBungee.getInstance().addProfile(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedList<String> getLastMessages() {
		return lastMessages;
	}

	public void setLastMessages(LinkedList<String> lastMessages) {
		this.lastMessages = lastMessages;
	}

	public String getLastPrivateMessage() {
		return lastPrivateMessage;
	}

	public void setLastPrivateMessage(String lastPrivateMessage) {
		this.lastPrivateMessage = lastPrivateMessage;
	}

	public long getLastPrivateMessageTime() {
		return lastPrivateMessageTime;
	}

	public void setLastPrivateMessageTime(long lastPrivateMessageTime) {
		this.lastPrivateMessageTime = lastPrivateMessageTime;
	}
	
	public double timeElapsesLastPrivateMessage() {
		return (double) ((System.currentTimeMillis() - getLastPrivateMessageTime())/1000);
	}
	
	public IgnoreWrapper getIgnoreWrapper() {
		return ignoreWrapper;
	}

	public String getLastPrivateMessageUser() {
		return lastPrivateMessageUser;
	}

	public void setLastPrivateMessageUser(String lastPrivateMessageUser) {
		this.lastPrivateMessageUser = lastPrivateMessageUser;
	}

	public void setIgnoreWrapper(IgnoreWrapper ignoreWrapper) {
		this.ignoreWrapper = ignoreWrapper;
	}
	
}
