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

    public BlockAdjustableLight(Material material, MapColor mapColor, String itemName, float hardness, float resistance, @Nullable CreativeTabs tab, float lightStrength) {
        super(material, mapColor, itemName, hardness, resistance, tab);
        this.setLightLevel(lightStrength);
    }


    @SuppressWarnings("deprecation")
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        updateLight(worldIn, pos);
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            if (this.powerActiveSide > 0 && !worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, BlockRegistry.LIGHTBLOCK.getDefaultState());
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
                worldIn.setBlockState(pos, BlockRegistry.LIGHTBLOCK.getDefaultState());
            }
            else if (worldIn.isBlockPowered(pos)) {
                switch(powerActiveSide) {
                    case 1: {
                        worldIn.setBlockState(pos, BlockRegistry.LIGHTBLOCK1.getDefaultState());
                        break;
                    }
                    case 2: {
                        worldIn.setBlockState(pos, BlockRegistry.LIGHTBLOCK2.getDefaultState());
                        break;
                    }
                    case 3: {
                        worldIn.setBlockState(pos, BlockRegistry.LIGHTBLOCK3.getDefaultState());
                        break;
                    }
                    case 4: {
                        worldIn.setBlockState(pos, BlockRegistry.LIGHTBLOCK4.getDefaultState());
                        break;
                    }
                    case 5: {
                        worldIn.setBlockState(pos, BlockRegistry.LIGHTBLOCK5.getDefaultState());
                        break;
                    }
                    case 6: {
                        worldIn.setBlockState(pos, BlockRegistry.LIGHTBLOCK6.getDefaultState());
                        break;
                    }
                    case 7: {
                        worldIn.setBlockState(pos, BlockRegistry.LIGHTBLOCK7.getDefaultState());
                        break;
                    }
                    case 8: {
                        worldIn.setBlockState(pos, BlockRegistry.LIGHTBLOCK8.getDefaultState());
                        break;
                    }
                    case 9: {
                        worldIn.setBlockState(pos, BlockRegistry.LIGHTBLOCK9.getDefaultState());
                        break;
                    }
                    case 10: {
                        worldIn.setBlockState(pos, BlockRegistry.LIGHTBLOCK10.getDefaultState());
                        break;
                    }
                    case 11: {
                        worldIn.setBlockState(pos, BlockRegistry.LIGHTBLOCK11.getDefaultState());
                        break;
                    }
                    case 12: {
                        worldIn.setBlockState(pos, BlockRegistry.LIGHTBLOCK12.getDefaultState());
                        break;
                    }
                    case 13: {
                        worldIn.setBlockState(pos, BlockRegistry.LIGHTBLOCK13.getDefaultState());
                        break;
                    }
                    case 14: {
                        worldIn.setBlockState(pos, BlockRegistry.LIGHTBLOCK14.getDefaultState());
                        break;
                    }
                    case 15: {
                        worldIn.setBlockState(pos, BlockRegistry.LIGHTBLOCK15.getDefaultState());
                        break;
                    }
                }
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
