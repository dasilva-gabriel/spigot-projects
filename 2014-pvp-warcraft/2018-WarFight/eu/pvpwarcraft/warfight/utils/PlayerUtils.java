package eu.pvpwarcraft.warfight.utils;

import java.lang.reflect.Field;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class PlayerUtils {
	
	public static void addEffect(Player player, PotionEffectType type, int timesec, int amplifier) {
		PotionEffect pEffect = new PotionEffect(type, timesec * 20, amplifier);
		player.addPotionEffect(pEffect);
	}
	
	public static void playSound(Player player, Sound sound) {
		player.playSound(player.getLocation(), sound, 1.0F, 1.0F);
	}
	
	public static void playSoundToTowPlayers(Player first, Player second, Sound sound) {
		playSound(first, sound);
		playSound(second, sound);
	}
	
	public static void sendTitleToTowPlayers(Player first, Player second, String titleText, String subtitleText) {
		sendTitle(first, titleText, subtitleText, 0, 10, 5);
		sendTitle(second, titleText, subtitleText, 0, 10, 5);
	}
	
	public static void sendMessageToTowPlayers(Player first, Player second, String message) {
		first.sendMessage(message);
		second.sendMessage(message);
	}
	

	public static void sendTitle(Player player, String titleText, String subtitleText, int fadeIn, int stay,
			int fadeOut) {
		PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE,
				ChatSerializer.a("{\"text\":\"" + titleText + "\"}"), fadeIn * 20, stay * 20, fadeOut * 20);
		PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE,
				ChatSerializer.a("{\"text\":\"" + subtitleText + "\"}"), fadeIn, stay, fadeOut);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(subtitle);
	}

	public static void sendActionBar(Player player, String message) {
		IChatBaseComponent baseaction = ChatSerializer.a("{\"text\": \"" + message + "\"}");

		PacketPlayOutChat actionpacket = new PacketPlayOutChat(baseaction, (byte) 2);

		((CraftPlayer) player).getHandle().playerConnection.sendPacket(actionpacket);

	}

	public static void spawnFirework(Location loc, FireworkEffect.Type type, int power, Color color1, Color color2,
			Boolean flicker, Boolean trail) {
		Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
		FireworkMeta fwm = fw.getFireworkMeta();

		FireworkEffect effect = FireworkEffect.builder().flicker(flicker.booleanValue()).withColor(color1)
				.withFade(color2).with(type).trail(trail.booleanValue()).build();

		fwm.addEffect(effect);
		fwm.setPower(power);

		fw.setFireworkMeta(fwm);
	}

	public static void setupTabulation(Player player, String textheader, String textfooter) {
		CraftPlayer craftplayer = (CraftPlayer) player;
		PlayerConnection connection = craftplayer.getHandle().playerConnection;
		IChatBaseComponent header = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + textheader + "\"}");
		IChatBaseComponent footer = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + textfooter + "\"}");
		PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
		try {
			Field headerField = packet.getClass().getDeclaredField("a");
			headerField.setAccessible(true);
			headerField.set(packet, header);
			headerField.setAccessible(!headerField.isAccessible());

			Field footerField = packet.getClass().getDeclaredField("b");
			footerField.setAccessible(true);
			footerField.set(packet, footer);
			footerField.setAccessible(!footerField.isAccessible());
		} catch (Exception e) {
			e.printStackTrace();
		}

		connection.sendPacket(packet);
	}

}
