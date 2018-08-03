package net.farugames.api.proxy.listeners.event;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.farugames.api.core.currency.Currency;
import net.farugames.api.core.data.DataType;
import net.farugames.api.proxy.ProxiedFaruPlayer;
import net.farugames.api.spigot.FaruPlayer;
import net.farugames.database.redis.RedisManager;
import net.farugames.database.sql.accounts.ICurrency;
import net.farugames.database.sql.accounts.IData;
import net.farugames.database.sql.accounts.IExperience;
import net.farugames.database.sql.accounts.IFriend;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import redis.clients.jedis.Jedis;

public class ServerDisconnectListener implements Listener {

	@EventHandler
	public void onServerDisconnect(ServerDisconnectEvent event) {
		ProxiedFaruPlayer.getPlayer(event.getPlayer().getUniqueId()).disconnect();
		UUID uuid = event.getPlayer().getUniqueId();

		for (Currency currency : Currency.values()) {
			Integer iCoins = 0;
			try (Jedis jedis = RedisManager.getJedisPool().getResource()) {
				if (jedis.exists("currency:" + uuid.toString() + ":" + currency.getColumn())) {
					iCoins = Integer.parseInt(jedis.get("currency:" + uuid.toString() + ":" + currency.getColumn()));
					jedis.del("currency:" + uuid.toString() + ":" + currency.getName());
					ICurrency.setCoins(uuid, currency, iCoins);
				}
			} catch (Exception e) {
				System.out.println("[IPlayer] Impossible de mettre à jours les Coins du Joueur.");
			}
		}
		
		try(Jedis jedis = RedisManager.getJedisPool().getResource()) {
			if(jedis.exists("friends:" + uuid.toString())) {
				List<UUID> friends = new ArrayList<UUID>();
				for(FaruPlayer faruPlayer : FaruPlayer.getPlayer(uuid).getFriends()) {
					friends.add(faruPlayer.getUUID());
				}
				IFriend.setFriend(uuid, friends);
				jedis.del("friends:" + uuid.toString());
			}
		} catch(Exception exception) {
			System.err.println("[IPlayer] Error trying to get Redis Resource");
		}

		for (DataType dataType : DataType.values()) {
			Object object = false;
			try (Jedis jedis = RedisManager.getJedisPool().getResource()) {
				if (jedis.exists("playerdata:" + uuid.toString() + ":" + dataType.getColumn())) {
					object = (boolean) Boolean
							.parseBoolean(jedis.get("playerdata:" + uuid.toString() + ":" + dataType.getColumn()));
					jedis.del("playerdata:" + uuid.toString() + ":" + dataType.getColumn());
					IData.setData(uuid, dataType, object);
				}
			} catch (Exception e) {
				System.out.println("[Disconnect] Impossible de mettre à jours les informations du Joueur.");
			}
		}

			Integer iLevel = 0;
			try (Jedis jedis = RedisManager.getJedisPool().getResource()) {
				if (jedis.exists("levelexperience:" + uuid.toString())) {
					iLevel = Integer
							.parseInt(jedis.get("levelexperience:" + uuid.toString()));
					jedis.del("levelexperience:" + uuid.toString());
					IExperience.setExperience(uuid, iLevel);
				}
			} catch (Exception e) {
				System.out.println("[IPlayer] Impossible de mettre à jour les Experiences du Joueur.");
			}

	}
}
