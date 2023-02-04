package me.sxyxuse.apibungee.redis;

import me.sxyxuse.commons.users.Account;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.HashMap;

public class RedisAccount {
    public static final String _KEY = "account:";

    private static final Jedis jedis = RedisManager.getJedis();

    public static boolean isAccountInRedis(String uuid) {
        try {
            return jedis.hget(_KEY + uuid, uuid) != null;
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void addAccountToRedis(Account account) {
        final HashMap<String, String> acc = new HashMap<>();
        acc.put("uuid:", account.getUuid().toString());
        acc.put("pseudo:", account.getPropsPseudo());
        acc.put("money:", String.valueOf(account.getPropsMoney()));
        acc.put("permissions:", String.valueOf(account.getPropsPermissions()));

        try {
            jedis.hset(_KEY + account.getUuid().toString(), acc);
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
}
