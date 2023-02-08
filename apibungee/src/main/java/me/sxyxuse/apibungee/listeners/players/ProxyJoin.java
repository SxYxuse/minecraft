package me.sxyxuse.apibungee.listeners.players;

import com.google.gson.JsonObject;
import me.sxyxuse.apibungee.api.Request;
import me.sxyxuse.apibungee.core.AccountProvider;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.IOException;

public class ProxyJoin implements Listener {
    @EventHandler
    public void onProxyJoin(PostLoginEvent event) throws IOException, InterruptedException {
        final AccountProvider accountProvider = new AccountProvider(event.getPlayer());
        accountProvider.initAccount();

        JsonObject json = new Request("/player").getWithHeader("uuid", event.getPlayer().getUniqueId().toString());
        System.out.println(json);
    }
}
