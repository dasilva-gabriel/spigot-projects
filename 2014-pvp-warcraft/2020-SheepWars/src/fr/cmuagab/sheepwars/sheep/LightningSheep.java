package fr.cmuagab.sheepwars.sheep;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.entity.CustomSheep;
import fr.cmuagab.sheepwars.handler.Sheep.SheepAction;
import fr.cmuagab.sheepwars.util.MathUtils;
import fr.cmuagab.sheepwars.util.WorldUtils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

public class LightningSheep implements SheepAction {
    private static final int RADIUS = 6;

    @Override
    public void onSpawn(final Player player, final CustomSheep customSheep, final Sheep sheep) {}

    @Override
    public boolean onTicking(final Player player, final long ticks, final CustomSheep customSheep, final Sheep sheep) {
        if (!sheep.hasMetadata("onGround")) {
            sheep.setMetadata("onGround", new FixedMetadataValue(SheepWarsPlugin.i, true));
            final World world = sheep.getWorld();
            world.setStorm(true);
            new BukkitRunnable() {
                private int seconds = 10;

                @Override
                public void run() {
                    if (this.seconds == 0) {
                        this.cancel();
                        world.setStorm(false);
                        world.setThundering(false);
                        return;
                    }
                    final Location location = sheep.getLocation();
                    for (int x = -LightningSheep.RADIUS; x < LightningSheep.RADIUS; x++) {
                        for (int y = -LightningSheep.RADIUS; y < LightningSheep.RADIUS; y++) {
                            for (int z = -LightningSheep.RADIUS; z < LightningSheep.RADIUS; z++) {
                                if (MathUtils.randomBoolean(0.01F)) {
                                    final Block block = world.getBlockAt(location.getBlockX() + x, location.getBlockY() + y, location.getBlockZ() + z);
                                    final Block top = block.getRelative(BlockFace.UP);
                                    if (block.getType() != Material.AIR && top.getType() == Material.AIR) {
                                        final Location topLoc = top.getLocation();
                                        world.strikeLightning(topLoc);
                                        WorldUtils.createExplosion(player, topLoc, 2.0F, true);
                                        this.seconds -= 2;
                                        return;
                                    }
                                }
                            }
                        }
                    }
                    this.seconds -= 2;
                }
            }.runTaskTimer(SheepWarsPlugin.i, 20, 40);
        }
        return false;
    }

    @Override
    public void onFinish(final Player player, final CustomSheep customSheep, final org.bukkit.entity.Sheep sheep, final boolean death) {}
}
