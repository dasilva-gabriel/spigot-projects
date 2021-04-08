package eu.pvpwarcraft.skypvp.commands;

import eu.pvpwarcraft.skypvp.SkyPvP;

public class Commands {
	
	public static void init(){
		SkyPvP.getInstance().getCommand("top").setExecutor(new TopCommand());
		SkyPvP.getInstance().getCommand("coins").setExecutor(new CoinsCommand());
	}

}
