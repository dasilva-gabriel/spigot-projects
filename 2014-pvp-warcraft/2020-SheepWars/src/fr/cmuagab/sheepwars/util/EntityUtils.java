package fr.cmuagab.sheepwars.util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.util.Vector;

public class EntityUtils {
    public static void moveToward(final Entity entity, final Location to, final double speed) {
        final Location location = entity.getLocation();
        final double x = location.getX() - to.getX();
        final double y = location.getY() - to.getY();
        final double z = location.getZ() - to.getZ();
        final Vector velocity = new Vector(x, y, z).normalize().multiply(-speed);
        entity.setVelocity(velocity);
    }

    @SuppressWarnings("deprecation")
    public static void spawnFallingBlock(final Block block, final World world, final float xSpeed, final float ySpeed, final float zSpeed) {
        final FallingBlock fallingBlock = world.spawnFallingBlock(block.getLocation(), block.getType(), block.getData());
        final float x = -xSpeed + (float) (Math.random() * ((xSpeed - -xSpeed) + 1));
        final float y = -ySpeed + (float) (Math.random() * ((ySpeed - -ySpeed) + 1));
        final float z = -zSpeed + (float) (Math.random() * ((zSpeed - -zSpeed) + 1));
        fallingBlock.setVelocity(new Vector(x, y, z));
        fallingBlock.setDropItem(false);
    }
}
