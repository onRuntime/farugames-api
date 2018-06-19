package net.faru.api.servers;

import net.faru.api.spigot.SpigotFaruAPI;
import net.faru.data.database.servers.IServer;

public class FaruServerAPI {
	
	private Integer id;
	private ServerType serverType;
	private String name;
	
	private Integer onlinePlayers;
	
	public FaruServerAPI(ServerType serverType) {
		this.serverType = serverType;
		this.id = IServer.getServers(this.serverType).size() + 1;
		this.name = serverType.getName();
		
		SpigotFaruAPI.iFaruServers.put(serverType.getNameID() + this.id, this);
	}
	
	public void register() {
		IServer.createServer(this.serverType, this.id);
	}
	
	public Integer getID() {
		return this.id;
	}
	
	public ServerType getServerType() {
		return this.serverType;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Integer getOnlinePlayers() {
		return this.onlinePlayers;
	}
	
	public static FaruServerAPI getFaruServer(ServerType serverType, Integer id) {
		return SpigotFaruAPI.iFaruServers.get(serverType.getNameID() + id) != null ? 
				SpigotFaruAPI.iFaruServers.get(serverType.getNameID() + id) : null;
	}
}
