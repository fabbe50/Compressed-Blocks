package com.fabbe50.compressedblocks.init;

import com.fabbe50.compressedblocks.common.block.BlockBlazeGen;
import com.fabbe50.compressedblocks.common.block.BlockCB;
import com.fabbe50.compressedblocks.common.tileentities.TileEntityBlazeGen;
import com.fabbe50.compressedblocks.core.creativetab.CreativeTabCB;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by fabbe50 on 20/04/2016.
 */
public class ModTileEntities {
    public static final Block blazegenactive = new BlockBlazeGen(true).setBlockName("blazegenactive").setLightLevel(0.625f);
    public static final Block blazegenidle = new BlockBlazeGen(false).setBlockName("blazegenidle").setCreativeTab(CreativeTabCB.CB_TAB2);

    public static void init() {
        GameRegistry.registerBlock(blazegenactive, "blazegenactive");
        GameRegistry.registerBlock(blazegenidle, "blazegenidle");
    }

    public static void initTileEntity() {
        GameRegistry.registerTileEntity(TileEntityBlazeGen.class, "blazegen");
    }
}
