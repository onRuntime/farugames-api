package net.farugames.database.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by SweetKebab_ on 2018-07-25
 */
public class RedisDatabase {

    private final String host, password;
    private final int port;

    private JedisPool jedisPool;

    public RedisDatabase(String host, String password, int port) {
        this.host = host;
        this.password = password;
        this.port = port;
    }

    public void connect() {
        ClassLoader previous = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(Jedis.class.getClassLoader());
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPool = new JedisPool(jedisPoolConfig, host, port, 3000, password);
        Thread.currentThread().setContextClassLoader(previous);
        try (Jedis jedisConnect = jedisPool.getResource()) {
            System.out.println("[RedisDatabase] FaruGames vient de se connecter ��� Redis.");
        } catch (Exception e) {
            System.out.println("[RedisDatabase] Erreur: impossible de se connecter ��� Redis.");
        }
    }

    public void disconnect() {
        try {
            jedisPool.close();
            System.out.println("[RedisDatabase] FaruGames vient de se d���connecter à Redis.");
        } catch (Exception e) {
            System.out.println("[RedisDatabase] Erreur: impossible de se déconnecter à Redis.");
            return;
        }
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }
}
