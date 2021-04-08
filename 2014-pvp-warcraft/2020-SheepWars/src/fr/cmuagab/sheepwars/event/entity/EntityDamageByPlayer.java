package fr.cmuagab.sheepwars.event.entity;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.event.SheepListener;
import fr.cmuagab.sheepwars.handler.Step;
import fr.cmuagab.sheepwars.handler.Team;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByPlayer extends SheepListener {
    public EntityDamageByPlayer(final SheepWarsPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onEntityDamageByPlayer(final EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && (Step.isStep(Step.LOBBY) || Team.getPlayerTeam((Player) event.getDamager()) == Team.SPEC)) {
            event.setCancelled(true);
        }
    }
}
