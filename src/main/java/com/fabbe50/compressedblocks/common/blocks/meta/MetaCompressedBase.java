package com.fabbe50.compressedblocks.common.blocks.meta;

import com.thefifthidiot.tficore.common.blocks.meta.MetaBlockBase;
import com.thefifthidiot.tficore.common.creativetabs.TFITab;
import com.thefifthidiot.tficore.lib.Configs;
import com.thefifthidiot.tficore.lib.EnumCompressed;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by fabbe50 on 23/06/2016.
 */
@SuppressWarnings("deprecation, unchecked")
public class MetaCompressedBase extends MetaBlockBase {
    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumCompressed.class);

    public MetaCompressedBase(Material material, MapColor mapColor, String modID, String itemName, float hardness, float resistance, @Nullable CreativeTabs tab) {
        super(material);
        setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumCompressed.SINGLE));
        this.setRegistryName(modID, itemName);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setCreativeTab(tab != null ? tab : (Configs.tfitabs ? TFITab.blockTab : null));
    }

    @Override
    protected BlockStateContainer createBlockState() {
	     return new BlockStateContainer(this, new IProperty[] { TYPE });
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TYPE, EnumCompressed.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumCompressed)state.getValue(TYPE)).getMetadata();
    }

    @Override
    public int damageDropped(IBlockState state) {
        return ((EnumCompressed)state.getValue(TYPE)).getMetadata();
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        for (EnumCompressed type : EnumCompressed.values()) {
            list.add(new ItemStack(itemIn, 1, type.getMetadata()));
        }
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        return stack.getItemDamage() == 0 ? "single" :
                (stack.getItemDamage() == 1 ? "double" :
                        (stack.getItemDamage() == 2 ? "triple" :
                                (stack.getItemDamage() == 3 ? "quadruple" :
                                        (stack.getItemDamage() == 4 ? "quintuple" :
                                                (stack.getItemDamage() == 5 ? "sextuple" :
                                                        (stack.getItemDamage() == 6 ? "septuple" :
                                                                (stack.getItemDamage() == 7 ? "octuple" : "single")))))));
    }
}
