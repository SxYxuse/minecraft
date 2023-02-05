package me.sxyxuse.manager;

import me.sxyxuse.manager.commands.Command;
import me.sxyxuse.manager.commands.Test;
import me.sxyxuse.manager.listeners.players.ChatEvent;
import me.sxyxuse.manager.listeners.players.JoinEvent;
import me.sxyxuse.manager.listeners.players.QuitEvent;
import me.sxyxuse.manager.redis.RedisManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Manager extends JavaPlugin {
    public static Manager MANAGER;
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
        this.command.createCommand("test", "", new Test(), "");

        this.registerListeners();

        World world = Bukkit.getWorld("world");
        world.setStorm(false);
        world.setThundering(false);
        world.setTime(1000L);


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
    }

    public void log(Level level, String message) {
        getLogger().log(level, message);
    }
}
