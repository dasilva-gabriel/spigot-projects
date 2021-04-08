package fr.cmuagab.sheepwars.event.player;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.event.SheepListener;
import fr.cmuagab.sheepwars.handler.Step;
import fr.cmuagab.sheepwars.handler.Team;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerMove extends SheepListener {
    public PlayerMove(final SheepWarsPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        final Location from = event.getFrom();
        final Location to = event.getTo();
        if (to.getBlockY() <= 0 && (from.getBlockX() != to.getBlockX() || from.getBlockY() != to.getBlockY() || from.getBlockZ() != to.getBlockZ())) {
            final Team team = Team.getPlayerTeam(player);
            if (Step.isStep(Step.LOBBY) || team == Team.SPEC) {
                player.teleport(Step.isStep(Step.LOBBY) || team.getSpawns().size() == 0 ? this.plugin.lobbyLocation : team.getNextSpawn());
            } else {
                if (!player.hasMetadata("falling") || System.currentTimeMillis() - player.getMetadata("falling").get(0).asLong() >= 2000) {
                    if (!player.hasMetadata("falling")) {
                        player.playSound(to, Sound.ITEM_BREAK, 1, 1);
                    }
                    player.setMetadata("falling", new FixedMetadataValue(this.plugin, System.currentTimeMillis()));
                    player.sendMessage(SheepWarsPlugin.prefix + ChatColor.RED + "Vous êtes en dehors de la zone de combat !");
                }
                player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 1));
            }
        }
    }
}
