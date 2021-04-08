package fr.cmuagab.sheepwars.sheep;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.entity.CustomSheep;
import fr.cmuagab.sheepwars.handler.Sheep.SheepAction;
import fr.cmuagab.sheepwars.util.MathUtils;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class FragmentationSheep implements SheepAction {

    @Override
    public void onSpawn(final Player player, final CustomSheep customSheep, final Sheep sheep) {}

    @Override
    public boolean onTicking(final Player player, final long ticks, final CustomSheep customSheep, final Sheep sheep) {
        if (ticks <= 60 && ticks % 3 == 0) {
            if (ticks == 60) {
                sheep.getWorld().playSound(sheep.getLocation(), Sound.FUSE, 1, 1);
            }
            sheep.setColor(sheep.getColor() == DyeColor.WHITE ? DyeColor.GRAY : DyeColor.WHITE);
        }
        return false;
    }

    @Override
    public void onFinish(final Player player, final CustomSheep customSheep, final org.bukkit.entity.Sheep sheep, final boolean death) {
        if (!death) {
            final Location location = sheep.getLocation();
            customSheep.explode(3.5F);
            for (int i = 0; i < 3; i++) {
                final Sheep baby = fr.cmuagab.sheepwars.handler.Sheep.spawnSheepStatic(location, player);
                baby.setColor(DyeColor.values()[MathUtils.random.nextInt(DyeColor.values().length)]);
                baby.setVelocity(new Vector(MathUtils.random(1.2F), MathUtils.random(1.0F), MathUtils.random(1.2F)));
                baby.setBaby();
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        ((CustomSheep) ((CraftEntity) baby).getHandle()).explode(3);
                    }
                }.runTaskLater(SheepWarsPlugin.i, 20);
            }
        }
    }
}
