package me.sxyxuse.apibungee.listeners.players;

import me.sxyxuse.apibungee.core.AccountProvider;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.IOException;

public class ProxyJoin implements Listener {
    @EventHandler
    public void onProxyJoin(PostLoginEvent event) throws IOException {
        final AccountProvider accountProvider = new AccountProvider(event.getPlayer());
        accountProvider.initAccount();
//        Account account = new Account(event.getPlayer());
//        JSONObject obj = account.getJson();
//        obj.put("first_login", System.currentTimeMillis());
//        obj.put("last_login", System.currentTimeMillis());
//
//        JsonObject json = new Request("/aplayer").post(obj);
//        System.out.println(json);
    }
}
