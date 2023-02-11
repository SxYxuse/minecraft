package me.sxyxuse.waitingqueue;

import me.sxyxuse.manager.Manager;
import me.sxyxuse.waitingqueue.commands.Queue;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

public class WaitingQueue extends JavaPlugin {
    public static WaitingQueue WAITINGQUEUE;
    public static List<UUID> playersQueue = new ArrayList<>();

    public static WaitingQueue getInstance() {
        return WAITINGQUEUE;
    }

    @Override
    public void onEnable() {
        WAITINGQUEUE = this;

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
