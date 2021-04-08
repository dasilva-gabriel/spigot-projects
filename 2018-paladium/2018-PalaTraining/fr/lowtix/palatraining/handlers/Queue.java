package fr.lowtix.palatraining.handlers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.lowtix.palatraining.PalaTraining;
import fr.lowtix.palatraining.enums.UnrankedQueueType;

public class Queue {

	private boolean ranked;
	private LinkedList<UUID> players;
	private UnrankedQueueType type;
	
	public Queue(boolean ranked, UnrankedQueueType type) {
		this.ranked = ranked;
		players = new LinkedList<UUID>();
		this.type = type;
	}

	public boolean isRanked() {
		return ranked;
	}

	public void setRanked(boolean ranked) {
		this.ranked = ranked;
	}

	public LinkedList<UUID> getPlayers() {
		return players;
	}

	public void setPlayers(LinkedList<UUID> players) {
		this.players = players;
	}
	
	public UnrankedQueueType getType() {
		return type;
	}

	public void setType(UnrankedQueueType type) {
		this.type = type;
	}

	public boolean isPlayerOnline(UUID uuid) {
		return (Bukkit.getPlayer(uuid) != null && Bukkit.getPlayer(uuid).isOnline());
	}
	
	public Player getPlayer(UUID uuid) {
		return (Bukkit.getPlayer(uuid));
	}
	
	public void check() {
		if(players.size() >= 1) {
			
			ArrayList<UUID> toRemove = new ArrayList<UUID>();
			
			for(UUID uuid : players) {
				if(!isPlayerOnline(uuid)) toRemove.add(uuid);
			}
			
			for(UUID uuid : toRemove) {
				players.remove(uuid);
			}
			
		}
		
		if(players.size() >= 2) {
			Player first = getPlayer(players.getFirst());
			Player second = getPlayer(players.get(1));
			
			if(PalaTraining.getInstance().getGameArenasManager().getReadyArenas(false).size() == 0) {
				first.sendMessage("§3Recherche §f» §7Nous recherchons une arène pour votre duel...");
				second.sendMessage("§3Recherche §f» §7Nous recherchons une arène pour votre duel...");
			} else {
				boolean b = PalaTraining.getInstance().getGameManager().createOneVsOne(first, second, isRanked(), getType());
				
				if(!b) {
					first.sendMessage("§4Erreur §f» §cProblème dans la préparation au combat...");
					second.sendMessage("§4Erreur §f» §cProblème dans la préparation au combat...");
					
					PalaTraining.getInstance().getQueueManager().removeFromQueue(first, true);
					PalaTraining.getInstance().getQueueManager().removeFromQueue(second, true);
					
				} else {
					
					PalaTraining.getInstance().getQueueManager().removeFromQueue(first, false);
					PalaTraining.getInstance().getQueueManager().removeFromQueue(second, false);
					
				}
			}
			
		}
	}
	
}
