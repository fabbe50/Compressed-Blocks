package com.fabbe50.compressedblocks.common.blocks;

import com.fabbe50.compressedblocks.common.items.ItemDNASample;
import com.fabbe50.compressedblocks.core.utils.PlayerTracker;
import com.thefifthidiot.tficore.common.blocks.base.BlockBase;
import com.thefifthidiot.tficore.utility.helper.LogHelper;
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
    private int power = 0;
    private Material material = null;
    private MapColor mapColor = null;
    private String blockName = "";
    private float hardness = 0;
    private float resistance = 0;
    private CreativeTabs tab = null;

    public BlockOnlineDetector(Material material, MapColor mapColor, String blockName, float hardness, float resistance, @Nullable CreativeTabs tab) {
        super(material, mapColor, blockName, hardness, resistance, tab);
        this.material = material;
        this.mapColor = mapColor;
        this.blockName = blockName;
        this.hardness = hardness;
        this.resistance = resistance;
        this.tab = tab;
    }

    public BlockOnlineDetector() {
        super(Material.REDSTONE_LIGHT, MapColor.BLUE, "NAME", 1.6f, 0.8f, null);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if (playerIn.getHeldItem(hand).getItem() instanceof ItemDNASample) {
                server = worldIn.getMinecraftServer();
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

                name = player;

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
        return 1;
    }

    public void update(World worldIn) {
        LogHelper.info("update");
        if (!worldIn.isRemote) {
            if (PlayerTracker.players.contains(name)) {
                power = 15;
            } else {
                power = 0;
            }
        }
    }

    @Override
    public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, @Nullable EnumFacing side) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return power;
    }
}
