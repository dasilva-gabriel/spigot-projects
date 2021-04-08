package fr.lowtix.warlobby.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

public class ParticlesUtils {

	public static void spawnParticles(Location loc, EnumParticle part){
		PacketPlayOutWorldParticles particle = new PacketPlayOutWorldParticles(part, false, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), 0.0F, 0.0F, 0.0F, 0.0F, 1, new int[] { 0 });
		for(Player all : Bukkit.getOnlinePlayers()){
			if(all.getLocation().distance(loc) < 20){
				((CraftPlayer)all).getHandle().playerConnection.sendPacket(particle);
			}
		}
	}
	
	public static void circle(Location loc, int particles_numb, EnumParticle part){
		Location location1 = loc;
        Location location2 = loc;
        Location location3 = loc;
        int particles = particles_numb;
        float radius = 0.7f;
        for (int i = 0; i < particles; i++) {
            double angle, x, z;
            angle = 2 * Math.PI * i / particles;
            x = Math.cos(angle) * radius;
            z = Math.sin(angle) * radius;
            location1.add(x, 0, z);
            location2.add(x, -0.66, z);
            location3.add(x, -1.33, z);
            spawnParticles(location1, part);
            spawnParticles(location2, part);
            spawnParticles(location3, part);
            location1.subtract(x, 0, z);
            location2.subtract(x, -0.66, z);
            location3.subtract(x, -1.33, z);
        }
	}
	
}
