package fr.cmuagab.sheepwars.sheep;

import fr.cmuagab.sheepwars.entity.CustomSheep;
import fr.cmuagab.sheepwars.handler.Sheep.SheepAction;
import fr.cmuagab.sheepwars.handler.Team;
import fr.cmuagab.sheepwars.util.ParticleEffect;
import net.minecraft.server.v1_7_R4.EntityPlayer;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.util.Vector;

public class EarthQuakeSheep implements SheepAction {
    private static final int RADIUS = 6;

    @Override
    public void onSpawn(final Player player, final CustomSheep customSheep, final Sheep sheep) {}

    @SuppressWarnings("deprecation")
    @Override
    public boolean onTicking(final Player player, final long ticks, final CustomSheep customSheep, final Sheep sheep) {
        if (ticks % 20 == 0) {
            final World world = sheep.getWorld();
            final Location location = sheep.getLocation();
            world.playSound(location, Sound.IRONGOLEM_HIT, 1, 1);
            for (int x = -EarthQuakeSheep.RADIUS; x < EarthQuakeSheep.RADIUS; x++) {
                for (int y = -EarthQuakeSheep.RADIUS; y < EarthQuakeSheep.RADIUS; y++) {
                    for (int z = -EarthQuakeSheep.RADIUS; z < EarthQuakeSheep.RADIUS; z++) {
                        final Block block = world.getBlockAt(location.getBlockX() + x, location.getBlockY() + y, location.getBlockZ() + z);
                        final Block top = block.getRelative(BlockFace.UP);
                        if (block.getType() != Material.AIR && top.getType() == Material.AIR) {
                            final Location topLocation = top.getLocation();
                            ParticleEffect.displayBlockCrack(topLocation, Material.DIRT.getId(), (byte) 0, 0, 0, 0, 1);
                        }
                    }
                }
            }
            final EntityPlayer killer = ((CraftPlayer) player).getHandle();
            for (final Entity entity : sheep.getNearbyEntities(EarthQuakeSheep.RADIUS, EarthQuakeSheep.RADIUS / 2, EarthQuakeSheep.RADIUS)) {
                if (entity instanceof Player && Team.getPlayerTeam(((Player) entity)) != Team.SPEC) {
                    final Player victim = (Player) entity;
                    victim.setVelocity(new Vector(0, 0.4, 0).add(entity.getLocation().getDirection()).multiply(0.7D));
                    if (victim != player) {
                        ((CraftPlayer) victim).getHandle().killer = killer;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void onFinish(final Player player, final CustomSheep customSheep, final org.bukkit.entity.Sheep sheep, final boolean death) {}
}
