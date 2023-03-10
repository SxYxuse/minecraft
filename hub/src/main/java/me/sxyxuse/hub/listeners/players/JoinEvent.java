package me.sxyxuse.hub.listeners.players;

import me.sxyxuse.commons.users.Account;
import me.sxyxuse.manager.permissions.Permissions;
import me.sxyxuse.manager.redis.RedisAccount;
import me.sxyxuse.manager.scoreboards.ScoreboardBuilder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
//    public static final ItemStack COMPASS =
//            ItemBuilder.createItem(
//                    "Cliquez-ici pour changer de serveur !",
//                    Material.COMPASS,
//                    Arrays.asList("Permet de choisir un serveur"),
//                    new ItemFlag[]{
//                            ItemFlag.HIDE_ATTRIBUTES,
//                            ItemFlag.HIDE_DESTROYS,
//                            ItemFlag.HIDE_UNBREAKABLE
//                    }
//            );

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final Account account = RedisAccount.getAccount(player);
//        final Inventory inventory = event.getPlayer().getInventory();
//        inventory.clear();
//        inventory.addItem(COMPASS);

        final ScoreboardBuilder scoreboardBuilder = new ScoreboardBuilder(" »  Survivia  « ");
        scoreboardBuilder.addScore("");
        scoreboardBuilder.addScore(ChatColor.GOLD + "➢ Informations");
        scoreboardBuilder.addScore(" §7  Pseudo : §b" + account.getPseudo());
        scoreboardBuilder.addScore(" §7  Rang : " + Permissions.getByPower(account.getPermissions()).getName());
        scoreboardBuilder.addScore(" ");
        scoreboardBuilder.addScore("§6➢ Serveur : " + "§aLobby");
        player.setScoreboard(scoreboardBuilder.build());
    }
}
