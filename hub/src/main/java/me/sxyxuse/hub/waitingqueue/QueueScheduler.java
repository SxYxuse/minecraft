//package me.sxyxuse.hub.waitingqueue;
//
//import com.google.common.io.ByteArrayDataOutput;
//import com.google.common.io.ByteStreams;
//import me.sxyxuse.hub.Hub;
//import me.sxyxuse.hub.listeners.items.CompassItemClick;
//import me.sxyxuse.manager.redis.RedisWaitingQueue;
//import me.sxyxuse.manager.servers.Servers;
//import net.md_5.bungee.api.ChatMessageType;
//import net.md_5.bungee.api.chat.TextComponent;
//import org.bukkit.Bukkit;
//import org.bukkit.ChatColor;
//import org.bukkit.entity.Player;
//import org.bukkit.scheduler.BukkitRunnable;
//
//import java.util.Objects;
//import java.util.UUID;
//
//public class QueueScheduler extends BukkitRunnable {
//    private final Hub hub;
//    private final Servers server;
//    private final String queueName;
//
//    public QueueScheduler(Hub hub, Servers server, String queueName) {
//        this.hub = hub;
//        this.server = server;
//        this.queueName = queueName;
//    }
//
//    public void sendNewPositionMessage() {
//        for (int i = 0; i < RedisWaitingQueue.getQueueSize(this.getQueueName()); i++) {
//            final Player player = Bukkit.getPlayer(UUID.fromString(RedisWaitingQueue.getFromIndex(this.getQueueName(), i)));
//            Objects.requireNonNull(player).spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD + " Vous êtes à la position " + ChatColor.YELLOW + (RedisWaitingQueue.getPosInQueue("queue_" + this.server.getBungeeServerName(), player.getUniqueId()) + 1) + ChatColor.GOLD + "/" + RedisWaitingQueue.getQueueSize("queue_" + this.server.getBungeeServerName()) + "."));
//        }
//    }
//
//    @Override
//    public void run() {
//        if (RedisWaitingQueue.getQueueSize(this.getQueueName()) > 0) {
//            final ByteArrayDataOutput output = ByteStreams.newDataOutput();
//            output.writeUTF("Connect");
//            output.writeUTF(this.server.getBungeeServerName());
//
//            final Player player = Bukkit.getPlayer(UUID.fromString(RedisWaitingQueue.getFromIndex(this.getQueueName(), 0)));
//            Objects.requireNonNull(player).sendPluginMessage(Hub.getInstance(), "BungeeCord", output.toByteArray());
//            RedisWaitingQueue.removeUuidFromQueue(this.getQueueName(), player.getUniqueId());
//            sendNewPositionMessage();
//        } else {
//            CompassItemClick.queueMap.replace(queueName, false);
//            RedisWaitingQueue.delQueue(this.getQueueName());
//            cancel();
//        }
//    }
//
//    public Hub getHub() {
//        return hub;
//    }
//
//    public Servers getServers() {
//        return server;
//    }
//
//    public String getQueueName() {
//        return queueName;
//    }
//}
