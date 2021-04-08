package eu.pvpwarcraft.autoclickerverif;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PlayerWrapper {

	public static HashMap<UUID, PlayerWrapper> players = new HashMap<>();

	public int clicks = 0;
	public int clicks2 = 0;
	public int clicks3 = 0;
	public int clicks4 = 0;
	public int clicks5 = 0;
	public int clicks6 = 0;

	public int nombreAlertesAutoClick = 0;
	public int maxClicks = 0;
	public long lastBlockInteraction = 0L;
	public long lastAlert = 0L;

	public long Connexion = 0L;
	public String pseudo = "";

	public PlayerWrapper(Player p) {
		players.put(p.getUniqueId(), this);
		this.pseudo = p.getName();
		this.Connexion = System.currentTimeMillis();
	}

	public String getName() {
		return this.pseudo;
	}

	public Player getPlayer() {
		return Bukkit.getPlayer(this.pseudo);
	}

	public static PlayerWrapper getByPlayer(Player p) {
		return (PlayerWrapper) players.get(p.getUniqueId());
	}

	public static PlayerWrapper getByString(String name) {
		if (Bukkit.getPlayerExact(name) == null) {
			return null;
		}
		return (PlayerWrapper) players.get(Bukkit.getPlayerExact(name).getUniqueId());
	}

	public static void removePlayer(Player p) {
		players.remove(p.getUniqueId());
	}

	public int getPing() {
		int ms = ((CraftPlayer) getPlayer()).getHandle().ping;
		return ms;
	}
}