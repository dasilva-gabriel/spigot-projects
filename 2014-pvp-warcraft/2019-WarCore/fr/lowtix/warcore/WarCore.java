package fr.lowtix.warcore;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.earth2me.essentials.Essentials;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import fr.lowtix.warcore.commands.AFKCommand;
import fr.lowtix.warcore.commands.AccountCommand;
import fr.lowtix.warcore.commands.AdminResCommand;
import fr.lowtix.warcore.commands.ArmorCommand;
import fr.lowtix.warcore.commands.BoosterCommand;
import fr.lowtix.warcore.commands.BoutiqueCommand;
import fr.lowtix.warcore.commands.CreatifCommand;
import fr.lowtix.warcore.commands.ExecShopCommand;
import fr.lowtix.warcore.commands.LevelUpCommand;
import fr.lowtix.warcore.commands.MenuCommand;
import fr.lowtix.warcore.commands.MerciCommand;
import fr.lowtix.warcore.commands.NoAFKCommand;
import fr.lowtix.warcore.commands.SetRankCommand;
import fr.lowtix.warcore.commands.SiteCommand;
import fr.lowtix.warcore.commands.TimeCommand;
import fr.lowtix.warcore.commands.VoteCommand;
import fr.lowtix.warcore.commands.moderation.ModCommand;
import fr.lowtix.warcore.commands.moderation.PunishCommand;
import fr.lowtix.warcore.commands.moderation.QuestionCommand;
import fr.lowtix.warcore.commands.moderation.TrackCommand;
import fr.lowtix.warcore.enums.Levels;
import fr.lowtix.warcore.enums.Ranks;
import fr.lowtix.warcore.events.UserJoinEvent;
import fr.lowtix.warcore.listeners.BowTrailListeners;
import fr.lowtix.warcore.listeners.ChatListeners;
import fr.lowtix.warcore.listeners.InOutListeners;
import fr.lowtix.warcore.listeners.JobListeners;
import fr.lowtix.warcore.listeners.KillListeners;
import fr.lowtix.warcore.listeners.ResetListeners;
import fr.lowtix.warcore.managers.BowTrailsManager;
import fr.lowtix.warcore.managers.CraftManager;
import fr.lowtix.warcore.managers.StaffChatManager;
import fr.lowtix.warcore.managers.StaffModManager;
import fr.lowtix.warcore.managers.boosters.BoostersManager;
import fr.lowtix.warcore.sql.PlayersSQL;
import fr.lowtix.warcore.tasks.AFKTask;
import fr.lowtix.warcore.tasks.BoardTask;
import fr.lowtix.warcore.tasks.BowTask;
import fr.lowtix.warcore.tasks.ModTask;
import fr.lowtix.warcore.tasks.TimeTask;
import net.milkbowl.vault.economy.Economy;

public class WarCore extends JavaPlugin {

	private static WarCore instance;
	public HashMap<String, WarPlayer> players = new HashMap<String, WarPlayer>();
	public PlayersSQL sql = new PlayersSQL();
	private Economy eco = null;
	public BoostersManager booster = new BoostersManager();
	public BowTrailsManager bowManager = new BowTrailsManager();
	public CraftManager craftManager = new CraftManager();
	public StaffChatManager staffChatManager = new StaffChatManager();
	public StaffModManager staffModManager = new StaffModManager();
	public boolean error = false;
	public Essentials essentials;
	public WorldGuardPlugin worldguard;

	@Override
	public void onEnable() {

		instance = this;

		try {
			long start = System.currentTimeMillis();
			Bukkit.broadcastMessage("§bInfo §8» §7Début du chargement du plugin §eWarCore§7.");

			essentials = (Essentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");

			saveDefaultConfig();
			initListeners();
			initCmds();
			setupEconomy();
			initBorders();
			this.sql.createTable();

			this.worldguard = getWorldGuardPlugin();

			new BoardTask().runTaskTimer(this, 40, 40);
			new TimeTask().runTaskTimerAsynchronously(this, 20, 1200);
			new BowTask().runTaskTimerAsynchronously(this, 1L, 1L);
			new ModTask().runTaskTimer(this, 40L, 40L);
			new AFKTask().runTaskTimerAsynchronously(this, 1200L, 1200L);

			Bukkit.broadcastMessage("§bInfo §8» §7Fin du chargement du plugin §eWarCore§7.");
			long end = System.currentTimeMillis();
			double time = Math.floor((end - start) * 100) / 100;
			getLogger().info("Temps de chargement = " + time);

			Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
			Bukkit.getMessenger().registerOutgoingPluginChannel(this, "Punish");
			
			booster.setup();

			this.craftManager.setup();

			for (Ranks rank : Ranks.values()) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex group " + rank.getDisplayName() + " create");
			}
			for (Levels level : Levels.values()) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
						"pex group " + level.name().toLowerCase() + " create");
			}
			
			
		} catch (Exception e) {
			error = true;
			e.printStackTrace();
		}
	}

	@Override
	public void onDisable() {
		for (WarPlayer players : getUsers().values()) {
			players.save();

			if (players.getModPlayer().isMod()) {
				WarCore.getInstance().staffModManager.desactiveMod(players);
			}
		}
	}

	private WorldGuardPlugin getWorldGuardPlugin() {
		Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
		if ((plugin == null) || (!(plugin instanceof WorldGuardPlugin))) {
			return null;
		}
		return (WorldGuardPlugin) plugin;
	}

	private void initCmds() {
		getCommand("punish").setExecutor(new PunishCommand());
		getCommand("boutique").setExecutor(new BoutiqueCommand());
		getCommand("menu").setExecutor(new MenuCommand());
		getCommand("levelup").setExecutor(new LevelUpCommand());
		getCommand("setrank").setExecutor(new SetRankCommand());
		getCommand("adminrestore").setExecutor(new AdminResCommand());
		getCommand("afk").setExecutor(new AFKCommand());
		getCommand("booster").setExecutor(new BoosterCommand());
		getCommand("merci").setExecutor(new MerciCommand());
		getCommand("wtime").setExecutor(new TimeCommand());
		getCommand("mod").setExecutor(new ModCommand());
		getCommand("question").setExecutor(new QuestionCommand());
		getCommand("track").setExecutor(new TrackCommand());
		getCommand("execshop").setExecutor(new ExecShopCommand());
		getCommand("account").setExecutor(new AccountCommand());
		getCommand("site").setExecutor(new SiteCommand());
		getCommand("vote").setExecutor(new VoteCommand());
		getCommand("creatif").setExecutor(new CreatifCommand());
		getCommand("armor").setExecutor(new ArmorCommand());
		getCommand("noafk").setExecutor(new NoAFKCommand());
	}

	private void initBorders() {
		for (World worlds : Bukkit.getWorlds()) {
			WorldBorder bordure = worlds.getWorldBorder();
			bordure.setCenter(0, 0);
			bordure.setSize(20000);
		}
	}

	private void initListeners() {
		PluginManager pm = Bukkit.getPluginManager();

		pm.registerEvents(new InOutListeners(), this);
		pm.registerEvents(new ChatListeners(), this);
		pm.registerEvents(new ResetListeners(), this);
		pm.registerEvents(new ResetListeners(), this);
		pm.registerEvents(new JobListeners(), this);
		pm.registerEvents(new KillListeners(), this);
		pm.registerEvents(new CraftManager(), this);
		pm.registerEvents(new BowTrailListeners(), this);
		pm.registerEvents(new StaffChatManager(), this);
		pm.registerEvents(new StaffModManager(), this);
	}

	public static WarCore getInstance() {
		return instance;
	}

	public void createPlayer(WarPlayer user) {
		players.put(user.getPlayer().getName(), user);
		Bukkit.getPluginManager().callEvent(new UserJoinEvent(user));
	}

	public void deletePlayer(WarPlayer user) {
		if (containsPlayer(user.getPlayer())) {
			players.remove(user.getPlayer().getName());
		}
	}

	public boolean containsPlayer(Player player) {
		return players.containsKey(player.getName());
	}

	public WarPlayer getUser(Player player) {
		if (containsPlayer(player)) {
			return players.get(player.getName());
		}
		return new WarPlayer(player);
	}

	public HashMap<String, WarPlayer> getUsers() {
		return this.players;
	}

	public Economy getEconomy() {
		return this.eco;
	}

	private void setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			this.eco = null;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			this.eco = null;
		}
		this.eco = (Economy) rsp.getProvider();
	}

}
