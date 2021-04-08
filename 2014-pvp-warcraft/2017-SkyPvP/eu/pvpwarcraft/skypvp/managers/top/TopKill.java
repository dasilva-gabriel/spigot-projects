package eu.pvpwarcraft.skypvp.managers.top;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import eu.pvpwarcraft.skypvp.SkyPvP;
import eu.pvpwarcraft.skypvp.managers.players.PlayersStats;

public class TopKill {

	public static List<Entry<String, Integer>> top = new ArrayList<Entry<String, Integer>>();
	public static List<String> topMessage = new ArrayList<String>();

	private static void refreshTop() {
		HashMap<String, Integer> players = new HashMap<String, Integer>();
		for (OfflinePlayer offplayer : Bukkit.getOfflinePlayers()) {
			players.put(offplayer.getName(), PlayersStats.getKills(offplayer));
		}
		ArrayList<Entry<String, Integer>> top = new ArrayList<Entry<String, Integer>>();
		top.addAll(players.entrySet());
		Collections.sort(top, new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}

		});
		
		//Buffer
		ArrayList<Entry<String, Integer>> topTwenty = new ArrayList<Entry<String, Integer>>();
		for(int i = 1; i <= 21; i++){
			topTwenty.add(top.get(i));
		}
		topMessage.clear();
		topMessage.add("§8⚊§7⚊§8⚊§7⚊§8⚊§7⚊§8⚊§7⚊ §3§lTOP 20 §8⚊§7⚊§8⚊§7⚊§8⚊§7⚊§8⚊§7⚊");
		topMessage.add("");
		for (int i = 1; i <= 20; i++) {
			String name = topTwenty.get(i).getKey();
			int kills = topTwenty.get(i).getValue();
			topMessage.add(" §8‹ §6§l" + i + " §8› §e" + name + " §8(§c" + kills + "§8)");
		}
		topMessage.add("");
		topMessage.add(" §3♽ §7Actualisation toutes les §b30 minutes");
		topMessage.add("");
		topMessage.add("§8⚊§7⚊§8⚊§7⚊§8⚊§7⚊§8⚊§7⚊ §3§lTOP 20 §8⚊§7⚊§8⚊§7⚊§8⚊§7⚊§8⚊§7⚊");
	}

	public static List<String> getTop() {
		if (topMessage.isEmpty()) {
			refreshTop();
			Bukkit.getScheduler().runTaskTimer(SkyPvP.getInstance(), new Runnable() {

				@Override
				public void run() {
					refreshTop();
				}

			}, 59 * 60 * 20, 59 * 60 * 20);
		}
		return topMessage;
	}

}
