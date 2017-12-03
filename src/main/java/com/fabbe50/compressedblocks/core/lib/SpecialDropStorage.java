package com.fabbe50.compressedblocks.core.lib;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabbe on 03/12/2017 - 2:57 AM.
 */
public class SpecialDropStorage {
    public static List<Block> specialDropBlock;
    public static List<Item> specialDropItem;

    public static void init() {
        specialDropBlock = new ArrayList<>();
        specialDropItem = new ArrayList<>();
    }
}
