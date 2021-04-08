package fr.lowtix.warcore.managers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import fr.lowtix.wartracker.utils.ItemBuilder;

public class CraftManager implements Listener {
	
	public ItemStack composite = new ItemBuilder(Material.MONSTER_EGG).addEnchant(Enchantment.ARROW_DAMAGE, 100).addFlag(ItemFlag.HIDE_ENCHANTS).setName("§eOeuf vide").build();
	public ItemStack creeperEgg = new ItemBuilder(Material.MONSTER_EGG).setDamage(50).build();
	public ItemStack cheatedApple = new ItemBuilder(Material.GOLDEN_APPLE).setDamage(1).build();
	
	public void setup() {
		
		ShapedRecipe recipe1 = new ShapedRecipe(composite);
		recipe1.shape(new String[] {"IBI", "DED", "IDI"});
		recipe1.setIngredient('I', Material.IRON_BLOCK);
		recipe1.setIngredient('B', Material.BONE);
		recipe1.setIngredient('D', Material.DIAMOND);
		recipe1.setIngredient('E', Material.EGG);
		
		ShapedRecipe recipe2 = new ShapedRecipe(creeperEgg);
		recipe2.shape(new String[] {"BTB", "CMC", "BTB"});
		recipe2.setIngredient('M', Material.MONSTER_EGG);
		recipe2.setIngredient('C', Material.EXPLOSIVE_MINECART);
		recipe2.setIngredient('B', Material.BLAZE_ROD);
		recipe2.setIngredient('T', Material.TNT);
		
		Bukkit.getServer().addRecipe(recipe1);
		Bukkit.getServer().addRecipe(recipe2);
	}
	
	@EventHandler
	public void onPlayerCraft(CraftItemEvent e) {
		if(e.getRecipe().getResult().getType().equals(Material.HOPPER) || e.getRecipe().getResult().equals(cheatedApple)) {
			e.setCancelled(true);
			e.getWhoClicked().closeInventory();
			e.getWhoClicked().sendMessage("§8[§e§l!§8] §cCe craft n'est pas autorisé, l'item soit être acheté dans le Shop.");
		}
	}

}
