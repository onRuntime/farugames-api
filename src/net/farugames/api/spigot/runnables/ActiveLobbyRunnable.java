package net.farugames.api.spigot.runnables;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import net.farugames.api.core.server.ServerStatus;
import net.farugames.database.redis.servers.IServer;

public class ActiveLobbyRunnable extends BukkitRunnable {

	@Override
	public void run() {
		IServer.setStatus(Bukkit.getServerName(), ServerStatus.LOBBY);
	}
}
