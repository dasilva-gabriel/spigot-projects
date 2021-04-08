package fr.cmuagab.sheepwars.sheep;

import fr.cmuagab.sheepwars.entity.CustomSheep;
import fr.cmuagab.sheepwars.handler.Sheep.SheepAction;
import fr.cmuagab.sheepwars.handler.Team;
import fr.cmuagab.sheepwars.util.EntityUtils;
import fr.cmuagab.sheepwars.util.MathUtils;
import fr.cmuagab.sheepwars.util.ParticleEffect;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

public class DistorsionSheep implements SheepAction {
    private static final int RADIUS = 6;

    @Override
    public void onSpawn(final Player player, final CustomSheep customSheep, final Sheep sheep) {}

    @Override
    public boolean onTicking(final Player player, final long ticks, final CustomSheep customSheep, final Sheep sheep) {
        if (ticks % 2 == 0) {
            final World world = sheep.getWorld();
            final Location location = sheep.getLocation();
            world.playSound(location, Sound.ENDERMAN_TELEPORT, 0.5F, 5);
            for (int x = -DistorsionSheep.RADIUS; x < DistorsionSheep.RADIUS; x++) {
                for (int y = -DistorsionSheep.RADIUS; y < DistorsionSheep.RADIUS; y++) {
                    for (int z = -DistorsionSheep.RADIUS; z < DistorsionSheep.RADIUS; z++) {
                        if (MathUtils.randomBoolean(0.025F)) {
                            final Block block = world.getBlockAt(location.getBlockX() + x, location.getBlockY() + y, location.getBlockZ() + z);
                            if (block.getType() != Material.AIR) {
                                EntityUtils.spawnFallingBlock(block, world, 0.1F, 0.3F, 0.1F);
                                if (MathUtils.randomBoolean()) {
                                    ParticleEffect.PORTAL.display(block.getLocation(), 0, 0, 0, 1, 10);
                                }
                                block.setType(Material.AIR);
                            }
                        }
                    }
                }
            }
            for (final Entity entity : sheep.getNearbyEntities(DistorsionSheep.RADIUS, DistorsionSheep.RADIUS, DistorsionSheep.RADIUS)) {
                if (entity instanceof Player && MathUtils.randomBoolean(0.1F) && Team.getPlayerTeam((Player) entity) != Team.SPEC) {
                    EntityUtils.moveToward(entity, location, 0.5D);
                }
            }
        }
        return false;
    }

    @Override
    public void onFinish(final Player player, final CustomSheep customSheep, final org.bukkit.entity.Sheep sheep, final boolean death) {}
}
