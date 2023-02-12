package me.sxyxuse.manager.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public class ItemBuilder {

    public static ItemStack createItem(String displayName, Material material, List<String> description, ItemFlag[] itemFlags) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();

        Objects.requireNonNull(meta).setDisplayName(displayName);
        meta.setLore(description);

        for (ItemFlag itemFlag : itemFlags)
            meta.addItemFlags(itemFlag);
        item.setItemMeta(meta);

        return item;
    }
}
