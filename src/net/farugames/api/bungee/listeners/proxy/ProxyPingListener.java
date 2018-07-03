package net.farugames.api.bungee.listeners.proxy;

import net.farugames.api.bungee.Main;
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
		
		serverPing.setDescriptionComponent(new TextComponent(Main.isMaintenance() ?
				Lang.MAINTENANCE_MOTD.in(Lang.ENGLISH) :
					Lang.NORMAL_MOTD.in(Lang.ENGLISH)));
		serverPing.setVersion(Main.protocol != null ? Main.protocol : null);
		serverPing.setFavicon(Main.favicon != null ? Main.favicon : null);
		event.setResponse(serverPing);
	}
}
