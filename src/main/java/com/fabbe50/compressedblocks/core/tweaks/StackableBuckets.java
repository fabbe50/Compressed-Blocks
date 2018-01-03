package com.fabbe50.compressedblocks.core.tweaks;

import com.fabbe50.compressedblocks.core.lib.Configs;
import com.fabbe50.compressedblocks.core.utils.helper.LogHelper;
import net.minecraft.block.BlockTallGrass;
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
                if (event.getWorld().getTileEntity(event.getPos()) != null) {}
                else
                    event.setCanceled(true);
            }
            else if (event.getItemStack().getItem() == Items.LAVA_BUCKET) {
                if (event.getWorld().getTileEntity(event.getPos()) != null) {}
                else
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
            if (event.getWorld().getTileEntity(event.getPos()) != null && !event.getEntityPlayer().isSneaking()) {

            }
            else if (event.getItemStack().getItem() == Items.WATER_BUCKET) {
                EnumFacing facing = event.getFace();

                try {
                    if (event.getWorld().getBlockState(event.getPos()).getBlock() instanceof BlockTallGrass && false)
                        event.getWorld().setBlockState(event.getPos(), Blocks.FLOWING_WATER.getDefaultState(), 11);
                    else
                        event.getWorld().setBlockState(event.getPos().offset(facing), Blocks.FLOWING_WATER.getDefaultState(), 11);
                } catch (Exception e) {
                    LogHelper.error("Failed to place liquid: " + e);
                    facing = EnumFacing.UP;
                    event.getWorld().setBlockState(event.getPos().offset(facing), Blocks.FLOWING_WATER.getDefaultState(), 11);
                }

                if (!event.getEntityPlayer().isCreative()) {
                    boolean notFull = event.getEntityPlayer().inventory.addItemStackToInventory(new ItemStack(Items.BUCKET, 1, 0));
                    if (!notFull)
                        event.getEntityPlayer().dropItem(Items.BUCKET, 1);


                    if (event.getItemStack().getCount() > 1) {
                        event.getItemStack().shrink(1);
                    } else if (event.getItemStack().getCount() == 1) {
                        event.getItemStack().setCount(0);
                    }
                }
            } else if (event.getItemStack().getItem() == Items.LAVA_BUCKET) {
                EnumFacing facing = event.getFace();

                try {
                    if (event.getWorld().getBlockState(event.getPos()).getBlock() instanceof BlockTallGrass)
                        event.getWorld().setBlockState(event.getPos(), Blocks.FLOWING_LAVA.getDefaultState(), 11);
                    else
                        event.getWorld().setBlockState(event.getPos().offset(facing), Blocks.FLOWING_LAVA.getDefaultState(), 11);
                } catch (Exception e) {
                    LogHelper.error("Failed to place liquid: " + e);
                    facing = EnumFacing.UP;
                    event.getWorld().setBlockState(event.getPos().offset(facing), Blocks.FLOWING_LAVA.getDefaultState(), 11);
                }

                if (!event.getEntityPlayer().isCreative()) {
                    boolean notFull = event.getEntityPlayer().inventory.addItemStackToInventory(new ItemStack(Items.BUCKET, 1, 0));
                    if (!notFull)
                        event.getEntityPlayer().dropItem(Items.BUCKET, 1);


                    if (event.getItemStack().getCount() > 1) {
                        event.getItemStack().shrink(1);
                    } else if (event.getItemStack().getCount() == 1) {
                        event.getItemStack().setCount(0);
                    }
                }
            }
        }
    }
}
