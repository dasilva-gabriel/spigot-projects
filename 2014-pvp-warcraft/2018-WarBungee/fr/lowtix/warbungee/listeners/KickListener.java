package fr.lowtix.warbungee.listeners;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class KickListener implements Listener {

	@EventHandler
    public void kickPlayer(ServerKickEvent event) {
        ServerInfo server = ProxyServer.getInstance().getServerInfo("lobby");
        event.getPlayer().connect(server);
        event.setCancelled(true);
        event.getPlayer().sendMessage(new TextComponent(""));
        event.getPlayer().sendMessage(new TextComponent("§cVous avez été expulsé du serveur où vous étiez..."));
        event.getPlayer().sendMessage(new TextComponent(""));
    }

	
}
