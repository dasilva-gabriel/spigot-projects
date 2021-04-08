package eu.pvpwarcraft.meetup.commands;

import eu.pvpwarcraft.meetup.Meetup;

public class Commands {
	
	public static void init(){
		Meetup.getInstance().getCommand("game").setExecutor(new GameCommand());
		Meetup.getInstance().getCommand("retry").setExecutor(new RetryCommand());
	}
}
