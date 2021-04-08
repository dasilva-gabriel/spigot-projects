package fr.cmuagab.sheepwars.event.entity;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.event.SheepListener;
import fr.cmuagab.sheepwars.util.MathUtils;

import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class EntityChangeBlock extends SheepListener {
    public EntityChangeBlock(final SheepWarsPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onEntityChangeBlock(final EntityChangeBlockEvent event) {
        if (event.getEntity() instanceof FallingBlock && MathUtils.randomBoolean(0.5F)) {
            event.setCancelled(true);
        }
    }
}
