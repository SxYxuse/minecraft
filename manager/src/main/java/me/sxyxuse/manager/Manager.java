package me.sxyxuse.manager;

import me.sxyxuse.manager.listeners.players.ChatEvent;
import me.sxyxuse.manager.listeners.players.JoinEvent;
import me.sxyxuse.manager.listeners.players.QuitEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Manager extends JavaPlugin {
    public static Manager MANAGER;

    public static Manager getInstance() {
        return MANAGER;
    }


    @Override
    public void onEnable() {
        MANAGER = this;

        this.registerListeners();

        this.log(Level.INFO, "Plugin démarré avec succès !");
    }

    @Override
    public void onDisable() {
        this.log(Level.INFO, "Plugin arrêté avec succès !");
    }

    private void registerListeners() {
        final PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinEvent(), this);
        pm.registerEvents(new QuitEvent(), this);
        pm.registerEvents(new ChatEvent(), this);
    }

    public void log(Level level, String message) {
        getLogger().log(level, message);
    }
}
