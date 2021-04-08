package eu.pvpwarcraft.dayz.listeners.weapons;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.shampaggon.crackshot.events.WeaponReloadCompleteEvent;
import com.shampaggon.crackshot.events.WeaponReloadEvent;

import eu.pvpwarcraft.dayz.utils.PlayerUtils;

public class PlayerReloadWeapon implements Listener {
	
	@EventHandler
	public void onReload(WeaponReloadEvent event){
		Player player = event.getPlayer();
		PlayerUtils.sendActionBar(player, "§8» §cRechargement du "+event.getWeaponTitle()+" en cours...");
	}
	
	@EventHandler
	public void onReloadComplete(WeaponReloadCompleteEvent event){
		Player player = event.getPlayer();
		PlayerUtils.sendActionBar(player, "§8» §aRechargement du "+event.getWeaponTitle()+" terminé.");
	}

}
