package eu.pvpwarcraft.skypvp.listeners;

import org.bukkit.Bukkit;

import eu.pvpwarcraft.skypvp.SkyPvP;
import eu.pvpwarcraft.skypvp.listeners.kill.KillListener;
import eu.pvpwarcraft.skypvp.listeners.players.ClickListener;
import eu.pvpwarcraft.skypvp.listeners.players.JoinListener;
import eu.pvpwarcraft.skypvp.listeners.players.MoveListener;
import eu.pvpwarcraft.skypvp.listeners.players.QuitListener;
import eu.pvpwarcraft.skypvp.listeners.sort.Sorts;
import eu.pvpwarcraft.skypvp.listeners.world.World;
import eu.pvpwarcraft.skypvp.utils.skybox.SkyBoxGive;

public class Listeners {
	
	public static void init(){
		Bukkit.getServer().getPluginManager().registerEvents(new JoinListener(), SkyPvP.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new QuitListener(), SkyPvP.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new KillListener(), SkyPvP.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new ClickListener(), SkyPvP.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new World(), SkyPvP.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new SkyBoxGive(), SkyPvP.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new MoveListener(), SkyPvP.getInstance());
		Sorts.init();
	}

}
