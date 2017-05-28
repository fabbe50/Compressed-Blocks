package com.fabbe50.compressedblocks.core.lib;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabbe on 09/05/2017.
 */
public class ReturnableItems {
    private static List<Item> items = new ArrayList<>();

    public static void add(Item item) {
        items.add(item);
    }

    public static void add(Block block) {
        items.add(Item.getItemFromBlock(block));
    }

    public static List<Item> getItems() {
        return items;
    }
}
