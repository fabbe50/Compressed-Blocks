package com.fabbe50.compressedblocks.init;

import com.fabbe50.compressedblocks.common.block.BlockCompressed;
import com.fabbe50.compressedblocks.common.item.itemblocks.ItemCompressedBlock;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by fabbe50 on 09/03/2016.
 */
public class ModFallbackBlocks {
    public static BlockCompressed comprcobbleblock = new BlockCompressed("cobble", 1);
    public static BlockCompressed comprdirtblock = new BlockCompressed("dirt", 2);
    public static BlockCompressed comprgravelblock = new BlockCompressed("gravel", 3);
    public static BlockCompressed comprsandblock = new BlockCompressed("sand", 4);

    public static void init() {
        if (!Loader.isModLoaded("ExtraUtilities")) {
            GameRegistry.registerBlock(comprcobbleblock, ItemCompressedBlock.class, "cobbleblock");
            GameRegistry.registerBlock(comprdirtblock, ItemCompressedBlock.class, "dirtblock");
            GameRegistry.registerBlock(comprgravelblock, ItemCompressedBlock.class, "gravelblock");
            GameRegistry.registerBlock(comprsandblock, ItemCompressedBlock.class, "sandblock");
        }
    }
}
