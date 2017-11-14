package com.fabbe50.compressedblocks.common.items;

import com.thefifthidiot.tficore.common.items.ItemBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * Created by fabbe50 on 23/04/2017.
 */
public class ItemEggHatcher extends ItemBase {
    public ItemEggHatcher(String itemName, CreativeTabs tab) {
        super(itemName, tab);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if (player.inventory.hasItemStack(new ItemStack(Items.EGG)) || player.isCreative()) {
                int spawnAmount = 1;
                if (!player.isCreative()) {
                    if (player.isSneaking()) {
                        spawnAmount = player.inventory.getStackInSlot(player.inventory.getSlotFor(new ItemStack(Items.EGG))).getCount();
                        player.inventory.deleteStack(player.inventory.getStackInSlot(player.inventory.getSlotFor(new ItemStack(Items.EGG))));
                    }
                    else
                        player.inventory.decrStackSize(player.inventory.getSlotFor(new ItemStack(Items.EGG)), 1);
                }

                for (int i = 0; i < spawnAmount; i++) {
                    Random rand = new Random();
                    Entity entity;
                    if (rand.nextInt(2000) == 1337) {
                        entity = new EntityPig(worldIn);
                        entity.setCustomNameTag("Chicken");
                        entity.setAlwaysRenderNameTag(true);
                    }
                    else
                        entity = new EntityChicken(worldIn);
                    BlockPos pos2 = new BlockPos(pos.offset(facing).getX(), pos.offset(facing).getY(), pos.offset(facing).getZ());

                    if (facing == EnumFacing.UP || facing == EnumFacing.DOWN) {
                        entity.setPosition(pos2.getX() + hitX, pos2.getY(), pos2.getZ() + hitZ);
                    } else if (facing == EnumFacing.EAST || facing == EnumFacing.WEST) {
                        entity.setPosition(pos2.getX() + 0.5, pos2.getY() + hitY, pos2.getZ() + hitZ);
                    } else if (facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH) {
                        entity.setPosition(pos2.getX() + hitX, pos2.getY() + hitY, pos2.getZ() + 0.5);
                    }

                    worldIn.spawnEntity(entity);
                }
                return EnumActionResult.SUCCESS;
            }
            return EnumActionResult.FAIL;
        }
        return EnumActionResult.PASS;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("100% hatch-rate!");
        tooltip.add("Shift + Right Click to Hatch full stack");
    }
}
