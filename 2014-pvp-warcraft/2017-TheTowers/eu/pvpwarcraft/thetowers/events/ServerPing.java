package eu.pvpwarcraft.thetowers.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import eu.pvpwarcraft.thetowers.handler.Step;

public class ServerPing implements Listener {
	
	@EventHandler
	public void onPing(ServerListPingEvent event){
		event.setMotd(Step.getCurrentStep().getMOTD());
	}

}
