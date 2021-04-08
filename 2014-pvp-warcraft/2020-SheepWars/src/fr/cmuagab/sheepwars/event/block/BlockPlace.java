package fr.cmuagab.sheepwars.event.block;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.event.SheepListener;
import fr.cmuagab.sheepwars.handler.Kit;
import fr.cmuagab.sheepwars.handler.Step;
import fr.cmuagab.sheepwars.handler.Team;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace extends SheepListener {
    public BlockPlace(final SheepWarsPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent event) {
        if (Step.isStep(Step.LOBBY) || Team.getPlayerTeam(event.getPlayer()) == Team.SPEC || Kit.getPlayerKit(event.getPlayer()) != Kit.BUILDER) {
            event.setCancelled(true);
        }
    }
}
