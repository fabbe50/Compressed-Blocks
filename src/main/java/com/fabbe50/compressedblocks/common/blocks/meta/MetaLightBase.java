package com.fabbe50.compressedblocks.common.blocks.meta;

import com.fabbe50.compressedblocks.common.creativetabs.CBTab;
import com.fabbe50.compressedblocks.core.lib.EnumLight;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 * Created by fabbe on 07/06/2017.
 */
public class MetaLightBase extends MetaBlockBase {
    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumLight.class);

    public MetaLightBase(Material material, MapColor mapColor, String modID, String itemName, float hardness, float resistance, @Nullable CreativeTabs tab) {
        super(material);
        setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumLight.LIGHT0));
        this.setRegistryName(modID, itemName);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setCreativeTab(CBTab.compressedBlocksTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.SOLID;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] { TYPE });
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TYPE, EnumLight.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumLight)state.getValue(TYPE)).getMetadata();
    }

    @Override
    public int damageDropped(IBlockState state) {
        return ((EnumLight)state.getValue(TYPE)).getMetadata();
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
        for (EnumLight type : EnumLight.values()) {
            list.add(new ItemStack(this, 1, type.getMetadata()));
        }
    }
}
