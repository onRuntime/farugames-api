package net.farugames.api.proxy.listeners.event;

import net.farugames.api.core.lang.LangOld;
import net.farugames.api.proxy.ProxyFaruGamesAPI;
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
		
		serverPing.setDescriptionComponent(new TextComponent(ProxyFaruGamesAPI.isMaintenance() ?
				LangOld.MAINTENANCE_MOTD.in(LangOld.ENGLISH) :
					LangOld.NORMAL_MOTD.in(LangOld.ENGLISH)));
		serverPing.setVersion(ProxyFaruGamesAPI.protocol != null ? ProxyFaruGamesAPI.protocol : null);
		serverPing.setFavicon(ProxyFaruGamesAPI.favicon != null ? ProxyFaruGamesAPI.favicon : null);
		event.setResponse(serverPing);
	}
}
