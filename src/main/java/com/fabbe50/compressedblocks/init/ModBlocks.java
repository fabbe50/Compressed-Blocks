package com.fabbe50.compressedblocks.init;

import com.fabbe50.compressedblocks.block.BlockCB;
import com.fabbe50.compressedblocks.block.BlockEndgamium;
import com.fabbe50.compressedblocks.block.BlockStar;
import com.fabbe50.compressedblocks.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by fabbe50 on 15/01/2016.
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {
    public static final BlockCB comprstarblock = new BlockStar();
    public static final BlockCB endgamiumblock = new BlockEndgamium();

    public static void init() {
        GameRegistry.registerBlock(comprstarblock, "comprstarblock");
        GameRegistry.registerBlock(endgamiumblock, "endgamiumblock");
    }
}
