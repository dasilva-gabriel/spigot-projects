package fr.cmuagab.sheepwars.sheep;

import fr.cmuagab.sheepwars.entity.CustomSheep;
import fr.cmuagab.sheepwars.handler.Sheep.SheepAction;

import org.bukkit.DyeColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

public class ExplosiveSheep implements SheepAction {

    @Override
    public void onSpawn(final Player player, final CustomSheep customSheep, final Sheep sheep) {}

    @Override
    public boolean onTicking(final Player player, final long ticks, final CustomSheep customSheep, final Sheep sheep) {
        if (ticks <= 60 && ticks % 3 == 0) {
            if (ticks == 60) {
                sheep.getWorld().playSound(sheep.getLocation(), Sound.FUSE, 1, 1);
            }
            sheep.setColor(sheep.getColor() == DyeColor.WHITE ? DyeColor.RED : DyeColor.WHITE);
        }
        return false;
    }

    @Override
    public void onFinish(final Player player, final CustomSheep customSheep, final org.bukkit.entity.Sheep sheep, final boolean death) {
        if (!death) {
            customSheep.explode(3.7F);
        }
    }
}
