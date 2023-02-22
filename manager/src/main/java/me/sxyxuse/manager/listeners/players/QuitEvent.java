package me.sxyxuse.manager.listeners.players;

import me.sxyxuse.commons.users.Account;
import me.sxyxuse.manager.Manager;
import me.sxyxuse.manager.redis.RedisAccount;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {
    @EventHandler
    public void onQuitEvent(PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        Account account = RedisAccount.getAccount(player);

        event.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.GOLD + account.getPseudo());

        Manager.getInstance().teamsTag.removePlayerFromTeam(account);
    }
}
