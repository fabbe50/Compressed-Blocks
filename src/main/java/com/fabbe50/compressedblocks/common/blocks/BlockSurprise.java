package com.fabbe50.compressedblocks.common.blocks;

import com.fabbe50.compressedblocks.common.blocks.base.BlockBase;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

/**
 * Created by fabbe50 on 24/09/2016.
 */
public class BlockSurprise extends BlockBase {
    protected static final AxisAlignedBB BB = new AxisAlignedBB(0, 0.01, 0, 1, 1, 1);
    static Random rand = new Random();

    public BlockSurprise(Material material, MapColor mapColor, String blockName, float hardness, float resistance, @Nullable CreativeTabs tab) {
        super(material, mapColor, blockName, hardness, resistance, tab);
        this.setTickRandomly(true);
    }

    @Override
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos) {
        return BB;
    }

    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if (!worldIn.isRemote) {
            if (entityIn instanceof EntityPlayer) {
                int i = rand.nextInt(99);
                worldIn.destroyBlock(pos, false);
                if (i == 0) {
                    worldIn.setBlockState(pos, Blocks.EMERALD_BLOCK.getDefaultState());
                } else if (0 < i && i <= 3) {
                    worldIn.setBlockState(pos, Blocks.DIAMOND_BLOCK.getDefaultState());
                } else if (4 <= i && i <= 12) {
                    worldIn.setBlockState(pos, Blocks.GOLD_BLOCK.getDefaultState());
                } else if (13 <= i && i <= 36) {
                    worldIn.setBlockState(pos, Blocks.IRON_BLOCK.getDefaultState());
                } else if (37 <= i && i <= 86) {
                    BlockPos pos2 = new BlockPos(pos.getX(), pos.getY() - 4, pos.getZ());
                    worldIn.destroyBlock(pos2, true);
                    worldIn.setBlockState(pos2, Blocks.GRASS.getDefaultState());
                    EntityPig pig = new EntityPig(worldIn);
                    pig.setPosition(pos.getX() + 0.5, pos.getY() - 3, pos.getZ() + 0.5);
                    worldIn.spawnEntity(pig);
                } else {
                    EntityTNTPrimed tnt = new EntityTNTPrimed(worldIn, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, (EntityLivingBase) entityIn);
                    worldIn.spawnEntity(tnt);
                }
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        tooltip.add("It'sa mee Minecraftio!");
    }
}
