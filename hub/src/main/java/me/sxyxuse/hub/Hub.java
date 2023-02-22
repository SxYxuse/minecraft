package me.sxyxuse.hub;

import me.sxyxuse.hub.listeners.players.JoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

public class Hub extends JavaPlugin {
    public static Hub HUB;
    public static List<UUID> playersQueue = new ArrayList<>();

    public static Hub getInstance() {
        return HUB;
    }

    @Override
    public void onEnable() {
        HUB = this;

        this.registerListeners();
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        this.log(Level.INFO, "Plugin démarré avec succès !");
    }

    @Override
    public void onDisable() {
        this.log(Level.INFO, "Plugin arrêté avec succès !");
    }

    private void registerListeners() {
        final PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinEvent(), this);
//        pm.registerEvents(new InteractEvent(), this);
//        pm.registerEvents(new CompassItemClick(), this);
    }

    public void log(Level level, String message) {
        getLogger().log(level, message);
    }
}
