package eu.pvpwarcraft.meetup.listeners.servers;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import eu.pvpwarcraft.meetup.handler.Step;

public class ServerPing implements Listener {
	
	@EventHandler
	public void onPing(ServerListPingEvent event){
		event.setMotd("NO_JOIN");
		if(Step.isStep(Step.LOBBY)){
			event.setMotd("LOBBY_"+Bukkit.getOnlinePlayers().size());
		}
		if(Step.isStep(Step.PRE_GAME)){
			event.setMotd("PREFACE");
		}
		if(Step.isStep(Step.IN_GAME)){
			event.setMotd("GAME");
		}
	}

}
