package fr.cmuagab.sheepwars.event.entity;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.event.SheepListener;

import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CreatureSpawn extends SheepListener {
    public CreatureSpawn(final SheepWarsPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onCreatureSpawn(final CreatureSpawnEvent event) {
        if (!(event.getEntity() instanceof Sheep)) {
            event.setCancelled(true);
        }
    }
}
