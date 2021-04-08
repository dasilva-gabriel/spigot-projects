package fr.cmuagab.sheepwars.event.player;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.event.SheepListener;
import fr.cmuagab.sheepwars.handler.PlayerData;
import fr.cmuagab.sheepwars.handler.Step;
import fr.cmuagab.sheepwars.handler.Team;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath extends SheepListener {
    public PlayerDeath(final SheepWarsPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onPlayerDeath(final PlayerDeathEvent event) {
        event.getDrops().clear();
        event.setDroppedExp(0);
        final Player player = event.getEntity();
        final Team playerTeam = Team.getPlayerTeam(player);
        if (Step.isStep(Step.LOBBY) || playerTeam == Team.SPEC) {
            event.setDeathMessage(null);
        } else {
            final Player killer = player.getKiller();
            final Team killerTeam = Team.getPlayerTeam(killer);
            if (killer != null) {
                killer.playSound(player.getLocation(), Sound.VILLAGER_HIT, 1, 1);
                final PlayerData data = this.plugin.getData(killer);
                data.setKills(data.getKills() + 1);
            }
            event.setDeathMessage(SheepWarsPlugin.prefix + (playerTeam.getColor() + player.getName() + ChatColor.GRAY + " " + (killer == null ? "a succombé." : "a été tué par " + killerTeam.getColor() + killer.getName())));
            player.sendMessage(SheepWarsPlugin.prefix + ChatColor.GRAY + "Vous êtes maintenant un " + ChatColor.WHITE + "Fantôme" + ChatColor.GRAY + ", pour quitter " + ChatColor.YELLOW + "/hub");
            player.sendMessage(SheepWarsPlugin.prefix + ChatColor.GRAY + "Seul les " + ChatColor.WHITE + "Fantômes" + ChatColor.GRAY + " peuvent vous entendre parler !");
            this.plugin.setSpectator(event.getEntity(), true);
        }
    }
}
