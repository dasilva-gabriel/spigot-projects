package fr.cmuagab.sheepwars.packet;

import java.io.IOException;

import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.PacketDataSerializer;
import net.minecraft.server.v1_7_R4.PacketListener;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;
import net.minecraft.server.v1_7_R4.PacketPlayOutListener;
import net.minecraft.server.v1_7_R4.PlayerConnection;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
* @author DarkSeraphim
*/
public class PacketPlayOutActionBar extends PacketPlayOutChat {
    public String json;

    public PacketPlayOutActionBar(final String message) {
        super();
        this.json = "{\"text\":\"" + message + "\"}";
    }

    @Override
    public void a(final PacketDataSerializer packetdataserializer) throws IOException {
        this.json = ChatSerializer.a(ChatSerializer.a(packetdataserializer.c(32767)));
    }

    @Override
    public void b(final PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.json);
        packetdataserializer.writeByte(0x2);
    }

    @Override
    public void a(final PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    @Override
    public String b() {
        return String.format("actionbar='%s'", new Object[] { this.json });
    }

    @Override
    public void handle(final PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }

    public void send(final Player player) {
        final PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().playerConnection;
        if (playerConnection.networkManager.getVersion() >= 47) {
            playerConnection.sendPacket(this);
        }
    }

    public void broadcast() {
        for (final Player player : Bukkit.getOnlinePlayers()) {
            this.send(player);
        }
    }
}