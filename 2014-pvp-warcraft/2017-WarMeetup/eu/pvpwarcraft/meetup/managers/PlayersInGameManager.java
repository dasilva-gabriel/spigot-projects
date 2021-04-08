package eu.pvpwarcraft.meetup.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import eu.pvpwarcraft.meetup.Meetup;

public class PlayersInGameManager {
	
	public static void addPlayer(Player player){
		if(Meetup.getInstance().players.contains(player.getName())){
			Meetup.getInstance().players.remove(player.getName());
		}
		Meetup.getInstance().players.add(player.getName());
	}
	
	public static void removePlayer(Player player){
		if(Meetup.getInstance().players.contains(player.getName())){
			Meetup.getInstance().players.remove(player.getName());
		}
	}
	
	public static boolean isInGame(Player player){
		if(Meetup.getInstance().players.contains(player.getName())){
			return true;
		}
		return false;
	}
	
	public static int getPlayersSize(){
		int i = Meetup.getInstance().players.size();
		return i;
	}
	
	public static Player winPlayer(){
		if(getPlayersSize() == 1){
			Player player = Bukkit.getPlayer(Meetup.getInstance().players.get(0));
			return player;
		}else{
			return null;
		}
	}

}
