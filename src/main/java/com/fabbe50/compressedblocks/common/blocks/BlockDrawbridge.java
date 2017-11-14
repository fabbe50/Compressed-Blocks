package com.fabbe50.compressedblocks.common.blocks;

import com.fabbe50.compressedblocks.common.tileentities.TileEntityDrawbridge;
import com.fabbe50.compressedblocks.core.registry.StatsRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockSourceImpl;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

/**
 * Created by fabbe on 13/08/2017 - 1:04 PM.
 */
public class BlockDrawbridge extends BlockContainer {
    public static final PropertyDirection FACING = BlockDirectional.FACING;
    public static final PropertyBool TRIGGERED = PropertyBool.create("triggered");

    public BlockDrawbridge(Material materialIn) {
        super(materialIn);
    }

    @Override
    public int tickRate(World worldIn) {
        return 4;
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        super.onBlockAdded(worldIn, pos, state);
        this.setDefaultDirection(worldIn, pos, state);
    }

    private void setDefaultDirection(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
            boolean flag = worldIn.getBlockState(pos.north()).isFullBlock();
            boolean flag1 = worldIn.getBlockState(pos.south()).isFullBlock();

            if (enumfacing == EnumFacing.NORTH && flag && !flag1) {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && flag1 && !flag) {
                enumfacing = EnumFacing.NORTH;
            }
            else {
                boolean flag2 = worldIn.getBlockState(pos.west()).isFullBlock();
                boolean flag3 = worldIn.getBlockState(pos.east()).isFullBlock();

                if (enumfacing == EnumFacing.WEST && flag2 && !flag3) {
                    enumfacing = EnumFacing.EAST;
                }
                else if (enumfacing == EnumFacing.EAST && flag3 && !flag2) {
                    enumfacing = EnumFacing.WEST;
                }
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing).withProperty(TRIGGERED, Boolean.valueOf(false)), 2);
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        }
        else {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if(tileEntity instanceof TileEntityDrawbridge) {
                playerIn.displayGUIChest((TileEntityDrawbridge)tileEntity);
                playerIn.addStat(StatsRegistry.DRAWBRIDGE_INTERACTION);
            }
            return true;
        }
    }

    private void drawWithdraw(World world, BlockPos pos, IBlockState state) {
        BlockSourceImpl blockSource = new BlockSourceImpl(world, pos);
        TileEntityDrawbridge tileEntityDrawbridge = (TileEntityDrawbridge)blockSource.getBlockTileEntity();

        if (tileEntityDrawbridge != null) {
            int i = tileEntityDrawbridge.getStackInSlot(0).getCount();

            if (i <= 0) {
                int k = 0;
                int size;
                Block block1 = world.getBlockState(pos.offset((EnumFacing)state.getValue(FACING), 1)).getBlock();
                for (int j = 2; j < 12; j++) {
                    if (block1 == world.getBlockState(pos.offset((EnumFacing)state.getValue(FACING), j))) {
                        k++;
                    }
                    else {
                        break;
                    }
                }
                for (int j = 1; j < k; j++) {
                    world.setBlockState(pos.offset((EnumFacing)state.getValue(FACING), j), Blocks.AIR.getDefaultState());
                }
                size = k + 1;
                tileEntityDrawbridge.setInventorySlotContents(0, new ItemStack(block1, size));
            }
            else {
                ItemStack itemStack = tileEntityDrawbridge.getStackInSlot(0);
                int size = itemStack.getCount();
                tileEntityDrawbridge.removeStackFromSlot(0);
                for (int j = 0; j < size; j++) {
                    world.setBlockState(pos.offset((EnumFacing)state.getValue(FACING), j + 1), Block.getBlockFromItem(itemStack.getItem()).getDefaultState());
                }
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        boolean powered = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(pos.up());
        boolean flag = (Boolean) state.getValue(TRIGGERED);

        if (powered && !flag) {
            worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
            worldIn.setBlockState(pos, state.withProperty(TRIGGERED, true), 4);
        }
        else if (!powered && flag) {
            worldIn.setBlockState(pos, state.withProperty(TRIGGERED, false), 4);
        }
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            drawWithdraw(worldIn, pos, state);
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityDrawbridge();
    }































































}
