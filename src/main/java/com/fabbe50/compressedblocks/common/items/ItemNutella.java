package com.fabbe50.compressedblocks.common.items;

import com.fabbe50.compressedblocks.common.entities.EntityCorgi;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

/**
 * Created by fabbe on 24/03/2018 - 5:34 PM.
 */
public class ItemNutella extends ItemFood {
    public ItemNutella(int amount, float saturation, boolean isWolfFood, String registryName) {
        super(amount, saturation, isWolfFood, registryName);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote)
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 100, 3));
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (target instanceof EntityWolf || target instanceof EntityCorgi || target instanceof EntityParrot) {
            if (!playerIn.isCreative())
                stack.shrink(1);
            target.attackEntityFrom(DamageSource.STARVE, 15);
            return true;
        }
        return false;
    }
}
