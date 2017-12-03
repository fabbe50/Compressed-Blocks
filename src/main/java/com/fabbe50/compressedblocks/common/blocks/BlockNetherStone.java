package com.fabbe50.compressedblocks.common.blocks;

import com.fabbe50.compressedblocks.common.blocks.base.BlockBase;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import javax.annotation.Nullable;

/**
 * Created by fabbe50 on 13/01/2017.
 */
public class BlockNetherStone extends BlockBase {
    public BlockNetherStone(Material material, MapColor mapColor, String blockName, float hardness, float resistance, @Nullable CreativeTabs tab) {
        super(material, mapColor, blockName, hardness, resistance, tab);
    }
}
