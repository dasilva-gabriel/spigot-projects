package eu.pvpwarcraft.meetup;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import eu.pvpwarcraft.meetup.commands.Commands;
import eu.pvpwarcraft.meetup.handler.Step;
import eu.pvpwarcraft.meetup.handler.servers.ServersManager;
import eu.pvpwarcraft.meetup.listeners.Listeners;
import eu.pvpwarcraft.meetup.managers.board.ScoreboardScheduler;
import eu.pvpwarcraft.meetup.managers.border.BorderManager;
import eu.pvpwarcraft.meetup.managers.types.TypesManager;
import eu.pvpwarcraft.meetup.utils.FilesManager;

public class Meetup extends JavaPlugin {

	private static Meetup instance;
	private BukkitTask scoreboard;
	private static Location spawnLocs;

	public static Meetup getInstance() {
		return instance;
	}

	public List<String> players = new ArrayList<String>();

	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {

		// OCEAN

		instance = this;
		Step.setCurrentStep(Step.PRE_LOBBY);
		checkBiome();
		// ------------------------------------------------------------------ //

		World world = (World) Bukkit.getWorlds().get(0);
		Location spawnLoc = new Location(world, 0.0D, 0.0D, 0.0D);
		world.getChunkAt(spawnLoc).load(true);
		spawnLoc.setY(world.getHighestBlockYAt(0, 0) + 36);
		spawnLocs = spawnLoc;
		for (int x = -10; x <= 10; x++) {
			for (int y = -1; y <= 7; y++) {
				for (int z = -10; z <= 10; z++) {
					Block block = spawnLoc.clone().add(x, y, z).getBlock();
					block.setType(Material.AIR);
					if (((y >= 0) && ((z == 10)) || (z == -10) || (x == 10) || (x == -10))) {
						block.setType(Material.STAINED_GLASS_PANE);
						Random rand = new Random();
						byte rNumb = (byte) rand.nextInt(14);
						block.setData(rNumb);
						block.getRelative(BlockFace.UP).setType(Material.BARRIER);
					}
					if ((y == -1)) {
						block.setType(Material.STAINED_GLASS);
					}
					if ((y == 7)) {
						block.setType(Material.BARRIER);
					}
					if (y == -1 && z == 0 && x == 0) {
						block.setType(Material.SEA_LANTERN);
					}
				}
			}
		}
		world.getChunkAt(spawnLoc).load(true);
		world.setSpawnLocation(0, spawnLoc.getBlockY() + 5, 0);
		world.setGameRuleValue("doDaylightCycle", "false");
		world.setTime(6000L);

		// ------------------------------------------------------------------ //

		BorderManager.setBorder(30);
		Listeners.init();
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		this.scoreboard = new ScoreboardScheduler().runTaskTimer(this, 10L, 10L);
		TypesManager.resetVotes();
		Commands.init();
		new BukkitRunnable() {

			@Override
			public void run() {
				world.getChunkAt(spawnLoc).load(true);
				Step.setCurrentStep(Step.LOBBY);

			}
		}.runTaskLater(this, 20);
	}

	public static void clearSpawnBox() {
		for (int x = -10; x <= 10; x++) {
			for (int y = -1; y <= 7; y++) {
				for (int z = -10; z <= 10; z++) {
					Block block = spawnLocs.clone().add(x, y, z).getBlock();
					block.setType(Material.AIR);
					if (((y >= 0) && ((z == 10)) || (z == -10) || (x == 10) || (x == -10))) {
						block.setType(Material.AIR);
						block.getRelative(BlockFace.UP).setType(Material.AIR);
					}
					if ((y == -1)) {
						block.setType(Material.AIR);
					}
					if ((y == 7)) {
						block.setType(Material.AIR);
					}
					if (y == -1 && z == 0 && x == 0) {
						block.setType(Material.AIR);
					}
				}
			}
		}
	}

	public static void checkBiome() {
		World world = (World) Bukkit.getWorlds().get(0);
		Location spawnLoc = new Location(world, 0.0D, 0.0D, 0.0D);
		Biome biome = spawnLoc.getWorld().getBiome(spawnLoc.getBlockX(), spawnLoc.getBlockZ());
		for (int i = 0; i < 5; i++) {
			System.out.println("§8[§cDebug§8] §7Le biome détecté est §4" + biome);
		}
		if (biome.equals(Biome.OCEAN) || biome.equals(Biome.DEEP_OCEAN) || biome.equals(Biome.FROZEN_OCEAN)
				|| biome.equals(Biome.EXTREME_HILLS) || biome.equals(Biome.EXTREME_HILLS_PLUS)
				|| biome.equals(Biome.EXTREME_HILLS_MOUNTAINS) || biome.equals(Biome.EXTREME_HILLS_PLUS_MOUNTAINS)
				|| biome.equals(Biome.FOREST_HILLS) || biome.equals(Biome.RIVER) || biome.equals(Biome.JUNGLE_HILLS)
				|| biome.equals(Biome.BEACH)) {
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "restart");
		}
	}

	public void onLoad() {
		try {
			File worldContainer = getServer().getWorldContainer();
			FilesManager.delete(new File(worldContainer, "world"));
			FilesManager.delete(new File(worldContainer, "world_nether"));
			FilesManager.delete(new File(worldContainer, "world_the_end"));
		} catch (Throwable $ex) {
			throw $ex;
		}
		
	}

	@Override
	public void onDisable() {
		if (this.scoreboard != null) {
			this.scoreboard.cancel();
		}
		for (Player all : Bukkit.getOnlinePlayers()) {
			all.sendMessage("§6WarFight §8» §cUne erreur est survenue. Retour au serveur principal...");
			ServersManager.sendToWarFightLobby(all);
		}
	}

}
