package me.sxyxuse.manager.listeners.commands;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PreprocessEvent implements Listener {
    @EventHandler
    public void onPreprocessEvent(PlayerCommandPreprocessEvent event) {
        final String message = event.getMessage();

        if (message.startsWith("/") && message.length() == 1) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "Merci de spécifier la commande !");
        }

    }
}
