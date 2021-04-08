package fr.cmuagab.sheepwars.event.player;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.event.SheepListener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;

public class PlayerAchievementAwarded extends SheepListener {
    public PlayerAchievementAwarded(final SheepWarsPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onPlayerAchievementArwarded(final PlayerAchievementAwardedEvent event) {
        event.setCancelled(true);
    }
}
