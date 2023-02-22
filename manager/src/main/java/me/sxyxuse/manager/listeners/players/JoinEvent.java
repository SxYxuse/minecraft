package me.sxyxuse.manager.listeners.players;

import me.sxyxuse.commons.users.Account;
import me.sxyxuse.manager.Manager;
import me.sxyxuse.manager.redis.RedisAccount;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class JoinEvent implements Listener {
    @EventHandler
    public void onJointEvent(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final Account account = RedisAccount.getAccount(player);

        event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.GOLD + Objects.requireNonNull(account).getPseudo());

        Manager.getInstance().teamsTag.addPlayerToTeam(account);
    }
}
