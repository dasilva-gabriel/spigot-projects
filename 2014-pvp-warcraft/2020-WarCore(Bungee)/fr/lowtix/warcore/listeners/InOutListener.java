package fr.lowtix.warcore.listeners;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.managers.PlayerWrapper;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class InOutListener implements Listener {

	@EventHandler
	public void onJoin(PlayerDisconnectEvent event) {
		ProxiedPlayer pl = event.getPlayer();
		if(WarCore.getInstance().containsWrapper(pl.getName())) {
			PlayerWrapper wrap = WarCore.getInstance().getWrapper(pl);
			WarCore.getInstance().getIgnoreTable().saveIgnores(wrap);
			WarCore.getInstance().removeWrapper(wrap);
		}
	}
	
}
