package me.sxyxuse.hub.listeners.compass;

import me.sxyxuse.hub.listeners.players.JoinEvent;
import me.sxyxuse.manager.inventories.InventoryBuilder;
import me.sxyxuse.manager.items.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class InteractEvent implements Listener {
    public static final ItemStack MINING =
            ItemBuilder.createItem(
                    "§cServeur minage",
                    Material.NETHERITE_PICKAXE,
                    Arrays.asList("§aCliquez pour vous connecter", "§aau serveur de minage"),
                    new ItemFlag[]{
                            ItemFlag.HIDE_DESTROYS,
                            ItemFlag.HIDE_ATTRIBUTES,
                            ItemFlag.HIDE_UNBREAKABLE
                    }
            );
    public static final ItemStack BUILD =
            ItemBuilder.createItem(
                    "§cServeur de build",
                    Material.SPRUCE_LOG,
                    Arrays.asList("§aCliquez pour vous connecter", "§aau serveur de build"),
                    new ItemFlag[]{}
            );

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final Action action = event.getAction();
        final ItemStack itemStack = player.getInventory().getItemInMainHand();

        if (action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR)
            if (itemStack.equals(JoinEvent.COMPASS)) {
                InventoryBuilder inventory = new InventoryBuilder(9, ChatColor.GOLD + "Veuillez choisir un serveur :");
                inventory.setItemIn(3, MINING);
                inventory.setItemIn(5, BUILD);

                player.openInventory(inventory.getInventory());
            }
    }
}
