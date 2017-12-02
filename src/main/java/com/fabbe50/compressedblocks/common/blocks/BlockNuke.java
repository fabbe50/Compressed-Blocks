package com.fabbe50.compressedblocks.common.blocks;

import com.fabbe50.compressedblocks.core.content.ExplodeDraconic;
import com.fabbe50.compressedblocks.core.content.ExplodeVanilla;
import com.thefifthidiot.tficore.common.blocks.base.BlockBase;
import com.thefifthidiot.tficore.utility.helper.LogHelper;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;

import javax.annotation.Nullable;

/**
 * Created by fabbe on 29/07/2017 - 5:40 PM.
 */
public class BlockNuke extends BlockBase {
    public BlockNuke(Material material, MapColor mapColor, String blockName, float hardness, float resistance, @Nullable CreativeTabs tab) {
        super(material, mapColor, blockName, hardness, resistance, tab);
    }

    private boolean exploded = false;

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if (!exploded) {
                LogHelper.info("Nuke Active");
                if (Loader.isModLoaded("brandonscore") && Loader.isModLoaded("draconicevolution"))
                    ExplodeDraconic.explode(pos, worldIn, playerIn);
                else
                    ExplodeVanilla.explode(pos, worldIn, playerIn);
                exploded = true;
            }

            return true;
        }

        return false;
    }
}
