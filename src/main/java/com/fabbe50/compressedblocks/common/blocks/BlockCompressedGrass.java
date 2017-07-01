package com.fabbe50.compressedblocks.common.blocks;

import com.fabbe50.compressedblocks.common.blocks.meta.MetaCompressedBase;
import com.fabbe50.compressedblocks.core.registry.BlockRegistry;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by fabbe on 01/07/2017.
 */
public class BlockCompressedGrass extends MetaCompressedBase {
    public BlockCompressedGrass(Material material, MapColor mapColor, String modID, String itemName, float hardness, float resistance, @Nullable CreativeTabs tab) {
        super(material, mapColor, modID, itemName, hardness, resistance, tab);
        setTickRandomly(true);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            int ticket = rand.nextInt(7);
            if (this.getMetaFromState(state) == 0) {
                if (ticket < 1) {
                    worldIn.setBlockState(pos, BlockRegistry.COMPRESSED_GRASS.getStateFromMeta(0));
                }
            }
            if (this.getMetaFromState(state) == 1) {
                if (ticket < 2) {
                    worldIn.setBlockState(pos, BlockRegistry.COMPRESSED_GRASS.getStateFromMeta(1));
                }
            }
            if (this.getMetaFromState(state) == 2) {
                if (ticket < 3) {
                    worldIn.setBlockState(pos, BlockRegistry.COMPRESSED_GRASS.getStateFromMeta(2));
                }
            }
            if (this.getMetaFromState(state) == 3) {
                if (ticket < 4) {
                    worldIn.setBlockState(pos, BlockRegistry.COMPRESSED_GRASS.getStateFromMeta(3));
                }
            }
            if (this.getMetaFromState(state) == 4) {
                if (ticket < 5) {
                    worldIn.setBlockState(pos, BlockRegistry.COMPRESSED_GRASS.getStateFromMeta(4));
                }
            }
            if (this.getMetaFromState(state) == 5) {
                if (ticket < 6) {
                    worldIn.setBlockState(pos, BlockRegistry.COMPRESSED_GRASS.getStateFromMeta(5));
                }
            }
            if (this.getMetaFromState(state) == 6) {
                if (ticket < 7) {
                    worldIn.setBlockState(pos, BlockRegistry.COMPRESSED_GRASS.getStateFromMeta(6));
                }
            }
            if (this.getMetaFromState(state) == 7) {
                if (ticket < 8) {
                    worldIn.setBlockState(pos, BlockRegistry.COMPRESSED_GRASS.getStateFromMeta(7));
                }
            }
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return new ItemStack(BlockRegistry.COMPRESSED_DIRT, 1, this.getMetaFromState(state)).getItem();
    }
}
