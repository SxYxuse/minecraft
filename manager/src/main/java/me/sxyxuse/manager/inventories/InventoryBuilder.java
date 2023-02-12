package me.sxyxuse.manager.inventories;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class InventoryBuilder implements InventoryHolder {

    private final Inventory inventory;

    public InventoryBuilder(int size, String title) {
        this.inventory = Bukkit.createInventory(this, size, title);
    }

    public void setItemIn(int pos, ItemStack itemStack) {
        inventory.setItem(pos, itemStack);
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
