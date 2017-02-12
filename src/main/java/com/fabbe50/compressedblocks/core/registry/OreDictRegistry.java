package com.fabbe50.compressedblocks.core.registry;

import com.fabbe50.compressedblocks.core.reference.MetaValues;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by fabbe50 on 17/09/2016.
 */
public class OreDictRegistry {
    public static void init() {
        addCompressedOreDict();
    }

    private static void addCompressedOreDict() {
        for (int i = 0; i < 8; i++) {
            if (Loader.isModLoaded("ExtraUtils2")) {
                OreDictionary.registerOre(MetaValues.COMPRESSED[i] + "Cobble", new ItemStack(ModItemRegistry.EU2_COBBLE, 1, i));
                if (i < 4)
                    OreDictionary.registerOre(MetaValues.COMPRESSED[i] + "Dirt", new ItemStack(ModItemRegistry.EU2_DIRT, 1, i));
                if (i < 2)
                    OreDictionary.registerOre(MetaValues.COMPRESSED[i] + "Gravel", new ItemStack(ModItemRegistry.EU2_GRAVEL, 1, i));
                if (i < 2)
                    OreDictionary.registerOre(MetaValues.COMPRESSED[i] + "Sand", new ItemStack(ModItemRegistry.EU2_SAND, 1, i));
            }

            OreDictionary.registerOre(MetaValues.COMPRESSED[i] + "Cobble", new ItemStack(BlockRegistry.COMPRESSED_COBBLESTONE, 1, i));
            OreDictionary.registerOre(MetaValues.COMPRESSED[i] + "Dirt", new ItemStack(BlockRegistry.COMPRESSED_DIRT, 1, i));
            OreDictionary.registerOre(MetaValues.COMPRESSED[i] + "Gravel", new ItemStack(BlockRegistry.COMPRESSED_GRAVEL, 1, i));
            OreDictionary.registerOre(MetaValues.COMPRESSED[i] + "Sand", new ItemStack(BlockRegistry.COMPRESSED_SAND, 1, i));
        }
    }
}
