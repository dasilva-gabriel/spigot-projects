package eu.pvpwarcraft.warfightapi.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import eu.pvpwarcraft.warfightapi.managers.User;

public class UserQuitEvent extends Event {

	public static final HandlerList handlers = new HandlerList();

	private User user;

	public UserQuitEvent(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public HandlerList getHandlersList() {
		return handlers;
	}

}
