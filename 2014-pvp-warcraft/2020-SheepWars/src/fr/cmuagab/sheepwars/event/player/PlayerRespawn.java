package fr.cmuagab.sheepwars.event.player;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.event.SheepListener;
import fr.cmuagab.sheepwars.handler.Step;
import fr.cmuagab.sheepwars.handler.Team;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerRespawn extends SheepListener {
    public PlayerRespawn(final SheepWarsPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onPlayerRespawn(final PlayerRespawnEvent event) {
        final Player player = event.getPlayer();
        final Team playerTeam = Team.getPlayerTeam(player);
        if (Step.isStep(Step.LOBBY) || playerTeam != Team.SPEC) {
            event.setRespawnLocation(this.plugin.lobbyLocation);
        } else {
            final Location spawn = Team.SPEC.getNextSpawn();
            event.setRespawnLocation(spawn == null ? this.plugin.lobbyLocation : spawn);
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.setFlying(true);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
                }
            }.runTaskLater(this.plugin, 1);
        }
    }
}
