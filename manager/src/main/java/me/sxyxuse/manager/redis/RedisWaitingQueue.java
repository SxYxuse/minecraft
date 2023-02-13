package me.sxyxuse.manager.redis;

import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.UUID;

public class RedisWaitingQueue {
    private static final UnifiedJedis jedis = RedisManager.getJedis();

    public static void addUuidInQueue(String queueName, UUID uuid) {
        try {
            jedis.rpush(queueName, uuid.toString());
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }
    }

    public static void removeUuidFromQueue(String queueName, UUID uuid) {
        try {
            jedis.lrem(queueName, 1, uuid.toString());
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }
    }

    public static String getFromIndex(String queueName, long i) {
        try {
            return jedis.lindex(queueName, i);
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long getPosInQueue(String queueName, UUID uuid) {
        try {
            return jedis.lrange(queueName, 0, -1).indexOf(uuid.toString());
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static long getQueueSize(String queueName) {
        try {
            return jedis.llen(queueName);
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static void delQueue(String queueName) {
        try {
            jedis.del(queueName);
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }
    }
}
