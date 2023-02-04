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
        this.account.setup();
        this.account.updateLastLogin();
        if (!RedisAccount.isAccountInRedis(this.proxiedPlayer.getUniqueId().toString()))
            RedisAccount.addAccountToRedis(this.account);
    }
}
