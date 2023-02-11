package me.sxyxuse.waitingqueue.commands;

import me.sxyxuse.waitingqueue.QueueScheduler;
import me.sxyxuse.waitingqueue.WaitingQueue;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.sxyxuse.waitingqueue.WaitingQueue.playersQueue;

public class Queue implements CommandExecutor {
    public QueueScheduler queueScheduler;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof final Player player))
            return false;

        playersQueue.add(player.getUniqueId());
        player.sendMessage(ChatColor.YELLOW + "[" + ChatColor.GOLD + "File d'attente" + ChatColor.YELLOW + "]" + ChatColor.GOLD + " Vous avez été ajouté dans la file d'attente.");
        QueueScheduler.sendNewPositionMessage();

        if (!QueueScheduler.isInStart()) {
            QueueScheduler.setInStart(true);
            queueScheduler = new QueueScheduler(WaitingQueue.getInstance());
            queueScheduler.runTaskTimer(WaitingQueue.getInstance(), 6 * 20L, 6 * 20L);
        }

        return true;
    }
}
