package fr.cmuagab.sheepwars.util;

import net.minecraft.server.v1_7_R4.World;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class WorldUtils {
    public static void createExplosion(final Player player, final Location location, final float power) {
        WorldUtils.createExplosion(player, ((CraftWorld) location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ(), power, true, false);
    }

    public static void createExplosion(final Player player, final Location location, final float power, final boolean fire) {
        WorldUtils.createExplosion(player, ((CraftWorld) location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ(), power, true, fire);
    }

    public static void createExplosion(final Player player, final World world, final double x, final double y, final double z, final float power, final boolean breakBlocks, final boolean fire) {
        world.createExplosion(((CraftPlayer) player).getHandle(), x, y, z, power, breakBlocks, fire);
    }
}
