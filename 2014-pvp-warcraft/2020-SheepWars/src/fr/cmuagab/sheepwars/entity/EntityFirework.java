package fr.cmuagab.sheepwars.entity;

import net.minecraft.server.v1_7_R4.EntityFireworks;
import net.minecraft.server.v1_7_R4.PacketPlayOutEntityStatus;
import net.minecraft.server.v1_7_R4.World;

import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

public class EntityFirework extends EntityFireworks {
    private boolean gone = false;
    private Player[] players = null;

    public EntityFirework(final World world, final Player... players) {
        super(world);
        this.players = players;
        this.a(0.25F, 0.25F);
    }

    @Override
    public void h() {
        if (this.gone) { return; }
        if (!this.world.isStatic) {
            this.gone = true;
            if (this.players != null) {
                if (this.players.length > 0) {
                    for (final Player player : this.players) {
                        (((CraftPlayer) player).getHandle()).playerConnection.sendPacket(new PacketPlayOutEntityStatus(this, (byte) 17));
                    }
                    this.die();
                    return;
                }
            }
            this.world.broadcastEntityEffect(this, (byte) 17);
            this.die();
        }
    }

    public static void spawn(final Location location, final FireworkEffect effect, final Player... players) {
        try {
            final EntityFirework firework = new EntityFirework(((CraftWorld) location.getWorld()).getHandle(), players);
            final FireworkMeta meta = ((Firework) firework.getBukkitEntity()).getFireworkMeta();
            meta.addEffect(effect);
            ((Firework) firework.getBukkitEntity()).setFireworkMeta(meta);
            firework.setPosition(location.getX(), location.getY(), location.getZ());
            if ((((CraftWorld) location.getWorld()).getHandle()).addEntity(firework)) {
                firework.setInvisible(true);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
