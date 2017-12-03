package com.fabbe50.compressedblocks.common.blocks.base;

import com.fabbe50.compressedblocks.common.creativetabs.CBTab;
import com.fabbe50.compressedblocks.core.lib.Configs;
import com.fabbe50.compressedblocks.core.lib.SpecialDropStorage;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by fabbe on 03/12/2017 - 2:50 AM.
 */
public class BlockBase extends Block {
    public static float hardness = 1f;
    public static float resistance = 1f;
    public static CreativeTabs tab = null;

    public BlockBase(Material material, MapColor mapColor, String blockName, float hardness, float resistance, @Nullable CreativeTabs tab) {
        super(material, mapColor);
        setBlockName(this, blockName);
        this.tab = tab;
        this.setCreativeTab(tab != null ? tab : CBTab.blockTab);
        this.hardness = hardness;
        this.resistance = resistance;
        setHardness(this.hardness);
        setResistance(this.resistance);
    }

    public BlockBase(Material materialIn, String blockName) {
        this(materialIn, materialIn.getMaterialMapColor(), blockName, getHardness(), getResistance(), tab);
    }

    private static float getResistance() {
        return BlockBase.resistance;
    }

    private static float getHardness() {
        return BlockBase.hardness;
    }

    public static void setBlockName(Block block, String blockName) {
        block.setRegistryName(blockName);
        block.setUnlocalizedName(block.getRegistryName().toString());
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return SpecialDropStorage.specialDropBlock.contains(state.getBlock()) ? SpecialDropStorage.specialDropItem.get(SpecialDropStorage.specialDropBlock.indexOf(state.getBlock())) : Item.getItemFromBlock(state.getBlock());
    }
}
