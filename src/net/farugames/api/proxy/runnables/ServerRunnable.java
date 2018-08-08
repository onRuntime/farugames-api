package net.farugames.api.proxy.runnables;

import net.farugames.api.core.server.FaruServer;
import net.farugames.api.proxy.ProxyFaruGamesAPI;

public class ServerRunnable implements Runnable {

	@Override
	public void run() {
		for(FaruServer server : ProxyFaruGamesAPI.iFaruServer.values()) {
			server.update();
			
			switch(server.getStatus()) {
				case LOBBY:
					break;
				case LOADING:
					break;
				case DELETE:
					server.delete();
					break;
				default:
					break;
			}
		}
	}
}
