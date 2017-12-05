package com.fabbe50.compressedblocks.core.utils.helper;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by fabbe on 05/12/2017 - 1:52 AM.
 */
public class TileEntityHelper {
    public static void markBlockForUpdate(World world, BlockPos pos)
    {
        world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
    }
}
