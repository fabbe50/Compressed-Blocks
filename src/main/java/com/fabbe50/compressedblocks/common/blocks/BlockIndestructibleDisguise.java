package com.fabbe50.compressedblocks.common.blocks;

import com.fabbe50.compressedblocks.common.creativetabs.CBTab;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by fabbe50 on 18/03/2017.
 */
public class BlockIndestructibleDisguise extends BlockContainer {
    protected BlockIndestructibleDisguise(Material materialIn, MapColor mapColor, String name, float hardness, float resistance, CreativeTabs tab) {
        super(materialIn, mapColor);
        setBlockName(this, name);
        this.setCreativeTab(tab != null ? tab : CBTab.blockTab);
        this.setHardness(hardness);
        this.setResistance(resistance);
    }

    public static void setBlockName(Block block, String blockName) {
        block.setRegistryName(blockName);
        block.setUnlocalizedName(block.getRegistryName().toString());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag flags) {
        tooltip.add("");
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }
}

