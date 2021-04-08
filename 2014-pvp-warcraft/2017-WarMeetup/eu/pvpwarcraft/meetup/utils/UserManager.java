package eu.pvpwarcraft.meetup.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class UserManager {
	
	public static String getGroup(Player player){
		String group;
		group = (String)PermissionsEx.getUser(player.getName()).getParentIdentifiers(player.getWorld().getName()).get(0);
		return group;
	}
	
	public static boolean isAdmin(Player player){
		if(getGroup(player).equalsIgnoreCase("Admin")){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isFriends(Player player){
		if(getGroup(player).equalsIgnoreCase("Ami")){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isFonda(Player player){
		if(getGroup(player).equalsIgnoreCase("Fondateur")){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isMod(Player player){
		if(getGroup(player).equalsIgnoreCase("Mod")){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isStaff(Player player){
		if(getGroup(player).equalsIgnoreCase("Staff")){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isPrem(Player player){
		if(getGroup(player).equalsIgnoreCase("Premium")){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isDefault(Player player){
		if(getGroup(player).equalsIgnoreCase("default")){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isPartner(Player player){
		if(getGroup(player).equalsIgnoreCase("Famous")){
			return true;
		}else{
			return false;
		}
	}
	
	public static void setRank(Player target, int ID){
		for(World worlds : Bukkit.getWorlds()){
			if(ID == 0){
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user "+target.getName()+" group set default "+worlds.getName());
			}
			if(ID == 10){
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user "+target.getName()+" group set default "+worlds.getName());
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user "+target.getName()+" group add premium "+worlds.getName()+" 1d");
			}
			if(ID == 20){
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user "+target.getName()+" group set default "+worlds.getName());
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user "+target.getName()+" group add premium "+worlds.getName()+" 7d");
			}
			if(ID == 30){
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user "+target.getName()+" group set default "+worlds.getName());
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user "+target.getName()+" group add premium "+worlds.getName()+" 30d");
			}
			if(ID == 40){
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user "+target.getName()+" group set default "+worlds.getName());
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user "+target.getName()+" group set premium "+worlds.getName());
			}
			if(ID == 50){
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user "+target.getName()+" group set default "+worlds.getName());
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user "+target.getName()+" group set famous "+worlds.getName());
			}
			if(ID == 60){
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user "+target.getName()+" group set default "+worlds.getName());
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user "+target.getName()+" group set ami "+worlds.getName());
			}
			if(ID == 70){
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user "+target.getName()+" group set default "+worlds.getName());
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user "+target.getName()+" group set staff "+worlds.getName());
			}
			if(ID == 80){
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user "+target.getName()+" group set default "+worlds.getName());
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user "+target.getName()+" group set mod "+worlds.getName());
			}
			if(ID == 90){
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user "+target.getName()+" group set default "+worlds.getName());
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user "+target.getName()+" group set Admin "+worlds.getName());
			}
			if(ID == 100){
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user "+target.getName()+" group set default "+worlds.getName());
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user "+target.getName()+" group set Fondateur "+worlds.getName());
			}
		}
	}

}
