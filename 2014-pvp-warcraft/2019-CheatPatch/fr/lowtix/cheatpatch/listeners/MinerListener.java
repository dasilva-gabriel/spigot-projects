package fr.lowtix.cheatpatch.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.lowtix.cheatpatch.CheatPatch;
import fr.lowtix.cheatpatch.sql.MinerSQL;

public class MinerListener implements Listener {

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		
		if(CheatPatch.getInstance().getMinerManager().hasMiner(e.getPlayer())) {
			if(e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockZ() != e.getTo().getBlockZ()) {
				e.getPlayer().teleport(e.getFrom());
				e.getPlayer().sendMessage("§cVous avez un miner, vous ne pouvez pas bouger... Faites §e/miner §cpour le désactiver");
			}
		}
		
	}
	
	@EventHandler
	public void onInteract(BlockBreakEvent e) {
		if(CheatPatch.getInstance().getMinerManager().hasMiner(e.getPlayer())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cVous avez un miner, vous ne pouvez pas bouger... Faites §e/miner §cpour le désactiver");
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		if(CheatPatch.getInstance().getMinerManager().hasMiner(e.getPlayer())) {
			CheatPatch.getInstance().getMinerManager().getMiner(e.getPlayer()).playerLeave();
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if(MinerSQL.contains(e.getPlayer().getName())) {
			int[] res = MinerSQL.hasMiner(e.getPlayer());
			
			if(res[0] > 0) {
				
				CheatPatch.getInstance().getMinerManager().createMinerWithArgs(e.getPlayer(), res[0], res[1], res[2], res[3], res[4]);
				
				MinerSQL.delete(e.getPlayer().getName());
				
			}
			
		}
	}
	
}
