package fr.cmuagab.sheepwars.packet;

import java.io.IOException;

import lombok.Setter;
import net.minecraft.server.v1_7_R4.Packet;
import net.minecraft.server.v1_7_R4.PacketDataSerializer;
import net.minecraft.server.v1_7_R4.PacketListener;
import net.minecraft.server.v1_7_R4.PlayerConnection;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PacketWorldBorder extends Packet {
    @Setter
    private Action action;
    private double x, z;
    private double radius;
    private double oldRadius;
    private double newRadius;
    private long speed;
    private int portalTeleportBoundary = 29999984;
    @Setter
    private int warningTime, warningBlocks;

    public PacketWorldBorder() {}

    public PacketWorldBorder(final Action action) {
        this.action = action;
    }

    public PacketWorldBorder(final double x, final double z, final double newRadius, final long speed, final int warningTime, final int warningBlocks) {
        this.action = Action.INITIALIZE;
        this.x = x;
        this.z = z;
        this.oldRadius = 0;
        this.newRadius = newRadius;
        this.speed = speed;
        this.warningTime = warningTime;
        this.warningBlocks = warningBlocks;
    }

    public PacketWorldBorder(final Action action, final double x, final double z, final double radius, final double oldRadius, final double newRadius, final long speed, final int portalTeleportBoundary, final int warningTime, final int warningBlocks) {
        this.x = x;
        this.z = z;
        this.action = action;
        this.radius = radius;
        this.oldRadius = oldRadius;
        this.newRadius = newRadius;
        this.speed = speed;
        this.portalTeleportBoundary = portalTeleportBoundary;
        this.warningTime = warningTime;
        this.warningBlocks = warningBlocks;
    }

    @Override
    public void a(final PacketDataSerializer packetdataserializer) throws IOException {
        this.action = Action.values()[packetdataserializer.a()];
        switch (this.action) {
        case SET_SIZE:
            this.radius = packetdataserializer.readInt();
            break;
        case LERP_SIZE:
            this.oldRadius = packetdataserializer.readDouble();
            this.newRadius = packetdataserializer.readDouble();
            this.speed = this.getSpeed(packetdataserializer);
            break;
        case SET_CENTER:
            this.x = packetdataserializer.readDouble();
            this.z = packetdataserializer.readDouble();
            break;
        case INITIALIZE:
            this.x = packetdataserializer.readDouble();
            this.z = packetdataserializer.readDouble();
            this.oldRadius = packetdataserializer.readDouble();
            this.newRadius = packetdataserializer.readDouble();
            this.speed = this.getSpeed(packetdataserializer);
            this.portalTeleportBoundary = packetdataserializer.a();
            this.warningTime = packetdataserializer.a();
            this.warningBlocks = packetdataserializer.a();
            break;
        case SET_WARNING_TIME:
            this.warningTime = packetdataserializer.a();
            break;
        case SET_WARNING_BLOCKS:
            this.warningBlocks = packetdataserializer.a();
        }
    }

    @Override
    public void b(final PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.b(this.action.ordinal());
        switch (this.action) {
        case SET_SIZE:
            packetdataserializer.writeDouble(this.radius);
            break;
        case LERP_SIZE:
            packetdataserializer.writeDouble(this.oldRadius);
            packetdataserializer.writeDouble(this.newRadius);
            this.setSpeed(packetdataserializer, this.speed);
            break;
        case SET_CENTER:
            packetdataserializer.writeDouble(this.x);
            packetdataserializer.writeDouble(this.z);
            break;
        case INITIALIZE:
            packetdataserializer.writeDouble(this.x);
            packetdataserializer.writeDouble(this.z);
            packetdataserializer.writeDouble(this.oldRadius);
            packetdataserializer.writeDouble(this.newRadius);
            this.setSpeed(packetdataserializer, this.speed);
            packetdataserializer.b(this.portalTeleportBoundary);
            packetdataserializer.b(this.warningTime);
            packetdataserializer.b(this.warningBlocks);
            break;
        case SET_WARNING_TIME:
            packetdataserializer.b(this.warningTime);
            break;
        case SET_WARNING_BLOCKS:
            packetdataserializer.b(this.warningBlocks);
        }
    }

    @Override
    public void handle(final PacketListener packetlistener) {}

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

    public static enum Action {
        SET_SIZE, LERP_SIZE, SET_CENTER, INITIALIZE, SET_WARNING_TIME, SET_WARNING_BLOCKS;

        private Action() {}
    }

    public long getSpeed(final PacketDataSerializer packetdataserializer) {
        long l = 0L;
        int i = 0;
        while (true) {
            final int j = packetdataserializer.readByte();
            l |= (j & 0x7F) << i++ * 7;
            if (i > 10) { throw new RuntimeException("VarLong too big"); }
            if ((j & 0x80) != 128) {
                break;
            }
        }
        return l;
    }

    public void setSpeed(final PacketDataSerializer packetdataserializer, long l) {
        while (true) {
            if ((l & 0xFFFFFF80) == 0L) {
                packetdataserializer.writeByte((int) l);
                return;
            }
            packetdataserializer.writeByte((int) (l & 0x7F) | 0x80);
            l >>>= 7;
        }
    }
}
