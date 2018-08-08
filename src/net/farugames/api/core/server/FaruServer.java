package net.farugames.api.core.server;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.farugames.api.proxy.ProxyFaruGamesAPI;
import net.farugames.api.spigot.FaruPlayer;
import net.farugames.api.tools.player.UUIDFetcher;
import net.farugames.database.redis.servers.IServer;

public class FaruServer {

	private String name;
	private InetSocketAddress address;
	private ServerType type;
	private ServerStatus status;
	private Collection<FaruPlayer> players;
	
	public FaruServer(String name, InetSocketAddress address, ServerType type, ServerStatus status) {
		this.name = name;
		this.address = address;
		this.type = type;
		this.status = status;
		
		this.players = new ArrayList<FaruPlayer>();
		
		ProxyFaruGamesAPI.iFaruServer.put(this.name, this);
	}
	
	public void update() {
		this.status = IServer.getStatus(this.name);
		this.players.clear();
		IServer.getOnlinePlayersNameList(this.name).forEach(playerName -> this.players.add(FaruPlayer.getPlayer(UUIDFetcher.getUUID(playerName))));
	}
	
	public void delete() {
		ProxyFaruGamesAPI.iFaruServer.remove(this.name);
		
		this.name = null;
		this.address = null;
		this.type = null;
		this.status = null;
		
		this.players = null;
		
		ProxyFaruGamesAPI.unregisterServer(this.name);
	}
	
	public void setStatus(ServerStatus status) {
		this.status = status;
	}
	
	public String getName() {return this.name;}
	public InetSocketAddress getAddress() {return this.address;}
	public ServerType getType() {return this.type;}
	public ServerStatus getStatus() {return this.status;}
	
	public List<FaruPlayer> getOnlinePlayers() {return (List<FaruPlayer>) this.players;}
	
	public static FaruServer getServer(String name) {
		return ProxyFaruGamesAPI.iFaruServer.containsKey(name)
				? ProxyFaruGamesAPI.iFaruServer.get(name)
				: null;
	}
}
