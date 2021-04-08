package fr.cmuagab.sheepwars.event.player;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.event.SheepListener;
import fr.cmuagab.sheepwars.handler.Step;
import fr.cmuagab.sheepwars.handler.Team;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChat extends SheepListener {
    public AsyncPlayerChat(final SheepWarsPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onPlayerChat(final AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        final Team playerTeam = Team.getPlayerTeam(player);
        event.setFormat((playerTeam != null ? playerTeam.getColor() : ChatColor.GRAY) + player.getName() + ChatColor.WHITE + ": " + event.getMessage());
        if (Step.isStep(Step.IN_GAME) && playerTeam == Team.SPEC) {
            for (final Player online : Bukkit.getOnlinePlayers()) {
                if (Team.getPlayerTeam(online) != Team.SPEC) {
                    event.getRecipients().remove(online);
                }
            }
        }
    }
}
