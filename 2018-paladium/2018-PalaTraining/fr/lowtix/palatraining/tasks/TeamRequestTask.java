package fr.lowtix.palatraining.tasks;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.lowtix.palatraining.PalaTraining;
import fr.lowtix.palatraining.handlers.GamePlayer;
import fr.lowtix.palatraining.handlers.TeamRequest;

public class TeamRequestTask extends BukkitRunnable {

	public ArrayList<UUID> uuids = new ArrayList<UUID>();
	
	@Override
	public void run() {
		
		for(TeamRequest req : PalaTraining.getInstance().getTeamsManager().getRequests().values()) {
			
			boolean receiverOnline = true;
			boolean senderOnline = true;
			
			if(Bukkit.getPlayer(req.getReceiver()) == null || Bukkit.getPlayer(req.getReceiver()).isOnline()) {
				receiverOnline = false;
			}
			
			if(Bukkit.getPlayer(req.getSender()) == null || Bukkit.getPlayer(req.getSender()).isOnline()) {
				senderOnline = false;
			}
			
			if(!receiverOnline) {
				if(senderOnline) {
					Player sender = Bukkit.getPlayer(req.getSender());
					Player receiver = Bukkit.getPlayer(req.getReceiver());
					sender.sendMessage("§dTeam §f» §7Le joueur §6"+receiver.getName()+" §7s'est déconnecté, la requête a été annulée.");
				}
			}
			
			if(receiverOnline) {
				Player receiver = Bukkit.getPlayer(req.getReceiver());
				GamePlayer gReceiver = PalaTraining.getInstance().getGamePlayer(receiver);
				
				if(gReceiver.isInTeam()) {
					receiver.sendMessage("§dTeam §f» §7Requête annulée, vous avez rejoint une autre team.");
					uuids.add(req.getReceiver());
				}
				
				if(senderOnline) {
					Player sender = Bukkit.getPlayer(req.getSender());
					GamePlayer gPlayer = PalaTraining.getInstance().getGamePlayer(sender);
					
					if(!gPlayer.isInTeam()) {
						receiver.sendMessage("§dTeam §f» §7Requête annulée, la team a été dissoute.");
						uuids.add(req.getReceiver());
					}
					
				} else {
					receiver.sendMessage("§dTeam §f» §7Requête annulée, la team a été dissoute a cause de la déconnexion du chef.");
				}
			}
			
			if(!senderOnline || !receiverOnline) {
				uuids.add(req.getReceiver());
			}
			
			long time = System.currentTimeMillis() - req.getAt();
			time /= 1000;
			
			if(time >= 15) {
				
				uuids.add(req.getReceiver());
				
				if(senderOnline && receiverOnline) {
					Player sender = Bukkit.getPlayer(req.getSender());
					Player receiver = Bukkit.getPlayer(req.getReceiver());
					
					receiver.sendMessage("§dTeam §f» §cLa requête a expirée !");
					sender.sendMessage("§dTeam §f» §cLa requête a expirée !");
					
				} 
				
			}
		}
		
		for(UUID uuid : uuids) {
			if(PalaTraining.getInstance().getTeamsManager().getRequests().containsKey(uuid)) {
				PalaTraining.getInstance().getTeamsManager().getRequests().remove(uuid);
			}
		}
		
		uuids.clear();
		
	}

}
