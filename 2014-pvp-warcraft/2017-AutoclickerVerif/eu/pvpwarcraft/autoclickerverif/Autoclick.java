package eu.pvpwarcraft.autoclickerverif;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.minecraft.server.v1_8_R3.MinecraftServer;

public class Autoclick extends JavaPlugin {

	public static Autoclick instance;
	static double[] tps = { -1, -1, -1 };

	public void onEnable() {
		System.out.println("[Autoclick] Starting to load.");
		instance = this;
		if ((getConfig().get("seconds_alerts") == null) || (!getConfig().isInt("seconds_alerts"))) {
			getConfig().set("seconds_alerts", Integer.valueOf(2));
		}
		if ((getConfig().get("cps_to_alert") == null) || (!getConfig().isInt("cps_to_alert"))) {
			getConfig().set("cps_to_alert", Integer.valueOf(19));
		}
		if ((getConfig().get("alert_message") == null) || (!getConfig().isString("alert_message"))) {
			getConfig().set("alert_message",
					"§4Warrior §8§l» &c{0} §7détecté pour §bAutoclick &8[&a{1} &7CPS &8| &a{2} &7MS &8| &a{3} &7TPS&8]");
		}
		if ((getConfig().get("permission_alert") == null) || (!getConfig().isString("permission_alert"))) {
			getConfig().set("permission_alert", "warrior.alert");
		}
		if ((getConfig().get("permission_verif") == null) || (!getConfig().isString("permission_verif"))) {
			getConfig().set("permission_verif", "warrior.command.verif");
		}
		getServer().getPluginManager().registerEvents(new AutoclickListener(), this);
		getCommand("verif").setExecutor(new VerifCommand(getConfig().getString("permission_verif")));
		saveConfig();
		new VerifRunnable().runTaskTimerAsynchronously(this, 0L, 2L);
		new CheckRunnable(getConfig().getInt("seconds_alerts"), getConfig().getInt("cps_to_alert"),
				getConfig().getString("alert_message"), getConfig().getString("permission_alert"))
						.runTaskTimerAsynchronously(this, 0L, 20L);
		for (Player p : Bukkit.getOnlinePlayers()) {
			new PlayerWrapper(p);
		}
		System.out.println("[Autoclick] Finished loading.");
	}

	public void onDisable() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			PlayerWrapper.removePlayer(p);
		}
		System.out.println("[Autoclick] Unloaded.");
	}


	public double getTPS() {
		tps = MinecraftServer.getServer().recentTps;
		String tps_s = "" + tps[0];
		return Double.parseDouble(tps_s.substring(0, 4));
	}
}