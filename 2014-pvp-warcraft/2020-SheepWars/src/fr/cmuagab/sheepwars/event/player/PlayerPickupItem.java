package fr.cmuagab.sheepwars.event.player;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.event.SheepListener;
import fr.cmuagab.sheepwars.handler.Sheep;
import fr.cmuagab.sheepwars.handler.Step;
import fr.cmuagab.sheepwars.handler.Team;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerPickupItem extends SheepListener {
    public PlayerPickupItem(final SheepWarsPlugin plugin) {
        super(plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPickupItem(final PlayerPickupItemEvent event) {
        if (!Step.isStep(Step.IN_GAME) || Team.getPlayerTeam(event.getPlayer()) == Team.SPEC) {
            event.setCancelled(true);
        } else {
            boolean cancel = true;
            for (final Sheep sheep : Sheep.values()) {
                if (sheep.getIcon().isSimilar(event.getItem().getItemStack())) {
                    cancel = false;
                    break;
                }
            }
            if (cancel) {
                event.setCancelled(true);
            }
        }
    }
}
