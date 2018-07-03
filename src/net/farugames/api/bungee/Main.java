package net.farugames.api.bungee;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import net.farugames.api.bungee.sanctions.Sanction;
import net.farugames.api.bungee.servers.FaruServerAPI;
import net.farugames.api.bungee.servers.ServerStatut;
import net.farugames.api.managers.BungeeCommandManager;
import net.farugames.api.managers.BungeeListenerManager;
import net.farugames.data.database.servers.IMaintenance;
import net.farugames.data.database.servers.IServer;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.BungeeServerInfo;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.Favicon;
import net.md_5.bungee.api.ServerPing.Protocol;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {

	private static Main instance;
	
	public static Favicon favicon;
	public static Protocol protocol;
	
	public static Map<String, FaruServerAPI> iFaruServers = new HashMap<String, FaruServerAPI>();
	public Collection<ProxiedPlayer> proxyPlayers = new ArrayList<ProxiedPlayer>();

	public static Boolean maintenance = false;
	private static List<String> maintenancePlayers = new ArrayList<String>();

	public static List<Sanction> sanctions = new ArrayList<Sanction>();

	public void onLoad() {
		instance = this;
		if(IMaintenance.isEnable()) maintenance = true;
		try {
			protocol = new Protocol(
						ChatColor.RED + "@FaruGames" + ChatColor.WHITE + " - " + ChatColor.DARK_RED + "✘" +
							ChatColor.DARK_GRAY + "/" + ChatColor.GRAY + String.valueOf(1500),
								339);
			favicon = Favicon.create(ImageIO.read(new URL("http://37.59.91.149/assets/img/logo-x64.png").openStream()));
		} catch(IOException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		  

		super.onLoad();
	}

	public void onEnable() {
		new BungeeListenerManager().register();
		new BungeeCommandManager().register();
		
		getProxy().getScheduler().schedule(this, new Runnable() {
            @Override
            public void run() {
            	for(FaruServerAPI server : iFaruServers.values()) {
            		if(!IServer.exists(server.getName())) server.unregister();
            		proxyPlayers = getProxy().getPlayers();
            	}
            	for(FaruServerAPI faruServer : IServer.getServers()) {
            		IServer.update(faruServer);
            		if(faruServer.getStatut() == ServerStatut.FINISH) {
            		}
            		if(faruServer.getStatut() == ServerStatut.UNREGISTERED) {
            			faruServer.register();
            		}
            		if(!iFaruServers.containsKey(faruServer.getName())) {
            			FaruServerAPI.getServer(faruServer.getName(), faruServer.getHost().getHostAddress(), faruServer.getPort());
            		}
            		if(faruServer.getStatut() != ServerStatut.DELETE) IServer.update(faruServer);
            	}
            }
        }, 0, 1, TimeUnit.SECONDS);

		
		
		
		super.onEnable();
	}

	public void onDisable() {
		IMaintenance.setState(maintenance);

		super.onDisable();
	}
	
	public Map<String, FaruServerAPI> getServers() {
		return iFaruServers;
	}

	public static void registerServer(String name, Integer port) {
		BungeeCord.getInstance().getConfig().addServer(new BungeeServerInfo(name, new InetSocketAddress("149.202.102.63", port), null, false));
	}
	
	public static void unregisterServer(String name) {
		BungeeCord.getInstance().getConfig().removeServerNamed(name);
	}
	
	public static Boolean isMaintenance() {
		return maintenance;
	}

	public static Boolean isMaintenance(String player) {
		return maintenancePlayers.contains(player);
	}

	public static Main getInstance() {
		return instance;
	}
}
