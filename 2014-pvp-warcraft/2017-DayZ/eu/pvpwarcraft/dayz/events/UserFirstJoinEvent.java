package eu.pvpwarcraft.dayz.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import eu.pvpwarcraft.dayz.users.DayzPlayer;

public class UserFirstJoinEvent extends Event {

	public static final HandlerList handlers = new HandlerList();

	private DayzPlayer user;

	public UserFirstJoinEvent(DayzPlayer user) {
		this.user = user;
	}

	public DayzPlayer getUser() {
		return user;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
