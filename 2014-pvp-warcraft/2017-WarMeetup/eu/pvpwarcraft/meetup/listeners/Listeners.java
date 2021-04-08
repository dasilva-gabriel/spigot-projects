package eu.pvpwarcraft.meetup.listeners;

import org.bukkit.Bukkit;

import eu.pvpwarcraft.meetup.Meetup;
import eu.pvpwarcraft.meetup.listeners.players.BlockBreak;
import eu.pvpwarcraft.meetup.listeners.players.BlockPlace;
import eu.pvpwarcraft.meetup.listeners.players.EntityDamage;
import eu.pvpwarcraft.meetup.listeners.players.EntityDamageByEntity;
import eu.pvpwarcraft.meetup.listeners.players.FoodLoss;
import eu.pvpwarcraft.meetup.listeners.players.PlayerChat;
import eu.pvpwarcraft.meetup.listeners.players.PlayerDeath;
import eu.pvpwarcraft.meetup.listeners.players.PlayerDrop;
import eu.pvpwarcraft.meetup.listeners.players.PlayerInteract;
import eu.pvpwarcraft.meetup.listeners.players.PlayerJoin;
import eu.pvpwarcraft.meetup.listeners.players.PlayerLogin;
import eu.pvpwarcraft.meetup.listeners.players.PlayerQuit;
import eu.pvpwarcraft.meetup.listeners.servers.ServerPing;

public class Listeners {
	
	public static void init(){
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoin(), Meetup.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerQuit(), Meetup.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerLogin(), Meetup.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new EntityDamage(), Meetup.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new BlockPlace(), Meetup.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new BlockBreak(), Meetup.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerInteract(), Meetup.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerDeath(), Meetup.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerDrop(), Meetup.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerChat(), Meetup.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new EntityDamageByEntity(), Meetup.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new ServerPing(), Meetup.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new FoodLoss(), Meetup.getInstance());
	}

}
