package fr.lowtix.warcore.managers;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.utils.PlayerUtils;

public class PunishManager {
	
	public void createSanction(Player player, String target, int type, String time, String reason){
		
		if(type == 1 && (Bukkit.getPlayer(target) != null && Bukkit.getPlayer(target).isOnline())) {
			Player taregtP = Bukkit.getPlayer(target);
			PlayerUtils.spawnFirework(taregtP.getLocation(), FireworkEffect.Type.BALL_LARGE, 0, Color.YELLOW, Color.RED, true, true);
			PlayerUtils.spawnFirework(taregtP.getLocation(), FireworkEffect.Type.BALL_LARGE, 0, Color.GREEN, Color.WHITE, true, true);
			PlayerUtils.spawnFirework(taregtP.getLocation(), FireworkEffect.Type.BALL_LARGE, 0, Color.ORANGE, Color.PURPLE, true, true);
		}
		
		player.sendMessage("§6Modération §8» §7Envoi de la sanction en cours....");
		
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		String serialized = "";
		serialized += "" + type + ";";
		serialized += player.getName() + ";";
		serialized += target + ";";
		serialized += time + ";";
		serialized += reason;
		out.writeUTF("Punish;" + serialized);
		player.sendPluginMessage(WarCore.getInstance(), "Punish", out.toByteArray());
	}

}
