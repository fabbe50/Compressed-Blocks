package com.fabbe50.compressedblocks.common.blocks.base;

import com.thefifthidiot.tficore.common.blocks.base.BlockBase;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;

/**
 * Created by fabbe on 17/06/2017.
 */
public class BlockBeaconBase extends BlockBase {
    public BlockBeaconBase(Material material, MapColor mapColor, String blockName, float hardness, float resistance, @Nullable CreativeTabs tab) {
        super(material, mapColor, blockName, hardness, resistance, tab);
    }

    @Override
    public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
        return true;
    }
}
