package me.sxyxuse.manager.listeners.players;

import me.sxyxuse.manager.Manager;
import me.sxyxuse.manager.redis.RedisAccount;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class JoinEvent implements Listener {
    @EventHandler
    public void onJointEvent(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final UUID uuid = player.getUniqueId();
        Manager.accountsPerms.put(uuid, RedisAccount.getPermissions(uuid.toString()));

        event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.GOLD + player.getName());
    }
}
