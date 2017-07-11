package com.fabbe50.compressedblocks.common.items;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Set;

/**
 * Created by fabbe on 11/07/2017.
 */
public class ItemStaff extends ItemTool {
    float[] multiplier = new float[]{0.2f, 0.4f, 0.6f, 0.8f, 1, 1.5f};

    protected ItemStaff(float attackDamageIn, float attackSpeedIn, ToolMaterial materialIn, Set<Block> effectiveBlocksIn) {
        super(attackDamageIn, attackSpeedIn, materialIn, effectiveBlocksIn);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            AxisAlignedBB aabb = new AxisAlignedBB(pos.getX() - 5, pos.getY() - 5, pos.getZ() - 5, pos.getX() + 5, pos.getY() + 5, pos.getZ() + 5);

            List<Entity> entities = worldIn.getEntitiesWithinAABB(EntityLiving.class, aabb);
        }
        return EnumActionResult.FAIL;
    }
}
