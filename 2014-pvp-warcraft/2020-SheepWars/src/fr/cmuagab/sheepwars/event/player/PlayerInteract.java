package fr.cmuagab.sheepwars.event.player;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.event.SheepListener;
import fr.cmuagab.sheepwars.handler.Kit;
import fr.cmuagab.sheepwars.handler.PlayerData;
import fr.cmuagab.sheepwars.handler.Sheep;
import fr.cmuagab.sheepwars.handler.Step;
import fr.cmuagab.sheepwars.handler.Team;
import fr.cmuagab.sheepwars.handler.Title;
import fr.cmuagab.sheepwars.packet.PacketPlayOutActionBar;
import fr.cmuagab.sheepwars.util.ItemBuilder;
import fr.cmuagab.sheepwars.util.MathUtils;
import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.PlayerConnection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.spigotmc.ProtocolInjector;

public class PlayerInteract extends SheepListener {
    public PlayerInteract(final SheepWarsPlugin plugin) {
        super(plugin);
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        if (Step.isStep(Step.LOBBY) || Team.getPlayerTeam(player) == Team.SPEC) {
            event.setCancelled(true);
        }
        if (event.getAction().name().contains("RIGHT")) {
            if (!Step.isStep(Step.LOBBY)) {
                final Block block = event.getClickedBlock();
                if (event.getAction() == Action.RIGHT_CLICK_BLOCK && block.getType() == Material.TNT && Kit.getPlayerKit(player) == Kit.BUILDER) {
                    block.setType(Material.AIR);
                    block.getWorld().spawn(block.getLocation(), TNTPrimed.class);
                } else if (event.hasItem()) {
                    final ItemStack item = event.getItem();
                    if (item.getType() == Material.BOW || item.getType() != Material.WOOL) { return; }
                    event.setCancelled(true);
                    if (Team.getPlayerTeam(player) != Team.SPEC) {
                        for (final Sheep sheep : Sheep.values()) {
                            if (sheep.getIcon().isSimilar(item)) {
                                if (item.getAmount() == 1) {
                                    player.setItemInHand(null);
                                } else {
                                    item.setAmount(item.getAmount() - 1);
                                    player.setItemInHand(item);
                                }
                                player.updateInventory();
                                final Location playerLocation = player.getLocation();
                                final Location location = sheep.isFriendly() ? playerLocation.toVector().add(playerLocation.getDirection().multiply(2.0D)).toLocation(player.getWorld()) : player.getLocation();
                                final org.bukkit.entity.Sheep sheepEntity = sheep.spawnSheep(location, player);
                                if (Kit.getPlayerKit(player) == Kit.ARMORED_SHEEP) {
                                    sheepEntity.setMaxHealth(17.6D);
                                    sheepEntity.setHealth(17.6D);
                                }
                                sheepEntity.setCustomName(sheep.getName());
                                if (!sheep.isFriendly()) {
                                    sheepEntity.setVelocity(playerLocation.getDirection().multiply(4.4D));
                                }
                            }
                        }
                    }
                }
            } else {
                event.setCancelled(true);
                if (event.hasItem()) {
                    final ItemStack item = event.getItem();
                    if (item.getType() == Material.NAME_TAG && item.hasItemMeta()) {
                        final PlayerData data = this.plugin.getData(player);
                        final Kit playerKit = Kit.getPlayerKit(player);
                        final Inventory inventory = Bukkit.createInventory(player, 9, "Séléction : " + ChatColor.YELLOW + (playerKit == null ? "Aucune" : playerKit.getName()));
                        for (final Kit kit : Kit.values()) {
                            final ItemBuilder builder = new ItemBuilder(kit.getMaterial(), kit.getDurability()).setTitle(kit.getName()).addLores(kit.getDescription().split("\n"));
                            if (kit.getWins() > data.getWins() && !player.hasPermission("games.vip")) {
                                builder.addLores("", ChatColor.RED + "Pour débloquer ce kit il vous faut", ChatColor.RED + "" + kit.getWins() + " victoire" + (kit.getWins() > 1 ? "s" : "") + " ou être " + ChatColor.GOLD + "VIP");
                            }
                            inventory.addItem(builder.build());
                        }
                        inventory.setItem(8, new ItemBuilder(Material.GOLD_INGOT).setTitle(ChatColor.GOLD + "Vos statistiques :").addLores(ChatColor.GRAY + "- " + ChatColor.AQUA + data.getKills() + ChatColor.GRAY + " kill" + (data.getKills() > 1 ? "s" : ""), ChatColor.GRAY + "- " + ChatColor.AQUA + data.getDeaths() + ChatColor.GRAY + " mort" + (data.getDeaths() > 1 ? "s" : ""), ChatColor.GRAY + "- " + ChatColor.AQUA + data.getWins() + ChatColor.GRAY + " victoire" + (data.getWins() > 1 ? "s" : ""), ChatColor.GRAY + "- " + ChatColor.AQUA + data.getGames() + ChatColor.GRAY + " partie" + (data.getGames() > 1 ? "s" : "") + " jouée" + (data.getGames() > 1 ? "s" : "")).build());
                        player.openInventory(inventory);
                    } else if (item.getType() == Material.INK_SACK && item.hasItemMeta()) {
                        for (final Team team : Team.values()) {
                            if (item.isSimilar(team.getIcon())) {
                                final String displayName = team.getDisplayName();
                                final Team playerTeam = Team.getPlayerTeam(player);
                                if (playerTeam != team) {
                                    if (Bukkit.getOnlinePlayers().length > 1 && team.getOnlinePlayers().size() >= MathUtils.ceil(Bukkit.getOnlinePlayers().length / 2)) {
                                        player.sendMessage(SheepWarsPlugin.prefix + ChatColor.GRAY + "Impossible de rejoindre cette équipe, trop de joueurs !");
                                    } else {
                                        if (playerTeam != Team.SPEC) {
                                            playerTeam.removePlayer(player);
                                        }
                                        team.addPlayer(player);
                                        if (Title.isPlayerRightVersion(player)) {
                                            final PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
                                            final IChatBaseComponent header = ChatSerializer.a("{\"color\": \"" + ChatColor.DARK_AQUA.name().toLowerCase() + "\", \"text\": \"" + ChatColor.GOLD + ChatColor.BOLD + "War Of Sheeps\"}");
                                            final IChatBaseComponent footer = ChatSerializer.a("{\"color\": \"" + ChatColor.GRAY.name().toLowerCase() + "\", \"text\": \"" + ChatColor.GRAY + "Equipe séléctionné : " + team.getColor() + team.getDisplayName() + "\"}");
                                            connection.sendPacket(new ProtocolInjector.PacketTabHeader(header, footer));
                                            final PacketPlayOutActionBar customPacket = new PacketPlayOutActionBar(ChatColor.GRAY + "Equipe séléctionné : " + team.getColor() + team.getDisplayName());
                                            customPacket.send(player);
                                        }
                                        player.sendMessage(SheepWarsPlugin.prefix + ChatColor.GRAY + "Vous rejoignez l'équipe " + team.getColor() + displayName);
                                    }
                                }
                                break;
                            }
                        }
                        player.updateInventory();
                        return;
                    }
                }
            }
        }
    }
}
