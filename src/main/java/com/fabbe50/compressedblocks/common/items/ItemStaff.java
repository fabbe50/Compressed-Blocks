package com.fabbe50.compressedblocks.common.items;

import com.fabbe50.compressedblocks.core.registry.ToolMaterialRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTool;
import net.minecraft.util.DamageSource;
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
    float[] size = new float[]{5, 7, 9, 11, 13, 15};
    float[] multiplier = new float[]{0.2f, 0.4f, 0.6f, 0.8f, 1, 1.5f};
    ToolMaterial toolMaterial = null;

    public ItemStaff(float attackDamageIn, float attackSpeedIn, ToolMaterial materialIn, Set<Block> effectiveBlocksIn, String name) {
        super(attackDamageIn, attackSpeedIn, materialIn, effectiveBlocksIn);
        setItemName(this, name);
        toolMaterial = materialIn;
    }

    private void setItemName(Item item, String name) {
        item.setRegistryName(name);
        item.setUnlocalizedName(item.getRegistryName().toString());
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            int index = 0;
            if (toolMaterial == ToolMaterial.WOOD)
                index = 0;
            else if (toolMaterial == ToolMaterial.STONE)
                index = 1;
            else if (toolMaterial == ToolMaterial.IRON)
                index = 2;
            else if (toolMaterial == ToolMaterial.GOLD)
                index = 3;
            else if (toolMaterial == ToolMaterial.DIAMOND)
                index = 4;
            else if (toolMaterial == ToolMaterialRegistry.TOOL_ENDGAMIUM)
                index = 5;

            AxisAlignedBB aabb = new AxisAlignedBB(pos.getX() - size[index], pos.getY() - size[index], pos.getZ() - size[index], pos.getX() + size[index], pos.getY() + size[index], pos.getZ() + size[index]);

            List<Entity> entities = worldIn.getEntitiesWithinAABB(EntityLiving.class, aabb);

            for (Entity e : entities) {
                e.attackEntityFrom(DamageSource.GENERIC, 5 * multiplier[index]);
            }
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }
}
