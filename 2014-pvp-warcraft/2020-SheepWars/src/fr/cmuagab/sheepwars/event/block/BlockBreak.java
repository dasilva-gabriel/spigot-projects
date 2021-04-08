package fr.cmuagab.sheepwars.event.block;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.event.SheepListener;
import fr.cmuagab.sheepwars.handler.Step;
import fr.cmuagab.sheepwars.handler.Team;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak extends SheepListener {
    public BlockBreak(final SheepWarsPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onBlockBreak(final BlockBreakEvent event) {
        if (!Step.isStep(Step.LOBBY) && Team.getPlayerTeam(event.getPlayer()) != Team.SPEC) {
            event.getBlock().setType(Material.AIR);
        }
        event.setCancelled(true);
    }
}
