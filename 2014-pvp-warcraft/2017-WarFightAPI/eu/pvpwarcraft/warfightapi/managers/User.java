package eu.pvpwarcraft.warfightapi.managers;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import eu.pvpwarcraft.warfightapi.sql.WarFightSQL;

public class User {

	private String name;
	private int gemmes;
	private String first_join;
	private int time_played;
	
	private MeetupStatistics stats;
	private Elos elos;

	public User(String name, int gemmes, String first_join, int time_played, MeetupStatistics stats, Elos elos) {
		this.name = name;
		this.gemmes = gemmes;
		this.stats = stats;
		this.elos = elos;
		this.first_join = first_join;
		this.time_played = time_played;
	}

	public User create() {
		WarFightSQL.createPlayer(this);
		return this;
	}

	public Player getPlayer() {
		return Bukkit.getPlayer(this.name);
	}

	public String getName() {
		return this.name;
	}
	
	public int getPing() {
		return ((CraftPlayer)getPlayer()).getHandle().ping;
	}

	public int getGemmes() {
		return this.gemmes;
	}

	public void setGemmes(int gemmes) {
		this.gemmes = gemmes;
	}

	public void save() {
		WarFightSQL.savePlayer(this);
	}

	public void addGemmes(int amount) {
		gemmes += amount;
	}

	public void removeGemmes(int amount) {
		gemmes -= amount;
	}
	
	public MeetupStatistics getStats(){
		return stats;
	}
	
	public void setStats(MeetupStatistics stats){
		this.stats = stats;
	}
	
	public Elos getElos(){
		return elos;
	}
	
	public void setStats(Elos elos){
		this.elos = elos;
	}
	
	public String getFirst_join() {
		return first_join;
	}

	public void setFirst_join(String first_join) {
		this.first_join = first_join;
	}

	public int getTime_played() {
		return time_played;
	}

	public void setTime_played(int time_played) {
		this.time_played = time_played;
	}



	public static class Elos {
		
		public HashMap<KitsManager, String> elos = new HashMap<>();
		
		public void generate(){
			for(KitsManager kits : KitsManager.values()){
				elos.put(kits, serialize(1200, 0, 0));
			}
		}
		
		public void regen(KitsManager kit, int elo, int wins, int loses){
			
			elos.put(kit, serialize(elo, wins, loses));
		}
		
		public String serialize(int elo, int wins, int loses) {
			String serialized = "";

			serialized += elo + ";";
			serialized += wins + ";";
			serialized += loses;

			return serialized;
		}
		
		public int getElo(KitsManager kit) {
			return eloFromSerial(elos.get(kit));
		}
		
		public int getWins(KitsManager kit) {
			return winsFromSerial(elos.get(kit));
		}
		
		public int getLoses(KitsManager kit) {
			return losesFromSerial(elos.get(kit));
		}
		
		public int eloFromSerial(String serial) {
			String[] result = serial.split(";");
			return Integer.parseInt(result[0]);
		}
		
		public int winsFromSerial(String serial) {
			String[] result = serial.split(";");
			return Integer.parseInt(result[1]);
		}
		
		public int losesFromSerial(String serial) {
			String[] result = serial.split(";");
			return Integer.parseInt(result[2]);
		}
		
	}

	public static class MeetupStatistics {

		private int meetUpParties = 0;
		private int meetUpKills = 0;
		private int meetUpDeaths = 0;

		public int getMeetUpParties() {
			return meetUpParties;
		}

		public void setMeetUpParties(int meetUpParties) {
			this.meetUpParties = meetUpParties;
		}

		public int getMeetUpKills() {
			return meetUpKills;
		}

		public void setMeetUpKills(int meetUpKills) {
			this.meetUpKills = meetUpKills;
		}

		public int getMeetUpDeaths() {
			return meetUpDeaths;
		}

		public void setMeetUpDeaths(int meetUpDeaths) {
			this.meetUpDeaths = meetUpDeaths;
		}

		public String serialize() {
			// 0:0:0:0:0:0
			String serialized = "";

			serialized += meetUpParties + ";";
			serialized += meetUpKills + ";";
			serialized += meetUpDeaths;

			return serialized;
		}

		public MeetupStatistics deserialize(String statistics) {
			String[] statistic = statistics.split(";");

			this.meetUpParties = Integer.parseInt(statistic[0]);
			this.meetUpKills = Integer.parseInt(statistic[1]);
			this.meetUpDeaths = Integer.parseInt(statistic[2]);

			return this;
		}
	}

}
