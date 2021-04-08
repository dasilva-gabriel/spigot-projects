package fr.cmuagab.sheepwars.sheep;

import fr.cmuagab.sheepwars.entity.CustomSheep;
import fr.cmuagab.sheepwars.handler.Sheep.SheepAction;
import fr.cmuagab.sheepwars.handler.Team;
import fr.cmuagab.sheepwars.util.ParticleEffect;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DarkSheep implements SheepAction {
    private static int RADIUS = 10;

    @Override
    public void onSpawn(final Player player, final CustomSheep customSheep, final Sheep sheep) {}

    @Override
    public boolean onTicking(final Player player, final long ticks, final CustomSheep customSheep, final Sheep sheep) {
        if (ticks % 20 == 0) {
            final Team playerTeam = Team.getPlayerTeam(player);
            for (final Entity entity : sheep.getNearbyEntities(DarkSheep.RADIUS, DarkSheep.RADIUS, DarkSheep.RADIUS)) {
                if (entity instanceof Player) {
                    final Player nearby = (Player) entity;
                    final Team team = Team.getPlayerTeam(nearby);
                    if (team != playerTeam && team != Team.SPEC) {
                        nearby.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1));
                    }
                }
            }
        } else if (ticks % 5 == 0) {
            ParticleEffect.SMOKE.display(sheep.getLocation().add(0.2f, 1, 0.2f), 0, 0, 0, 0, 10);
        }
        return false;
    }

    @Override
    public void onFinish(final Player player, final CustomSheep customSheep, final org.bukkit.entity.Sheep sheep, final boolean death) {}
}
