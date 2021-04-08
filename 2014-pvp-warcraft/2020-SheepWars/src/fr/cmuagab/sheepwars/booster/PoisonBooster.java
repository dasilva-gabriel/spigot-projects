package fr.cmuagab.sheepwars.booster;

import fr.cmuagab.sheepwars.handler.Booster;
import fr.cmuagab.sheepwars.handler.Booster.BoosterAction;
import fr.cmuagab.sheepwars.handler.Booster.TriggerAction;
import fr.cmuagab.sheepwars.handler.Team;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PoisonBooster implements BoosterAction {

    @Override
    public boolean onStart(final Player player, final Team team, final Booster booster) {
        final Team opponents = team == Team.BLUE ? Team.RED : Team.BLUE;
        for (final Player opponent : opponents.getOnlinePlayers()) {
            opponent.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 1));
        }
        return true;
    }

    @Override
    public boolean onEvent(final Player player, final Event event, final TriggerAction trigger) {
        return false;
    }
}
