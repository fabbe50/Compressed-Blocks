package com.fabbe50.compressedblocks.core.registry;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by fabbe50 on 19/09/2016.
 */
public class SmeltingRegistry {
    public static void init() {
        addSmeltingRecipes();
    }

    private static void addSmeltingRecipes() {
        //GameRegistry.addSmelting(new ItemStack(BlockRegistry.COMPRESSED_COBBLESTONE, 1, 7), new ItemStack(ItemRegistry.BEDROCK_INGOT, 1, 0), 0);

        if (Loader.isModLoaded("ExtraUtils2")) {
            //GameRegistry.addSmelting(new ItemStack(ModItemRegistry.EU2_COBBLE, 1, 7), new ItemStack(ItemRegistry.BEDROCK_INGOT, 1, 0), 0);
        }
    }
}
