package net.faru.api.bungee.listeners;

import net.md_5.bungee.api.plugin.Listener;

public enum Listeners {

	POST_LOGIN_LISTENER(new PostLoginListener());
	
	private Listener listener;
	
	Listeners(Listener listener) {
		this.listener = listener;
	}
	
	public Listener getListener() {
		return this.listener;
	}
}
