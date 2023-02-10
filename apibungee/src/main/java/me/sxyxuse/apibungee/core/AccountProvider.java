package me.sxyxuse.apibungee.core;

import me.sxyxuse.apibungee.redis.RedisAccount;
import me.sxyxuse.commons.users.Account;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class AccountProvider {
    private final ProxiedPlayer proxiedPlayer;
    private final Account account;

    public AccountProvider(ProxiedPlayer proxiedPlayer) {
        this.proxiedPlayer = proxiedPlayer;
        this.account = new Account(this.proxiedPlayer);
    }

    public void initAccount() {
        String uuid = this.proxiedPlayer.getUniqueId().toString();

        this.account.setup();
        this.account.updateLastLogin();
        
        if (!RedisAccount.isAccountInRedis(uuid))
            RedisAccount.addAccountToRedis(this.account);
        else
            RedisAccount.persistAccountFromRedis(uuid);
    }
}
