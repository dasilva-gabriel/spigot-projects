package fr.cmuagab.sheepwars.sheep;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.entity.CustomSheep;
import fr.cmuagab.sheepwars.entity.EntityMeteor;
import fr.cmuagab.sheepwars.handler.Sheep.SheepAction;
import fr.cmuagab.sheepwars.handler.Team;
import fr.cmuagab.sheepwars.util.EntityUtils;
import fr.cmuagab.sheepwars.util.MathUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

public class IntergalacticSheep implements SheepAction {
    private final int RADIUS = 5;

    @Override
    public void onSpawn(final Player player, final CustomSheep customSheep, final Sheep sheep) {
        Bukkit.broadcastMessage(SheepWarsPlugin.prefix + Team.getPlayerTeam(player).getColor() + player.getName() + " a lancé " + ChatColor.YELLOW + ChatColor.MAGIC + "|" + ChatColor.AQUA + ChatColor.MAGIC + "|" + ChatColor.GREEN + ChatColor.MAGIC + "|" + ChatColor.RED + ChatColor.MAGIC + "|" + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "|" + ChatColor.DARK_BLUE + ChatColor.BOLD + " Mouton Intergalactique " + ChatColor.YELLOW + ChatColor.MAGIC + "|" + ChatColor.AQUA + ChatColor.MAGIC + "|" + ChatColor.GREEN + ChatColor.MAGIC + "|" + ChatColor.RED + ChatColor.MAGIC + "|" + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "|");
        for (final Player online : Bukkit.getOnlinePlayers()) {
            online.playSound(online.getLocation(), Sound.WITHER_SPAWN, 1, 0.5F);
        }
    }

    @Override
    public boolean onTicking(final Player player, final long ticks, final CustomSheep customSheep, final Sheep sheep) {
        if (!sheep.hasMetadata("onGround")) {
            final World world = sheep.getWorld();
            final Location location = sheep.getLocation();
            sheep.setNoDamageTicks(Integer.MAX_VALUE);
            sheep.setMetadata("onGround", new FixedMetadataValue(SheepWarsPlugin.i, true));
            world.setTime(20000);
            new BukkitRunnable() {
                private int seconds = MathUtils.random(4, 10) + 1;

                @Override
                public void run() {
                    if (this.seconds == 0) {
                        this.cancel();
                        sheep.remove();
                        world.setTime(6000);
                        return;
                    } else if (this.seconds > 2) {
                        final EntityMeteor meteor = new EntityMeteor(((CraftWorld) location.getWorld()).getHandle(), player);
                        meteor.setPosition(location.getX() + MathUtils.random(-20, 20), location.getY() + 40, location.getZ() + MathUtils.random(-20, 20));
                        ((CraftWorld) location.getWorld()).getHandle().addEntity(meteor);
                        final Fireball fireball = (Fireball) meteor.getBukkitEntity();
                        fireball.setBounce(false);
                        fireball.setIsIncendiary(true);
                        EntityUtils.moveToward(fireball, location.clone().add(MathUtils.random(-IntergalacticSheep.this.RADIUS, IntergalacticSheep.this.RADIUS), 0, MathUtils.random(-IntergalacticSheep.this.RADIUS, IntergalacticSheep.this.RADIUS)), 1);
                    }
                    this.seconds--;
                }
            }.runTaskTimer(SheepWarsPlugin.i, 0, 20);
        }
        return false;
    }

    @Override
    public void onFinish(final Player player, final CustomSheep customSheep, final org.bukkit.entity.Sheep sheep, final boolean death) {}
}
