package fr.lowtix.warbox.players;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import fr.lowtix.warbox.WarBox;
import fr.lowtix.warbox.enums.Groups;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class WarPlayer {
	
	public boolean canSave = false;
	
	private Player player;
	private String name;
	
	private int coins;
	
	private int exp;
	private int level;
	
	private PlayerStats stats;
	private Groups group;
	
	private int killstreak;

	public WarPlayer(Player player) {
		this.player = player;
		this.name = player.getName();
		this.exp = 0;
		this.level = 1;
		this.stats = new PlayerStats(this);
		this.group = Groups.DEFAULT;
		this.canSave = false;
		this.killstreak = 0;
		
		boolean load = WarBox.getInstance().getSql().loadUser(this);
		WarBox.getInstance().createPlayer(this);
		
		reloadTabulation();
		
		PermissionUser user = PermissionsEx.getUser(player);
		List<String> groups = user.getParentIdentifiers();
		
		if(!groups.isEmpty() || groups.size() != 0) {
			Groups gr = Groups.getRankFromName(groups.get(0));
			
			if(gr != null) {
				this.group = gr;
			}
			
		}
		
		if(load) {
			canSave = true;
		}
		
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public PlayerStats getStats() {
		return stats;
	}

	public void setStats(PlayerStats stats) {
		this.stats = stats;
	}
	
	public Groups getGroup() {
		return group;
	}
	
	public void setGroup(Groups group) {
		this.group = group;
	}
	
	public int getCoins() {
		return coins;
	}
	
	public void setCoins(int coins) {
		this.coins = coins;
	}
	
	public void removeCoins(int coins) {
		this.coins -= coins;
	}
	
	public void addCoins(int coins) {
		this.coins += coins;
	}
	
	public void removeExp(int exp) {
		this.exp -= exp;
	}
	
	public void addExp(int exp) {
		this.exp += exp;
	}
	
	public int getKillstreak() {
		return killstreak;
	}
	
	public void setKillstreak(int killstreak) {
		this.killstreak = killstreak;
	}
	
	public void clear() {
		player.setMaxHealth(40);
		player.setHealth(40);
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);

		for (PotionEffect effects : player.getActivePotionEffects()) {
			player.removePotionEffect(effects.getType());
		}
	}
	
	public void save() {
		WarBox.getInstance().getSql().saveUser(this);
	}
	
	public void reloadTabulation() {
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "nametagedit player "+player.getName()+" clear");
		
		String tag = getGroup().getPrefix() + "§r " + getGroup().getGroupColor(); 
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "nametagedit player "+player.getName()+" prefix "+tag);
		int prio = 8 - getGroup().getPower();
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "nametagedit player "+player.getName()+" priority "+prio);
		
	}

}
