package me.sxyxuse.waitingqueue;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

import static me.sxyxuse.waitingqueue.WaitingQueue.playersQueue;

public class QueueScheduler extends BukkitRunnable {
    private static boolean inStart = false;
    private final WaitingQueue waitingQueue;

    public QueueScheduler(WaitingQueue waitingQueue) {
        this.waitingQueue = waitingQueue;
    }

    public static boolean isInStart() {
        return inStart;
    }

    public static void setInStart(boolean inStart) {
        QueueScheduler.inStart = inStart;
    }

    public static void sendNewPositionMessage() {
        for (int i = 0; i < playersQueue.size(); i++) {
            final Player player = Bukkit.getPlayer(playersQueue.get(i));
            Objects.requireNonNull(player).sendMessage("Vous êtes " + (playersQueue.indexOf(Objects.requireNonNull(player).getUniqueId()) + 1) + "/" + playersQueue.size());
        }
    }

    @Override
    public void run() {
        if (!isInStart()) {
            cancel();
            return;
        }

        if (!playersQueue.isEmpty()) {
            Objects.requireNonNull(Bukkit.getPlayer(playersQueue.get(0))).sendMessage(ChatColor.GOLD + "Vous avez été retiré de la file d'attente.");
            playersQueue.remove(0);
            sendNewPositionMessage();
        } else {
            inStart = false;
            cancel();
        }

    }

    public WaitingQueue getWaitingQueue() {
        return waitingQueue;
    }
}
