package me.sxyxuse.apibungee.redis;

import me.sxyxuse.apibungee.ApiBungee;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.providers.PooledConnectionProvider;

import java.util.logging.Level;

public class RedisManager {
    private static UnifiedJedis jedis;
    private final String host;
    private final int port;

    public RedisManager(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static UnifiedJedis getJedis() {
        return jedis;
    }

    public void start() {
        if (!IsRunning()) {
//            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//            jedisPoolConfig.setMaxTotal(10);
//
//            jedis = new JedisPool(jedisPoolConfig, this.host, this.port).getResource();
            HostAndPort config = new HostAndPort(Protocol.DEFAULT_HOST, 6379);
            PooledConnectionProvider provider = new PooledConnectionProvider(config);

            jedis = new UnifiedJedis(provider);
        }

        ApiBungee.getInstance().log(Level.WARNING, "La connexion à REDIS à été établie.");
    }

    public void stop() {
        if (IsRunning())
            ApiBungee.getInstance().log(Level.WARNING, "La connexion à REDIS à été interrompue.");
    }

    public boolean IsRunning() {
        return jedis != null;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
