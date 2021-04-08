package eu.pvpwarcraft.tournoipvp;

import org.bukkit.entity.Player;

public class RegistersManager {
	
	public static void saveTeam(String name, Player first_player, Player second_player, int state){
		
		ConfigManager.getRegistersConfig().set(name + ".First", first_player.getName());
		ConfigManager.getRegistersConfig().set(name + ".Second", second_player.getName());
		ConfigManager.getRegistersConfig().set(name + ".State", state);
		
	}
	
	public static boolean containTeam(String name){
		return ConfigManager.getRegistersConfig().contains(name);
	}
	
	public static boolean containPlayer(Player player){
		boolean result = false;
		for(String keys : ConfigManager.getRegistersConfig().getKeys(false)){
			if(player.getName().equalsIgnoreCase(getSavedFirstPlayerName(keys))){
				result = true;
			}
			if(player.getName().equalsIgnoreCase(getSavedSecondPlayerName(keys))){
				result = true;
			}
		}
		return result;
	}
	
	public static boolean containName(String name){
		boolean result = false;
		for(String keys : ConfigManager.getRegistersConfig().getKeys(false)){
			if(keys.equalsIgnoreCase(name)){
				result = true;
			}
		}
		return result;
		
	}
	
	public static String getSavedName(Player player){
		String result = "Aucune";
		for(String keys : ConfigManager.getRegistersConfig().getKeys(false)){
			if(player.getName().equalsIgnoreCase(getSavedFirstPlayerName(keys))){
				result = keys;
			}
			if(player.getName().equalsIgnoreCase(getSavedSecondPlayerName(keys))){
				result = keys;
			}
		}
		return result;
	}
	
	public static String getSavedFirstPlayerName(String name){
		return ConfigManager.getRegistersConfig().getString(name + ".First");
	}
	
	public static String getSavedSecondPlayerName(String name){
		return ConfigManager.getRegistersConfig().getString(name + ".Second");
	}
	
	public static int getSavedState(String name){
		return ConfigManager.getRegistersConfig().getInt(name + ".State");
	}

}
