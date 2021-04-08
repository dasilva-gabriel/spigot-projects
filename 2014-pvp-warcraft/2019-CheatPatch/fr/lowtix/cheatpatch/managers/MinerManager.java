package fr.lowtix.cheatpatch.managers;

import java.util.HashMap;

import org.bukkit.entity.Player;

import fr.lowtix.cheatpatch.managers.miner.PlayerMiner;
import fr.lowtix.cheatpatch.sql.MinerSQL;

public class MinerManager {

	/*
	 * Level 1: 15'000$ > 1'500$, 3 émeraude, 5% clef MinerCrate
	 * Level 2: 100'000$ > 10'000$, 4 émeraudes, 10% clef MinerCrate
	 * Level 3: 200'000$ > 20'000$, 5 émeraudes, 15% clef MinerCrate
	 * Level 4: 500'000$ > 50'000$, 6 émeraudes, 20% clef MinerCrate
	 * Level 5: 1'200'000$ > 120'000$, 7 émeraudes, 25% clef MinerCrate
	 */
	
	public MinerManager() {
		//new MinerScheduler().runTaskTimerAsynchronously(CheatPatch.getInstance(), 0, 40);
	}
	
	private HashMap<String, PlayerMiner> miners = new HashMap<String, PlayerMiner>();

	public boolean hasMiner(Player player) {
		return miners.containsKey(player.getName());
	}

	public PlayerMiner getMiner(Player player) {
		if (!hasMiner(player)) { 
			return null; 
		}
		return miners.get(player.getName());
	}
	
	public int moneyPerTick(int level) {

		switch (level) {
		case 1: return 1500;
		case 2: return 10000;
		case 3: return 20000;
		case 4: return 40000;
		case 5: return 100000;
		}
		
		return 0;
	}
	
	public PlayerMiner createMiner(Player player, int level) {
		PlayerMiner m = new PlayerMiner(player, level, false);
		
		miners.put(player.getName(), m);
		MinerSQL.save(m);
		
		return m;
	}
	
	public PlayerMiner createMinerWithArgs(Player player, int level, int money, int emerald, int keys, int seconds) {
		PlayerMiner m = new PlayerMiner(player.getName(), level, money, emerald, keys, false, seconds);
		
		miners.put(player.getName(), m);
		MinerSQL.save(m);
		
		return m;
	}
	
	public void removeMiner(String name, boolean bdd_delete) {
		if(bdd_delete && MinerSQL.contains(name)) {
			MinerSQL.delete(name);
		}
		if(miners.containsKey(name)) {
			miners.remove(name);
		}
	}
	
	public void removeMiner(Player player, boolean bdd_delete) {
		removeMiner(player.getName(), bdd_delete);
	}
	
	public HashMap<String, PlayerMiner> getMiners() {
		return miners;
	}

}
