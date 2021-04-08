package fr.cmuagab.sheepwars.scheduler;

import fr.cmuagab.sheepwars.SheepWarsPlugin;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class HubTeleportation extends BukkitRunnable {
    private int timeUntilTeleporation = 10;
    private final SheepWarsPlugin plugin;
    private final Player player;

    public HubTeleportation(final SheepWarsPlugin plugin, final Player player) {
        this.plugin = plugin;
        this.player = player;
        this.runTaskTimer(plugin, 0l, 20l);
    }

    @Override
    public void run() {
        if (this.timeUntilTeleporation == 0) {
            this.cancel();
            this.plugin.teleportToLobby(this.player);
            return;
        }
        this.timeUntilTeleporation--;
    }
}
