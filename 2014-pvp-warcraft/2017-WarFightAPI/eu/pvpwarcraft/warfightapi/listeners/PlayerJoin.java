package eu.pvpwarcraft.warfightapi.listeners;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import eu.pvpwarcraft.warfightapi.WarFightAPI;
import eu.pvpwarcraft.warfightapi.events.UserJoinEvent;
import eu.pvpwarcraft.warfightapi.managers.User;
import eu.pvpwarcraft.warfightapi.sql.WarFightSQL;

public class PlayerJoin implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent event) {
		User user = null;
		Player player = event.getPlayer();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date = new Date();
		if (!WarFightAPI.getInstance().getUsers().containsKey(player.getName())) {
			if (!WarFightSQL.containsPlayer(player.getName())) {
				user = new User(player.getName(), 0, dateFormat.format(date), 0, new User.MeetupStatistics(),
						new User.Elos()).create();
				user.getElos().generate();
				WarFightSQL.createPlayer(user);
				user.save();
				WarFightAPI.getInstance().getUsers().put(player.getName(), user);
			} else {
				user = new User(player.getName(), 0, dateFormat.format(date), 0, new User.MeetupStatistics(),
						new User.Elos()).create();
				WarFightSQL.initUser(user);
				WarFightAPI.getInstance().getUsers().remove(player.getName());
				WarFightAPI.getInstance().getUsers().put(player.getName(), user);
			}
		} else {
			user = new User(player.getName(), 0, dateFormat.format(date), 0, new User.MeetupStatistics(),
					new User.Elos()).create();
			WarFightSQL.initUser(user);
			WarFightAPI.getInstance().getUsers().remove(player.getName());
			WarFightAPI.getInstance().getUsers().put(player.getName(), user);
		}
		user.getElos().generate();
		Bukkit.getPluginManager().callEvent(new UserJoinEvent(user));
	}

}
