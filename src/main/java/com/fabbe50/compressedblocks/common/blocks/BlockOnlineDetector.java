package com.fabbe50.compressedblocks.common.blocks;

import com.fabbe50.compressedblocks.common.blocks.base.BlockBase;
import com.fabbe50.compressedblocks.common.items.ItemDNASample;
import com.fabbe50.compressedblocks.core.utils.PlayerTracker;
import com.fabbe50.compressedblocks.core.utils.helper.LogHelper;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by fabbe50 on 23/04/2017.
 */
public class BlockOnlineDetector extends BlockBase {
    private MinecraftServer server = null;
    private String name = "";
    private boolean online = false;

    public BlockOnlineDetector(Material material, MapColor mapColor, String blockName, float hardness, float resistance, @Nullable CreativeTabs tab) {
        super(material, mapColor, blockName, hardness, resistance, tab);
        setTickRandomly(true);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if (playerIn.getHeldItem(hand).getItem() instanceof ItemDNASample) {
                this.server = worldIn.getMinecraftServer();
                LogHelper.info("DNA Analyzing");
                ItemStack stack = playerIn.getHeldItem(hand);
                NBTTagCompound tags = stack.getTagCompound();
                String player;

                if (tags != null && tags.getSize() == 1 || tags != null && tags.getSize() == 3) {
                    player = tags.getString("player");
                    LogHelper.info("DNA Analyzed, bound to player: " + player);
                }
                else
                    return false;

                this.name = player;

                return true;
            }
        }
        return false;
    }

    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
        update(worldIn);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        update(worldIn);
    }

    @Override
    public int tickRate(World worldIn) {
        return 2;
    }

    public void update(World worldIn) {
        LogHelper.info("update");
        if (!worldIn.isRemote) {
            if (PlayerTracker.players.contains(name)) {
                online = true;
            } else {
                online = false;
            }
        }
    }

    @Override
    public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, @Nullable EnumFacing side) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canProvidePower(IBlockState state) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return online ? 15 : 0;
    }
}
