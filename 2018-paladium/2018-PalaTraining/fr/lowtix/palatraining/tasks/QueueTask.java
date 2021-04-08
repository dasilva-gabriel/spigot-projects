package fr.lowtix.palatraining.tasks;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.lowtix.palatraining.PalaTraining;
import fr.lowtix.palatraining.handlers.Queue;

public class QueueTask extends BukkitRunnable {

	@Override
	public void run() {
		
		for(Queue queue : PalaTraining.getInstance().getQueueManager().getQueues()) {
			
			queue.check();
			
			for(UUID uuid : queue.getPlayers()) {
				
				if(Bukkit.getPlayer(uuid) != null && Bukkit.getPlayer(uuid).isOnline()) {
					
					Player player = Bukkit.getPlayer(uuid);
					
					if(PalaTraining.getInstance().getQueueManager().getQueue(player) == null && !PalaTraining.getInstance().getQueueManager().getQueue(player).equals(queue)) {
						PalaTraining.getInstance().getQueueManager().getPlayersInQueue().put(uuid, queue);
					}
					
				}
			}
			
		}
		
	}

}
