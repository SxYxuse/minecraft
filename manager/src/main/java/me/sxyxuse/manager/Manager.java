package me.sxyxuse.manager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Manager extends JavaPlugin {

    @Override
    public void onEnable() {
        this.registerListeners();
        this.log(Level.INFO, "Plugin démarré avec succès !");
    }

    @Override
    public void onDisable() {
        this.log(Level.INFO, "Plugin arrêté avec succès !");
    }

    private void registerListeners() {
        final PluginManager pm = Bukkit.getPluginManager();
    }

    public void log(Level level, String message) {
        getLogger().log(level, message);
    }
}
