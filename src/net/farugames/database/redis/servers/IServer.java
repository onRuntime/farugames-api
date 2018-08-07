package net.farugames.database.redis.servers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.farugames.api.core.server.ServerStatus;
import net.farugames.api.core.server.ServerType;
import net.farugames.database.redis.RedisManager;
import redis.clients.jedis.Jedis;

public class IServer {

	public static void create(String name, String ip, Integer port, String host, String map) {
		try (Jedis jedis = RedisManager.getRedisDatabase().getJedisPool().getResource()) {
			jedis.set("servers:" + "name" + ":" + name, name);
			jedis.set("servers:" + "ip" + ":" + name, ip);
			jedis.set("servers:" + "port" + ":" + name, port.toString());
			jedis.set("servers:" + "status" + ":" + name, "LOADING");
			jedis.set("servers:" + "host" + ":" + name, host);
			jedis.set("servers:" + "map" + ":" + name, map);
			jedis.set("servers:" + "onlineplayers" + ":" + name, "0");
			jedis.rpush("servers:" + "onlineplayersname" + ":" + name, "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void request(ServerType serverType) {
		request(serverType, "default", "default");
	}

	public static void request(ServerType serverType, String host, String map) {
		try (Jedis jedis = RedisManager.getRedisDatabase().getJedisPool().getResource()) {
			jedis.rpush("requested_list:" + serverType.toString().toLowerCase() + ":" + "host", host);
			jedis.rpush("requested_list:" + serverType.toString().toLowerCase() + ":" + "map", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void remove(String serverName) {
		try (Jedis jedis = RedisManager.getRedisDatabase().getJedisPool().getResource()) {
			jedis.set("servers:" + "status" + ":" + serverName, ServerStatus.DELETE.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void delete(String serverName) {
		try (Jedis jedis = RedisManager.getRedisDatabase().getJedisPool().getResource()) {
			jedis.del("servers:" + "name" + ":" + serverName);
			jedis.del("servers:" + "ip" + ":" + serverName);
			jedis.del("servers:" + "port" + ":" + serverName);
			jedis.del("servers:" + "status" + ":" + serverName);
			jedis.del("servers:" + "host" + ":" + serverName);
			jedis.del("servers:" + "map" + ":" + serverName);
			jedis.del("servers:" + "onlineplayers" + ":" + serverName);
			jedis.del("servers:" + "onlineplayersname" + ":" + serverName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setStatus(String serverName, ServerStatus serverStatus) {
		try (Jedis jedis = RedisManager.getRedisDatabase().getJedisPool().getResource()) {
			jedis.set("servers:" + "status" + ":" + serverName, serverStatus.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ServerStatus getStatus(String serverName) {
		ServerStatus serverStatus = null;
		try (Jedis jedis = RedisManager.getRedisDatabase().getJedisPool().getResource()) {
			serverStatus = ServerStatus.valueOf(jedis.get("servers:" + "status" + ":" + serverName)) != null
					? ServerStatus.valueOf(jedis.get("servers:" + "status" + ":" + serverName))
					: null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serverStatus;
	}
	
	public static List<String> getServersStatusList() {
		List<String> list = new ArrayList<String>();
		try (Jedis jedis = RedisManager.getRedisDatabase().getJedisPool().getResource()) {
			String keys = jedis.keys("*servers:status*").toString();
			String keysResult = keys.replace("[", "").replace("]", "").replace(" ", "");
			List<String> keysList = new ArrayList<String>(Arrays.asList(keysResult.split(",")));
			for (int i = 0; i < keysList.size(); i++) {
				list.add(jedis.get(keysList.get(i)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<String> getServersNameList() {
		List<String> list = new ArrayList<String>();
		try (Jedis jedis = RedisManager.getRedisDatabase().getJedisPool().getResource()) {
			String keys = jedis.keys("*servers:name*").toString();
			String keysResult = keys.replace("[", "").replace("]", "").replace(" ", "");
			List<String> keysList = new ArrayList<String>(Arrays.asList(keysResult.split(",")));
			for (int i = 0; i < keysList.size(); i++) {
				list.add(jedis.get(keysList.get(i)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Integer> getServersPortList() {
		List<Integer> list = new ArrayList<Integer>();
		try (Jedis jedis = RedisManager.getRedisDatabase().getJedisPool().getResource()) {
			String keys = jedis.keys("*servers:port*").toString();
			String keysResult = keys.replace("[", "").replace("]", "").replace(" ", "");
			List<String> keysList = new ArrayList<String>(Arrays.asList(keysResult.split(",")));
			for (int i = 0; i < keysList.size(); i++) {
				String key = jedis.get(keysList.get(i));
				if (key != null) {
					list.add(Integer.parseInt(key));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
