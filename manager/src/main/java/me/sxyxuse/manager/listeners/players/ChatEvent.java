package me.sxyxuse.manager.listeners.players;

import me.sxyxuse.manager.Manager;
import me.sxyxuse.manager.permissions.Permissions;
import me.sxyxuse.manager.redis.RedisAccount;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;

public class ChatEvent implements Listener {
    protected final String ERROR_SPAM = "Merci de patienter une seconde avant de renvoyer un message !";
    private final ArrayList<Player> cooldown = new ArrayList<>();

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        final Permissions perms = Permissions.getByPower(RedisAccount.getPermissions(player.getUniqueId().toString()));
        final String prefix = perms.getPrefix();
        final String messageColor = perms.getMessageColor();
        final TextComponent chatContent = new TextComponent();

        chatContent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/test"));
        chatContent.addExtra(prefix + " " + player.getName() + " §7» " + messageColor + event.getMessage());

        event.setFormat(perms.getPrefix() + " %1$s §7» %2$s");

        if (checkPlayerCooldown(player)) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + ERROR_SPAM);
            return;
        }

        for (Player playerOnline : Bukkit.getOnlinePlayers())
            playerOnline.spigot().sendMessage(chatContent);

        event.setCancelled(true);

        cooldown.add(player);
        Bukkit.getScheduler().runTaskLater(Manager.getInstance(), () -> cooldown.remove(player), 20L);
    }

    private boolean checkPlayerCooldown(Player player) {
        return cooldown.contains(player);
    }
}
