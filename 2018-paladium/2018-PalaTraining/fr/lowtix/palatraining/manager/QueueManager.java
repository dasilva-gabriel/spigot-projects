package fr.lowtix.palatraining.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import fr.lowtix.palatraining.PalaTraining;
import fr.lowtix.palatraining.enums.UnrankedQueueType;
import fr.lowtix.palatraining.handlers.GamePlayer;
import fr.lowtix.palatraining.handlers.Queue;

public class QueueManager {
	
	private ArrayList<Queue> queues = new ArrayList<Queue>();
	private HashMap<UUID, Queue> playersInQueue = new HashMap<UUID, Queue>();
	
	public QueueManager() {
		
		queues.add(new Queue(false, UnrankedQueueType.MONEY));
		queues.add(new Queue(false, UnrankedQueueType.STUFF));
		
		queues.add(new Queue(true, UnrankedQueueType.NONE));
		
	}
	
	public ArrayList<Queue> getQueues() {
		return queues;
	}
	
	public HashMap<UUID, Queue> getPlayersInQueue() {
		return playersInQueue;
	}
	
	public void removeFromQueue(Player player, boolean sendMessage) {
		
		removeFromQueue(PalaTraining.getInstance().getGamePlayer(player), sendMessage);
		
	}
	
	public void removeFromQueue(GamePlayer gplayer, boolean sendMessage) {
		
		Player p = gplayer.getPlayer();
		
		if(getQueue(p) != null) {
			Queue queue = playersInQueue.get(p.getUniqueId());
			
			if(queue.getPlayers().contains(p.getUniqueId())) {
				queue.getPlayers().remove(p.getUniqueId());
			}
			
			playersInQueue.remove(p.getUniqueId());
			
		}
		
		if(sendMessage) {
			p.sendMessage("§3Recherche §f» §7Vous quittez votre recherche d'adversaire!");
		}
		
	}
	
	public Queue getQueue(boolean ranked, UnrankedQueueType type) {
		
		for(Queue queue : queues) {
			if(queue.isRanked() && ranked) {
				return queue;
			} else if(!queue.isRanked() && !ranked && queue.getType().equals(type)) {
				return queue;
			}
		}
		
		return null;
		
	}
	
	public Queue getQueue(Player player) {
		if(playersInQueue.containsKey(player.getUniqueId())) {
			return playersInQueue.get(player.getUniqueId());
		}
		
		return null;
	}
	
	public void addInQueue(GamePlayer gplayer, Queue queue) {
		
		Player p = gplayer.getPlayer();
		
		if(gplayer.isInGame()) {
			p.sendMessage("§3Recherche §f» §7Votre ne recherche d'adversaire ne peut aboutir car vous êtes en jeu.");
			return;
		}
		
		if(gplayer.isInTeam()) {
			p.sendMessage("§3Recherche §f» §7Votre ne recherche d'adversaire ne peut aboutir car vous êtes dans une team.");
			return;
		}
		
		if(getQueue(p) != null) {
			p.sendMessage("§3Recherche §f» §cVous êtes déjà en recherche d'un adversaire...");
			return;
		}
		
		p.sendMessage("§3Recherche §f» §aVous avez été ajouté dans la file !");
		queue.getPlayers().add(p.getUniqueId());
		playersInQueue.put(p.getUniqueId(), queue);
		
	}

}
