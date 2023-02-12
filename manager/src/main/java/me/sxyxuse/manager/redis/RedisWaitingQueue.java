package me.sxyxuse.manager.redis;

import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.UUID;

public class RedisWaitingQueue {
    private static final UnifiedJedis jedis = RedisManager.getJedis();

    public static void addUuidInQueue(UUID uuid) {
        try {
            jedis.rpush("queue", uuid.toString());
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }
    }

    public static void removeUuidFromQueue(UUID uuid) {
        try {
            jedis.lrem("queue", 1, uuid.toString());
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }
    }

    public static String getFromIndex(long i) {
        try {
            return jedis.lindex("queue", i);
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long getPosInQueue(UUID uuid) {
        try {
            return jedis.lrange("queue", 0, -1).indexOf(uuid.toString());
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static long getQueueSize() {
        try {
            return jedis.llen("queue");
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static void delQueue(String key) {
        try {
            jedis.del(key);
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }
    }
}
