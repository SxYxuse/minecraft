package me.sxyxuse.hub.listeners.players;

import me.sxyxuse.manager.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class JoinEvent implements Listener {
    public static final ItemStack COMPASS =
            ItemBuilder.createItem(
                    "Cliquez-ici pour changer de serveur !",
                    Material.COMPASS,
                    Arrays.asList("Permet de choisir un serveur"),
                    new ItemFlag[]{
                            ItemFlag.HIDE_ATTRIBUTES,
                            ItemFlag.HIDE_DESTROYS,
                            ItemFlag.HIDE_UNBREAKABLE
                    }
            );

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        final Inventory inventory = event.getPlayer().getInventory();
        inventory.clear();
        inventory.addItem(COMPASS);
    }
}
