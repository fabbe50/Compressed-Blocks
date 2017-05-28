package com.fabbe50.compressedblocks.common.blocks;

import com.fabbe50.compressedblocks.core.registry.BlockRegistry;
import com.thefifthidiot.tficore.common.blocks.base.BlockBase;
import com.thefifthidiot.tficore.utility.helper.RedstoneHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

/**
 * Created by fabbe50 on 20/04/2017.
 */
public class BlockAdjustableLight extends BlockBase {
    int powerActiveSide = 0;

    public BlockAdjustableLight(Material material, MapColor mapColor, String blockName, float hardness, float resistance, @Nullable CreativeTabs tab) {
        super(material, mapColor, blockName, hardness, resistance, tab);
    }

    @SuppressWarnings("deprecation")
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        updateLight(worldIn, pos);
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            if (this.powerActiveSide > 0 && !worldIn.isBlockPowered(pos)) {
                this.setLightLevel(0);
            }
        }
    }

    private void updateLight(World worldIn, BlockPos pos) {
        int[] power = RedstoneHelper.getRedstonePowerFromAllSides(worldIn, pos);
        for (int aPower : power) {
            if (aPower > 0) {
                powerActiveSide = aPower;
                break;
            }
        }
        if (!worldIn.isRemote) {
            if (this.powerActiveSide > 0 && !worldIn.isBlockPowered(pos)) {
                this.setLightLevel(0);
            }
            else if (worldIn.isBlockPowered(pos)) {
                this.setLightLevel((float)(this.powerActiveSide / 15));
            }
        }
    }

    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(BlockRegistry.LIGHTBLOCK);
    }

    @SuppressWarnings("deprecation")
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(BlockRegistry.LIGHTBLOCK);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("Adjustable light-source");
    }
}
