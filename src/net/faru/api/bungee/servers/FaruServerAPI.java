package net.faru.api.bungee.servers;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

import net.faru.api.bungee.BungeeFaruAPI;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class FaruServerAPI {

	private String name;
	private InetAddress host;
	private Integer port;
	
	private ServerMode mode;
	private ServerStatut statut;
	
	private Boolean joinable;
	
	private Collection<ProxiedPlayer> players;
	
	public FaruServerAPI(String name, InetAddress host, Integer port) {
		this.name = name;
		this.host = host;
		this.port = port;
		
		this.mode = this.port == 25000 ? ServerMode.DEFAULT : ServerMode.RANDOM;
		this.statut = ServerStatut.UNREGISTERED;
		
		players = new ArrayList<ProxiedPlayer>();
	}
	
	public void register() {
		this.joinable = false;
		
		BungeeFaruAPI.iFaruServers.put(this.name, this);
		BungeeFaruAPI.registerServer(this.name, this.port);
		
    	this.setStatut(ServerStatut.REGISTERED);
    	
    	Timer timer = new Timer();
    	timer.schedule(new TimerTask() {
    	       @Override
    	       public void run() {
    	    	   players = ProxyServer.getInstance().getServerInfo(name) == null ? null : ProxyServer.getInstance().getServerInfo(name).getPlayers();
    	       }
    	    }, 0, 500);
	}
	
	public void unregister() {
		BungeeFaruAPI.iFaruServers.remove(this.name);
		BungeeFaruAPI.unregisterServer(this.name);
	}
	
	public void delete() {
		this.joinable = false;
		this.statut = ServerStatut.DELETE;
		
		this.unregister();
	}
	
	public String getName() {
		return this.name;
	}
	
	public InetAddress getHost() {
		return this.host;
	}
	
	public Integer getPort() {
		return this.port;
	}
	
	public ServerMode getMode() {
		return this.mode;
	}
	
	public void setStatut(ServerStatut statut) {
		this.statut = statut;
	}
	
	public ServerStatut getStatut() {
		return this.statut;
	}
	
	public void setJoinable(Boolean joinable) {
		this.joinable = joinable;
	}
	
	public Boolean isJoinable() {
		return this.joinable;
	}
	
	public Collection<ProxiedPlayer> getPlayers() {
		return this.players;
	}
	
	public Integer getOnlinePlayers() {
		return this.players == null ? 0 : this.players.size();
	}
	
	public static FaruServerAPI getServer(String name, String host, Integer port) {
		if(!BungeeFaruAPI.iFaruServers.containsKey(name)) {
			try {
				BungeeFaruAPI.iFaruServers.put(name, new FaruServerAPI(name, InetAddress.getByName(host), port));
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		return BungeeFaruAPI.iFaruServers.get(name);
	}
}
