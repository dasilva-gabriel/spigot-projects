package eu.pvpwarcraft.warfight.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import eu.pvpwarcraft.warfight.PlayerWrapper;
import eu.pvpwarcraft.warfight.WarFight;
import eu.pvpwarcraft.warfight.PlayerWrapper.PlayerStates;
import eu.pvpwarcraft.warfight.managers.Locations;
import eu.pvpwarcraft.warfight.managers.LocationsManager;
import eu.pvpwarcraft.warfight.managers.board.BoardType;
import eu.pvpwarcraft.warfight.managers.lobby.LobbyManager;

public class InOutListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		event.setJoinMessage(null);
		
		Player player = event.getPlayer();
		
		for(int i = 0; i < WarFight.errors.size(); i++){
			player.sendMessage(WarFight.errors.get(i));
		}
		
		if(WarFight.isReady){
			player.teleport(LocationsManager.getSavedLocation(Locations.SPAWN.getPath(), true));
		}else{
			player.sendMessage("§4Warn §8» §cLe Serveur rencontre des problèmes.");
		}
		
		PlayerWrapper pw = WarFight.getPlayer(player);
		pw.setState(PlayerStates.LOBBY);
		
		player.sendMessage("§8§m--+------------------------------------------------+--");
		player.sendMessage("");
		player.sendMessage("  §8» §7Bienvenue §e"+pw.getName()+" §7sur le serveur.");
		player.sendMessage("");
		player.sendMessage("     §8■ §7Vos coins: §b0 coins");
		player.sendMessage("");
		player.sendMessage("§8§m--+------------------------------------------------+--");
		
		LobbyManager.goToLobby(player, true, true);
		
	}
	
	@EventHandler
	public void onSneak(PlayerToggleSneakEvent event){
		Player player = event.getPlayer();
		PlayerWrapper pw = WarFight.getPlayer(player);
		
		if(player.isSneaking()){
			pw.getBoard().loadBoardToPlayer(BoardType.ELO);
		}else{
			pw.getBoard().loadBoardToPlayer(BoardType.LOBBY);
		}
		
	}

}
