package com.fabbe50.compressedblocks.item;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.List;

/**
 * Created by fabbe50 on 24/02/2016.
 */
public class ItemCorgiFood {
    private static Item[] corgiFood;

    public ItemCorgiFood () {
    }

    public static void registerFoodTypes () {
        corgiFood = new Item[1];

        corgiFood[0] = Items.baked_potato;
    }

    private static boolean checkItem(Item item) {
        for (int i = 0; i < corgiFood.length; i++) {
            if (item == corgiFood[i]) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCorgisFavoriteFood (Item item) {
        return checkItem(item);
    }
}
