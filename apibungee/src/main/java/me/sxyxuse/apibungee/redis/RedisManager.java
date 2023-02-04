package me.sxyxuse.apibungee.redis;

import me.sxyxuse.apibungee.ApiBungee;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.logging.Level;

public class RedisManager {
    private static Jedis jedis;
    private final String host;
    private final int port;

    public RedisManager(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static Jedis getJedis() {
        return jedis;
    }

    public void start() {
        if (!IsRunning()) {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(10);

            jedis = new JedisPool(jedisPoolConfig, this.host, this.port).getResource();
        }

        ApiBungee.getInstance().log(Level.WARNING, "La connexion à REDIS à été établie.");
    }

    public void stop() {
        if (IsRunning())
            jedis.disconnect();

        ApiBungee.getInstance().log(Level.WARNING, "La connexion à REDIS à été interrompue.");
    }

    public boolean IsRunning() {
        return jedis != null;
    }
    
}
