package fr.lowtix.warlobby.tasks;

import org.bukkit.scheduler.BukkitRunnable;

import fr.lowtix.warlobby.WarLobby;
import fr.lowtix.warlobby.managers.ServersManagers;

public class ServersTask extends BukkitRunnable {

	@Override
	public void run() {
		
		ServersManagers sv = WarLobby.getInstance().getServersManager();
				
		sv.factionsCount = sv.onlinePlayers(sv.factionsPort);
		sv.pvpboxCount = sv.onlinePlayers(sv.pvpboxPort);
		
	}

}
