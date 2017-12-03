package com.fabbe50.compressedblocks.common.items;

import com.fabbe50.compressedblocks.core.registry.BlockRegistry;
import com.fabbe50.compressedblocks.core.registry.ItemRegistry;
import com.fabbe50.compressedblocks.core.registry.ToolMaterialRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
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
            if (worldIn.getBlockState(pos).getBlock() == Blocks.CAULDRON) {
                List<EntityItem> entityItemList = worldIn.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos), null);
                if (entityItemList.size() == 1) {
                    for (EntityItem item : entityItemList) {
                        if (item.getItem().getItem() == ItemRegistry.ENDGAMIUM_NUGGET) {
                            item.isDead = true;
                            worldIn.setBlockState(pos, BlockRegistry.BIN.getDefaultState());
                        }
                    }
                }
            }
        }
        if (worldIn.isRemote) {
            if (worldIn.getBlockState(pos).getBlock() == Blocks.CAULDRON) {
                List<EntityItem> entityItemList = worldIn.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos), null);
                if (entityItemList.size() == 1) {
                    for (EntityItem item : entityItemList) {
                        if (item.getItem().getItem() == ItemRegistry.ENDGAMIUM_NUGGET) {
                            for (int i = 0; i < 1000; i++) {
                                double d0 = (double) ((float) pos.getX() + worldIn.rand.nextFloat());
                                double d1 = (double) ((float) pos.getY() + 1 + worldIn.rand.nextFloat());
                                double d2 = (double) ((float) pos.getZ() + worldIn.rand.nextFloat());
                                double d3 = d0 - pos.getX();
                                double d4 = d1 - pos.getY();
                                double d5 = d2 - pos.getZ();
                                double d6 = (double) MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
                                d3 = d3 / d6;
                                d4 = d4 / d6;
                                d5 = d5 / d6;
                                double d7 = 0.5D / (d6 / (double) 4 + 0.1D);
                                d7 = d7 * (double) (worldIn.rand.nextFloat() * worldIn.rand.nextFloat() + 0.3F);
                                d3 = d3 * d7;
                                d4 = d4 * d7;
                                d5 = d5 * d7;
                                worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (d0 + pos.getX()) * 0.5, (d1 + pos.getY()) * 0.5, (d2 + pos.getZ()) * 0.5, d3, d4, d5, new int[0]);
                                worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, d3, d4, d5, new int[0]);
                            }
                            return EnumActionResult.SUCCESS;
                        }
                    }
                }
            }
        }
        return EnumActionResult.PASS;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
        if (!worldIn.isRemote) {
            BlockPos pos = player.getPosition();
            ItemStack stack = player.getHeldItem(hand);
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
                if (!(e instanceof EntityPlayer) && !(e instanceof EntityXPOrb) && !(e instanceof EntityItem)) {
                    e.attackEntityFrom(DamageSource.causePlayerDamage(player), 5 * multiplier[index]);
                    stack.damageItem(1, player);
                }
            }
            return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
        }
        return ActionResult.newResult(EnumActionResult.PASS, player.getHeldItem(hand));
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return super.getDurabilityForDisplay(stack);
    }
}
