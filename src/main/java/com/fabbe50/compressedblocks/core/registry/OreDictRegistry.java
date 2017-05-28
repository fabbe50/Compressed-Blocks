package com.fabbe50.compressedblocks.core.registry;

import com.fabbe50.compressedblocks.core.reference.MetaValues;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import scala.tools.nsc.doc.model.Public;

/**
 * Created by fabbe50 on 17/09/2016.
 */
public class OreDictRegistry {
    private static final int WILDCARD_VALUE = Short.MAX_VALUE;
    public static final String[] color = new String[]{"Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "Light_Gray", "Gray", "Pink", "Lime", "Yellow", "Light_Blue", "Magenta", "Orange", "White"};
    public static final String[] colorR = new String[]{"White", "Orange", "Magenta", "Light_Blue", "Yellow", "Lime", "Pink", "Gray", "Light_Gray", "Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Black"};

    public static void init() {
        addCompressedOreDict();
        addDyeOreDict();
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

    private static void addDyeOreDict() {
        for (int i = 0; i <= 15; i++) {
            OreDictionary.registerOre("dye" + color[i], new ItemStack(ItemRegistry.INKBOTTLE, 1, i));
            OreDictionary.registerOre("dye" + color[i], new ItemStack(Items.DYE, 1, i));
        }
    }
}
