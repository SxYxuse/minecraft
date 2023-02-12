package me.sxyxuse.hub.waitingqueue;

import me.sxyxuse.hub.Hub;
import me.sxyxuse.manager.redis.RedisWaitingQueue;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;
import java.util.UUID;

public class QueueScheduler extends BukkitRunnable {
    private static boolean inStart = false;
    private final Hub hub;

    public QueueScheduler(Hub hub) {
        this.hub = hub;
    }

    public static boolean isInStart() {
        return inStart;
    }

    public static void setInStart(boolean inStart) {
        QueueScheduler.inStart = inStart;
    }

    public static void sendNewPositionMessage() {
        for (int i = 0; i < RedisWaitingQueue.getQueueSize(); i++) {
            final Player player = Bukkit.getPlayer(UUID.fromString(RedisWaitingQueue.getFromIndex(i)));
            final UUID uuid = Objects.requireNonNull(player).getUniqueId();
            Objects.requireNonNull(player).sendMessage(ChatColor.YELLOW + "[" + ChatColor.GOLD + "File d'attente" + ChatColor.YELLOW + "]" + ChatColor.GOLD + " Vous êtes à la position " + ChatColor.YELLOW + (RedisWaitingQueue.getPosInQueue(uuid) + 1) + ChatColor.GOLD + "/" + RedisWaitingQueue.getQueueSize() + ".");
        }
    }

    @Override
    public void run() {
        if (!isInStart()) {
            cancel();
            return;
        }

        if (RedisWaitingQueue.getQueueSize() > 0) {
            Objects.requireNonNull(Bukkit.getPlayer(UUID.fromString(RedisWaitingQueue.getFromIndex(0)))).sendMessage(ChatColor.YELLOW + "[" + ChatColor.GOLD + "File d'attente" + ChatColor.YELLOW + "]" + ChatColor.GOLD + " Vous avez été retiré de la file d'attente.");
            RedisWaitingQueue.removeUuidFromQueue(UUID.fromString(RedisWaitingQueue.getFromIndex(0)));
            sendNewPositionMessage();
        } else {
            inStart = false;
            RedisWaitingQueue.delQueue("queue");
            cancel();
        }
    }

    public Hub getHub() {
        return hub;
    }
}
