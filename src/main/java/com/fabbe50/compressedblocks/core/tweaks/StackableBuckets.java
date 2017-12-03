package com.fabbe50.compressedblocks.core.tweaks;

import com.fabbe50.compressedblocks.core.lib.Configs;
import com.fabbe50.compressedblocks.core.utils.helper.LogHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by fabbe on 13/05/2017.
 */
public class StackableBuckets {
    @SubscribeEvent(priority = EventPriority.LOW)
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

    @SubscribeEvent(priority = EventPriority.LOW)
    public void rightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!event.getEntityPlayer().world.isRemote && Configs.vanillaHooks) {
            if (event.getWorld().getTileEntity(event.getPos()) != null) {

            }
            else if (event.getItemStack().getItem() == Items.WATER_BUCKET) {
                EnumFacing facing = event.getFace();

                try {
                    event.getWorld().setBlockState(event.getPos().offset(facing), Blocks.FLOWING_WATER.getDefaultState());
                }
                catch (Exception e) {
                    LogHelper.error("Failed to place liquid: " + e);
                    facing = EnumFacing.UP;
                    event.getWorld().setBlockState(event.getPos().offset(facing), Blocks.FLOWING_WATER.getDefaultState());
                }

                boolean notFull = event.getEntityPlayer().inventory.addItemStackToInventory(new ItemStack(Items.BUCKET, 1, 0));
                if (!notFull)
                    event.getEntityPlayer().dropItem(Items.BUCKET, 1);

                if (event.getItemStack().getCount() > 1) {
                    event.getItemStack().shrink(1);
                }
                else if (event.getItemStack().getCount() == 1) {
                    event.getItemStack().setCount(0);
                }
            }
            else if (event.getItemStack().getItem() == Items.LAVA_BUCKET) {
                EnumFacing facing = event.getFace();

                try {
                    event.getWorld().setBlockState(event.getPos().offset(facing), Blocks.FLOWING_LAVA.getDefaultState());
                }
                catch (Exception e) {
                    LogHelper.error("Failed to place liquid: " + e);
                    facing = EnumFacing.UP;
                    event.getWorld().setBlockState(event.getPos().offset(facing), Blocks.FLOWING_LAVA.getDefaultState());
                }

                boolean notFull = event.getEntityPlayer().inventory.addItemStackToInventory(new ItemStack(Items.BUCKET, 1, 0));
                if (!notFull)
                    event.getEntityPlayer().dropItem(Items.BUCKET, 1);

                if (event.getItemStack().getCount() > 1) {
                    event.getItemStack().shrink(1);
                }
                else if (event.getItemStack().getCount() == 1) {
                    event.getItemStack().setCount(0);
                }
            }
        }
    }
}
