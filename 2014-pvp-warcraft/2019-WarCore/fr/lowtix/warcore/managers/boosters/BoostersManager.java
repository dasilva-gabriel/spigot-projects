package fr.lowtix.warcore.managers.boosters;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.enums.Boosters;
import fr.lowtix.warcore.enums.Ranks;

public class BoostersManager {
	
	public HashMap<Boosters, Booster> boosters = new HashMap<Boosters, Booster>();
	
	public void setup() {
		for(Boosters boost : Boosters.values()) {
			Booster booster = new Booster(boost);
			boosters.put(boost, booster);
		}
	}
	
	public double getBoostByVIP() {
		double booster = -15.0;
		
		for(WarPlayer all : WarCore.getInstance().getUsers().values()) {
			if(all.getRank().equals(Ranks.SILVER)) {
				booster += 2.0;
			} else if(all.getRank().equals(Ranks.GOLD)) {
				booster += 5.0;
			} else if(all.getRank().isHigher(Ranks.DIAMOND)) {
				booster += 10.0;
			}
		}
		
		
		if(booster > 200) {
			booster = 200;
		}
		
		if(booster < 0) {
			booster = 0;
		}
		
		booster /= 100;
		booster += 1;
		
		return booster;
	}
	
	public ArrayList<Boosters> getBoostersActive(){
		
		ArrayList<Boosters> result = new ArrayList<>();
		
		for(Boosters boost : Boosters.values()) {
			
			if(isActive(boost)) {
				result.add(boost);
			}
			
		}
		
		return result;
	}
	
	public double getKillBoost(double base) {
		
		double booster = getBoostByVIP();
		
		base *= booster;
		
		if(isActive(Boosters.KILLS)) {
			booster *= 2;
		}
		
		return booster;		
	}
	
	public void requestBooster(Player player, Boosters boosterType) {
		Booster booster = boosters.get(boosterType);
		
		if(booster.active) {
			player.sendMessage("§dBoutique §8» §cUn booster de ce type est déjà actif.");
			return;
		} else {
			WarPlayer wPlayer = WarCore.getInstance().getUser(player);
			if(wPlayer.getGemmes() < boosterType.getCost()) {
				player.sendMessage("§dBoutique §8» §cVous n'avez pas les fonds nécéssaires.");
				return;
			} else {
				wPlayer.setGemmes(wPlayer.getGemmes() - boosterType.getCost());
				wPlayer.save();
				player.sendMessage("§dBoutique §8» §7Vous avez §aacheté §7un booster §e"+boosterType.getDisplay()+"§7. §8(§c-"+boosterType.getCost()+"G§8)");
				booster.active(player);
				return;
			}
		}
	}
	
	public boolean isActive(Boosters boost) {
		return this.boosters.get(boost).isActive();
	}
	
	public void checkBooster() {
		for(Booster boosts : this.boosters.values()) {
			if(boosts.isActive()) {
				if(boosts.getMinuteLived() >= 60) {
					boosts.end();
				}
			}
		}
	}

}
