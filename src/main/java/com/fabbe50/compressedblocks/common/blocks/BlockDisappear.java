package com.fabbe50.compressedblocks.common.blocks;

import com.fabbe50.compressedblocks.core.lib.Configs;
import com.fabbe50.compressedblocks.core.lib.EntityBlacklistStorage;
import com.fabbe50.compressedblocks.core.utils.Utilities;
import com.thefifthidiot.tficore.common.blocks.base.BlockBase;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by fabbe50 on 21/09/2016.
 */
public class BlockDisappear extends BlockBase {
    protected static final AxisAlignedBB BB = new AxisAlignedBB(0, 0, 0, 1, 0.99, 1);

    public BlockDisappear(Material material, MapColor mapColor, String blockName, float hardness, float resistance, @Nullable CreativeTabs tab) {
        super(material, mapColor, blockName, hardness, resistance, tab);
        this.setTickRandomly(true);
    }

    @Override
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos) {
        return BB;
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if (Configs.isWhitelist ? EntityBlacklistStorage.entityBlackList.contains(entityIn.getClass()) :
                !EntityBlacklistStorage.entityBlackList.contains(entityIn.getClass()) &&
                        Configs.playerIsTrigger ? entityIn instanceof EntityPlayer :
                        entityIn.getClass() != EntityPlayer.class) {
            Utilities.destroyBlock(worldIn, pos, false, false, false);
        }
    }
}
