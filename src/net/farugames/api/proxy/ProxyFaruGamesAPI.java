package net.farugames.api.proxy;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import net.farugames.api.core.lang.I18n;
import net.farugames.api.core.lang.Lang;
import net.farugames.api.core.sanction.Sanction;
import net.farugames.api.core.server.FaruServer;
import net.farugames.api.proxy.commands.CommandsManager;
import net.farugames.api.proxy.listeners.ListenersManager;
import net.farugames.api.proxy.runnables.ServerRunnable;
import net.farugames.database.redis.RedisManager;
import net.farugames.database.sql.SQLManager;
import net.farugames.database.sql.accounts.IMaintenance;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.BungeeServerInfo;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.Favicon;
import net.md_5.bungee.api.ServerPing.Protocol;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

public class ProxyFaruGamesAPI extends Plugin {

	private static ProxyFaruGamesAPI instance;
	
	public static Favicon favicon;
	public static Protocol protocol;
	
	public Collection<ProxiedPlayer> proxyPlayers = new ArrayList<ProxiedPlayer>();

	public static Boolean maintenance = false;
	private static List<String> maintenancePlayers = new ArrayList<String>();

	public static List<Sanction> sanctions = new ArrayList<Sanction>();
	
	public static Map<UUID, ProxiedFaruPlayer> iFaruPlayer = new HashMap<UUID, ProxiedFaruPlayer>();
	public static Map<String, FaruServer> iFaruServer = new HashMap<String, FaruServer>();

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
		new CommandsManager().register();
		
		getProxy().getScheduler().schedule(this, new ServerRunnable(), 0, 1, TimeUnit.SECONDS);
		
		I18n.supportProxyTranslate(Lang.ENGLISH);
		
		super.onEnable();
	}

	public void onDisable() {
		IMaintenance.setState(maintenance);

		super.onDisable();
	}

	public static void registerServer(String name, String ip, Integer port) {
		BungeeCord.getInstance().getConfig().addServer(new BungeeServerInfo(name, new InetSocketAddress(ip, port), null, false));
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

	public static ProxyFaruGamesAPI getInstance() {
		return instance;
	}
	
	public static String getIp() {
		String ip = "127.0.0.1";
		try (final DatagramSocket socket = new DatagramSocket()) {
			socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			ip = socket.getLocalAddress().getHostAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ip;
	}
	/*
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

	private static void update(FaruServer server) {
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
	}*/
}
