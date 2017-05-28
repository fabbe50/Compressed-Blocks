package com.fabbe50.compressedblocks.core.tweaks;

import com.fabbe50.compressedblocks.core.lib.Configs;
import com.thefifthidiot.tficore.utility.helper.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by fabbe on 13/05/2017.
 */
public class StackableBuckets {
    @SubscribeEvent
    public void onRightClick(PlayerInteractEvent.RightClickItem event) {
        if (!event.getEntityPlayer().world.isRemote && Configs.vanillaHooks) {
            if (event.getItemStack().getItem() == Items.WATER_BUCKET) {
                event.setCanceled(true);
            }
            else if (event.getItemStack().getItem() == Items.LAVA_BUCKET) {
                event.setCanceled(true);
            }
            else if (event.getItemStack().getItem() == Items.MILK_BUCKET && Configs.undrinkableBuckets) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void rightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!event.getEntityPlayer().world.isRemote && Configs.vanillaHooks) {
            if (event.getItemStack().getItem() == Items.WATER_BUCKET) {
                EnumFacing facing = event.getFace();

                try {
                    event.getWorld().setBlockState(event.getPos().offset(facing), Blocks.WATER.getDefaultState());
                }
                catch (Exception e) {
                    LogHelper.error("Failed to place liquid: " + e);
                    facing = EnumFacing.UP;
                    event.getWorld().setBlockState(event.getPos().offset(facing), Blocks.WATER.getDefaultState());
                }

                if (event.getItemStack().getCount() > 1) {
                    event.getItemStack().shrink(1);
                }
                else if (event.getItemStack().getCount() == 1) {
                    event.getItemStack().setCount(0);
                }

                boolean notFull = event.getEntityPlayer().inventory.addItemStackToInventory(new ItemStack(Items.BUCKET, 1, 0));
                if (!notFull)
                    event.getEntityPlayer().dropItem(Items.BUCKET, 1);
            }
            else if (event.getItemStack().getItem() == Items.LAVA_BUCKET) {
                EnumFacing facing = event.getFace();

                try {
                    event.getWorld().setBlockState(event.getPos().offset(facing), Blocks.LAVA.getDefaultState());
                }
                catch (Exception e) {
                    LogHelper.error("Failed to place liquid: " + e);
                    facing = EnumFacing.UP;
                    event.getWorld().setBlockState(event.getPos().offset(facing), Blocks.LAVA.getDefaultState());
                }

                if (event.getItemStack().getCount() > 1) {
                    event.getItemStack().shrink(1);
                }
                else if (event.getItemStack().getCount() == 1) {
                    event.getItemStack().setCount(0);
                }

                boolean notFull = event.getEntityPlayer().inventory.addItemStackToInventory(new ItemStack(Items.BUCKET, 1, 0));
                if (!notFull)
                    event.getEntityPlayer().dropItem(Items.BUCKET, 1);
            }
        }
    }
}
