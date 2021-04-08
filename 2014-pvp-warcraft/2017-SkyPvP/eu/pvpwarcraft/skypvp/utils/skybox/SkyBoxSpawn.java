package eu.pvpwarcraft.skypvp.utils.skybox;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import eu.pvpwarcraft.skypvp.managers.EnumLocations;
import eu.pvpwarcraft.skypvp.managers.Locations;
import eu.pvpwarcraft.skypvp.utils.ItemBuilder;

public class SkyBoxSpawn {
	
	private static void spawnBlock(Location loc){
		Block block1 = loc.getBlock();
		block1.setType(Material.STAINED_GLASS);
		ArmorStand as1 = (ArmorStand) loc.getWorld().spawnEntity(loc.add(0.5, 0, 0.5).subtract(0, 0.5, 0), EntityType.ARMOR_STAND);
		as1.setSmall(true);
		as1.setBasePlate(false);
		as1.setGravity(false);
		as1.setHelmet(new ItemBuilder(Material.SEA_LANTERN).build());
		as1.setVisible(false);
		as1.setCanPickupItems(false);
		ArmorStand as2 = (ArmorStand) loc.getWorld().spawnEntity(loc.add(0, 0.5, 0), EntityType.ARMOR_STAND);
		as2.setSmall(true);
		as2.setBasePlate(false);
		as2.setGravity(false);
		as2.setVisible(false);
		as2.setCanPickupItems(false);
		as2.setCustomName("§d§l§k|§a§l§k|§3§l§k|§2§l§k|§r §6§lSkyBox §2§l§k|§3§l§k|§a§l§k|§d§l§k|§r");
		as2.setCustomNameVisible(true);
		as2.setCanPickupItems(false);
	}
	
	public static void setup(){
		
		SkyBoxGive.canpichkup.put(EnumLocations.SKYBOX1, 0);
		SkyBoxGive.canpichkup.put(EnumLocations.SKYBOX2, 0);
		SkyBoxGive.canpichkup.put(EnumLocations.SKYBOX3, 0);
		SkyBoxGive.canpichkup.put(EnumLocations.SKYBOX4, 0);
		SkyBoxGive.canpichkup.put(EnumLocations.SKYBOX5, 0);
		SkyBoxGive.canpichkup.put(EnumLocations.SKYBOX6, 0);
		SkyBoxGive.canpichkup.put(EnumLocations.SKYBOX7, 0);
		SkyBoxGive.canpichkup.put(EnumLocations.SKYBOX8, 0);
	}
	
	public static void spawn(){
		for(Entity all : Bukkit.getWorld("world").getEntities()){
			if(all instanceof ArmorStand){
				all.remove();
			}
		}
		spawnBlock(Locations.getLocation(EnumLocations.SKYBOX1));
		spawnBlock(Locations.getLocation(EnumLocations.SKYBOX2));
		spawnBlock(Locations.getLocation(EnumLocations.SKYBOX3));
		spawnBlock(Locations.getLocation(EnumLocations.SKYBOX4));
		spawnBlock(Locations.getLocation(EnumLocations.SKYBOX5));
		spawnBlock(Locations.getLocation(EnumLocations.SKYBOX6));
		spawnBlock(Locations.getLocation(EnumLocations.SKYBOX7));
		spawnBlock(Locations.getLocation(EnumLocations.SKYBOX8));
	}

}
