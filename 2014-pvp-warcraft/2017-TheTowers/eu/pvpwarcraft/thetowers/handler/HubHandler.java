package eu.pvpwarcraft.thetowers.handler;

import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import eu.pvpwarcraft.thetowers.TheTowers;

public class HubHandler {
	
	public static void sendToHub(Player player){
		
		player.sendMessage("§3Hub §8» §7Envoi vers le hub en cours...");
		
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(TheTowers.getInstance().hub_server);
        player.sendPluginMessage(TheTowers.getInstance(), "BungeeCord", out.toByteArray());
	}

}
