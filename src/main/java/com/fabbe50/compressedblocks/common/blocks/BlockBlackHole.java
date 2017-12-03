package com.fabbe50.compressedblocks.common.blocks;

import com.fabbe50.compressedblocks.common.blocks.base.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

/**
 * Created by fabbe50 on 01/02/2017.
 */
public class BlockBlackHole extends BlockBase {
    AxisAlignedBB ar = new AxisAlignedBB(-10, -10, -10, 10, 10, 10);
    AxisAlignedBB bb = new AxisAlignedBB(0, 0, 0, 1, 1, 1);
    private int range = 16;

    public BlockBlackHole(Material material, MapColor mapColor, String blockName, float hardness, float resistance, @Nullable CreativeTabs tab) {
        super(material, mapColor, blockName, hardness, resistance, tab);
        setTickRandomly(true);

    }

    @Override
    public Block setTickRandomly(boolean shouldTick) {
        return shouldTick ? this : null;
    }

    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
        updateTick(worldIn, pos, state, random);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            int rangeSqr = range * range;
            List<Entity> entities = worldIn.getEntitiesWithinAABB(Entity.class, ar);
            for (Entity entity : entities) {
                double x = (pos.getX() + 0.5D - entity.posX);
                double y = (pos.getY() + 0.5D - entity.posY);
                double z = (pos.getZ() + 0.5D - entity.posZ);

                double distance = Math.sqrt(x * x + y * y + z * z);
                double speed = 0.06;
                double distScale = 1.0 - Math.min(0.9, distance / rangeSqr);
                distScale *= distScale;

                entity.motionX += x / distance * distScale * speed;
                entity.motionY += y / distance * distScale * 0.2;
                entity.motionZ += z / distance * distScale * speed;
            }
        }
    }

    @Override
    public int tickRate(World worldIn) {
        return 2;
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        entityIn.attackEntityFrom(DamageSource.GENERIC, 3f);
    }

    @Nullable
    @Override
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return null;
    }

    @Override
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return bb;
    }
}
