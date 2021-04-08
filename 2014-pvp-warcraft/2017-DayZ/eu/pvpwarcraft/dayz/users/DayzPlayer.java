package eu.pvpwarcraft.dayz.users;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import eu.pvpwarcraft.dayz.DayZ;

public class DayzPlayer {

	private String name;
	private double thirst;
	
	private InGameRanks igranks;
	
	private PlayerCapacities pcapacities;
	private Statistics stats;

	public DayzPlayer(String name, double thirst, PlayerCapacities pcapacities, Statistics stats, InGameRanks igranks) {
		this.name = name;
		this.thirst = thirst;
		this.pcapacities = pcapacities;
		this.stats = stats;
		this.igranks = igranks;
		DayZ.addPlayer(this);
	}
	
	

	public InGameRanks getIgranks() {
		return igranks;
	}

	public void setIgranks(InGameRanks igranks) {
		this.igranks = igranks;
	}

	public void rankUp(){
		setIgranks(getIgranks().upRank());
	}
	
	public void rankDown(){
		setIgranks(getIgranks().downRank());
	}

	public double getThirst() {
		return thirst;
	}

	public void setThirst(double thirst) {
		this.thirst = thirst;
	}

	public void addThirst(double thirst) {
		this.thirst += thirst;
		if (this.thirst > 200) {
			this.thirst = 200;
		}
	}

	public void removeThirst(double thirst) {
		this.thirst -= thirst;
		if (this.thirst < 0) {
			this.thirst = 0;
		}
	}

	public String getName() {
		return name;
	}

	public Player getPlayer() {
		return Bukkit.getPlayer(name);
	}

	public String getBar() {
		int points = (int) thirst + 1;
		points = points / 10;
		String bar = "";
		for (int i = 1; i <= points; i++) {
			bar = bar + "§3¼";
		}
		for (int i = 1; i <= (20 - points); i++) {
			bar = bar + "§7¼";
		}
		return bar;
	}
	
	public PlayerCapacities getCapacities() {
		return pcapacities;
	}

	public Statistics getStats() {
		return stats;
	}



	public static class PlayerCapacities {

		private int medicLevel = 0;
		private double medicExp = 0;
		private boolean medicUnlocked = false;
		
		private int grenadierLevel = 0;
		private double grenadierExp = 0;
		private boolean grenadierUnlocked = false;
		
		private int eclaireurLevel = 0;
		private double eclaireurExp = 0;
		private boolean eclaireurUnlocked = false;
		
		private int soutienLevel = 0;
		private double soutienExp = 0;
		private boolean soutienUnlocked = false;
		
		public String getMedicBar() {
			int points = (int) medicExp;
			points = (int) makePEC(20, points, maxExp(points));
			String bar = "";
			for (int i = 1; i <= points; i++) {
				bar = bar + "§a•";
			}
			for (int i = 1; i <= (20 - points); i++) {
				bar = bar + "§7•";
			}
			return bar;
		}
		
		public int maxExp(int level){
			double result = 100 * (5^level);
			return (int) result;
		}
		
		private double makePEC(double arg0, double arg1, double arg3){
			double result = 0.0;
			
			result = (arg0*arg3)/arg1;
			
			return result;
		}

		public int getMedicLevel() {
			return medicLevel;
		}

		public void setMedicLevel(int medicLevel) {
			this.medicLevel = medicLevel;
		}

		public double getMedicExp() {
			return medicExp;
		}

		public void setMedicExp(double medicExp) {
			this.medicExp = medicExp;
		}

		public boolean isMedicUnlocked() {
			return medicUnlocked;
		}

		public void setMedicUnlocked(boolean medicUnlocked) {
			this.medicUnlocked = medicUnlocked;
		}

		public int getGrenadierLevel() {
			return grenadierLevel;
		}

		public void setGrenadierLevel(int grenadierLevel) {
			this.grenadierLevel = grenadierLevel;
		}

		public double getGrenadierExp() {
			return grenadierExp;
		}

		public void setGrenadierExp(double grenadierExp) {
			this.grenadierExp = grenadierExp;
		}

		public boolean isGrenadierUnlocked() {
			return grenadierUnlocked;
		}

		public void setGrenadierUnlocked(boolean grenadierUnlocked) {
			this.grenadierUnlocked = grenadierUnlocked;
		}

		public int getEclaireurLevel() {
			return eclaireurLevel;
		}

		public void setEclaireurLevel(int eclaireurLevel) {
			this.eclaireurLevel = eclaireurLevel;
		}

		public double getEclaireurExp() {
			return eclaireurExp;
		}

		public void setEclaireurExp(double eclaireurExp) {
			this.eclaireurExp = eclaireurExp;
		}

		public boolean isEclaireurUnlocked() {
			return eclaireurUnlocked;
		}

		public void setEclaireurUnlocked(boolean eclaireurUnlocked) {
			this.eclaireurUnlocked = eclaireurUnlocked;
		}

		public int getSoutienLevel() {
			return soutienLevel;
		}

		public void setSoutienLevel(int soutienLevel) {
			this.soutienLevel = soutienLevel;
		}

		public double getSoutienExp() {
			return soutienExp;
		}

		public void setSoutienExp(double soutienExp) {
			this.soutienExp = soutienExp;
		}

		public boolean isSoutienUnlocked() {
			return soutienUnlocked;
		}

		public void setSoutienUnlocked(boolean soutienUnlocked) {
			this.soutienUnlocked = soutienUnlocked;
		}

		public String serialize() {
			// 0:0:0:0:0:0
			String serialized = "";

			serialized += medicLevel + ";";
			serialized += medicExp + ";";
			serialized += medicUnlocked == true ? "1" : "0";
			serialized += ";";

			serialized += grenadierLevel + ";";
			serialized += grenadierExp + ";";
			serialized += grenadierUnlocked == true ? "1" : "0";
			serialized += ";";
			
			serialized += eclaireurLevel + ";";
			serialized += eclaireurExp + ";";
			serialized += eclaireurUnlocked == true ? "1" : "0";
			serialized += ";";
			
			serialized += soutienLevel + ";";
			serialized += soutienExp + ";";
			serialized += soutienUnlocked == true ? "1" : "0";

			return serialized;
		}

		public PlayerCapacities deserialize(String capacities) {
			
			String[] capacitie = capacities.split(";");

			this.medicLevel = Integer.parseInt(capacitie[0]);
			this.medicExp = Double.parseDouble(capacitie[1]);
			this.medicUnlocked = capacitie[2] == "1" ? true : false;
			
			this.grenadierLevel = Integer.parseInt(capacitie[3]);
			this.grenadierExp = Double.parseDouble(capacitie[4]);
			this.grenadierUnlocked = capacitie[5] == "1" ? true : false;
			
			this.eclaireurLevel = Integer.parseInt(capacitie[6]);
			this.eclaireurExp = Double.parseDouble(capacitie[7]);
			this.eclaireurUnlocked = capacitie[8] == "1" ? true : false;
			
			this.soutienLevel = Integer.parseInt(capacitie[9]);
			this.soutienExp = Double.parseDouble(capacitie[10]);
			this.soutienUnlocked = capacitie[11] == "1" ? true : false;

			return this;
		}
	}
	
	public static class Statistics {

		private int playersKilled = 0;
		private int zombiesKilled = 0;
		private int deaths = 0;

		public int getPlayersKilled() {
			return playersKilled;
		}

		public void setPlayersKilled(int playersKilled) {
			this.playersKilled = playersKilled;
		}

		public int getZombiesKilled() {
			return zombiesKilled;
		}

		public void setZombiesKilled(int zombiesKilled) {
			this.zombiesKilled = zombiesKilled;
		}

		public int getDeath() {
			return deaths;
		}

		public void setDeath(int death) {
			this.deaths = death;
		}

		public String serialize() {
			String serialized = "";

			serialized += playersKilled + ";";
			serialized += zombiesKilled + ";";
			serialized += deaths;

			return serialized;
		}

		public Statistics deserialize(String statistics) {
			
			String[] statistic = statistics.split(";");

			this.playersKilled = Integer.parseInt(statistic[0]);
			this.zombiesKilled = Integer.parseInt(statistic[1]);
			this.deaths = Integer.parseInt(statistic[2]);

			return this;
		}
	}

}
