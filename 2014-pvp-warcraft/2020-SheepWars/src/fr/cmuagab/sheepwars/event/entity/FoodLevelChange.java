package fr.cmuagab.sheepwars.event.entity;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.event.SheepListener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChange extends SheepListener {
    public FoodLevelChange(final SheepWarsPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onFoodLevelChange(final FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
}
