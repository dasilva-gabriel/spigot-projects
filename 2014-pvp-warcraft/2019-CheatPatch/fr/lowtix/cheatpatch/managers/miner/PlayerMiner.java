package fr.lowtix.cheatpatch.managers.miner;

import java.util.Random;

import org.bukkit.entity.Player;

import fr.lowtix.cheatpatch.CheatPatch;
import fr.lowtix.cheatpatch.sql.MinerSQL;

public class PlayerMiner {
	
	private String name;
	
	private int level;
	private int timer;
	
	private int money_earned;
	private int emerald_earned;
	private int keys_earned;
	
	private boolean vip;
	
	
	
	public PlayerMiner(String name, int level, int money_earned, int emerald_earned, int keys_earned, boolean vip, int seconds) {
		this.name = name;
		this.level = level;
		this.money_earned = money_earned;
		this.emerald_earned = emerald_earned;
		this.keys_earned = keys_earned;
		this.vip = vip;
		this.timer = seconds;
	}

	public PlayerMiner(Player player, int level, boolean vip) {
		this.name= player.getName();
		this.level = level;
		this.timer = 0;
		this.money_earned = 0;
		this.emerald_earned = 0;
		this.keys_earned = 0;
		this.vip = vip;
	}

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public int getTimer() {
		return timer;
	}
	
	public void addTimer(int i) {
		timer += i;
	}

	public int getMoney_earned() {
		return money_earned;
	}

	public boolean isVip() {
		return vip;
	}

	public int getEmerald_earned() {
		return emerald_earned;
	}

	public int getKeys_earned() {
		return keys_earned;
	}
	
	public void addMoney(int toAdd) {
		this.money_earned += toAdd;
	}
	
	public void addEmerald(int toAdd) {
		this.emerald_earned += toAdd;
	}
	
	public void addKeys(int toAdd) {
		this.keys_earned += toAdd;
	}
	
	public void setMoney_earned(int money_earned) {
		this.money_earned = money_earned;
	}

	public void setEmerald_earned(int emerald_earned) {
		this.emerald_earned = emerald_earned;
	}

	public void setKeys_earned(int keys_earned) {
		this.keys_earned = keys_earned;
	}

	public void tick() {
		
			addMoney(CheatPatch.getInstance().getMinerManager().moneyPerTick(level));
			addEmerald(3+level);
			
			if(randomKeys((5*level))) {
				addKeys(1);
			}
			
		
	}
	
	private boolean randomKeys(int chance) {
		
		return new Random().nextInt(100) < chance;
		
	}
	
	public int getAliveSeconds() {
		return 21600-getTimer();
	}
	
	public void playerLeave() {
		MinerSQL.save(this);
		CheatPatch.getInstance().getMinerManager().removeMiner(getName(), false);
	}

}
