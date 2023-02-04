package me.sxyxuse.apibungee.listeners.players;

import me.sxyxuse.apibungee.redis.RedisAccount;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyLeft implements Listener {
    @EventHandler
    public void onProxyLeft(PlayerDisconnectEvent event) {
        RedisAccount.dellAccountFromRedis(event.getPlayer().getUniqueId().toString());
    }
}
