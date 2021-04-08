package fr.cmuagab.sheepwars.handler;

import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.PlayerConnection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.spigotmc.ProtocolInjector;
import org.spigotmc.ProtocolInjector.PacketTitle;

/**
* Minecraft 1.8 Title
*
* @version 1.0.4
* @author Maxim Van de Wynckel
*/
public class Title {
    /* Title text and color */
    private String title = "";
    private ChatColor titleColor = ChatColor.WHITE;
    /* Subtitle text and color */
    private String subtitle = "";
    private ChatColor subtitleColor = ChatColor.WHITE;
    /* Title timings */
    private int fadeInTime = -1;
    private int stayTime = -1;
    private int fadeOutTime = -1;
    private boolean ticks = false;

    public Title(final String title) {
        this.title = title;
    }

    public Title(final String title, final String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public Title(final Title title) {
        // Copy title
        this.title = title.title;
        this.subtitle = title.subtitle;
        this.titleColor = title.titleColor;
        this.subtitleColor = title.subtitleColor;
        this.fadeInTime = title.fadeInTime;
        this.fadeOutTime = title.fadeOutTime;
        this.stayTime = title.stayTime;
        this.ticks = title.ticks;
    }

    public Title(final String title, final String subtitle, final int fadeInTime, final int stayTime, final int fadeOutTime) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadeInTime = fadeInTime;
        this.stayTime = stayTime;
        this.fadeOutTime = fadeOutTime;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public Title setSubtitle(final String subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public void setTitleColor(final ChatColor color) {
        this.titleColor = color;
    }

    public void setSubtitleColor(final ChatColor color) {
        this.subtitleColor = color;
    }

    public void setFadeInTime(final int time) {
        this.fadeInTime = time;
    }

    public void setFadeOutTime(final int time) {
        this.fadeOutTime = time;
    }

    public void setStayTime(final int time) {
        this.stayTime = time;
    }

    public void setTimingsToTicks() {
        this.ticks = true;
    }

    public void setTimingsToSeconds() {
        this.ticks = false;
    }

    public void send(final Player player) {
        if (Title.isPlayerRightVersion(player)) {
            // First reset previous settings
            this.resetTitle(player);
            try {
                // Send timings first
                final PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
                if (this.fadeInTime != -1 && this.fadeOutTime != -1 && this.stayTime != -1) {
                    connection.sendPacket(new PacketTitle(PacketTitle.Action.TIMES, this.fadeInTime * (this.ticks ? 1 : 20), this.stayTime * (this.ticks ? 1 : 20), this.fadeOutTime * (this.ticks ? 1 : 20)));
                }

                // Send title
                IChatBaseComponent serialized = ChatSerializer.a("{text:\"" + ChatColor.translateAlternateColorCodes('&', this.title) + "\",color:" + this.titleColor.name().toLowerCase() + "}");
                PacketTitle packet = new PacketTitle(PacketTitle.Action.TITLE, serialized);
                connection.sendPacket(packet);
                if (this.subtitle != "") {
                    // Send subtitle if present
                    serialized = ChatSerializer.a("{text:\"" + ChatColor.translateAlternateColorCodes('&', this.subtitle) + "\",color:" + this.subtitleColor.name().toLowerCase() + "}");
                    packet = new PacketTitle(PacketTitle.Action.SUBTITLE, serialized);
                    connection.sendPacket(packet);
                }
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcast() {
        for (final Player player : Bukkit.getOnlinePlayers()) {
            this.send(player);
        }
    }

    public void clearTitle(final Player player) {
        if (Title.isPlayerRightVersion(player)) {
            try {
                // Send timings first
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new ProtocolInjector.PacketTitle(PacketTitle.Action.CLEAR));
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void resetTitle(final Player player) {
        if (Title.isPlayerRightVersion(player)) {
            try {
                // Send timings first
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new ProtocolInjector.PacketTitle(PacketTitle.Action.RESET));
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isPlayerRightVersion(final Player player) {
        return ((CraftPlayer) player).getHandle().playerConnection.networkManager.getVersion() >= 47;
    }
}
