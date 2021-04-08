package fr.lowtix.warbox.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class UserFirstJoinEvent extends Event {

	public static final HandlerList handlers = new HandlerList();
	
	private Player player;
	
	public UserFirstJoinEvent(Player player) {
		this.player = player;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList(){
		return handlers;
	}

}
