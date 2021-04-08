package fr.cmuagab.sheepwars.event.player;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.event.SheepListener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreprocess extends SheepListener {
    public PlayerCommandPreprocess(final SheepWarsPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onPlayerCommandPreprocess(final PlayerCommandPreprocessEvent event) {
        final Player player = event.getPlayer();
        if (player.isOp() && event.getMessage().split(" ")[0].contains("reload")) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "Cette fonctionnalité est désactivée par le plugin WarOfSheeps à cause de contraintes techniques (reset de map).");
        }
    }
}
