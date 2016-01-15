package com.fabbe50.compressedblocks.init;

import com.fabbe50.compressedblocks.block.BlockCB;
import com.fabbe50.compressedblocks.block.BlockEndgamium;
import com.fabbe50.compressedblocks.block.BlockEndgamiumC;
import com.fabbe50.compressedblocks.block.BlockStar;
import com.fabbe50.compressedblocks.reference.Reference;
import com.fabbe50.compressedblocks.utility.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by fabbe50 on 15/01/2016.
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {
    public static final BlockCB comprstarblock = new BlockStar();
    public static final BlockCB endgamiumblock = new BlockEndgamium();
    public static final BlockCB endgamiumblockc = new BlockEndgamiumC();

    public static void init() {
        LogHelper.info("Adding Blocks");
        GameRegistry.registerBlock(comprstarblock, "comprstarblock");
        GameRegistry.registerBlock(endgamiumblock, "endgamiumblock");
        GameRegistry.registerBlock(endgamiumblockc, "endgamiumblockc");
        LogHelper.info("Done!");
    }
}
