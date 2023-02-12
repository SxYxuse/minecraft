package me.sxyxuse.hub.commands;

import me.sxyxuse.hub.Hub;
import me.sxyxuse.hub.waitingqueue.QueueScheduler;
import me.sxyxuse.manager.redis.RedisWaitingQueue;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Queue implements CommandExecutor {
    public QueueScheduler queueScheduler;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof final Player player))
            return false;

        RedisWaitingQueue.addUuidInQueue(player.getUniqueId());

        player.sendMessage(ChatColor.YELLOW + "[" + ChatColor.GOLD + "File d'attente" + ChatColor.YELLOW + "]" + ChatColor.GOLD + " Vous avez été ajouté dans la file d'attente.");
        player.sendMessage(ChatColor.YELLOW + "[" + ChatColor.GOLD + "File d'attente" + ChatColor.YELLOW + "]" + ChatColor.GOLD + " Vous êtes à la position " + ChatColor.YELLOW + (RedisWaitingQueue.getPosInQueue(player.getUniqueId()) + 1) + ChatColor.GOLD + "/" + RedisWaitingQueue.getQueueSize() + ".");

        if (!QueueScheduler.isInStart()) {
            QueueScheduler.setInStart(true);
            queueScheduler = new QueueScheduler(Hub.getInstance());
            queueScheduler.runTaskTimer(Hub.getInstance(), 10 * 20L, 10 * 20L);
        }

        return true;
    }
}
