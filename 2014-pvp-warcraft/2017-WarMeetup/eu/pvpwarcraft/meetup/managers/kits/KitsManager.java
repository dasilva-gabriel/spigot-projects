package eu.pvpwarcraft.meetup.managers.kits;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import eu.pvpwarcraft.meetup.managers.types.Types;
import eu.pvpwarcraft.meetup.managers.types.TypesManager;
import eu.pvpwarcraft.meetup.utils.ItemBuilder;

public class KitsManager {

	public static List<ItemBuilder> helmet = new ArrayList<ItemBuilder>();
	public static List<ItemBuilder> chestplate = new ArrayList<ItemBuilder>();
	public static List<ItemBuilder> leggings = new ArrayList<ItemBuilder>();
	public static List<ItemBuilder> boots = new ArrayList<ItemBuilder>();
	public static List<ItemBuilder> sword = new ArrayList<ItemBuilder>();
	public static List<ItemBuilder> rod = new ArrayList<ItemBuilder>();
	public static List<ItemBuilder> bow = new ArrayList<ItemBuilder>();
	public static List<ItemBuilder> apple = new ArrayList<ItemBuilder>();
	public static List<ItemBuilder> arrow = new ArrayList<ItemBuilder>();
	public static List<ItemBuilder> items = new ArrayList<ItemBuilder>();

	public static void moreCheat() {
		helmet.add(new ItemBuilder(Material.DIAMOND_HELMET).addEnchant(Enchantment.DURABILITY, 2));
		helmet.add(new ItemBuilder(Material.DIAMOND_HELMET).addEnchant(Enchantment.DURABILITY, 2)
				.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
		helmet.add(new ItemBuilder(Material.DIAMOND_HELMET).addEnchant(Enchantment.DURABILITY, 2));
		helmet.add(new ItemBuilder(Material.DIAMOND_HELMET).addEnchant(Enchantment.DURABILITY, 2)
				.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
		chestplate.add(new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.DURABILITY, 2));
		chestplate.add(new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.DURABILITY, 2)
				.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
		chestplate.add(new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.DURABILITY, 2)
				.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2));
		chestplate.add(new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.DURABILITY, 2));
		chestplate.add(new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.DURABILITY, 2)
				.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
		chestplate.add(new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.DURABILITY, 2)
				.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2));
		sword.add(new ItemBuilder(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1));
		sword.add(new ItemBuilder(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1));
		sword.add(new ItemBuilder(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1));
		sword.add(new ItemBuilder(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1));
		apple.add(new ItemBuilder(Material.GOLDEN_APPLE).setAmount(5));
		apple.add(new ItemBuilder(Material.GOLDEN_APPLE).setAmount(5));
	}
	
	public static void init() {
		helmet.clear();
		chestplate.clear();
		leggings.clear();
		boots.clear();
		sword.clear();
		rod.clear();
		bow.clear();
		apple.clear();
		arrow.clear();
		items.clear();

		helmet.add(new ItemBuilder(Material.DIAMOND_HELMET).addEnchant(Enchantment.DURABILITY, 2));
		helmet.add(new ItemBuilder(Material.DIAMOND_HELMET).addEnchant(Enchantment.DURABILITY, 2)
				.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
		helmet.add(new ItemBuilder(Material.DIAMOND_HELMET).addEnchant(Enchantment.DURABILITY, 2)
				.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2));
		helmet.add(new ItemBuilder(Material.IRON_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2));
		helmet.add(new ItemBuilder(Material.IRON_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
				.addEnchant(Enchantment.PROTECTION_PROJECTILE, 3));
		helmet.add(new ItemBuilder(Material.IRON_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2));
		helmet.add(new ItemBuilder(Material.IRON_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
				.addEnchant(Enchantment.PROTECTION_PROJECTILE, 3));
		helmet.add(new ItemBuilder(Material.IRON_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
				.addEnchant(Enchantment.PROTECTION_FIRE, 3));

		chestplate.add(new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.DURABILITY, 2));
		chestplate.add(new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.DURABILITY, 2)
				.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
		chestplate.add(new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.DURABILITY, 2)
				.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2));
		chestplate.add(new ItemBuilder(Material.IRON_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2));
		chestplate.add(new ItemBuilder(Material.IRON_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
		chestplate.add(new ItemBuilder(Material.IRON_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2));
		chestplate.add(new ItemBuilder(Material.IRON_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1));

		leggings.add(new ItemBuilder(Material.DIAMOND_LEGGINGS).addEnchant(Enchantment.DURABILITY, 2));
		leggings.add(new ItemBuilder(Material.DIAMOND_LEGGINGS).addEnchant(Enchantment.DURABILITY, 2)
				.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
		leggings.add(new ItemBuilder(Material.DIAMOND_LEGGINGS).addEnchant(Enchantment.DURABILITY, 2)
				.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2));
		leggings.add(new ItemBuilder(Material.IRON_LEGGINGS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2));
		leggings.add(new ItemBuilder(Material.IRON_LEGGINGS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1));

		boots.add(new ItemBuilder(Material.DIAMOND_BOOTS).addEnchant(Enchantment.DURABILITY, 2));
		boots.add(new ItemBuilder(Material.DIAMOND_BOOTS).addEnchant(Enchantment.DURABILITY, 2)
				.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
		boots.add(new ItemBuilder(Material.DIAMOND_BOOTS).addEnchant(Enchantment.DURABILITY, 2)
				.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2));
		boots.add(new ItemBuilder(Material.IRON_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2));
		boots.add(new ItemBuilder(Material.IRON_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
				.addEnchant(Enchantment.PROTECTION_PROJECTILE, 3));
		boots.add(new ItemBuilder(Material.IRON_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
				.addEnchant(Enchantment.PROTECTION_FIRE, 3));

		sword.add(new ItemBuilder(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1));
		sword.add(new ItemBuilder(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1));
		sword.add(new ItemBuilder(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1));
		sword.add(new ItemBuilder(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1));
		sword.add(new ItemBuilder(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 2));
		sword.add(new ItemBuilder(Material.IRON_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1));
		sword.add(new ItemBuilder(Material.IRON_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 2));
		
		if(!TypesManager.getTypesWinner().equals(Types.FLAME_LESS)){
			sword.add(new ItemBuilder(Material.IRON_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1)
					.addEnchant(Enchantment.FIRE_ASPECT, 1));
			sword.add(new ItemBuilder(Material.IRON_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 2)
					.addEnchant(Enchantment.FIRE_ASPECT, 1));
		}

		rod.add(new ItemBuilder(Material.FISHING_ROD).addEnchant(Enchantment.DURABILITY, 1));
		rod.add(new ItemBuilder(Material.FISHING_ROD).addEnchant(Enchantment.DURABILITY, 2));

		bow.add(new ItemBuilder(Material.BOW).addEnchant(Enchantment.DURABILITY, 1));
		bow.add(new ItemBuilder(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 1));
		bow.add(new ItemBuilder(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 1));
		bow.add(new ItemBuilder(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 1));
		bow.add(new ItemBuilder(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 1));
		bow.add(new ItemBuilder(Material.BOW).addEnchant(Enchantment.ARROW_KNOCKBACK, 1));
		if(!TypesManager.getTypesWinner().equals(Types.FLAME_LESS)){
			bow.add(new ItemBuilder(Material.BOW).addEnchant(Enchantment.ARROW_FIRE, 1));
			bow.add(new ItemBuilder(Material.BOW).addEnchant(Enchantment.ARROW_FIRE, 1).addEnchant(Enchantment.ARROW_DAMAGE,
					1));
		}

		apple.add(new ItemBuilder(Material.GOLDEN_APPLE).setAmount(3));
		apple.add(new ItemBuilder(Material.GOLDEN_APPLE).setAmount(4));
		apple.add(new ItemBuilder(Material.GOLDEN_APPLE).setAmount(5));

		arrow.add(new ItemBuilder(Material.ARROW).setAmount(16));
		arrow.add(new ItemBuilder(Material.ARROW).setAmount(32));
		arrow.add(new ItemBuilder(Material.ARROW).setAmount(64));

		items.add(new ItemBuilder(Material.BREAD).setAmount(16));
		items.add(new ItemBuilder(Material.BREAD).setAmount(32));
		items.add(new ItemBuilder(Material.COOKED_BEEF).setAmount(16));
		items.add(new ItemBuilder(Material.COOKED_CHICKEN).setAmount(16));
		items.add(new ItemBuilder(Material.COOKED_BEEF).setAmount(32));
		items.add(new ItemBuilder(Material.COOKED_CHICKEN).setAmount(32));
		items.add(new ItemBuilder(Material.BREAD).setAmount(16));
		items.add(new ItemBuilder(Material.BREAD).setAmount(32));
		items.add(new ItemBuilder(Material.COOKED_BEEF).setAmount(16));
		items.add(new ItemBuilder(Material.COOKED_CHICKEN).setAmount(16));
		items.add(new ItemBuilder(Material.COOKED_BEEF).setAmount(32));
		items.add(new ItemBuilder(Material.COOKED_CHICKEN).setAmount(32));
		items.add(new ItemBuilder(Material.ANVIL).setAmount(3));
		items.add(new ItemBuilder(Material.ANVIL).setAmount(2));
		items.add(new ItemBuilder(Material.ANVIL).setAmount(1));
		items.add(new ItemBuilder(Material.EXP_BOTTLE).setAmount(5));
		items.add(new ItemBuilder(Material.EXP_BOTTLE).setAmount(10));
		items.add(new ItemBuilder(Material.EXP_BOTTLE).setAmount(12));
		items.add(new ItemBuilder(Material.EXP_BOTTLE).setAmount(5));
		items.add(new ItemBuilder(Material.EXP_BOTTLE).setAmount(10));
		items.add(new ItemBuilder(Material.EXP_BOTTLE).setAmount(12));
		items.add(new ItemBuilder(Material.COBBLESTONE).setAmount(64));
		items.add(new ItemBuilder(Material.WOOD).setAmount(64));
		items.add(new ItemBuilder(Material.WOOD).setAmount(64));
		items.add(new ItemBuilder(Material.COBBLESTONE).setAmount(64));
		items.add(new ItemBuilder(Material.WOOD).setAmount(64));
		items.add(new ItemBuilder(Material.WOOD).setAmount(64));
		items.add(new ItemBuilder(Material.COBBLESTONE).setAmount(64));
		items.add(new ItemBuilder(Material.WOOD).setAmount(64));
		items.add(new ItemBuilder(Material.ARROW).setAmount(10));
		items.add(new ItemBuilder(Material.ARROW).setAmount(10));
		items.add(new ItemBuilder(Material.WOOD).setAmount(64));
		items.add(new ItemBuilder(Material.IRON_PICKAXE).addEnchant(Enchantment.DURABILITY, 2));
		items.add(new ItemBuilder(Material.IRON_PICKAXE).addEnchant(Enchantment.DURABILITY, 1));
		items.add(new ItemBuilder(Material.IRON_PICKAXE).addEnchant(Enchantment.DURABILITY, 2));
		items.add(new ItemBuilder(Material.IRON_PICKAXE).addEnchant(Enchantment.DURABILITY, 1));
		items.add(new ItemBuilder(Material.WOOD_PICKAXE).addEnchant(Enchantment.DIG_SPEED, 50));
		items.add(new ItemBuilder(Material.IRON_AXE).addEnchant(Enchantment.DURABILITY, 2));
		items.add(new ItemBuilder(Material.IRON_AXE).addEnchant(Enchantment.DURABILITY, 1));
	}

	public static void giveItems(Player player) {
		ItemBuilder pHelmet = helmet.get(RandomNumb(helmet.size()));
		ItemBuilder pChestplate = chestplate.get(RandomNumb(chestplate.size()));
		ItemBuilder pLeggings = leggings.get(RandomNumb(leggings.size()));
		ItemBuilder pBoots = boots.get(RandomNumb(boots.size()));
		ItemBuilder pSword = sword.get(RandomNumb(sword.size()));
		ItemBuilder pRod = rod.get(RandomNumb(rod.size()));
		ItemBuilder pBow = bow.get(RandomNumb(bow.size()));
		ItemBuilder pApple = apple.get(RandomNumb(apple.size()));
		ItemBuilder pArrow = arrow.get(RandomNumb(arrow.size()));
		int pSize = RandomNumb(12) + 4;

		player.setMaxHealth(20);
		player.setHealth(player.getMaxHealth());
		player.setFoodLevel(20);
		player.getInventory().setHelmet(pHelmet.build());
		player.getInventory().setChestplate(pChestplate.build());
		player.getInventory().setLeggings(pLeggings.build());
		player.getInventory().setBoots(pBoots.build());
		player.getInventory().setItem(0, pSword.build());
		player.getInventory().setItem(1, pRod.build());
		player.getInventory().setItem(2, pApple.build());
		player.getInventory().setItem(3, pBow.build());
		if(TypesManager.getTypesWinner().equals(Types.BOW_LESS)){
			player.getInventory().setItem(3, new ItemBuilder(Material.AIR).build());
		}
		if(TypesManager.getTypesWinner().equals(Types.APPLE_LESS)){
			player.getInventory().setItem(2, new ItemBuilder(Material.AIR).build());
		}
		if(TypesManager.getTypesWinner().equals(Types.ROD_LESS)){
			player.getInventory().setItem(1, new ItemBuilder(Material.AIR).build());
		}
		if(!TypesManager.getTypesWinner().equals(Types.FLAME_LESS)){
			player.getInventory().setItem(6, new ItemBuilder(Material.LAVA_BUCKET).build());
			player.getInventory().setItem(10, new ItemBuilder(Material.LAVA_BUCKET).build());
		}
		player.getInventory().setItem(7, new ItemBuilder(Material.WATER_BUCKET).build());
		player.getInventory().setItem(8, new ItemBuilder(Material.COBBLESTONE).setAmount(64).build());
		player.getInventory().setItem(11, new ItemBuilder(Material.WATER_BUCKET).build());
		player.getInventory().setItem(12, new ItemBuilder(Material.COBBLESTONE).setAmount(64).build());
		player.getInventory().setItem(9, pArrow.build());
		for (int i = 18; i <= (18 + pSize); i++) {
			ItemBuilder pItem = items.get(RandomNumb(items.size()));
			player.getInventory().setItem(i, pItem.build());
		}
		player.setGameMode(GameMode.SURVIVAL);
	}

	private static int RandomNumb(int size) {
		Random rand = new Random();
		int rNumb = rand.nextInt(size);
		return rNumb;
	}

}
