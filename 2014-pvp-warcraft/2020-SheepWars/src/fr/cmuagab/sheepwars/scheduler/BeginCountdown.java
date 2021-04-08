package fr.cmuagab.sheepwars.scheduler;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.handler.Kit;
import fr.cmuagab.sheepwars.handler.PlayerData;
import fr.cmuagab.sheepwars.handler.Step;
import fr.cmuagab.sheepwars.handler.Team;
import fr.cmuagab.sheepwars.handler.Title;
import fr.cmuagab.sheepwars.util.ItemBuilder;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class BeginCountdown extends BukkitRunnable {
    public static boolean started = false;
    public static int timeUntilStart = 5;
    private final SheepWarsPlugin plugin;

    public BeginCountdown(final SheepWarsPlugin plugin) {
        this.plugin = plugin;
        this.runTaskTimer(plugin, 0, 20);
        BeginCountdown.started = true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void run() {
        if (BeginCountdown.timeUntilStart == 0) {
            this.cancel();
            if (Bukkit.getOnlinePlayers().length < 1) {
                Bukkit.broadcastMessage(SheepWarsPlugin.prefix + ChatColor.RED + "Il n'y a pas assez de joueurs !");
                BeginCountdown.timeUntilStart = 120;
                BeginCountdown.started = false;
            } else {
                new Title(ChatColor.AQUA + "La partie commence !").broadcast();
                Bukkit.broadcastMessage(SheepWarsPlugin.prefix + ChatColor.AQUA + "La partie commence, " + ChatColor.GOLD + "un mouton toutes les " + ChatColor.GREEN + "40 " + ChatColor.GOLD + "secondes.");
                final Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
                Objective obj = board.getObjective("health");
                if (obj != null) {
                    obj.unregister();
                }
                obj = board.registerNewObjective("health", "health");
                obj.setDisplayName(ChatColor.RED + "♥");
                obj.setDisplaySlot(DisplaySlot.BELOW_NAME);
                for (final Player player : Bukkit.getOnlinePlayers()) {
                    Team team = Team.getPlayerTeam(player);
                    if (team == Team.SPEC) {
                        team = Team.getRandomTeam();
                        team.addPlayer(player);
                    }
                    BeginCountdown.resetPlayer(player, GameMode.SURVIVAL);
                    final Color color = team.getLeatherColor();
                    final Kit kit = Kit.getPlayerKit(player);
                    player.getInventory().addItem(new ItemBuilder(Material.BOW).addEnchantment(Enchantment.ARROW_INFINITE, 1).build());
                    player.getInventory().addItem(new ItemStack(kit == Kit.BETTER_SWORD ? Material.WOOD_SWORD : Material.STONE_SWORD));
                    if (kit == Kit.BUILDER) {
                        player.getInventory().addItem(new ItemStack(Material.TNT));
                        player.getInventory().addItem(new ItemStack(Material.SAND, 5, (byte) 1));
                    }
                    player.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET).setLeatherColor(color).addEnchantment(Enchantment.PROTECTION_PROJECTILE, 2).build());
                    player.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setLeatherColor(color).addEnchantment(Enchantment.PROTECTION_PROJECTILE, 2).build());
                    player.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).setLeatherColor(color).addEnchantment(Enchantment.PROTECTION_PROJECTILE, 2).build());
                    if (kit != Kit.MOBILITY) {
                        player.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setLeatherColor(color).addEnchantment(Enchantment.PROTECTION_PROJECTILE, 2).build());
                    } else {
                        player.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setLeatherColor(color).addEnchantment(Enchantment.PROTECTION_PROJECTILE, 2).addEnchantment(Enchantment.PROTECTION_FALL, 1).build());
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
                    }
                    player.getInventory().setItem(9, new ItemStack(Material.ARROW));
                    if (kit == Kit.MORE_HEALTH) {
                        player.setMaxHealth(22.0D);
                        player.setHealthScaled(false);
                        player.setHealth(22.0D);
                    }
                    obj.getScore(player).setScore(kit == Kit.MOBILITY ? 22 : 20);
                    final PlayerData data = this.plugin.getData(player);
                    data.setGames(data.getGames() + 1);
                    player.teleport(team.getNextSpawn());
                }
                final Objective kills = board.getObjective("kills");
                if (kills != null) {
                    kills.unregister();
                }
                board.registerNewObjective("kills", "playerKillCount").setDisplaySlot(DisplaySlot.PLAYER_LIST);
                Step.setCurrentStep(Step.IN_GAME);
                new GameTask(this.plugin);
            }
            return;
        }
        final int remainingMins = BeginCountdown.timeUntilStart / 60 % 60;
        final int remainingSecs = BeginCountdown.timeUntilStart % 60;
        if (BeginCountdown.timeUntilStart % 30 == 0 || remainingMins == 0 && (remainingSecs % 10 == 0 || remainingSecs < 10)) {
            if (BeginCountdown.timeUntilStart % 30 == 0 || BeginCountdown.timeUntilStart == 10 || BeginCountdown.timeUntilStart <= 5) {
                final Title title = new Title("");
                title.setSubtitle(ChatColor.GOLD + "Démarrage du jeu dans " + ChatColor.YELLOW + (remainingMins > 0 ? remainingMins + " minute" + (remainingMins > 1 ? "s" : "") : "") + (remainingSecs > 0 ? (remainingMins > 0 ? " " : "") + remainingSecs + " seconde" + (remainingSecs > 1 ? "s" : "") : ""));
                for (final Player player : Bukkit.getOnlinePlayers()) {
                    if (Title.isPlayerRightVersion(player)) {
                        title.send(player);
                    }
                }
            }
            Bukkit.broadcastMessage(SheepWarsPlugin.prefix + ChatColor.GOLD + "Démarrage du jeu dans " + ChatColor.YELLOW + (remainingMins > 0 ? remainingMins + " minute" + (remainingMins > 1 ? "s" : "") : "") + (remainingSecs > 0 ? (remainingMins > 0 ? " " : "") + remainingSecs + " seconde" + (remainingSecs > 1 ? "s" : "") : "") + ".");
            if (remainingMins == 0 && remainingSecs <= 10) {
                for (final Player player : Bukkit.getOnlinePlayers()) {
                    player.playSound(player.getLocation(), Sound.CLICK, 1f, 1f);
                }
            }
        }
        BeginCountdown.timeUntilStart--;
    }

    public static void resetPlayer(final Player player, final GameMode gameMode) {
        player.setFireTicks(0);
        player.setMaxHealth(20.0D);
        player.setHealthScaled(true);
        player.setHealth(20.0D);
        player.setFoodLevel(20);
        player.setExhaustion(5);
        player.setFallDistance(0);
        player.setExp(0);
        player.setLevel(0);
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setGameMode(gameMode);
        player.closeInventory();
        for (final PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
    }
}
