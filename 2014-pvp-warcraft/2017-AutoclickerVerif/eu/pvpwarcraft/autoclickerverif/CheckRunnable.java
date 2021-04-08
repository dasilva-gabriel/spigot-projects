package eu.pvpwarcraft.autoclickerverif;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import eu.pvpwarcraft.autoclickerverif.customevents.AutoclickAlertEvent;

public class CheckRunnable extends BukkitRunnable {
	private int timeBetweenAlerts;
	private int maxCps;
	private String AlertMessage;
	private String permission;

	public CheckRunnable(int secondes, int maxCps, String AlertMessage, String permission) {
		this.timeBetweenAlerts = secondes;
		this.maxCps = maxCps;
		this.AlertMessage = AlertMessage;
		this.permission = permission;
	}

	public void run()
  {
    for (PlayerWrapper wp : PlayerWrapper.players.values()) {
      int ping = wp.getPing();
      double tps = Autoclick.instance.getTPS();
      int AntiLag = (int)((20.0D - tps) * 2.0D);
      AntiLag += ping / 50;
      if ((wp.clicks >= this.maxCps + AntiLag) && (wp.lastAlert + this.timeBetweenAlerts * 1000L < System.currentTimeMillis()))
      {
        AutoclickAlertEvent event = new AutoclickAlertEvent(wp.pseudo, wp.clicks, ping, tps);
        Bukkit.getServer().getPluginManager().callEvent(event);
        wp.lastAlert = System.currentTimeMillis();
        if (!event.isCancelled()) {
        	for(Player all : Bukkit.getOnlinePlayers()){
        		if(all.isOp() || all.hasPermission(permission))
        		all.sendMessage(this.AlertMessage.replace("{0}", wp.pseudo)
            .replace("{1}", String.valueOf(wp.clicks))
            .replace("{2}", String.valueOf(ping))
            .replace("{3}", String.valueOf(tps)).replace("&", "§"));
        	}
          wp.nombreAlertesAutoClick += 1;
        }
      }
      wp.clicks6 = wp.clicks5;
      wp.clicks5 = wp.clicks4;
      wp.clicks4 = wp.clicks3;
      wp.clicks3 = wp.clicks2;
      wp.clicks2 = wp.clicks;
      wp.clicks = 0;
    }
  }
}