package eu.pvpwarcraft.thetowers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import eu.pvpwarcraft.thetowers.commands.StartCommand;
import eu.pvpwarcraft.thetowers.events.Events;
import eu.pvpwarcraft.thetowers.handler.HubHandler;
import eu.pvpwarcraft.thetowers.handler.Step;
import eu.pvpwarcraft.thetowers.handler.Team;
import eu.pvpwarcraft.thetowers.handler.TowerPlayer;
import eu.pvpwarcraft.thetowers.schedulers.StartScheduler;
import eu.pvpwarcraft.thetowers.utils.WorldManager;
import eu.pvpwarcraft.warcraftapi.WarCraftAPI;
import eu.pvpwarcraft.warcraftapi.configuration.Servers;

public class TheTowers extends JavaPlugin {

	private static Map<String, TowerPlayer> players = new HashMap<String, TowerPlayer>();
	private static Map<Team.Teams, Team> teams = new HashMap<Team.Teams, Team>();

	private static TheTowers instance;
	
	public String hub_server = "hub";

	public static TheTowers getInstance() {
		return instance;
	}

	@Override
	public void onDisable() {
		WorldManager.deleteWorld("world");
		
		for(Player all : Bukkit.getOnlinePlayers()){
			all.sendMessage("§6TheTowers §8» §cUne erreur est survenue, le serveur va crash. Nous vous renvoyons au plus vite au Hub...");
			HubHandler.sendToHub(all);
		}
		
		Step.setCurrentStep(Step.POST_GAME);
		TheTowers.refreshServ();
	}	
	
	@Override
	public void onLoad() {
		File from = new File("worldCOPY");
		File to = new File("world");
		try {
			WorldManager.copyFolder(from, to);
		} catch (IOException e) {
			System.err.println("Erreur lors de la copie de la map!");
		}
	}

	@Override
	public void onEnable() {
		instance = this;

		try {
			
			Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
			
			Bukkit.getWorld("world").setAutoSave(false);
			
			getCommand("forcestart").setExecutor(new StartCommand());

			for (Team.Teams team : Team.Teams.values()) {
				teams.put(team, new Team(team.getName(), team.getPoints(), team.getSpawn(), team.getColor(),
						team.getLeatherColor()));
			}

			Events.init();

			StartScheduler start = new StartScheduler(this);
			start.runTaskTimer(this, 2, 2);

		} catch (Exception e) {
			e.printStackTrace();
			getLogger().warning("Error while enabling the plugin.");
		}

		Step.setCurrentStep(Step.LOBBY);
		Bukkit.broadcastMessage("§6TheTowers §8» §7Le plugin à été chargé...");
	}

	// TOWERPLAYERS //

	public List<Player> getPlayers() {
		ArrayList<Player> rplayers = new ArrayList<Player>();
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (players.containsKey(all.getName())) {
				rplayers.add(all);
			}
		}
		return rplayers;
	}

	public static TowerPlayer getPlayer(Player player) {
		if (players.containsKey(player.getName())) {
			return (TowerPlayer) players.get(player.getName());
		}
		return new TowerPlayer(player.getName(), 0, 0, 0, 0, Team.Teams.AUCUNE, null, 0);
	}

	public static void addPlayer(TowerPlayer player) {
		players.put(player.getPlayer().getName(), player);
	}
	
	public static void removePlayer(TowerPlayer player) {
		players.remove(player.getPlayer().getName());
	}

	// TEAMS //

	public static Team getTeam(Team.Teams team) {
		if (teams.containsKey(team)) {
			return teams.get(team);
		}
		return new Team(team.getName(), 0, team.getSpawn(), team.getColor(), team.getLeatherColor());
	}

	// MAP //
	public void completeDelete(File args) {
		for (File file : args.listFiles())
			if (file.isDirectory())
				completeDelete(file);
			else
				file.delete();
	}

	public static void copyFolder(File src, File dest) {
		if (src.isDirectory()) {
			if (!dest.exists()) {
				dest.mkdirs();
			}
			for (String file : src.list()) {
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				copyFolder(srcFile, destFile);
			}
		} else {
			try {
				InputStream in = new FileInputStream(src);
				Object out = new FileOutputStream(dest);
				byte[] buffer = new byte[1024];
				int length;
				while ((length = in.read(buffer)) > 0) {
					((OutputStream) out).write(buffer, 0, length);
				}
				in.close();
				((OutputStream) out).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void refreshServ(){
		Servers serv = WarCraftAPI.getInstance().getServer(Bukkit.getServer().getPort());
		
		serv.setDesc(Step.getCurrentStep().getMOTD());
		serv.setPlayers(Bukkit.getOnlinePlayers().size());
		serv.save();
		
	}

}
