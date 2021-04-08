package fr.cmuagab.sheepwars.event.entity;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.event.SheepListener;
import fr.cmuagab.sheepwars.handler.Sheep;
import fr.cmuagab.sheepwars.handler.Step;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class EntityDamage extends SheepListener {
    public EntityDamage(final SheepWarsPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onEntityDamage(final EntityDamageEvent event) {
        if (Step.isStep(Step.LOBBY)) {
            event.setCancelled(true);
        } else if (event.getEntity() instanceof org.bukkit.entity.Sheep) {
            if (event.getCause() == DamageCause.FALL) {
                event.setCancelled(true);
            } else {
                final org.bukkit.entity.Sheep entity = (org.bukkit.entity.Sheep) event.getEntity();
                // Custom Sheeps
                if (Sheep.INTERGALACTIC.getColor() == entity.getColor() && Sheep.INTERGALACTIC.getName().equals(entity.getCustomName()) && event.getCause().name().contains("_EXPLOSION")) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
