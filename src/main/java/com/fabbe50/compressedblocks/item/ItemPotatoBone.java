package com.fabbe50.compressedblocks.item;


import com.fabbe50.compressedblocks.entities.mobspawning.SpawnMob;
import com.fabbe50.compressedblocks.entities.tamables.EntityCorgi;
import com.fabbe50.compressedblocks.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeCache;

/**
 * Created by fabbe50 on 29/02/2016.
 */
public class ItemPotatoBone extends ItemCB {
    EntityCorgi corgi;

    public ItemPotatoBone () {
        super();
        setUnlocalizedName("potatobone");
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
                corgi = new EntityCorgi(world);
                Entity entity = SpawnMob.spawnEntity(world, x + 0.5D, y + d0, z + 0.5D, corgi);
                if (entity != null) {
                    if (entity instanceof EntityLivingBase && itemStack.hasDisplayName()) {
                        ((EntityLiving)entity).setCustomNameTag(itemStack.getDisplayName());
                    }
                    if (!player.capabilities.isCreativeMode) {
                        --itemStack.stackSize;
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
