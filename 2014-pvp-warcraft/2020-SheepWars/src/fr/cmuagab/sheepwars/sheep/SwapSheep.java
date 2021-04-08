package fr.cmuagab.sheepwars.sheep;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.entity.CustomSheep;
import fr.cmuagab.sheepwars.handler.Sheep.SheepAction;
import fr.cmuagab.sheepwars.handler.Team;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class SwapSheep implements SheepAction {

    @Override
    public void onSpawn(final Player player, final CustomSheep customSheep, final Sheep sheep) {}

    @Override
    public boolean onTicking(final Player player, final long ticks, final CustomSheep customSheep, final Sheep sheep) {
        if (!sheep.hasMetadata("onGround")) {
            sheep.setMetadata("onGround", new FixedMetadataValue(SheepWarsPlugin.i, true));
            final Team playerTeam = Team.getPlayerTeam(player);
            final Location location = player.getLocation();
            int distance = Integer.MAX_VALUE;
            Player nearest = null;
            Location lastLocation = null;
            for (final Player online : Bukkit.getOnlinePlayers()) {
                final Team team = Team.getPlayerTeam(online);
                if (online != player && team != Team.SPEC && team != playerTeam) {
                    final int dist = (int) (lastLocation = online.getLocation()).distanceSquared(location);
                    if (dist < distance) {
                        distance = dist;
                        nearest = online;
                    }
                }
            }
            if (nearest == null) {
                player.sendMessage(SheepWarsPlugin.prefix + ChatColor.RED + "Aucun joueur n'a été trouvé.");
                return true;
            } else {
                final Player nearestFinal = nearest;
                player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 120, 1));
                nearest.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 120, 1));
                player.playSound(location, Sound.PORTAL_TRAVEL, 1, 1);
                nearest.playSound(lastLocation, Sound.PORTAL_TRAVEL, 1, 1);
                new BukkitRunnable() {
                    private int seconds = 4;

                    @Override
                    public void run() {
                        if (this.seconds == 0) {
                            this.cancel();
                            final Location location = player.getLocation();
                            player.sendMessage(SheepWarsPlugin.prefix + ChatColor.YELLOW + ChatColor.MAGIC + "|" + ChatColor.AQUA + ChatColor.MAGIC + "|" + ChatColor.GREEN + ChatColor.MAGIC + "|" + ChatColor.GRAY + " Vous venez d'être swapé " + ChatColor.YELLOW + ChatColor.MAGIC + "|" + ChatColor.AQUA + ChatColor.MAGIC + "|" + ChatColor.GREEN + ChatColor.MAGIC + "|");
                            nearestFinal.sendMessage(SheepWarsPlugin.prefix + ChatColor.YELLOW + ChatColor.MAGIC + "|" + ChatColor.AQUA + ChatColor.MAGIC + "|" + ChatColor.GREEN + ChatColor.MAGIC + "|" + ChatColor.GRAY + " Vous venez d'être swapé " + ChatColor.YELLOW + ChatColor.MAGIC + "|" + ChatColor.AQUA + ChatColor.MAGIC + "|" + ChatColor.GREEN + ChatColor.MAGIC + "|");
                            player.setFallDistance(0);
                            player.teleport(nearestFinal.getLocation());
                            nearestFinal.setFallDistance(0);
                            nearestFinal.teleport(location);
                            sheep.remove();
                            return;
                        } else if (this.seconds == 1) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1));
                            nearestFinal.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1));
                        }
                        this.seconds--;
                    }
                }.runTaskTimer(SheepWarsPlugin.i, 0, 20);
            }
        }
        return false;
    }

    @Override
    public void onFinish(final Player player, final CustomSheep customSheep, final org.bukkit.entity.Sheep sheep, final boolean death) {}
}
