package fr.cmuagab.sheepwars.sheep;

import fr.cmuagab.sheepwars.entity.CustomSheep;
import fr.cmuagab.sheepwars.handler.Sheep.SheepAction;

import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

public class BoardingSheep implements SheepAction {

    @Override
    public void onSpawn(final Player player, final CustomSheep customSheep, final Sheep sheep) {
        sheep.setPassenger(player);
    }

    @Override
    public boolean onTicking(final Player player, final long ticks, final CustomSheep customSheep, final Sheep sheep) {
        return sheep.getPassenger() == null || sheep.isOnGround();
    }

    @Override
    public void onFinish(final Player player, final CustomSheep customSheep, final org.bukkit.entity.Sheep sheep, final boolean death) {}
}
