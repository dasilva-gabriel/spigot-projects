package fr.cmuagab.sheepwars.event.player;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.event.SheepListener;
import fr.cmuagab.sheepwars.scheduler.BeginCountdown;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit extends SheepListener {
    public PlayerQuit(final SheepWarsPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {
        event.setQuitMessage(null);
        final Player player = event.getPlayer();
        BeginCountdown.resetPlayer(player, GameMode.ADVENTURE);
        this.plugin.removePlayer(player);
    }
}
