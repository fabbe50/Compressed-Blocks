package com.fabbe50.compressedblocks.core.registry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Created by fabbe50 on 30/04/2017.
 */
public class EndgameCraftingRegistry {
    public static void init() {
        registerRecipes();
    }

    private static void registerRecipes() {
        ModRegistry.addEndgameRecipe(new ItemStack(Blocks.DRAGON_EGG), new Object[]{new ItemStack(Items.EGG), new ItemStack(ItemRegistry.ENDGAMIUM_NUGGET)});
        ModRegistry.addEndgameRecipe(new ItemStack(Items.NETHER_STAR), new Object[]{new ItemStack(Items.SKULL, 3, 1), new ItemStack(Blocks.SOUL_SAND, 4), new ItemStack(Items.DIAMOND_SWORD)});
    }
}
