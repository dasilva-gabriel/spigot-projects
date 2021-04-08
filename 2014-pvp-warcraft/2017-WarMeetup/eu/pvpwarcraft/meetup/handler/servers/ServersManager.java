package eu.pvpwarcraft.meetup.handler.servers;

import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import eu.pvpwarcraft.meetup.Meetup;

public class ServersManager {

	public static void sendToWarFightLobby(Player player) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF("warfight");
		player.sendPluginMessage(Meetup.getInstance(), "BungeeCord", out.toByteArray());
	}

}
