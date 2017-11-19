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
        ModRegistry.addEndgameRecipe(new ItemStack(Items.COOKED_FISH, 1, 0), new Object[]{new ItemStack(Items.FISH, 1, 0)});
        ModRegistry.addEndgameRecipe(new ItemStack(Items.COOKED_FISH, 1, 1), new Object[]{new ItemStack(Items.FISH, 1, 1)});
        ModRegistry.addEndgameRecipe(new ItemStack(Items.WHEAT, 1, 0), new Object[]{new ItemStack(Items.WHEAT_SEEDS, 1, 0), new ItemStack(Items.DYE, 1, 15)});
        ModRegistry.addEndgameRecipe(new ItemStack(Blocks.MELON_BLOCK, 1, 0), new Object[]{new ItemStack(Items.MELON_SEEDS, 1, 0), new ItemStack(Items.DYE, 1, 15)});
        ModRegistry.addEndgameRecipe(new ItemStack(Blocks.PUMPKIN, 1, 0), new Object[]{new ItemStack(Items.PUMPKIN_SEEDS, 1, 0), new ItemStack(Items.DYE, 1, 15)});
        ModRegistry.addEndgameRecipe(new ItemStack(Items.BEETROOT, 1, 0), new Object[]{new ItemStack(Items.BEETROOT_SEEDS, 1, 0), new ItemStack(Items.DYE, 1, 15)});
        ModRegistry.addEndgameRecipe(new ItemStack(ItemRegistry.TRINKET, 1, 0), new Object[]{new ItemStack(BlockRegistry.COMPRESSED_IRON, 1, 0)});
        ModRegistry.addEndgameRecipe(new ItemStack(ItemRegistry.TRINKET, 1, 1), new Object[]{new ItemStack(ItemRegistry.TRINKET, 1, 0), new ItemStack(ItemRegistry.ENDERAPPLE, 1, 1)});
        ModRegistry.addEndgameRecipe(new ItemStack(ItemRegistry.TRINKET, 1, 2), new Object[]{new ItemStack(ItemRegistry.TRINKET, 1, 0), new ItemStack(BlockRegistry.ENDERBLOCK, 1, 0)});
        ModRegistry.addEndgameRecipe(new ItemStack(ItemRegistry.TRINKET, 1, 3), new Object[]{new ItemStack(ItemRegistry.TRINKET, 1, 0), new ItemStack(Blocks.END_BRICKS, 1, 0), new ItemStack(Blocks.NETHER_BRICK, 1, 0)});
    }
}
