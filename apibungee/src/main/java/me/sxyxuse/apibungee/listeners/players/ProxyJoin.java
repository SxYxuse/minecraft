package me.sxyxuse.apibungee.listeners.players;

import me.sxyxuse.apibungee.core.AccountProvider;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyJoin implements Listener {
    @EventHandler
    public void onProxyJoin(PostLoginEvent event) {
        final AccountProvider accountProvider = new AccountProvider(event.getPlayer());
        accountProvider.initAccount();
//        BungeeCord.getInstance().getScheduler().runAsync(ApiBungee.getInstance(), () -> {
//
//        });
    }
}
