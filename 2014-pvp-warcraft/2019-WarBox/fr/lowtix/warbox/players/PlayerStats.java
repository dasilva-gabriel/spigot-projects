package fr.lowtix.warbox.players;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.lowtix.warbox.enums.BowTrail;
import fr.lowtix.warbox.enums.Tags;

public class PlayerStats {
	
	public static final String split = "¤";
	public static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	private WarPlayer warPlayer;
	
	private int keys;
	
	private BowTrail trail;
	private Tags active_tag;
	
	private String lastRankUp;
	private String lastDailyPickUp;
	
	private int kills;
	private int deaths;
	private int best_killstreak;
	
	public PlayerStats(WarPlayer warPlayer) {
		this.warPlayer = warPlayer;
		this.keys = 0;
		this.trail = BowTrail.NONE;
		this.active_tag = Tags.NONE;
		this.lastRankUp = "01/01/2000 10:10";
		this.lastDailyPickUp = dateFormat.format(new Date());
		this.kills = 0;
		this.deaths = 0;
		this.best_killstreak = 0;
	}

	public WarPlayer getWarPlayer() {
		return warPlayer;
	}

	public void setWarPlayer(WarPlayer warPlayer) {
		this.warPlayer = warPlayer;
	}

	public int getKeys() {
		return keys;
	}

	public void setKeys(int keys) {
		this.keys = keys;
	}

	public BowTrail getTrail() {
		return trail;
	}

	public void setTrail(BowTrail trail) {
		this.trail = trail;
	}

	public Tags getActive_tag() {
		return active_tag;
	}

	public void setActive_tag(Tags active_tag) {
		this.active_tag = active_tag;
	}

	public String getLastRankUp() {
		return lastRankUp;
	}

	public void setLastRankUp(String lastRankUp) {
		this.lastRankUp = lastRankUp;
	}

	public String getLastDailyPickUp() {
		return lastDailyPickUp;
	}

	public void setLastDailyPickUp(String lastDailyPickUp) {
		this.lastDailyPickUp = lastDailyPickUp;
	}
	
	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	
	public int getBest_killstreak() {
		return best_killstreak;
	}
	
	public void setBest_killstreak(int best_killstreak) {
		this.best_killstreak = best_killstreak;
	}

	public String getSerial() {
		String serialized = "SERIAL" + split;
		serialized += ""+getKeys();
		serialized += split;
		serialized += getTrail().name();
		serialized += split;
		serialized += getActive_tag().name();
		serialized += split;
		serialized += getLastRankUp();
		serialized += split;
		serialized += getLastDailyPickUp();
		serialized += split;
		serialized += getKills();
		serialized += split;
		serialized += getDeaths();
		serialized += split;
		serialized += getBest_killstreak();
		return serialized;
	}
	
	public void loadBySerial(String s) {
		String[] serialized = s.split(split);
		if (serialized.length >= 2 && serialized[1] != null) {
			setKeys(Integer.parseInt(serialized[1]));
		}

		if (serialized.length >= 3 && serialized[2] != null) {
			setTrail(BowTrail.getTrail(serialized[2]));
		}

		if (serialized.length >= 4 && serialized[3] != null) {
			setActive_tag(Tags.getTagFromName(serialized[3]));
		}
		if (serialized.length >= 5 && serialized[4] != null) {
			setLastRankUp(serialized[4]);
		}
		if (serialized.length >= 6 && serialized[5] != null) {
			setLastDailyPickUp(serialized[5]);
		}
		if (serialized.length >= 7 && serialized[6] != null) {
			setKills(Integer.valueOf(serialized[6]));
		}
		if (serialized.length >= 8 && serialized[7] != null) {
			setDeaths(Integer.valueOf(serialized[7]));
		}
		if (serialized.length >= 9 && serialized[8] != null) {
			setBest_killstreak(Integer.valueOf(serialized[8]));

		}
	}
	
}
