package me.sxyxuse.manager.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Test implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof final Player player) {
//            player.sendMessage("Bonjour : " + RedisAccount.getPseudo(player.getUniqueId().toString()));
            return true;
        }

        return false;
    }
}
