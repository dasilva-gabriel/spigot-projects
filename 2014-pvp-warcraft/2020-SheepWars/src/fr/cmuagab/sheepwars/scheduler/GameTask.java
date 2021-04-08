package fr.cmuagab.sheepwars.scheduler;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.handler.Sheep;
import fr.cmuagab.sheepwars.handler.Step;
import fr.cmuagab.sheepwars.handler.Title;
import fr.cmuagab.sheepwars.packet.PacketPlayOutActionBar;
import fr.cmuagab.sheepwars.util.MathUtils;
import fr.cmuagab.sheepwars.util.ParticleEffect;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;

public class GameTask extends BukkitRunnable {
    public static int remainingDurationInSecs = 1200;
    private final SheepWarsPlugin plugin;
    private final Objective objective;

    public GameTask(final SheepWarsPlugin plugin) {
        this.plugin = plugin;
        this.objective = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("teams");
        this.runTaskTimer(plugin, 0, 20);
    }

    @Override
    public void run() {
        if (GameTask.remainingDurationInSecs == 0 || !Step.isStep(Step.IN_GAME)) {
            this.cancel();
            if (Step.isStep(Step.IN_GAME)) {
                Bukkit.broadcastMessage(SheepWarsPlugin.prefix + ChatColor.GOLD + ChatColor.BOLD + "Le temps est écoulé " + ChatColor.YELLOW + ChatColor.MAGIC + "|" + ChatColor.AQUA + ChatColor.MAGIC + "|" + ChatColor.GREEN + ChatColor.MAGIC + "|" + ChatColor.RED + ChatColor.MAGIC + "|" + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "|" + ChatColor.AQUA + ChatColor.BOLD + " Egalité " + ChatColor.YELLOW + ChatColor.MAGIC + "|" + ChatColor.AQUA + ChatColor.MAGIC + "|" + ChatColor.GREEN + ChatColor.MAGIC + "|" + ChatColor.RED + ChatColor.MAGIC + "|" + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "|");
            }
            this.plugin.stopGame(null);
            return;
        }
        final int remainingMins = GameTask.remainingDurationInSecs / 60 % 60;
        final int remainingSecs = GameTask.remainingDurationInSecs % 60;
        this.objective.setDisplayName(ChatColor.DARK_GRAY + "-" + ChatColor.YELLOW + "WarOfSheeps " + ChatColor.GREEN + (remainingMins < 10 ? "0" : "") + remainingMins + ":" + (remainingSecs < 10 ? "0" : "") + remainingSecs + ChatColor.DARK_GRAY + "-");
        if (remainingMins == 19 && remainingSecs == 40) {
            new Title("").setSubtitle(ChatColor.DARK_PURPLE + "" + ChatColor.MAGIC + "||" + ChatColor.GREEN + "Laines Bonus" + ChatColor.DARK_PURPLE + ChatColor.MAGIC + "||").broadcast();
            Bukkit.broadcastMessage(SheepWarsPlugin.prefix + ChatColor.DARK_PURPLE + ChatColor.MAGIC + "||" + ChatColor.GREEN + "Laines Bonus" + ChatColor.DARK_PURPLE + ChatColor.MAGIC + "|| " + ChatColor.GOLD + "tirez sur les blocs magiques !");
            new BukkitRunnable() {
                @Override
                public void run() {
                    final Location magicBlockLocation = SheepWarsPlugin.i.boosters.get(MathUtils.random.nextInt(SheepWarsPlugin.i.boosters.size()));
                    final Block magicBlock = magicBlockLocation.getBlock();
                    magicBlock.setType(Material.WOOL);
                    for (final Player online : Bukkit.getOnlinePlayers()) {
                        online.playSound(online.getLocation(), Sound.LEVEL_UP, 1, 1);
                    }
                    new BukkitRunnable() {
                        private int seconds = 10;

                        @SuppressWarnings("deprecation")
                        @Override
                        public void run() {
                            if (this.seconds == 0 || magicBlock.getType() == Material.AIR) {
                                this.cancel();
                                magicBlock.setType(Material.AIR);
                                return;
                            }
                            ParticleEffect.RED_DUST.display(magicBlockLocation, 0.5F, 0.5F, 0.5F, 1.0F, 10);
                            magicBlock.setData(DyeColor.values()[MathUtils.random.nextInt(DyeColor.values().length)].getWoolData());
                            this.seconds--;
                        }
                    }.runTaskTimer(GameTask.this.plugin, 0, 20);
                }
            }.runTaskTimer(this.plugin, 0, 400);
        } else if (GameTask.remainingDurationInSecs == 1200) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    for (final Player player : Bukkit.getOnlinePlayers()) {
                        final Sheep sheep = Sheep.giveRandomSheep(player);
                        if (Title.isPlayerRightVersion(player)) {
                            final PacketPlayOutActionBar customPacket = new PacketPlayOutActionBar(sheep.getName());
                            customPacket.send(player);
                        }
                    }
                }
            }.runTaskTimer(SheepWarsPlugin.i, 0, 800);
        } else if (remainingMins == 15 && remainingSecs == 0) {
            final Title title = new Title("");
            title.setSubtitle(ChatColor.DARK_PURPLE + "" + ChatColor.MAGIC + "||" + ChatColor.WHITE + "A l'arbodage" + ChatColor.DARK_PURPLE + ChatColor.MAGIC + "||");
            for (final Player player : Bukkit.getOnlinePlayers()) {
                title.send(player);
            }
            Bukkit.broadcastMessage(SheepWarsPlugin.prefix + ChatColor.DARK_PURPLE + ChatColor.MAGIC + "||" + ChatColor.WHITE + "A l'arbodage" + ChatColor.DARK_PURPLE + ChatColor.MAGIC + "|| " + ChatColor.GOLD + "un mouton d'abordage chaque minute !");
            new BukkitRunnable() {
                @Override
                public void run() {
                    final PacketPlayOutActionBar actionBar = new PacketPlayOutActionBar(Sheep.BOARDING.getName());
                    for (final Player player : Bukkit.getOnlinePlayers()) {
                        Sheep.giveSheep(player, Sheep.BOARDING);
                        actionBar.send(player);
                    }
                }
            }.runTaskTimer(SheepWarsPlugin.i, 0, 1200);
        }
        GameTask.remainingDurationInSecs--;
    }
}
