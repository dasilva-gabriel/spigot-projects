package eu.pvpwarcraft.thetowers.managers;

import eu.pvpwarcraft.thetowers.TheTowers;
import eu.pvpwarcraft.thetowers.handler.Team.Teams;
import eu.pvpwarcraft.thetowers.handler.TowerPlayer;

public class TeamManager {

	public static boolean isInTeam(TowerPlayer towerpl, Teams team) {
		if (towerpl.getTeam().equals(team)) {
			return true;
		}
		return false;
	}

	public static void addTeam(TowerPlayer towerpl, Teams team) {
		if (!isInTeam(towerpl, team)) {
			towerpl.setTeam(team);
			towerpl.getPlayer().setCustomName(TheTowers.getTeam(team).getColor() + TheTowers.getTeam(team).getName()
					+ " " + towerpl.getPlayer().getName());
			towerpl.getPlayer().setPlayerListName(towerpl.getPlayer().getCustomName());
			towerpl.getPlayer().setCustomNameVisible(true);
		}
	}
	
	public static void removeTeam(TowerPlayer towerpl, Teams team) {
		if (isInTeam(towerpl, team)) {
			Teams other = Teams.AUCUNE;
			towerpl.setTeam(other);
			towerpl.getPlayer().setCustomName(TheTowers.getTeam(other).getColor() + TheTowers.getTeam(other).getName()+ " " + towerpl.getPlayer().getName());
			towerpl.getPlayer().setPlayerListName(towerpl.getPlayer().getCustomName());
			towerpl.getPlayer().setCustomNameVisible(true);
		}
	}

	public static boolean canJoinTeam(TowerPlayer towerpl, Teams team) {
		Teams other = Teams.AUCUNE;
		if (team.equals(Teams.CYAN)) {
			other = Teams.ORANGE;
		}
		if (team.equals(Teams.ORANGE)) {
			other = Teams.CYAN;
		}
		if (TheTowers.getTeam(team).getPlayers().size() < TheTowers.getTeam(other).getPlayers().size() + 1) {
			return false;
		}
		return true;
	}
	
	public static Teams randomTeam() {
		Teams cyan = Teams.CYAN;
		Teams orange = Teams.ORANGE;
		
		if(TheTowers.getTeam(cyan).getPlayers().size() < TheTowers.getTeam(orange).getPlayers().size() + 1){
			return cyan;
		}else{
			return orange;
		}
		
	}

}
