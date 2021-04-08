package fr.cmuagab.sheepwars.sheep;

import java.util.HashMap;
import java.util.Map;

import fr.cmuagab.sheepwars.entity.CustomSheep;
import fr.cmuagab.sheepwars.handler.Sheep.SheepAction;

import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RemoteSheep implements SheepAction {
    private final Map<Sheep, Player> sheeps = new HashMap<>();
    private final Map<Player, Location> locations = new HashMap<>();

    @Override
    public void onSpawn(final Player player, final CustomSheep customSheep, final Sheep sheep) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
        this.sheeps.put(sheep, player);
        this.locations.put(player, player.getLocation());
        sheep.setPassenger(player);
    }

    @Override
    public boolean onTicking(final Player player, final long ticks, final CustomSheep customSheep, final Sheep sheep) {
        return sheep.getPassenger() == null;
    }

    @Override
    public void onFinish(final Player player, final CustomSheep customSheep, final org.bukkit.entity.Sheep sheep, final boolean death) {
        player.removePotionEffect(PotionEffectType.INVISIBILITY);
        player.teleport(this.locations.remove(player));
        player.setFireTicks(0);
        player.setNoDamageTicks(0);
        player.setHealth(player.getHealthScale() - 4);
        player.playEffect(EntityEffect.HURT);
        if (!death) {
            customSheep.explode(3.5F);
        }
    }
}
