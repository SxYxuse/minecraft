package me.sxyxuse.manager.commands;

import me.sxyxuse.manager.inventories.InventoryBuilder;
import me.sxyxuse.manager.items.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class Test implements CommandExecutor {
    public final ItemStack REPORT =
            ItemBuilder.createItem(
                    "§cReport le message",
                    Material.BARRIER,
                    Arrays.asList("§aCliquez pour report", "§ale message."),
                    new ItemFlag[]{}
            );

    public final ItemStack FRIEND =
            ItemBuilder.createItem(
                    "§eAjouter en ami",
                    Material.EMERALD,
                    Arrays.asList("§aCliquez pour ajouter", "§aen ami."),
                    new ItemFlag[]{}
            );

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof final Player player))
            return false;

        final InventoryBuilder inventory = new InventoryBuilder(9, ChatColor.GOLD + "Veuillez faire un choix :");
        inventory.setItemIn(2, this.REPORT);
        inventory.setItemIn(4, this.FRIEND);

        player.openInventory(inventory.getInventory());

        return true;
    }
}
