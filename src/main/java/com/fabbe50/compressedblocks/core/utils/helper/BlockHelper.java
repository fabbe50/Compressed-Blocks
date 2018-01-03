package com.fabbe50.compressedblocks.core.utils.helper;

import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by fabbe on 03/01/2018 - 12:49 PM.
 */
public class BlockHelper {
    public static boolean isSourceBlock(World world, BlockPos pos) {
        if (world.getBlockState(pos).getBlock() == Blocks.WATER || world.getBlockState(pos).getBlock() == Blocks.LAVA)
            if (world.getBlockState(pos).getValue(BlockStaticLiquid.LEVEL) == 0)
                return true;
        return false;
    }
}
