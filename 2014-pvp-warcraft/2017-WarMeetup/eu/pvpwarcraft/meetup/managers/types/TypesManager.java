package eu.pvpwarcraft.meetup.managers.types;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class TypesManager {
	
	private static Map<Types, Integer> typesVote = new HashMap<Types, Integer>();
	private static Map<String, Types> playersVoted = new HashMap<String, Types>();
	private static Types winner;
	
	public static void resetVotes(){
		Types.init();
		typesVote.clear();
		typesVote.put(Types.AUCUNS, 0);
		typesVote.put(Types.ANVIL_LESS, 0);
		typesVote.put(Types.BOW_LESS, 0);
		typesVote.put(Types.FLAME_LESS, 0);
		typesVote.put(Types.ROD_LESS, 0);
		typesVote.put(Types.APPLE_LESS, 0);
		winner = Types.AUCUNS;
		playersVoted.clear();
	}
	
	public static Types getTypesWinner(){
		return winner;
	}
	
	public static int getVotes(Types type){
		return typesVote.get(type);
	}
	
	public static void addVotes(Player player, Types type){
		playersVoted.put(player.getName(), type);
		typesVote.put(type, (Integer.valueOf(getVotes(type))+1));
		if(getVotes(winner) <= getVotes(type)){
			winner = type;
		}
	}
	
	public static boolean hasVoted(Player player, Types type){
		if(playersVoted.containsKey(player.getName()) && (playersVoted.get(player.getName()) == type)){
			return true;
		}
		return false;
	}

}
