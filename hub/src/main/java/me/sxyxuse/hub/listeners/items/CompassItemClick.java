//package me.sxyxuse.hub.listeners.items;
//
//import me.sxyxuse.hub.Hub;
//import me.sxyxuse.hub.listeners.compass.InteractEvent;
//import me.sxyxuse.hub.waitingqueue.QueueScheduler;
//import me.sxyxuse.manager.inventories.InventoryBuilder;
//import me.sxyxuse.manager.redis.RedisWaitingQueue;
//import me.sxyxuse.manager.servers.Servers;
//import net.md_5.bungee.api.ChatMessageType;
//import net.md_5.bungee.api.chat.TextComponent;
//import org.bukkit.ChatColor;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.inventory.InventoryClickEvent;
//import org.bukkit.inventory.ItemStack;
//
//import java.util.HashMap;
//import java.util.Objects;
//import java.util.UUID;
//
//public class CompassItemClick implements Listener {
//    public static HashMap<String, Boolean> queueMap = new HashMap<>();
//    private final Hub hub = Hub.getInstance();
//
//    @EventHandler
//    public void onCompassItemClick(InventoryClickEvent event) {
//        if (event.getClickedInventory() == null)
//            return;
//
//        if (event.getClickedInventory().getHolder() instanceof InventoryBuilder) {
//            event.setCancelled(true);
//
//            if (event.getCurrentItem() == null)
//                return;
//
//            final Player player = (Player) event.getWhoClicked();
//            final UUID uuid = player.getUniqueId();
//
//            final ItemStack itemStack = event.getCurrentItem();
//            if (itemStack.getType() == InteractEvent.MINING.getType() || itemStack.getType() == InteractEvent.BUILD.getType())
//                for (Servers server : Servers.values())
//                    if (server.getDisplayName().equals(Objects.requireNonNull(itemStack.getItemMeta()).getDisplayName())) {
//                        final String keyName = "queue_" + server.getBungeeServerName();
//                        final String serverQueueName = server.getQueueName();
//                        final String prefix = ChatColor.GOLD + "[" + serverQueueName + "]";
//
//                        if (!this.checkIfUidInQueue(keyName, uuid))
//                            this.addInQueue(player, prefix, keyName, uuid, server);
//                        else
//                            player.sendMessage(ChatColor.RED + "Vous êtes déjà dans une file d'attente !");
//                    }
//            player.closeInventory();
//        }
//    }
//
//    private boolean checkIfUidInQueue(String keyName, UUID uuid) {
//        return RedisWaitingQueue.getPosInQueue(keyName, uuid) != -1 ||
//                RedisWaitingQueue.getPosInQueue(keyName, uuid) != -1;
//    }
//
//    private void addInQueue(Player player, String prefix, String keyName, UUID uuid, Servers server) {
//        RedisWaitingQueue.addUuidInQueue(keyName, uuid);
//        player.sendMessage(prefix + " Vous avez été ajouté à la file d'attente.");
//        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD + " Vous êtes à la position " + (RedisWaitingQueue.getPosInQueue(keyName, uuid) + 1) + "/" + RedisWaitingQueue.getQueueSize(keyName) + "."));
//
//        if (!queueMap.containsKey(keyName))
//            queueMap.put(keyName, false);
//
//        if (!queueMap.get(keyName)) {
//            final QueueScheduler queue = new QueueScheduler(hub, server, keyName);
//            queueMap.put(keyName, true);
//            queue.runTaskTimer(hub, 10 * 20L, 10 * 20L);
//        }
//    }
//}
