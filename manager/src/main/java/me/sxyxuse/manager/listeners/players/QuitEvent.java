package me.sxyxuse.manager.listeners.players;

import me.sxyxuse.manager.Manager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {
    @EventHandler
    public void onQuitEvent(PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        Manager.accountsPerms.remove(player.getUniqueId());

        event.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.GOLD + player.getName());
    }
}
