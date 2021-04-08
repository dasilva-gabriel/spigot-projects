package fr.cmuagab.sheepwars.event.block;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.event.SheepListener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockSpreadEvent;

public class BlockSpread extends SheepListener {
    public BlockSpread(final SheepWarsPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onBlockSpread(final BlockSpreadEvent event) {
        event.setCancelled(true);
    }
}
