package com.fabbe50.compressedblocks.core.utils.helper;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Created by fabbe50 on 12/10/2016.
 */
public class ItemHelper {
    public static int getItemDamage(ItemStack stack) {
        return Items.DIAMOND.getDamage(stack);
    }
}
