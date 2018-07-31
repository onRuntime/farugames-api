package net.farugames.api.bungee;

import net.farugames.api.bungee.listeners.ListenersManager;
import net.farugames.api.bungee.proxiedplayer.FaruBungeePlayer;
import net.farugames.api.bungee.sanctions.Sanction;
import net.farugames.api.bungee.servers.FaruServerAPI;
import net.farugames.api.bungee.servers.ServerStatut;
import net.farugames.api.managers.BungeeCommandManager;
import net.farugames.api.redis.RedisManager;
import net.farugames.api.sql.SQLManager;
import net.farugames.api.sql.accounts.IMaintenance;
import net.farugames.api.sql.accounts.IServer;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.BungeeServerInfo;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.Favicon;
import net.md_5.bungee.api.ServerPing.Protocol;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BungeeFaruAPI extends Plugin {

	private static BungeeFaruAPI instance;
	
	public static Favicon favicon;
	public static Protocol protocol;
	
	public static Map<String, FaruServerAPI> iFaruServers = new HashMap<String, FaruServerAPI>();
	public Collection<ProxiedPlayer> proxyPlayers = new ArrayList<ProxiedPlayer>();

	public static Boolean maintenance = false;
	private static List<String> maintenancePlayers = new ArrayList<String>();

	public static List<Sanction> sanctions = new ArrayList<Sanction>();
	
	public static Map<UUID, FaruBungeePlayer> iFaruPlayer = new HashMap<UUID, FaruBungeePlayer>();

    public SQLManager sqlManager;
    public RedisManager redisManager;

	public void onLoad() {
		instance = this;

        redisManager = new RedisManager("149.202.102.63","b4z5MT4Nk6hA",6379);
        redisManager.connect();

        sqlManager = new SQLManager("149.202.102.63","farugames","proxy","HCK2u7a8Up4d",3306);
        sqlManager.connect();

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
		new ListenersManager(this).registerListeners();
		new BungeeCommandManager().register();
		
		getProxy().getScheduler().schedule(this, () -> {
			for(Iterator<FaruServerAPI> serverI = iFaruServers.values().iterator(); serverI.hasNext();) {
				FaruServerAPI serverAPI = serverI.next();
				if(!exists(serverAPI.getName())) serverAPI.unregister();
				proxyPlayers = getProxy().getPlayers();
			}
			for(FaruServerAPI faruServer : IServer.getServers()) {
				update(faruServer);
				if(faruServer.getStatut() == ServerStatut.FINISH) {
				}
				if(faruServer.getStatut() == ServerStatut.UNREGISTERED) {
					faruServer.register();
				}
				if(!iFaruServers.containsKey(faruServer.getName())) {
					FaruServerAPI.getServer(faruServer.getName(), faruServer.getHost().getHostAddress(), faruServer.getPort());
				}
				if(faruServer.getStatut() != ServerStatut.DELETE) update(faruServer);
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

	public static BungeeFaruAPI getInstance() {
		return instance;
	}

	private static boolean exists(String serverName){
		boolean exists = false;
		try {
			final Connection connection = SQLManager.getRessource();
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT name FROM " + IServer.getTable() + " WHERE name = ?");
			preparedStatement.setString(1, serverName);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) exists = true;
			connection.close();
			rs.close();
			preparedStatement.close();
		} catch (SQLException e) {
			System.err.print("[IServer] Error trying to connect to database : ");
			e.printStackTrace();
		}
		return exists;
	}

	private static void update(FaruServerAPI server) {
		try {
			final Connection connection = SQLManager.getRessource();
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement("SELECT name FROM " + IServer.getTable() + " WHERE name = ?");
			preparedStatement.setString(1, server.getName());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				preparedStatement.close();
				preparedStatement = (PreparedStatement) connection
						.prepareStatement("UPDATE " + IServer.getTable() + " SET mode = ?, statut = ?, onlineplayers = ?, onlineplayersname = ? WHERE name = ?");
				preparedStatement.setString(1, server.getMode().toString());
				preparedStatement.setString(2, server.getStatut().toString());
				preparedStatement.setInt(3, server.getOnlinePlayers());
				preparedStatement.setString(4, server.getPlayers() != null ? server.getPlayers().toString() : "NULL");
				preparedStatement.setString(5, server.getName());
				preparedStatement.executeUpdate();
			}
			preparedStatement.close();
			rs.close();
			connection.close();
		} catch (SQLException e) {
			System.err.print("[IServer] Error trying to connect to database : ");
			e.printStackTrace();
		}
	}
}
