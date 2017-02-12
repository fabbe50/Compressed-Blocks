package com.fabbe50.compressedblocks.core.utils;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Iterator;

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
        Item inMainHand = player.getHeldItemMainhand() != null ? player.getHeldItemMainhand().getItem() : null;
        Item inOffHand = player.getHeldItemOffhand() != null ? player.getHeldItemOffhand().getItem() : null;
        if (inMainHand instanceof ItemTool || inOffHand instanceof ItemTool) {
            return true;
        }
        return false;
    }

    public static String upperCaseFirstLetter(String str) {
        String s1 = str.substring(0, 1).toUpperCase();
        return s1 + str.substring(1);
    }
}
