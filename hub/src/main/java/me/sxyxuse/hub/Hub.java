package me.sxyxuse.hub;

import me.sxyxuse.hub.commands.Queue;
import me.sxyxuse.manager.Manager;
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

        Manager.getInstance().getCommand().createCommand("queue", "", new Queue(), "");

        this.log(Level.INFO, "Plugin démarré avec succès !");
    }

    @Override
    public void onDisable() {
        this.log(Level.INFO, "Plugin arrêté avec succès !");
    }

    public void log(Level level, String message) {
        getLogger().log(level, message);
    }
}
