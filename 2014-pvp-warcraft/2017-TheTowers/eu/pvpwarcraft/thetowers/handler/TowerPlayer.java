package eu.pvpwarcraft.thetowers.handler;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import eu.pvpwarcraft.thetowers.TheTowers;
import eu.pvpwarcraft.thetowers.handler.Team.Teams;

public class TowerPlayer {

	private String name;
	private int points;
	private int kills;
	private int deaths;
	private int damage;
	private Teams team;
	private String last_damager;
	private double earned_points;

	public TowerPlayer(String name, int points, int kills, int deaths, int damage, Teams team, String last_damager, int earned_points) {
		this.name = name;
		this.points = points;
		this.kills = kills;
		this.deaths = deaths;
		this.damage = damage;
		this.team = team;
		this.last_damager = null;
		this.earned_points = earned_points;
		if(Step.isStep(Step.LOBBY)){
			TheTowers.addPlayer(this);
		}
	}

	public String getLast_damager() {
		return last_damager;
	}

	public void setLast_damager(String last_damager) {
		this.last_damager = last_damager;
	}

	public Teams getTeam() {
		return team;
	}

	public void setTeam(Teams team) {
		this.team = team;
	}

	public Player getPlayer() {
		return Bukkit.getPlayer(name);
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}

	public void addPoints(int points) {
		this.points += points;
	}

	public int getKills() {
		return kills;
	}

	public void addKills(int kills) {
		this.kills += kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void addDeaths(int deaths) {
		this.deaths += deaths;
	}

	public int getDamage() {
		return damage;
	}

	public void addDamage(int damage) {
		this.damage += damage;
	}

	public int getEarned_points() {
		return (int) earned_points;
	}

	public void addEarned_points(double earned_points) {
		this.earned_points += earned_points;
	}
	
	

}
