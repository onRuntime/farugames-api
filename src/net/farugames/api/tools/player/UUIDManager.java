package net.farugames.api.tools.player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.JSONObject;

public class UUIDManager {

	public final static Map<String, UUID> uuidCache = new HashMap<String, UUID>();
	public final static Map<String, String> nameCache = new HashMap<String, String>();
	
	public static UUID getUUID(String playerName) {
		if (uuidCache.containsKey(playerName))
			return uuidCache.get(playerName);
		return generateUUID(playerName);
	}

	public static String getUUIDInString(String playerName) {
		if (uuidCache.containsKey(playerName))
			return uuidCache.get(playerName).toString();
		return generateUUID(playerName).toString();
	}

	private static UUID generateUUID(String playerName) {
		if (uuidCache.containsKey(playerName))
			return uuidCache.get(playerName);
		try {
			URLConnection conn = new URL("https://api.mojang.com/users/profiles/minecraft/" + playerName)
					.openConnection();
			String response = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while (br.ready()) {
				response = response + br.readLine();
			}
			JSONObject json = new JSONObject(response);
			String uuidName = json.getString("id");
			UUID uuid = new UUID(new BigInteger(uuidName.substring(0, 16), 16).longValue(),
					new BigInteger(uuidName.substring(16), 16).longValue());
			uuidCache.put(playerName, uuid);
			return uuid;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getName(String uuid) {
		if (nameCache.containsKey(uuid))
			return nameCache.get(uuid);
		try {
			URLConnection conn = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid.replace("-", ""))
					.openConnection();
			String response = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while (br.ready()) {
				response = response + br.readLine();
			}
			JSONObject json = new JSONObject(response);
			String pseudoName = json.getString("name");
			nameCache.put(uuid, pseudoName);
			return pseudoName;
		} catch (Exception e) {
			return null;
		}
	}
}