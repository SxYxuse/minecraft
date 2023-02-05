package me.sxyxuse.manager.redis;

import me.sxyxuse.commons.users.Account;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.json.Path;

public class RedisAccount {
    public static final String _KEY = "account:";

    private static final UnifiedJedis jedis = RedisManager.getJedis();

    public static boolean isAccountInRedis(String uuid) {
        try {
            return jedis.jsonGet(_KEY + uuid, new Path(".uuid")) != null;
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void addAccountToRedis(Account account) {
        try {
            jedis.jsonSet(_KEY + account.getUuid().toString(), account.getJson());
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }
    }

    public static void dellAccountFromRedis(String uuid) {
        try {
            jedis.del(_KEY + uuid);
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }
    }

    public static void expireAccountFromRedis(String uuid) {
        try {
            jedis.expire(_KEY + uuid, 60 * 30);
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }
    }

    public static void persistAccountFromRedis(String uuid) {
        try {
            jedis.persist(_KEY + uuid);
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }
    }

    public static String getPseudo(String uuid) {
        try {
            return (String) jedis.jsonGet(_KEY + uuid, new Path(".pseudo"));
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static int getPermissions(String uuid) {
        try {
            return ((Double) jedis.jsonGet(_KEY + uuid, new Path(".permissions"))).intValue();
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
