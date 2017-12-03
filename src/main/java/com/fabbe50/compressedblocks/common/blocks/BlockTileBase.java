package com.fabbe50.compressedblocks.common.blocks;

import com.fabbe50.compressedblocks.common.blocks.base.BlockBase;
import com.fabbe50.compressedblocks.common.tileentities.TileBase;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by fabbe50 on 11/10/2016.
 */
public class BlockTileBase extends BlockBase {
    protected boolean basicGui = true;

    public BlockTileBase(Material material, MapColor mapColor, String name, float hardness, float resistance, CreativeTabs tab) {
        super(material, mapColor, name, hardness, resistance, tab);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState blockState, EntityLivingBase living, ItemStack itemStack) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof TileBase) {
            //((TileBase)tile)
        }
    }
}
