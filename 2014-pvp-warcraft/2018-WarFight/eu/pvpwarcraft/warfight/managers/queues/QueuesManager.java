package eu.pvpwarcraft.warfight.managers.queues;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import eu.pvpwarcraft.warfight.PlayerWrapper;
import eu.pvpwarcraft.warfight.WarFight;
import eu.pvpwarcraft.warfight.managers.lobby.LobbyManager;
import eu.pvpwarcraft.warfight.PlayerWrapper.PlayerStates;
import eu.pvpwarcraft.warfight.games.GamesManager;
import eu.pvpwarcraft.warfight.utils.ItemBuilder;

public class QueuesManager {
	
	public static HashMap<Queues, Queue> s_queues = new HashMap<>();
	
	public static void init(){
		for(Queues queues : Queues.values()){
			s_queues.put(queues, new Queue(queues.name(), queues.getKit(), queues.isRanked()));
		}
	}
	
	public static void removeFromQueue(Player player){
		PlayerWrapper pl = WarFight.getPlayer(player);
		
		if(PlayerStates.isState(pl, PlayerStates.IN_QUEUE)){
			Queues queue = pl.getQueue();
			Queue qu = s_queues.get(queue);
			
			if(qu.getPlayer_in_queue().equalsIgnoreCase(pl.getName())){
				qu.setPlayer_in_queue("none");
			}
			
			player.sendMessage("§dFiles §8» §7Vous avez été retiré de la file §b"+queue.getName());
			
			LobbyManager.goToLobby(player, false, false);
			
		}
	}
	
	public static void addQueue(Player player, Queues queue){
		PlayerWrapper pl = WarFight.getPlayer(player);
		
		Queue qu = s_queues.get(queue);
		pl.setQueue(queue);
		pl.setState(PlayerStates.IN_QUEUE);
		
		player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0F, 6.0F);
		player.sendMessage("§dFiles §8» §7Vous avez été ajouté à la file §b"+queue.getName());
		
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		player.getInventory().setItem(4, new ItemBuilder(Material.INK_SACK).setDamage(1).setName("§4§lQuitter la file §8• §7Clique droit").build());
		
		if(qu.getPlayer_in_queue().equalsIgnoreCase("none")){
			qu.setPlayer_in_queue(player.getName());
		}else{
			Bukkit.broadcastMessage("MATCH > "+player.getName()+" vs "+qu.getPlayer_in_queue());
			
			Player second = Bukkit.getPlayer(qu.getPlayer_in_queue());
			
			GamesManager.start1v1Game(player, second, queue.getKit(), queue.isRanked());
		}
	}
	
	public static void removeQueue(Player player){
		PlayerWrapper pl = WarFight.getPlayer(player);
		Queue qu = s_queues.get(pl.getQueue());
		pl.setState(PlayerStates.LOBBY);
		
		qu.setPlayer_in_queue("none");
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		
		LobbyManager.giveLobbyItems(player);
		
	}

}
