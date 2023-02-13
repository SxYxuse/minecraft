package me.sxyxuse.manager.listeners.players;

import me.sxyxuse.commons.users.Account;
import me.sxyxuse.manager.permissions.Permissions;
import me.sxyxuse.manager.redis.RedisAccount;
import me.sxyxuse.manager.scoreboards.ScoreboardBuilder;
import org.bukkit.Bukkit;
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

        final ScoreboardBuilder scoreboardBuilder = new ScoreboardBuilder(ChatColor.GOLD + "Server Name");
        scoreboardBuilder.addScore("");
        scoreboardBuilder.addScore(ChatColor.GOLD + "➢ Informations");
        scoreboardBuilder.addScore(" §7  Pseudo : §b" + account.getPseudo());
        scoreboardBuilder.addScore(" §7  Rang : " + Permissions.getByPower(account.getPermissions()).getName());
        scoreboardBuilder.addScore(" ");
        scoreboardBuilder.addScore("§6➢ Serveur : " + "§aLobby");
        player.setScoreboard(scoreboardBuilder.build());

        for (Player p : Bukkit.getOnlinePlayers())
            ScoreboardBuilder.update(p);
    }
}
