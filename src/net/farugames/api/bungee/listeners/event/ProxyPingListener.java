package net.farugames.api.bungee.listeners.event;

import net.farugames.api.bungee.BungeeFaruAPI;
import net.farugames.api.spigot.player.languages.Lang;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class ProxyPingListener implements Listener {

	@EventHandler(priority = EventPriority.HIGH)
	public void onPingEvent(ProxyPingEvent event) {
		ServerPing serverPing = event.getResponse();
		
		serverPing.setDescriptionComponent(new TextComponent(BungeeFaruAPI.isMaintenance() ?
				Lang.MAINTENANCE_MOTD.in(Lang.ENGLISH) :
					Lang.NORMAL_MOTD.in(Lang.ENGLISH)));
		serverPing.setVersion(BungeeFaruAPI.protocol != null ? BungeeFaruAPI.protocol : null);
		serverPing.setFavicon(BungeeFaruAPI.favicon != null ? BungeeFaruAPI.favicon : null);
		event.setResponse(serverPing);
	}
}
