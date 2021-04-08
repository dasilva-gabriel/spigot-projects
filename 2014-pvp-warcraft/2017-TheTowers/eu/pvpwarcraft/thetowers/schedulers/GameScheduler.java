package eu.pvpwarcraft.thetowers.schedulers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import eu.pvpwarcraft.thetowers.TheTowers;
import eu.pvpwarcraft.thetowers.handler.Step;
import eu.pvpwarcraft.thetowers.handler.Team;
import eu.pvpwarcraft.thetowers.handler.Team.Teams;
import eu.pvpwarcraft.thetowers.handler.TowerPlayer;
import eu.pvpwarcraft.thetowers.managers.WinManager;
import eu.pvpwarcraft.thetowers.utils.ItemBuilder;
import eu.pvpwarcraft.thetowers.utils.Locations;
import eu.pvpwarcraft.warcraftapi.WarCraftAPI;
import eu.pvpwarcraft.warcraftapi.configuration.User;

public class GameScheduler extends BukkitRunnable {

	private TheTowers main;
	private int timer = 1200;
	private int secondState = 0;
	private int refreshState = 0;
	private BossBar bossbarTimer;
	private BossBar bossbarEnd;

	public GameScheduler(TheTowers main) {
		this.main = main;
	}

	@Override
	public void run() {
		
		if(refreshState >= 3){
			refreshState = 0;
			TheTowers.refreshServ();
		}
		
		if (secondState >= 4) {
			secondState = 0;
			timer--;
			refreshState++;
			spawnItem(Locations.IRON_INGOT.getLoc(), Material.IRON_INGOT, "§7Lingot de Fer");
			spawnItem(Locations.EXP.getLoc(), Material.EXP_BOTTLE, "§eBouteille d'Expérience");
			
		} else {
			secondState++;
		}

		if (bossbarTimer == null) {
			bossbarTimer = Bukkit.createBossBar("§e❱❱❱ §7La partie se termine dans §a §e❰❰❰", BarColor.WHITE, BarStyle.SOLID, new BarFlag[0]);
		}

		if (bossbarEnd == null) {
			bossbarEnd = Bukkit.createBossBar("§6mc.pvp-warcraft.eu", BarColor.YELLOW, BarStyle.SOLID, new BarFlag[0]);
		}
		
		if(timer == 1170){
			for (Player all : Bukkit.getOnlinePlayers()) {
				User user = WarCraftAPI.getInstance().getUsers().get(all.getName());
				TowerPlayer twpl = TheTowers.getPlayer(all);
				user.getStats().setTowersParties(Integer.valueOf((user.getStats().getTowersParties())+1));
				twpl.addEarned_points(1);
			}
		}

		for (Team.Teams team : Team.Teams.values()) {
			int i = 0;
			for(Player all : Bukkit.getOnlinePlayers()){
				TowerPlayer alltw = TheTowers.getPlayer(all);
				if(alltw.getTeam().equals(team)){
					i++;
				}
			}
			if(!team.equals(Teams.AUCUNE) && i <= 0){
				WinManager.winByLoser(team);
				bossbarTimer.removeAll();
				for(Player all : Bukkit.getOnlinePlayers()){
					bossbarEnd.addPlayer(all);
				}
				cancel();
				return;
			}
			if (TheTowers.getTeam(team).getPoints() >= 10) {
				WinManager.winByWinner(team);
				bossbarTimer.removeAll();
				for(Player all : Bukkit.getOnlinePlayers()){
					bossbarEnd.addPlayer(all);
				}
				cancel();
				return;
			}
		}

		if (!Step.isStep(Step.IN_GAME) || main.getPlayers().size() <= 0) {
			bossbarTimer.removeAll();
			cancel();
			return;
		}

		bossbarTimer.setTitle(getStyleBefore(secondState) + " §7La partie se termine dans §a" + getTime() + " "
				+ getStyleAfter(secondState));
		bossbarTimer.setProgress(makePEC(timer, 1200, 1.0));

		for (Player all : Bukkit.getOnlinePlayers()) {
			if (!bossbarTimer.getPlayers().contains(all)) {
				bossbarTimer.addPlayer(all);
			}
		}

	}

	private double makePEC(double arg0, double arg1, double arg3) {
		double result = 0.0;

		result = (arg0 * arg3) / arg1;

		return result;
	}

	private String getStyleAfter(int numb) {
		String bar = "";
		bar += "§8";
		for (int i = 1; i <= (4 - numb); i++) {
			bar += "<";
		}
		bar += "§e§l";
		for (int i = 1; i <= numb; i++) {
			bar += "<";
		}

		return bar;
	}

	private String getStyleBefore(int numb) {
		String bar = "";
		bar += "§e§l";
		for (int i = 1; i <= numb; i++) {
			bar += ">";
		}
		bar += "§8";
		for (int i = 1; i <= (4 - numb); i++) {
			bar += ">";
		}

		return bar;
	}

	private String getTime() {
		return new SimpleDateFormat("mm:ss").format(new Date(this.timer * 1000));
	}
	
	private void spawnItem(Location l, Material mat, String name){
		ItemStack it = new ItemBuilder(mat).setName(name).build();
		
		Location loc = l.clone();
		Item item = loc.getWorld().dropItem(loc, it);
		item.teleport(loc);
	}

}
