package com.fabbe50.compressedblocks.common.blocks;

import com.fabbe50.compressedblocks.core.registry.BlockRegistry;
import com.fabbe50.compressedblocks.core.utils.helper.RedstoneHelper;
import com.thefifthidiot.tficore.common.blocks.base.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

/**
 * Created by fabbe50 on 12/10/2016.
 */
public class BlockMultiColored extends BlockBase {
    private int powerLevel = 0;
    int powerActiveSide = 0;
    public static final PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.<EnumDyeColor>create("color", EnumDyeColor.class);

    public BlockMultiColored(Material material, MapColor mapColor, String blockName, float hardness, float resistance, @Nullable CreativeTabs tab) {
        super(material, mapColor, blockName, hardness, resistance, tab);
        this.setDefaultState(this.blockState.getBaseState().withProperty(COLOR, EnumDyeColor.BLACK));
        this.powerLevel = 15;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list) {
        for (EnumDyeColor enumdyecolor : EnumDyeColor.values()) {
            list.add(new ItemStack(itemIn, 1, enumdyecolor.getMetadata()));
        }
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        updateColors(worldIn, pos);
    }

    @SuppressWarnings("deprecation")
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        updateColors(worldIn, pos);
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            if (this.powerLevel > 0 && !worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, BlockRegistry.COLORBLOCK.getDefaultState().withProperty(COLOR, EnumDyeColor.BLACK));
            }
        }
    }

    private void updateColors(World worldIn, BlockPos pos) {
        int[] power = RedstoneHelper.getRedstonePowerFromAllSides(worldIn, pos);
        for (int aPower : power) {
            if (aPower > 0) {
                powerActiveSide = aPower;
                break;
            }
        }
        if (!worldIn.isRemote) {
            if (this.powerLevel > 0 && !worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, BlockRegistry.COLORBLOCK.getDefaultState().withProperty(COLOR, EnumDyeColor.BLACK));
            }
            else if (worldIn.isBlockPowered(pos)) { //this.powerLevel == 0 && worldIn.isBlockPowered(pos) || this.powerLevel != powerActiveSide &&
                if (powerActiveSide == 15) {
                    worldIn.setBlockState(pos, BlockRegistry.COLORBLOCK.getDefaultState().withProperty(COLOR, EnumDyeColor.WHITE));
                }
                else {
                    worldIn.setBlockState(pos, BlockRegistry.COLORBLOCK.getDefaultState().withProperty(COLOR, EnumDyeColor.byDyeDamage(powerActiveSide)));
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    public MapColor getMapColor(IBlockState state) {
        return state.getValue(COLOR).getMapColor();
    }

    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(BlockRegistry.COLORBLOCK);
    }

    @SuppressWarnings("deprecation")
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(BlockRegistry.COLORBLOCK);
    }

    protected ItemStack createStackedBlock(IBlockState state) {
        return new ItemStack(BlockRegistry.COLORBLOCK);
    }

    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(meta));
    }

    public int getMetaFromState(IBlockState state) {
        return state.getValue(COLOR).getMetadata();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, COLOR);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("A completely pointless block that");
        tooltip.add("changes colors based on redstone strength.");
    }
}
