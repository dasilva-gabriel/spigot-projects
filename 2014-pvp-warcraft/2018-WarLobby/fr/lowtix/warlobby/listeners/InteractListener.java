package fr.lowtix.warlobby.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.lowtix.warlobby.WarLobby;
import fr.lowtix.warlobby.guis.ServersGui;
import fr.lowtix.warlobby.utils.GuiManager;

public class InteractListener implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if(event.getAction() == null) return;
		if(event.getItem() == null) return;
		
		event.setCancelled(true);
		
		Player player = event.getPlayer();
		
		if(event.getItem().getType().equals(Material.COMPASS)) {
			GuiManager.openGui(new ServersGui(player));
		} else if(event.getItem().getType().equals(Material.REDSTONE_COMPARATOR)) {
			
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("Connect");
			out.writeUTF("changepass");
			player.sendPluginMessage(WarLobby.getInstance(), "BungeeCord", out.toByteArray());
			
			player.getInventory().setHeldItemSlot(7);
			
		} else if(event.getItem().getType().equals(Material.GOLD_INGOT)) {
			
			player.sendMessage("§8[§3§lW§fInfo§8] §6Informations sur le serveur:");
			player.sendMessage("  §8§ §b/changepass §7§ochanger votre mot de passe");
			player.sendMessage("  §8§ §b/discord §7§oavoir le lien du discord");
		}
	}

}
