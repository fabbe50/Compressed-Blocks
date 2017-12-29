package com.fabbe50.compressedblocks.common.items;

import com.fabbe50.compressedblocks.common.items.base.ItemBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

/**
 * Created by fabbe on 04/12/2017 - 12:51 AM.
 */
public class ItemInkExtractor extends ItemBase {
    public ItemInkExtractor(String itemName, CreativeTabs tab) {
        super(itemName, tab);
        this.setMaxStackSize(1);
        this.setMaxDamage(128);
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        this.setDamage(stack, 128);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (target instanceof EntitySquid) {
            playerIn.inventory.addItemStackToInventory(new ItemStack(Items.DYE));
            stack.damageItem(1, playerIn);
            target.attackEntityFrom(DamageSource.causePlayerDamage(playerIn), itemRand.nextInt(3));
            target.performHurtAnimation();
            return true;
        }
        return false;
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        this.showDurabilityBar(stack);
    }
}
