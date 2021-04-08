package eu.pvpwarcraft.warcore.manager;

import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import eu.pvpwarcraft.warcore.WarCore;

public class SanctionManager {
	
	public static void sancPlayer(Player player, Player target, SancList sanc){
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		String serialized = "";
		serialized += target.getName() + ";";
		serialized += sanc.toString();
		out.writeUTF("ModSanc" + serialized);
		player.sendPluginMessage(WarCore.getInstance(), "ModSanc", out.toByteArray());
	}

}
