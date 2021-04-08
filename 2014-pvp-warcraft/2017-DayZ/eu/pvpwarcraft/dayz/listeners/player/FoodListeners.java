package eu.pvpwarcraft.dayz.listeners.player;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import eu.pvpwarcraft.dayz.DayZ;
import eu.pvpwarcraft.dayz.users.DayzPlayer;
import eu.pvpwarcraft.dayz.utils.ItemBuilder;
import eu.pvpwarcraft.dayz.utils.PlayerUtils;

public class FoodListeners implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event){
		if(event.getAction() == null) return;
		if(event.getItem() == null) return;
		
		Player player = event.getPlayer();
		DayzPlayer dzp = DayZ.getPlayer(player);
		
		ItemStack egg = new ItemBuilder(Material.EGG).setAmount(player.getItemInHand().getAmount()).setName(player.getItemInHand().getItemMeta().getDisplayName()).build();
		
		if(player.getItemInHand().equals(egg)){
			event.setCancelled(true);
		}
		
		ItemStack pepsi = new ItemBuilder(Material.INK_SACK).setDamage(14).setAmount(player.getItemInHand().getAmount()).setName(player.getItemInHand().getItemMeta().getDisplayName()).build();
		String pepsi_name = "§8• §7Canette de §3Pepsi §8•";
		
		if(player.getItemInHand().equals(pepsi)){
			if(player.getItemInHand().getItemMeta().getDisplayName() == null || !player.getItemInHand().getItemMeta().getDisplayName().equals(pepsi_name)){
				ItemStack iteminHand = new ItemBuilder(Material.INK_SACK).setDamage(14).setName(pepsi_name).setAmount(player.getItemInHand().getAmount()).build();
				player.getItemInHand().setType(Material.AIR);
				player.setItemInHand(iteminHand);
			}
			if(player.getItemInHand().getAmount() == 1){
				player.getItemInHand().setAmount(-1);
				player.updateInventory();
			}else if (player.getItemInHand().getAmount() > 1){
				player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
			}else if (player.getItemInHand().getAmount() < 1){
				PlayerUtils.playSound(player, Sound.VILLAGER_NO);
				player.sendMessage(DayZ.getInstance().prefix + "§cVous n'avez plus de canette. Jettez la ou conservez la en souvenir :D");
				event.setCancelled(true);
				return;
			}
			
			Location loc = player.getLocation();
			Item item = loc.getWorld().dropItem(loc, new ItemBuilder(Material.EGG).setName("§8• §7Déchet §8•").build());
			item.teleport(loc);
			PlayerUtils.playSound(player, Sound.PORTAL);
			dzp.addThirst(30);
			PlayerUtils.playSound(player, Sound.DRINK);
		}
		
		ItemStack beans = new ItemBuilder(Material.INK_SACK).setDamage(4).setAmount(player.getItemInHand().getAmount()).setName(player.getItemInHand().getItemMeta().getDisplayName()).build();
		String beans_name = "§8• §2Haricots §7en boîte §8•";
		
		if(player.getItemInHand().equals(beans)){
			if(player.getItemInHand().getItemMeta().getDisplayName() == null || !player.getItemInHand().getItemMeta().getDisplayName().equals(beans_name)){
				ItemStack iteminHand = new ItemBuilder(Material.INK_SACK).setDamage(4).setName(beans_name).setAmount(player.getItemInHand().getAmount()).build();
				player.getItemInHand().setType(Material.AIR);
				player.setItemInHand(iteminHand);
			}
			if(player.getItemInHand().getAmount() == 1){
				player.getItemInHand().setAmount(-1);
				player.updateInventory();
			}else if (player.getItemInHand().getAmount() > 1){
				player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
			}else if (player.getItemInHand().getAmount() < 1){
				PlayerUtils.playSound(player, Sound.VILLAGER_NO);
				player.sendMessage(DayZ.getInstance().prefix + "§cVous n'avez plus d'haricots dans la boîte. Jettez la ou conservez la en souvenir :D");
				event.setCancelled(true);
				return;
			}
			
			Location loc = player.getLocation();
			Item item = loc.getWorld().dropItem(loc, new ItemBuilder(Material.EGG).setName("§8• §7Déchet §8•").build());
			item.teleport(loc);
			PlayerUtils.playSound(player, Sound.EAT);
			dzp.removeThirst(1);
			if(player.getHealth() + 2 <= player.getMaxHealth()){
				player.setHealth(player.getHealth() + 2);
			}else{
				player.setHealth(player.getHealth() + (player.getMaxHealth() - player.getHealth()));
			}
			
			if(player.getFoodLevel() + 3 <= 20){
				player.setFoodLevel(player.getFoodLevel() + 3);
			}else{
				player.setFoodLevel(player.getFoodLevel() + (20 - player.getFoodLevel()));
			}
			PlayerUtils.playSound(player, Sound.CHICKEN_EGG_POP);
		}
		
		ItemStack pasta = new ItemBuilder(Material.INK_SACK).setDamage(11).setAmount(player.getItemInHand().getAmount()).setName(player.getItemInHand().getItemMeta().getDisplayName()).build();
		String pasta_name = "§8• §ePâtes §7surgelés §8•";
		
		if(player.getItemInHand().equals(pasta)){
			if(player.getItemInHand().getItemMeta().getDisplayName() == null || !player.getItemInHand().getItemMeta().getDisplayName().equals(pasta_name)){
				ItemStack iteminHand = new ItemBuilder(Material.INK_SACK).setDamage(11).setName(pasta_name).setAmount(player.getItemInHand().getAmount()).build();
				player.getItemInHand().setType(Material.AIR);
				player.setItemInHand(iteminHand);
			}
			if(player.getItemInHand().getAmount() == 1){
				player.getItemInHand().setAmount(-1);
				player.updateInventory();
			}else if (player.getItemInHand().getAmount() > 1){
				player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
			}else if (player.getItemInHand().getAmount() < 1){
				PlayerUtils.playSound(player, Sound.VILLAGER_NO);
				player.sendMessage(DayZ.getInstance().prefix + "§cVous n'avez plus de pâtes dans la boîte. Jettez la ou conservez la en souvenir :D");
				event.setCancelled(true);
				return;
			}
			
			Location loc = player.getLocation();
			Item item = loc.getWorld().dropItem(loc, new ItemBuilder(Material.EGG).setName("§8• §7Déchet §8•").build());
			item.teleport(loc);
			PlayerUtils.playSound(player, Sound.EAT);
			dzp.removeThirst(1);
			if(player.getHealth() + 4 <= player.getMaxHealth()){
				player.setHealth(player.getHealth() + 4);
			}else{
				player.setHealth(player.getHealth() + (player.getMaxHealth() - player.getHealth()));
			}
			
			if(player.getFoodLevel() + 4 <= 20){
				player.setFoodLevel(player.getFoodLevel() + 4);
			}else{
				player.setFoodLevel(player.getFoodLevel() + (20 - player.getFoodLevel()));
			}
			PlayerUtils.playSound(player, Sound.CHICKEN_EGG_POP);
		}
		
		ItemStack fish = new ItemBuilder(Material.COOKED_FISH).setAmount(player.getItemInHand().getAmount()).setName(player.getItemInHand().getItemMeta().getDisplayName()).build();
		String fish_name = "§8• §6Sardines §7en boîte §8•";
		
		if(player.getItemInHand().equals(fish)){
			if(player.getItemInHand().getItemMeta().getDisplayName() == null || !player.getItemInHand().getItemMeta().getDisplayName().equals(fish_name)){
				ItemStack iteminHand = new ItemBuilder(Material.COOKED_FISH).setName(fish_name).setAmount(player.getItemInHand().getAmount()).build();
				player.getItemInHand().setType(Material.AIR);
				player.setItemInHand(iteminHand);
			}
			if(player.getItemInHand().getAmount() == 1){
				player.getItemInHand().setAmount(-1);
				player.updateInventory();
			}else if (player.getItemInHand().getAmount() > 1){
				player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
			}else if (player.getItemInHand().getAmount() < 1){
				PlayerUtils.playSound(player, Sound.VILLAGER_NO);
				player.sendMessage(DayZ.getInstance().prefix + "§cVous n'avez plus de sardines dans la boîte. Jettez la ou conservez la en souvenir :D");
				event.setCancelled(true);
				return;
			}
			
			Location loc = player.getLocation();
			Item item = loc.getWorld().dropItem(loc, new ItemBuilder(Material.EGG).setName("§8• §7Déchet §8•").build());
			item.teleport(loc);
			PlayerUtils.playSound(player, Sound.EAT);
			dzp.addThirst(10);
			if(player.getHealth() + 6 <= player.getMaxHealth()){
				player.setHealth(player.getHealth() + 6);
			}else{
				player.setHealth(player.getHealth() + (player.getMaxHealth() - player.getHealth()));
			}
			
			if(player.getFoodLevel() + 6 <= 20){
				player.setFoodLevel(player.getFoodLevel() + 6);
			}else{
				player.setFoodLevel(player.getFoodLevel() + (20 - player.getFoodLevel()));
			}
			PlayerUtils.playSound(player, Sound.CHICKEN_EGG_POP);
		}
		
		ItemStack whisky = new ItemBuilder(Material.INK_SACK).setDamage(15).setAmount(player.getItemInHand().getAmount()).setName(player.getItemInHand().getItemMeta().getDisplayName()).build();
		String whisky_name = "§8• §aWhisky §7du pêcheur §8•";
		
		if(player.getItemInHand().equals(whisky)){
			if(player.getItemInHand().getItemMeta().getDisplayName() == null || !player.getItemInHand().getItemMeta().getDisplayName().equals(whisky_name)){
				ItemStack iteminHand = new ItemBuilder(Material.INK_SACK).setDamage(15).setName(whisky_name).setAmount(player.getItemInHand().getAmount()).build();
				player.getItemInHand().setType(Material.AIR);
				player.setItemInHand(iteminHand);
			}
			if(player.getItemInHand().getAmount() == 1){
				player.getItemInHand().setAmount(-1);
				player.updateInventory();
			}else if (player.getItemInHand().getAmount() > 1){
				player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
			}else if (player.getItemInHand().getAmount() < 1){
				PlayerUtils.playSound(player, Sound.VILLAGER_NO);
				player.sendMessage(DayZ.getInstance().prefix + "§cVous n'avez plus de Whisky. Jettez la ou conservez la en souvenir :D");
				event.setCancelled(true);
				return;
			}
			
			Location loc = player.getLocation();
			Item item = loc.getWorld().dropItem(loc, new ItemBuilder(Material.EGG).setName("§8• §7Déchet §8•").build());
			item.teleport(loc);
			PlayerUtils.playSound(player, Sound.PORTAL);
			dzp.addThirst(10);
			if(player.getHealth() + 8 <= player.getMaxHealth()){
				player.setHealth(player.getHealth() + 8);
			}else{
				player.setHealth(player.getHealth() + (player.getMaxHealth() - player.getHealth()));
			}
			
			if(player.getFoodLevel() + 8 <= 20){
				player.setFoodLevel(player.getFoodLevel() + 8);
			}else{
				player.setFoodLevel(player.getFoodLevel() + (20 - player.getFoodLevel()));
			}
			dzp.addThirst(25);
			PlayerUtils.playSound(player, Sound.DRINK);
		}
		
	}

}
