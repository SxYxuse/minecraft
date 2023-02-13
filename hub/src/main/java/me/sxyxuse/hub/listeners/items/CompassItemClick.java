package me.sxyxuse.hub.listeners.items;

import me.sxyxuse.hub.Hub;
import me.sxyxuse.hub.listeners.compass.InteractEvent;
import me.sxyxuse.hub.waitingqueue.QueueScheduler;
import me.sxyxuse.manager.inventories.InventoryBuilder;
import me.sxyxuse.manager.redis.RedisWaitingQueue;
import me.sxyxuse.manager.servers.Servers;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class CompassItemClick implements Listener {
    private final Hub hub = Hub.getInstance();
    private final String key = "queue_";
    private final HashMap<String, QueueScheduler> queueMap = new HashMap<>();

    @EventHandler
    public void onCompassItemClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null)
            return;

        if (event.getClickedInventory().getHolder() instanceof InventoryBuilder) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null)
                return;

            final Player player = (Player) event.getWhoClicked();
            final UUID uuid = player.getUniqueId();

            final ItemStack itemStack = event.getCurrentItem();
            if (itemStack.getType() == InteractEvent.MINING.getType() || itemStack.getType() == InteractEvent.BUILD.getType())
                for (Servers server : Servers.values())
                    if (server.getDisplayName().equals(Objects.requireNonNull(itemStack.getItemMeta()).getDisplayName())) {
                        final String keyName = key + server.getBungeeServerName();
                        final String serverQueueName = server.getQueueName();
                        final String prefix = ChatColor.GOLD + "[" + serverQueueName + "]";

                        if (!this.checkIfUidInQueue(uuid)) {
                            this.addInQueue(player, prefix, keyName, uuid, server);
                        } else
                            player.sendMessage(ChatColor.RED + "Vous êtes déjà dans une file d'attente !");
                    }
            player.closeInventory();
        }
    }

    private boolean checkIfUidInQueue(UUID uuid) {
        return RedisWaitingQueue.getPosInQueue(this.getKey() + "mining", uuid) != -1 ||
                RedisWaitingQueue.getPosInQueue(this.getKey() + "build", uuid) != -1;
    }

    private void addInQueue(Player player, String prefix, String keyName, UUID uuid, Servers server) {
        RedisWaitingQueue.addUuidInQueue(keyName, uuid);
        player.sendMessage(prefix + " Vous avez été ajouté à la file d'attente.");
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD + " Vous êtes à la position " + (RedisWaitingQueue.getPosInQueue(keyName, uuid) + 1) + "/" + RedisWaitingQueue.getQueueSize(keyName) + "."));
        final QueueScheduler queue = new QueueScheduler(hub, server, keyName);
        if (!queue.isInStart()) {
            queue.setInStart(true);
            queue.runTaskTimer(hub, 4 * 20L, 4 * 20L);
        }
    }

    private String getKey() {
        return key;
    }
}
