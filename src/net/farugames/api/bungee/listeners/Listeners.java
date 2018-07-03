package net.farugames.api.bungee.listeners;

import net.farugames.api.bungee.listeners.proxiedplayer.PostLoginListener;
import net.farugames.api.bungee.listeners.proxiedplayer.ServerConnectListener;
import net.farugames.api.bungee.listeners.proxiedplayer.ServerDisconnectListener;
import net.farugames.api.bungee.listeners.proxy.ProxyPingListener;
import net.md_5.bungee.api.plugin.Listener;

public enum Listeners {

	POST_LOGIN_LISTENER(new PostLoginListener()),
	PROXY_PING_LISTENER(new ProxyPingListener()),
	SERVER_CONNECT_LISTENER(new ServerConnectListener()),
	SERVER_DISCONNECT_LISTENER(new ServerDisconnectListener());
	
	private Listener listener;
	
	Listeners(Listener listener) {
		this.listener = listener;
	}
	
	public Listener getListener() {
		return this.listener;
	}
}
