package fr.cmuagab.sheepwars.event.projectile;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.event.SheepListener;
import fr.cmuagab.sheepwars.handler.Booster;
import fr.cmuagab.sheepwars.handler.Booster.TriggerAction;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class ProjectileLaunch extends SheepListener {
    public ProjectileLaunch(final SheepWarsPlugin plugin) {
        super(plugin);
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onProjectileLaunch(final ProjectileLaunchEvent event) {
        if (event.getEntity() instanceof Arrow) {
            final Arrow arrow = (Arrow) event.getEntity();
            if (arrow.getShooter() instanceof Player) {
                final Player player = (Player) arrow.getShooter();
                for (final Booster booster : Booster.values()) {
                    if (booster.getTriggers().contains(TriggerAction.ARROW_LAUNCH)) {
                        booster.getAction().onEvent(player, event, TriggerAction.ARROW_LAUNCH);
                    }
                }
            }
        }
    }
}
