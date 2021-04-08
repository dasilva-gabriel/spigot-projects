package fr.cmuagab.sheepwars.sheep;

import fr.cmuagab.sheepwars.entity.CustomSheep;
import fr.cmuagab.sheepwars.handler.Sheep.SheepAction;
import fr.cmuagab.sheepwars.handler.Team;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FrozenSheepAction implements SheepAction {
    private static final int RADIUS = 8;

    @Override
    public void onSpawn(final Player player, final CustomSheep customSheep, final Sheep sheep) {}

    @SuppressWarnings("deprecation")
    @Override
    public boolean onTicking(final Player player, final long ticks, final CustomSheep customSheep, final Sheep sheep) {
        if (ticks % 40 == 0) { // && SheepWarsPlugin.isOnGround(sheep)
            final World world = sheep.getWorld();
            final Location location = sheep.getLocation();
            for (int x = -FrozenSheepAction.RADIUS; x < FrozenSheepAction.RADIUS; x++) {
                for (int y = -FrozenSheepAction.RADIUS; y < FrozenSheepAction.RADIUS; y++) {
                    for (int z = -FrozenSheepAction.RADIUS; z < FrozenSheepAction.RADIUS; z++) {
                        final Block block = world.getBlockAt(location.getBlockX() + x, location.getBlockY() + y, location.getBlockZ() + z);
                        final Block top = block.getRelative(BlockFace.UP);
                        if ((block.getType() != Material.AIR && block.getType() != Material.SNOW && block.getType().isSolid()) && (top.getType() == Material.AIR || top.getType() == Material.SNOW)) {
                            if (top.getType() != Material.SNOW) {
                                top.setData((byte) 0);
                                top.setType(Material.SNOW);
                            } else {
                                final byte data = top.getData();
                                if (data < 7) {
                                    top.setData((byte) (1 + data));
                                }
                            }
                        }
                    }
                }
            }
            final Team playerTeam = Team.getPlayerTeam(player);
            for (final Entity entity : sheep.getNearbyEntities(FrozenSheepAction.RADIUS, FrozenSheepAction.RADIUS, FrozenSheepAction.RADIUS)) {
                if (entity instanceof Player) {
                    final Player nearby = (Player) entity;
                    final Team team = Team.getPlayerTeam(nearby);
                    if (team != playerTeam && team != Team.SPEC) {
                        nearby.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 1));
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void onFinish(final Player player, final CustomSheep customSheep, final org.bukkit.entity.Sheep sheep, final boolean death) {}
}
