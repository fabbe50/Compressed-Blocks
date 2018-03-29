package com.fabbe50.compressedblocks.core.utils;

import com.fabbe50.compressedblocks.core.utils.helper.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;

/**
 * Created by fabbe50 on 23/09/2016.
 */
public class Utilities {
    public static boolean destroyBlock(World world, BlockPos pos, boolean dropBlock, boolean dropTrueBlock, boolean soundParticles) {
        IBlockState iblockstate = world.getBlockState(pos);
        Block block = iblockstate.getBlock();

        if (block.isAir(iblockstate, world, pos))
            return false;
        else {
            if (soundParticles)
                world.playEvent(2001, pos, Block.getStateId(iblockstate));

            if (dropBlock && !dropTrueBlock)
                block.dropBlockAsItem(world, pos, iblockstate, 0);
            else if (dropTrueBlock)
                world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Item.getItemFromBlock(block))));

            return world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        }
    }

    public static boolean isHoldingWrench(EntityPlayer player, int x, int y, int z) {
        Item inMainHand = player.getHeldItemMainhand() != ItemStack.EMPTY ? player.getHeldItemMainhand().getItem() : null;
        Item inOffHand = player.getHeldItemOffhand() != ItemStack.EMPTY ? player.getHeldItemOffhand().getItem() : null;
        if (inMainHand instanceof ItemTool || inOffHand instanceof ItemTool) {
            return true;
        }
        return false;
    }

    public static String upperCaseFirstLetter(String str) {
        String s1 = str.substring(0, 1).toUpperCase();
        return s1 + str.substring(1);
    }

    public static void replaceBlockInASpiral(int X, int Z, int timesToTry, World world, BlockPos pos, Map<Block, Block> replacements) {
        for (int tries = 0; tries < timesToTry; tries++) {
            int x = 0, z = 0, dx = 0, dz = -1;
            int t = Math.max(X, Z);
            int maxI = t * t;
            for (int i = 0; i < maxI; i++) {
                if ((-X / 2 <= x) && (x <= X / 2) && (-Z / 2 <= z) && (z <= Z / 2)) {
                    for (int y = 0; y < 255; y++) {
                        if (replacements.containsKey(world.getBlockState(new BlockPos(pos.getX() + x, y, pos.getZ() + z)).getBlock())) {
                            world.setBlockState(new BlockPos(pos.getX() + x, y, pos.getZ() + z), replacements.get(world.getBlockState(new BlockPos(pos.getX() + x, y, pos.getZ() + z)).getBlock()).getDefaultState());
                        }
                    }
                    if (i % 200 == 0) {
                        world.tick();
                    }
                }

                if ((x == z) || ((x < 0) && (x == -z)) || ((x > 0) && (x == 1 - z))) {
                    t = dx;
                    dx = -dz;
                    dz = t;
                }
                x += dx;
                z += dz;
            }
        }
    }
}
