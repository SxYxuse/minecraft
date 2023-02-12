package me.sxyxuse.hub.listeners.items;

import me.sxyxuse.hub.Hub;
import me.sxyxuse.hub.listeners.compass.InteractEvent;
import me.sxyxuse.hub.waitingqueue.QueueScheduler;
import me.sxyxuse.manager.inventories.InventoryBuilder;
import me.sxyxuse.manager.redis.RedisWaitingQueue;
import me.sxyxuse.manager.servers.Servers;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class CompassItemClick implements Listener {
    public QueueScheduler queueScheduler;

    @EventHandler
    public void onCompassItemClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null)
            return;

        if (event.getClickedInventory().getHolder() instanceof InventoryBuilder) {
            event.setCancelled(true);
            final Player player = (Player) event.getWhoClicked();

            if (event.getCurrentItem() == null)
                return;

            final ItemStack itemStack = event.getCurrentItem();
            if (itemStack.getType() == InteractEvent.MINING.getType() || itemStack.getType() == InteractEvent.BUILD.getType()) {
                for (Servers server : Servers.values())
                    if (server.getDisplayName().equals(Objects.requireNonNull(itemStack.getItemMeta()).getDisplayName())) {
                        RedisWaitingQueue.addUuidInQueue(player.getUniqueId());

                        player.sendMessage(ChatColor.YELLOW + "[" + ChatColor.GOLD + "File d'attente" + ChatColor.YELLOW + "]" + ChatColor.GOLD + " Vous avez été ajouté dans la file d'attente.");
                        player.sendMessage(ChatColor.YELLOW + "[" + ChatColor.GOLD + "File d'attente" + ChatColor.YELLOW + "]" + ChatColor.GOLD + " Vous êtes à la position " + ChatColor.YELLOW + (RedisWaitingQueue.getPosInQueue(player.getUniqueId()) + 1) + ChatColor.GOLD + "/" + RedisWaitingQueue.getQueueSize() + ".");

                        if (!QueueScheduler.isInStart()) {
                            QueueScheduler.setInStart(true);
                            queueScheduler = new QueueScheduler(Hub.getInstance(), server);
                            queueScheduler.runTaskTimer(Hub.getInstance(), 10 * 20L, 10 * 20L);
                        }
                    }
                player.closeInventory();
            }

        }
    }
}
