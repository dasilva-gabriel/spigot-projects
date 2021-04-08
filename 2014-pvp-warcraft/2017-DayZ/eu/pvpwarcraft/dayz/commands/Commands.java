package eu.pvpwarcraft.dayz.commands;

import eu.pvpwarcraft.dayz.DayZ;

public class Commands {
	
	public static void init(){
		
		DayZ.getInstance().getLogger().info("Commands: Registering commands...");
		
		DayZ.getInstance().getCommand("administration").setExecutor(new AdministrationCommand());
		
	}

}
