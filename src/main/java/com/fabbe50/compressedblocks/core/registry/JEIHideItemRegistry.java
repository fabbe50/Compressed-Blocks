package com.fabbe50.compressedblocks.core.registry;

import mezz.jei.api.ingredients.IIngredientRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabbe on 16/06/2017.
 */
public class JEIHideItemRegistry {
    public static List<ItemStack> hide = new ArrayList<>();
    private static final int WILDCARD_VALUE = Short.MAX_VALUE;

    public static void init() {
        hide.add(new ItemStack(BlockRegistry.BLACKHOLE));
        hide.add(new ItemStack(BlockRegistry.ONLINEDETECTOR));
        hide.add(new ItemStack(BlockRegistry.COMPRESSED_FURNACE_ACTIVE));
        hide.add(new ItemStack(BlockRegistry.LIGHTBLOCK1));
        hide.add(new ItemStack(BlockRegistry.LIGHTBLOCK2));
        hide.add(new ItemStack(BlockRegistry.LIGHTBLOCK3));
        hide.add(new ItemStack(BlockRegistry.LIGHTBLOCK4));
        hide.add(new ItemStack(BlockRegistry.LIGHTBLOCK5));
        hide.add(new ItemStack(BlockRegistry.LIGHTBLOCK6));
        hide.add(new ItemStack(BlockRegistry.LIGHTBLOCK7));
        hide.add(new ItemStack(BlockRegistry.LIGHTBLOCK8));
        hide.add(new ItemStack(BlockRegistry.LIGHTBLOCK9));
        hide.add(new ItemStack(BlockRegistry.LIGHTBLOCK10));
        hide.add(new ItemStack(BlockRegistry.LIGHTBLOCK11));
        hide.add(new ItemStack(BlockRegistry.LIGHTBLOCK12));
        hide.add(new ItemStack(BlockRegistry.LIGHTBLOCK13));
        hide.add(new ItemStack(BlockRegistry.LIGHTBLOCK14));
        hide.add(new ItemStack(BlockRegistry.LIGHTBLOCK15));
        hide.add(new ItemStack(BlockRegistry.BREWER));
        hide.add(new ItemStack(BlockRegistry.OVERLAYTEST));
        hide.add(new ItemStack(BlockRegistry.COMPRESSED_GRASS_EATEN, 1, WILDCARD_VALUE));
        hide.add(new ItemStack(BlockRegistry.NUKE));
        hide.add(new ItemStack(BlockRegistry.DRAWBRIDGE));

        //hide.add(new ItemStack(ItemRegistry.TWOHANDITEMTEST));
        //hide.add(new ItemStack(ItemRegistry.DNASAMPLE));
    }
}
