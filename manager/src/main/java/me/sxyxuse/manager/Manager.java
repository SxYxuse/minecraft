package me.sxyxuse.manager;

import me.sxyxuse.manager.commands.Command;
import me.sxyxuse.manager.listeners.commands.PreprocessEvent;
import me.sxyxuse.manager.listeners.players.ChatEvent;
import me.sxyxuse.manager.listeners.players.JoinEvent;
import me.sxyxuse.manager.listeners.players.QuitEvent;
import me.sxyxuse.manager.redis.RedisManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;

public class Manager extends JavaPlugin {
    public static Manager MANAGER;
    public static HashMap<UUID, Integer> accountsPerms = new HashMap<>();
    public RedisManager redisManager;
    public Command command;

    public static Manager getInstance() {
        return MANAGER;
    }


    public Command getCommand() {
        return command;
    }

    @Override
    public void onEnable() {
        MANAGER = this;

        this.redisManager = new RedisManager("127.0.0.1", 6379);
        this.redisManager.start();
        this.command = new Command();

        this.registerListeners();

        this.log(Level.INFO, "Plugin démarré avec succès !");
    }

    @Override
    public void onDisable() {
        this.redisManager.stop();

        this.log(Level.INFO, "Plugin arrêté avec succès !");
    }

    private void registerListeners() {
        final PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinEvent(), this);
        pm.registerEvents(new QuitEvent(), this);
        pm.registerEvents(new ChatEvent(), this);
        pm.registerEvents(new PreprocessEvent(), this);
    }

    public void log(Level level, String message) {
        getLogger().log(level, message);
    }
}
