package com.fabbe50.compressedblocks.init;

import com.fabbe50.compressedblocks.common.block.*;
import com.fabbe50.compressedblocks.common.item.itemblocks.ItemCompressedBlock;
import com.fabbe50.compressedblocks.common.item.itemblocks.ItemEndgamiumBlock;
import com.fabbe50.compressedblocks.core.reference.Reference;
import com.fabbe50.compressedblocks.utility.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by fabbe50 on 15/01/2016.
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {

    //Normal Blocks
    public static final BlockCB netherstarblock = new BlockNetherStar();
    public static final BlockCB comprstarblock = new BlockStar();
    public static final BlockCB endgamiumblock = new BlockEndgamium();
    public static final BlockCB endgamiumblockc = new BlockEndgamiumC();
    public static final BlockCB potatoblock = new BlockPotatoblock();

    //Blocks with Metadata
    public static BlockCompressed comprpotatoblock = new BlockCompressed("potato", 0);


    public static void init() {
        LogHelper.info("Adding Blocks");

        //Normal Blocks
        GameRegistry.registerBlock(netherstarblock, "netherstarblock");
        GameRegistry.registerBlock(comprstarblock, "starblock");
        GameRegistry.registerBlock(endgamiumblock, ItemEndgamiumBlock.class, "endgamiumblock");
        GameRegistry.registerBlock(endgamiumblockc, "endgamiumblockc");
        GameRegistry.registerBlock(potatoblock, "potatoblock");

        //Blocks with Metadata
        GameRegistry.registerBlock(comprpotatoblock, ItemCompressedBlock.class, "potato");


        LogHelper.info("Done!");
    }
}
