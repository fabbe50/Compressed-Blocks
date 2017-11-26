package com.fabbe50.compressedblocks.common.blocks.meta;

import com.fabbe50.compressedblocks.common.creativetabs.CBTab;
import com.fabbe50.compressedblocks.core.lib.CBLibrary;
import com.thefifthidiot.tficore.common.blocks.meta.MetaBlockBase;
import com.thefifthidiot.tficore.common.creativetabs.TFITab;
import com.thefifthidiot.tficore.lib.Configs;
import com.thefifthidiot.tficore.lib.EnumCompressed;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by fabbe50 on 23/06/2016.
 */
@SuppressWarnings("deprecation, unchecked")
public class MetaCompressedBase extends MetaBlockBase {
    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumCompressed.class);
    private static boolean beaconBase = false;
    private static int beaconLevel = 0;

    public MetaCompressedBase(Material material, MapColor mapColor, String modID, String itemName, float hardness, float resistance, @Nullable CreativeTabs tab) {
        super(material);
        setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumCompressed.SINGLE));
        this.setRegistryName(modID, itemName);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setCreativeTab(CBTab.compressedBlocksTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
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
    public void getSubBlocks(CreativeTabs tabs, NonNullList<ItemStack> list) {
        for (EnumCompressed type : EnumCompressed.values()) {
            list.add(new ItemStack(this, 1, type.getMetadata()));
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

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        int itemDamage = stack.getItemDamage();

        for (String s : CBLibrary.compressedBlocks) {
            if (stack.getUnlocalizedName().contains(s))
                itemDamage++;
        }

        boolean selected = false;
        switch (itemDamage) {
            case 0: {
                tooltip.add("9 " + getBaseItem(stack));
                selected = true;
            }
            case 1: {
                if (!selected) {
                    tooltip.add("81 " + getBaseItem(stack));
                    selected = true;
                }
            }
            case 2: {
                if (!selected) {
                    tooltip.add("729 " + getBaseItem(stack));
                    selected = true;
                }
            }
            case 3: {
                if (!selected) {
                    tooltip.add("6,561 " + getBaseItem(stack));
                    selected = true;
                }
            }
            case 4: {
                if (!selected) {
                    tooltip.add("59,049 " + getBaseItem(stack));
                    selected = true;
                }
            }
            case 5: {
                if (!selected) {
                    tooltip.add("531,441 " + getBaseItem(stack));
                    selected = true;
                }
            }
            case 6: {
                if (!selected) {
                    tooltip.add("4,782,969 " + getBaseItem(stack));
                    selected = true;
                }
            }
            case 7: {
                if (!selected) {
                    tooltip.add("43,046,721 " + getBaseItem(stack));
                    selected = true;
                }
            }
            case 8: {
                if (!selected)
                    tooltip.add("387,420,489 " + getBaseItem(stack));
            }
        }
    }

    private String getBaseItem(ItemStack stack) {
        if (stack.getUnlocalizedName().contains("potato"))
            return "potatoes";
        else if (stack.getUnlocalizedName().contains("cobble"))
            return "cobblestone";
        else if (stack.getUnlocalizedName().contains("dirt"))
            return "dirt";
        else if (stack.getUnlocalizedName().contains("sand"))
            return "sand";
        else if (stack.getUnlocalizedName().contains("gravel"))
            return "gravel";
        else if (stack.getUnlocalizedName().contains("iron"))
            return "iron ingots";
        else if (stack.getUnlocalizedName().contains("gold"))
            return "gold ingots";
        else if (stack.getUnlocalizedName().contains("diamond"))
            return "diamond ingots";
        else if (stack.getUnlocalizedName().contains("emerald"))
            return "emeralds";
        else if (stack.getUnlocalizedName().contains("grass"))
            return "grass blocks";
        else
            return "";
    }

    @Override
    public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
        return beaconBase;
    }
}
