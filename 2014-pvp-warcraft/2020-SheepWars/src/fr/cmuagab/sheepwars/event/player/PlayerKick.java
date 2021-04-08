package fr.cmuagab.sheepwars.event.player;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.event.SheepListener;
import fr.cmuagab.sheepwars.scheduler.BeginCountdown;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerKickEvent;

public class PlayerKick extends SheepListener {
    public PlayerKick(final SheepWarsPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onPlayerKick(final PlayerKickEvent event) {
        event.setLeaveMessage(null);
        final Player player = event.getPlayer();
        BeginCountdown.resetPlayer(player, GameMode.ADVENTURE);
        this.plugin.removePlayer(player);
    }
}
