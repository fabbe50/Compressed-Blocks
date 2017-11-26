package com.fabbe50.compressedblocks.common.blocks;

import com.fabbe50.compressedblocks.core.registry.BlockRegistry;
import com.fabbe50.compressedblocks.core.registry.ItemRegistry;
import com.thefifthidiot.tficore.common.blocks.base.BlockBase;
import com.thefifthidiot.tficore.utility.LogHelper;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

/**
 * Created by fabbe on 12/09/2017 - 2:02 PM.
 */
public class BlockMagicalWashingBin extends BlockBase {
    protected static final AxisAlignedBB AABB_LEGS = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D);
    protected static final AxisAlignedBB AABB_WALL_NORTH = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
    protected static final AxisAlignedBB AABB_WALL_SOUTH = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB AABB_WALL_EAST = new AxisAlignedBB(0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB AABB_WALL_WEST = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.125D, 1.0D, 1.0D);

    public BlockMagicalWashingBin(Material material, MapColor mapColor, String blockName, float hardness, float resistance, @Nullable CreativeTabs tab) {
        super(material, mapColor, blockName, hardness, resistance, tab);
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if (!worldIn.isRemote) {
            if (entityIn instanceof EntityItem) {
                EntityItem entityItem = (EntityItem)entityIn;
                double posX = entityItem.posX;
                double posY = entityItem.posY;
                double posZ = entityItem.posZ;
                ItemStack entityItemStack = entityItem.getItem();
                int itemDamage = entityItemStack.getItemDamage();
                int itemStackSize = entityItemStack.getCount();
                Item item = entityItemStack.getItem();
                EntityItem temp = null;

                ItemStack tempStack = new ItemStack(item, itemStackSize, 0);
                tempStack.setItemDamage(itemDamage);

                if (item == Items.ENCHANTED_BOOK) {
                    temp = new EntityItem(worldIn, posX, posY, posZ, new ItemStack(Items.BOOK, itemStackSize, 0));
                } else if (item instanceof ItemSword && entityItemStack.isItemEnchanted()) {
                    temp = new EntityItem(worldIn, posX, posY, posZ, tempStack);
                } else if (item instanceof ItemTool && entityItemStack.isItemEnchanted()) {
                    temp = new EntityItem(worldIn, posX, posY, posZ, tempStack);
                } else if (item instanceof ItemHoe && entityItemStack.isItemEnchanted()) {
                    temp = new EntityItem(worldIn, posX, posY, posZ, tempStack);
                } else if (item instanceof ItemBow && entityItemStack.isItemEnchanted()) {
                    temp = new EntityItem(worldIn, posX, posY, posZ, tempStack);
                }

                if (temp != null) {
                    entityIn.isDead = true;
                    temp.setVelocity(0, 0, 0);
                    worldIn.spawnEntity(temp);
                }
            }
        }
        if (worldIn.isRemote) {
            if (entityIn instanceof EntityItem) {
                EntityItem entityItem = (EntityItem)entityIn;
                ItemStack entityItemStack = entityItem.getItem();
                Item item = entityItemStack.getItem();

                if (item == Items.ENCHANTED_BOOK || (item instanceof ItemSword && entityItemStack.isItemEnchanted()) || (item instanceof ItemTool && entityItemStack.isItemEnchanted()) || (item instanceof ItemHoe && entityItemStack.isItemEnchanted()) || (item instanceof ItemBow && entityItemStack.isItemEnchanted())) {
                    for (int i = 0; i < 500; i++) {
                        double d0 = (double) ((float) pos.getX() + worldIn.rand.nextFloat());
                        double d1 = (double) ((float) pos.getY() + 1 + worldIn.rand.nextFloat());
                        double d2 = (double) ((float) pos.getZ() + worldIn.rand.nextFloat());
                        worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0, 0.1, 0);
                    }
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_) {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_LEGS);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_WEST);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_NORTH);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_EAST);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_SOUTH);
    }

    @SuppressWarnings("deprecation")
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return FULL_BLOCK_AABB;
    }

    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Items.CAULDRON;
    }

    @SuppressWarnings("deprecation")
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(BlockRegistry.BIN);
    }

    public boolean blocksMovement(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }
}
