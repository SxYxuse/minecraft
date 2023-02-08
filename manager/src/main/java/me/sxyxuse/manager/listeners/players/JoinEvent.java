package me.sxyxuse.manager.listeners.players;

import me.sxyxuse.manager.Manager;
import me.sxyxuse.manager.permissions.Permissions;
import me.sxyxuse.manager.redis.RedisAccount;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

import java.util.UUID;

public class JoinEvent implements Listener {
    @EventHandler
    public void onJointEvent(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final UUID uuid = player.getUniqueId();
        Manager.accountsPerms.put(uuid, RedisAccount.getPermissions(uuid.toString()));

        event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.GOLD + player.getName());

        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective obj = scoreboard.registerNewObjective("lobby", Criteria.DUMMY, "lobby");
        obj.setDisplayName(ChatColor.GOLD + "  » Server Name «  ");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score void_space1 = obj.getScore("");
        void_space1.setScore(8);

        Score information = obj.getScore(ChatColor.GOLD + "➢ Informations");
        information.setScore(7);

        Score pseudo = obj.getScore(" §7  Pseudo : §b" + RedisAccount.getPseudo(uuid.toString()));
        pseudo.setScore(6);

        Score rank = obj.getScore(" §7  Rang : " + Permissions.getByPower(Manager.accountsPerms.get(uuid)).getName());
        rank.setScore(5);

        Score void_space2 = obj.getScore(" ");
        void_space2.setScore(4);

        Score server = obj.getScore("§6➢ Serveur : " + "§aLobby");
        server.setScore(3);

        Score player_online = obj.getScore(" §8  En ligne(s) : " + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getServer().getMaxPlayers());
        player_online.setScore(2);

        player.setScoreboard(scoreboard);
    }
}
