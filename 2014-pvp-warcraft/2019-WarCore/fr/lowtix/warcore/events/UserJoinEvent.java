package fr.lowtix.warcore.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.lowtix.warcore.WarPlayer;

public class UserJoinEvent extends Event {

	public static final HandlerList handlers = new HandlerList();
	
	private WarPlayer user;
	
	public UserJoinEvent(WarPlayer user) {
		this.user = user;
	}
	
	public WarPlayer getUser(){
		return user;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList(){
		return handlers;
	}

}
