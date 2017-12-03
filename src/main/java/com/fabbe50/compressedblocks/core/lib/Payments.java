package com.fabbe50.compressedblocks.core.lib;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabbe50 on 30/04/2017.
 */
public class Payments {
    public static List<Item> payment = new ArrayList<>();

    public static void init() {
        //payment.add(ItemRegistry.testItem);
        payment.add(Items.DIAMOND);
        payment.add(Items.EMERALD);
    }
}
