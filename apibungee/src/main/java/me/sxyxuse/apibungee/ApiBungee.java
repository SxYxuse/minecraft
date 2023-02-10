package me.sxyxuse.apibungee;

import me.sxyxuse.apibungee.listeners.players.ProxyJoin;
import me.sxyxuse.apibungee.listeners.players.ProxyLeft;
import me.sxyxuse.apibungee.redis.RedisManager;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import java.util.logging.Level;

public class ApiBungee extends Plugin {

    public static ApiBungee APIBUNGEE;
    public RedisManager redisManager;

    public static ApiBungee getInstance() {
        return APIBUNGEE;
    }

    @Override
    public void onEnable() {
        APIBUNGEE = this;

        this.redisManager = new RedisManager("127.0.0.1", 6379);
        this.redisManager.start();

        this.registerListeners();

        this.log(Level.INFO, "Plugin démarré avec succès !");
    }

    @Override
    public void onDisable() {
        this.redisManager.stop();

        this.log(Level.INFO, "Plugin arrêté avec succès !");
    }

    private void registerListeners() {
        final PluginManager pm = getProxy().getPluginManager();
        pm.registerListener(this, new ProxyJoin());
        pm.registerListener(this, new ProxyLeft());
    }

    public void log(Level level, String message) {
        getLogger().log(level, message);
    }
}
