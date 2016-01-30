package com.fabbe50.compressedblocks.init;

import com.fabbe50.compressedblocks.block.*;
import com.fabbe50.compressedblocks.reference.Reference;
import com.fabbe50.compressedblocks.utility.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by fabbe50 on 15/01/2016.
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {
    public static final BlockCB comprstarblock = new BlockStar();
    public static final BlockCB endgamiumblock = new BlockEndgamium();
    public static final BlockCB endgamiumblockc = new BlockEndgamiumC();
    public static final Block potatoBlock = new BlockPotato();

    public static void init() {
        LogHelper.info("Adding Blocks");
        GameRegistry.registerBlock(comprstarblock, "comprstarblock");
        GameRegistry.registerBlock(endgamiumblock, "endgamiumblock");
        GameRegistry.registerBlock(endgamiumblockc, "endgamiumblockc");
        GameRegistry.registerBlock(potatoBlock, "potatoblock");
        LogHelper.info("Done!");
    }
}
