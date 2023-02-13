package me.sxyxuse.manager.redis;

import me.sxyxuse.commons.users.Account;
import org.bukkit.entity.Player;
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
            jedis.jsonSet(_KEY + account.getUuid().toString(), account.getJsonAccount());
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

    public static Account getAccount(Player player) {
        final String uuid = player.getUniqueId().toString();
        final String accountKey = _KEY + uuid;
        try {
            final Account account = new Account(player);
            account.setPseudo((String) jedis.jsonGet(accountKey, new Path(".pseudo")));
            account.setMoney(convertToInt(jedis.jsonGet(accountKey, new Path(".money"))));
            account.setPermissions(convertToByte(jedis.jsonGet(accountKey, new Path(".permissions"))));
            account.setGrade(convertToByte(jedis.jsonGet(accountKey, new Path(".grade"))));
            account.setGrade_time_left(convertToInt(jedis.jsonGet(accountKey, new Path(".grade_time_left"))));

            return account;
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static int convertToInt(Object value) {
        return Double.valueOf(value.toString()).intValue();
    }

    private static byte convertToByte(Object value) {
        return Double.valueOf(value.toString()).byteValue();
    }
}
