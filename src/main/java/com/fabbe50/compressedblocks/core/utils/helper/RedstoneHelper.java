package com.fabbe50.compressedblocks.core.utils.helper;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by fabbe50 on 12/10/2016.
 */
public class RedstoneHelper {
    public static int[] getRedstonePowerFromAllSides(World worldIn, BlockPos pos) {
        int[] power = new int[6];
        power[0] = worldIn.getRedstonePower(pos, EnumFacing.EAST);
        power[1] = worldIn.getRedstonePower(pos, EnumFacing.WEST);
        power[2] = worldIn.getRedstonePower(pos, EnumFacing.SOUTH);
        power[3] = worldIn.getRedstonePower(pos, EnumFacing.NORTH);
        power[4] = worldIn.getRedstonePower(pos, EnumFacing.UP);
        power[5] = worldIn.getRedstonePower(pos, EnumFacing.DOWN);
        return power;
    }
}
