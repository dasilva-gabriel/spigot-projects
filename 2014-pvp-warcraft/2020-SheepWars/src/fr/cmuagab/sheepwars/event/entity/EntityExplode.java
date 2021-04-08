package fr.cmuagab.sheepwars.event.entity;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.event.SheepListener;
import fr.cmuagab.sheepwars.handler.Step;
import fr.cmuagab.sheepwars.util.EntityUtils;
import fr.cmuagab.sheepwars.util.MathUtils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityExplode extends SheepListener {
    public EntityExplode(final SheepWarsPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onEntityExplode(final EntityExplodeEvent event) {
        if (Step.isStep(Step.LOBBY)) {
            event.blockList().clear();
        } else {
            final Location center = event.getLocation();
            for (final Block block : event.blockList()) {
                if (MathUtils.random.nextBoolean()) {
                    EntityUtils.spawnFallingBlock(block, center.getWorld(), 0.3F, 1.2F, 0.3F);
                }
                block.setType(Material.AIR);
            }
        }
    }
}
