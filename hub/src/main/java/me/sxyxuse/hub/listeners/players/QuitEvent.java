package me.sxyxuse.hub.listeners.players;

import me.sxyxuse.manager.Manager;
import me.sxyxuse.manager.scoreboards.ScoreboardBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {
    @EventHandler
    public void onQuitEvent(PlayerQuitEvent event) {
        Manager.remNumberOfConnected();
        
        for (Player p : Bukkit.getOnlinePlayers())
            ScoreboardBuilder.update(p);
    }
}
