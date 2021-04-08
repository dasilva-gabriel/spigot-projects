package fr.lowtix.warcore.managers.boosters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.enums.Boosters;
import fr.lowtix.warcore.enums.Ranks;

public class Booster {
	
	public boolean active;
	public String id, owner, launched;
	public Boosters type;
	public ArrayList<String> thanks = new ArrayList<>();
	
	public Booster(Boosters type) {
		this.active = false;
		this.id = "_null_";
		this.owner = "_null_";
		this.launched = "_null_";
		this.type = type;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getLaunched() {
		return launched;
	}

	public void setLaunched(String launched) {
		this.launched = launched;
	}

	public Boosters getType() {
		return type;
	}

	public void setType(Boosters type) {
		this.type = type;
	}
	
	public int getMinuteLived() {
		 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		 
		 double result = -1;
		 
		 try {
			Date start = format.parse(launched);
			Date now = new Date();
			
			long duration  = now.getTime() - start.getTime();
			result = TimeUnit.MILLISECONDS.toMinutes(duration);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (int) result;
	}
	
	public void active(Player player) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		
		this.active = true;
		this.owner = player.getName();
		this.launched = dateFormat.format(date);
		this.thanks.clear();
		
		Bukkit.broadcastMessage("§8§l«§6-§8§l»§8§m------------------------------------------§8§l«§6-§8§l»");
		Bukkit.broadcastMessage("    §e§lUn booster a été lancé par §a§l"+player.getName()+" §e§l!");
		Bukkit.broadcastMessage(" ");
		Bukkit.broadcastMessage("    §7Durée: §b1 heure");
		Bukkit.broadcastMessage("    §7Type: §b"+this.type.getDisplay());
		Bukkit.broadcastMessage("    §7Remerciez le avec le §6/merci§7...");
		Bukkit.broadcastMessage("§8§l«§6-§8§l»§8§m------------------------------------------§8§l«§6-§8§l»");
		
		for(WarPlayer users : WarCore.getInstance().getUsers().values()) {
			users.getPlayer().playSound(users.getPlayer().getLocation(), Sound.WITHER_HURT, 1.0F, 10.0F);
			if(users.getRank().isHigher(Ranks.DIAMOND)) {
				users.getPlayer().sendMessage("§8[§e§l!§8] §7Grâce a votre grade vous remercier automatiquement le booster.");
				thanks(users.getPlayer());
			}
		}
	}
	
	public void thanks(Player player) {
		if(this.thanks.size() > 75) {
			player.sendMessage("§dBooster §8» §cLe booster a déja atteint son maximum de remerciement.");
			return;
		}
		
		if(this.thanks.contains(player.getName())) {
			player.sendMessage("§dBooster §8» §cVous avez déjà remercié "+this.owner+" pour son booster.");
			return;
		} else {
			this.thanks.add(player.getName());
		}
		
		WarPlayer wPlayer = WarCore.getInstance().getUser(player);
		
		player.sendMessage("§dBooster §8» §7Vous avez remercié §b"+this.owner+"§7, vous recevez §f750 ✴§7.");
		wPlayer.addPointsEffect(750);
		
		if(Bukkit.getPlayer(this.owner) != null && Bukkit.getPlayer(this.owner).isOnline()) {
			
			Player playerOwn = Bukkit.getPlayer(this.owner);
			WarPlayer userOwn = WarCore.getInstance().getUser(playerOwn);
			playerOwn.sendMessage("§dBooster §8» §7Vous avez été remercié par §e"+player.getName()+" §e+20⛃ §7et §f+75✴");
			
			userOwn.addCoins(20);
			userOwn.addPoints(75);
			
		}
		
	}
	
	public void end() {
		if(Bukkit.getPlayer(this.owner) != null && Bukkit.getPlayer(this.owner).isOnline()) {
			Player playerOwn = Bukkit.getPlayer(this.owner);
			playerOwn.sendMessage("§8[§5§l!§8] §d§lFin du booster!");
			playerOwn.sendMessage("§8[§5§l!§8] §7Nous te remercions d'avoir utiliser un Booster. Il est maintenant terminé.");
		}
		
		this.active = false;
		this.owner = "_null_";
	}

}
