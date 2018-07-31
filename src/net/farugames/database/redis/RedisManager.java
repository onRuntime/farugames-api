package net.farugames.database.redis;

import redis.clients.jedis.JedisPool;

/**
 * Created by SweetKebab_ on 2018-07-25
 */
public class RedisManager {

    private final String host, password;
    private final int port;

    private static RedisDatabase redisDatabase;

    public RedisManager(String host, String password, int port) {
        this.host = host;
        this.password = password;
        this.port = port;
    }

    public void connect() {
        redisDatabase = new RedisDatabase(host, password, port);
        redisDatabase.connect();
    }

    public void disconnect() {
        redisDatabase.disconnect();
    }

    public static JedisPool getJedisPool() {
        return redisDatabase.getJedisPool();
    }

    public static RedisDatabase getRedisDatabase() {
        return redisDatabase;
    }
}
