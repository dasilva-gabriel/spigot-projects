package fr.cmuagab.sheepwars.event.inventory;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.event.SheepListener;
import fr.cmuagab.sheepwars.handler.Kit;
import fr.cmuagab.sheepwars.handler.PlayerData;
import fr.cmuagab.sheepwars.handler.Step;
import fr.cmuagab.sheepwars.handler.Title;
import fr.cmuagab.sheepwars.packet.PacketPlayOutActionBar;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClick extends SheepListener {
    public InventoryClick(final SheepWarsPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        final ItemStack current = event.getCurrentItem();
        if (Step.isStep(Step.LOBBY)) {
            event.setCancelled(true);
            if (event.getInventory().getTitle().contains("Séléction :") && event.getSlot() == event.getRawSlot() && current != null && current.hasItemMeta()) {
                final Player player = (Player) event.getWhoClicked();
                final PlayerData data = this.plugin.getData(player);
                for (final Kit kit : Kit.values()) {
                    if (kit.getName().equals(current.getItemMeta().getDisplayName())) {
                        if (kit.getWins() > data.getWins() && !player.hasPermission("games.vip")) {
                            final int diff = kit.getWins() - data.getWins();
                            player.sendMessage(SheepWarsPlugin.prefix + ChatColor.GRAY + "Vous devez gagner " + diff + " partie" + (diff > 1 ? "s" : "") + " ou être " + ChatColor.GOLD + "VIP" + ChatColor.GRAY + " pour utiliser ce kit !");
                        } else {
                            Kit.setPlayerKit(player, kit);
                            if (Title.isPlayerRightVersion(player)) {
                                final PacketPlayOutActionBar customPacket = new PacketPlayOutActionBar(ChatColor.GRAY + "Kit séléctionné : " + ChatColor.GOLD + kit.getName());
                                customPacket.send(player);
                            }
                            player.sendMessage(SheepWarsPlugin.prefix + ChatColor.GRAY + "Kit séléctionné : " + ChatColor.GOLD + kit.getName());
                        }
                        player.closeInventory();
                        break;
                    }
                }
            }
        }
    }
}
