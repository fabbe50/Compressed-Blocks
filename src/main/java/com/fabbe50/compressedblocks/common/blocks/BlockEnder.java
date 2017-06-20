package com.fabbe50.compressedblocks.common.blocks;

import com.fabbe50.compressedblocks.core.registry.BlockRegistry;
import com.thefifthidiot.tficore.common.blocks.base.BlockBase;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by fabbe50 on 29/04/2017.
 */
public class BlockEnder extends BlockBase {
    public BlockEnder(Material material, MapColor mapColor, String blockName, float hardness, float resistance, @Nullable CreativeTabs tab) {
        super(material, mapColor, blockName, hardness, resistance, tab);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if (!playerIn.isSneaking()) {
                BlockPos pos1;

                if (facing == EnumFacing.NORTH) {
                    pos1 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 50);
                    worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
                    worldIn.setBlockState(pos1, BlockRegistry.ENDERBLOCK.getDefaultState());
                    playerIn.attemptTeleport(playerIn.posX, playerIn.posY, playerIn.posZ + 50);
                    return true;
                } else if (facing == EnumFacing.SOUTH) {
                    pos1 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 50);
                    worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
                    worldIn.setBlockState(pos1, BlockRegistry.ENDERBLOCK.getDefaultState());
                    playerIn.attemptTeleport(playerIn.posX, playerIn.posY, playerIn.posZ - 50);
                    return true;
                } else if (facing == EnumFacing.WEST) {
                    pos1 = new BlockPos(pos.getX() + 50, pos.getY(), pos.getZ());
                    worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
                    worldIn.setBlockState(pos1, BlockRegistry.ENDERBLOCK.getDefaultState());
                    playerIn.attemptTeleport(playerIn.posX + 50, playerIn.posY, playerIn.posZ);
                    return true;
                } else if (facing == EnumFacing.EAST) {
                    pos1 = new BlockPos(pos.getX() - 50, pos.getY(), pos.getZ());
                    worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
                    worldIn.setBlockState(pos1, BlockRegistry.ENDERBLOCK.getDefaultState());
                    playerIn.attemptTeleport(playerIn.posX - 50, playerIn.posY, playerIn.posZ);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
        return true;
    }
}
