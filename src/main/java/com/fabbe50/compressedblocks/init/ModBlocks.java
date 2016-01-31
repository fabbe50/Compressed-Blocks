package com.fabbe50.compressedblocks.init;

import com.fabbe50.compressedblocks.block.*;
import com.fabbe50.compressedblocks.item.ItemPotatoBlock;
import com.fabbe50.compressedblocks.reference.Reference;
import com.fabbe50.compressedblocks.utility.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by fabbe50 on 15/01/2016.
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {
    //Normal Blocks
    public static final BlockCB comprstarblock = new BlockStar();
    public static final BlockCB endgamiumblock = new BlockEndgamium();
    public static final BlockCB endgamiumblockc = new BlockEndgamiumC();
    //public static final BlockCB potatoblock = new

    //Blocks with Metadata
    public static BlockPotatoCompr comprpotatoblock  = new BlockPotatoCompr();


    public static void init() {
        LogHelper.info("Adding Blocks");

        //Normal Blocks
        GameRegistry.registerBlock(comprstarblock, "comprstarblock");
        GameRegistry.registerBlock(endgamiumblock, "endgamiumblock");
        GameRegistry.registerBlock(endgamiumblockc, "endgamiumblockc");


        //Blocks with Metadata
        GameRegistry.registerBlock(comprpotatoblock, ItemPotatoBlock.class, "potatoblock");

        LogHelper.info("Done!");
    }
}
