package com.fabbe50.compressedblocks.common.blocks;

import com.brandon3055.brandonscore.handlers.ProcessHandler;
import com.brandon3055.draconicevolution.blocks.reactor.ProcessExplosion;
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

import javax.annotation.Nullable;
import java.util.List;

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
                ProcessExplosion explosion = new ProcessExplosion(pos, 350, worldIn.getMinecraftServer().getWorld(playerIn.dimension), 0);
                ProcessHandler.addProcess(explosion);
                exploded = true;
            }

            return true;
        }

        return false;
    }
}
