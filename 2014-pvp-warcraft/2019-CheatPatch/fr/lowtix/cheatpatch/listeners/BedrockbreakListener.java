package fr.lowtix.cheatpatch.listeners;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FPlayers;

public class BedrockbreakListener implements Listener {
	
	private HashMap<String, BlockFace> faces = new HashMap<String, BlockFace>();
	
	@EventHandler
	public void onBreak(BlockDamageEvent e) {
		
		if(!e.isCancelled() && e.getPlayer().getWorld().equals(Bukkit.getWorld("world"))) {
			
			Block b = e.getBlock();
			Player p = e.getPlayer();
			
			if(Board.getInstance().getFactionAt(new FLocation(b.getLocation())).getId().equals("0") || Board.getInstance().getFactionAt(new FLocation(b.getLocation())).getFPlayers().contains(FPlayers.getInstance().getByPlayer(p))) {
				if(b.getType().equals(Material.BEDROCK) && b.getLocation().getBlockY() != 0) {
					if(p.getItemInHand() != null) {
						
						ItemStack it = p.getItemInHand();
						
						if(b != null && it.hasItemMeta() && it.getItemMeta().hasEnchants()) {
							
								
							if(it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lPelleteuse")) {
								
								b.setType(Material.AIR);
								p.playSound(b.getLocation(), Sound.DIG_STONE, 1.0F, 1.0F);
								it.setDurability((short) (e.getItemInHand().getDurability() + 1));
								
							} else if(it.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
								
								b.setType(Material.AIR);
								p.playSound(b.getLocation(), Sound.DIG_STONE, 1.0F, 1.0F);
								it.setDurability((short) (e.getItemInHand().getDurability() + 4));
								
							}
							
							if(it.getDurability() < 0) {
								it.setType(Material.AIR);
							}
							
						}
					}
				}
			}
			
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		

		if(!e.isCancelled() && e.getPlayer().getWorld().equals(Bukkit.getWorld("world"))) {
			
			Block b = e.getBlock();
			Player p = e.getPlayer();
			
			if(Board.getInstance().getFactionAt(new FLocation(b.getLocation())).getId().equals("0") || Board.getInstance().getFactionAt(new FLocation(b.getLocation())).getFPlayers().contains(FPlayers.getInstance().getByPlayer(p))) {
				if(b.getLocation().getBlockY() >= 2) {
					if(p.getItemInHand() != null) {
						
						ItemStack it = p.getItemInHand();
						
						if(it.hasItemMeta() && it.getItemMeta() != null && it.getItemMeta().hasEnchants()) {
							
								
							if(it.getItemMeta().getDisplayName() != null && it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lBulldozer")) {
								
								BlockFace fc = getBlockFaceByPlayerName(p.getName());
								
								switch (fc) {
								
								case SOUTH:
								case NORTH:
									breakBlock(p, b.getRelative(BlockFace.UP), b.getType(), false);
									breakBlock(p, b.getRelative(BlockFace.DOWN), b.getType(), false);
									breakBlock(p, b.getRelative(BlockFace.WEST), b.getType(), false);
									breakBlock(p, b.getRelative(BlockFace.EAST), b.getType(), false);
									breakBlock(p, b.getRelative(BlockFace.WEST).getRelative(BlockFace.UP), b.getType(), false);
									breakBlock(p, b.getRelative(BlockFace.EAST).getRelative(BlockFace.UP), b.getType(), false);
									breakBlock(p, b.getRelative(BlockFace.WEST).getRelative(BlockFace.DOWN), b.getType(), false);
									breakBlock(p, b.getRelative(BlockFace.EAST).getRelative(BlockFace.DOWN), b.getType(), false);
									break;

								case WEST:
								case EAST:
									breakBlock(p, b.getRelative(BlockFace.UP), b.getType(), false);
									breakBlock(p, b.getRelative(BlockFace.DOWN), b.getType(), false);
									breakBlock(p, b.getRelative(BlockFace.NORTH), b.getType(), false);
									breakBlock(p, b.getRelative(BlockFace.SOUTH), b.getType(), false);
									breakBlock(p, b.getRelative(BlockFace.NORTH).getRelative(BlockFace.UP), b.getType(), false);
									breakBlock(p, b.getRelative(BlockFace.SOUTH).getRelative(BlockFace.UP), b.getType(), false);
									breakBlock(p, b.getRelative(BlockFace.NORTH).getRelative(BlockFace.DOWN), b.getType(), false);
									breakBlock(p, b.getRelative(BlockFace.SOUTH).getRelative(BlockFace.DOWN), b.getType(), false);
									break;
									
								case UP:
								case DOWN:
									breakBlock(p, b.getRelative(BlockFace.NORTH), b.getType(), false);
									breakBlock(p, b.getRelative(BlockFace.SOUTH), b.getType(), false);
									breakBlock(p, b.getRelative(BlockFace.EAST), b.getType(), false);
									breakBlock(p, b.getRelative(BlockFace.WEST), b.getType(), false);
									breakBlock(p, b.getRelative(BlockFace.NORTH).getRelative(BlockFace.WEST), b.getType(), false);
									breakBlock(p, b.getRelative(BlockFace.SOUTH).getRelative(BlockFace.WEST), b.getType(), false);
									breakBlock(p, b.getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST), b.getType(), false);
									breakBlock(p, b.getRelative(BlockFace.SOUTH).getRelative(BlockFace.EAST), b.getType(), false);
									
									
								default:
									break;
								}
								
								
							}
							
						}
					}
				}
			}
			
		}
		
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void saveBlockFace(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		BlockFace bf = event.getBlockFace();

		if (player != null && bf != null) {
			String name = player.getName();
			faces.put(name, bf);
		}
	}

	public BlockFace getBlockFaceByPlayerName(String name) {
		return faces.get(name);
	}
	
	public void breakBlock(Player p, Block b, Material type, boolean air) {
		if(!b.getType().equals(type)) {
			return;
		}
		p.playSound(b.getLocation(), Sound.DIG_STONE, 1.0F, 1.0F);
		if(air) {
			b.setType(Material.AIR);
		} else {
			b.breakNaturally();
		}
	}

}
