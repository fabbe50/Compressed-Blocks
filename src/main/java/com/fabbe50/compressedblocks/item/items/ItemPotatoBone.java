package com.fabbe50.compressedblocks.item.items;


import com.fabbe50.compressedblocks.entities.mobspawning.SpawnMob;
import com.fabbe50.compressedblocks.entities.tamables.corgis.EntityCorgi;
import com.fabbe50.compressedblocks.init.ModBlocks;
import com.fabbe50.compressedblocks.item.ItemCB;
import com.fabbe50.compressedblocks.utility.ColorHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * Created by fabbe50 on 29/02/2016.
 */
public class ItemPotatoBone extends ItemCB {
    EntityCorgi corgi;

    public ItemPotatoBone () {
        super();
        setUnlocalizedName("potatobone");
    }

    @Override
    public void addInformation (ItemStack stack, EntityPlayer player, List list, boolean par) {
        list.add(ColorHelper.magenta + "Now why would you ever pierce a potato with a rainbow bone?");
        list.add(ColorHelper.purple + "To spawn a Corgi of course!");
    }

    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float offsetX, float offsetY, float offsetZ) {
            if (world.isRemote) {
                return true;
            }
            else {
                Block block = world.getBlock(x, y, z);

                x += Facing.offsetsXForSide[side];
                y += Facing.offsetsYForSide[side];
                z += Facing.offsetsZForSide[side];
                double d0 = 0.0D;
                if (side == 1 && block.getRenderType() == 11) {
                    d0 = 0.5D;
                }

                if (block == Block.getBlockFromItem((new ItemStack(ModBlocks.comprpotatoblock,1,7).getItem()))) {
                    corgi = new EntityCorgi(world);
                    Entity entity = SpawnMob.spawnEntity(world, x + 0.5D, y + d0, z + 0.5D, corgi);
                    if (entity != null) {
                        if (entity instanceof EntityLivingBase && itemStack.hasDisplayName()) {
                            ((EntityLiving) entity).setCustomNameTag(itemStack.getDisplayName());
                        }
                        if (!player.capabilities.isCreativeMode) {
                            --itemStack.stackSize;
                        }
                    }
                }
                return true;
            }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if (world.isRemote) {
            return itemStack;
        }
        else {
            MovingObjectPosition movingobjectposition = getMovingObjectPositionFromPlayer(world, player, true);
            if (movingobjectposition == null) {
                return itemStack;
            }
            else {
                if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                    int i = movingobjectposition.blockX;
                    int j = movingobjectposition.blockY;
                    int k = movingobjectposition.blockZ;

                    if (!world.canMineBlock(player, i, j, k)) {
                        return itemStack;
                    }
                    if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, itemStack)) {
                        return itemStack;
                    }
                    if (world.getBlock(i, j, k) instanceof BlockLiquid) {
                        Entity entity = SpawnMob.spawnEntity(world, i, j, k, corgi);
                        if (entity != null) {
                            if (entity instanceof EntityLivingBase && itemStack.hasDisplayName()) {
                                ((EntityLiving)entity).setCustomNameTag(itemStack.getDisplayName());
                            }
                            if (!player.capabilities.isCreativeMode) {
                                --itemStack.stackSize;
                            }
                        }
                    }
                }
                return itemStack;
            }
        }
    }
}
