package com.fabbe50.compressedblocks.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
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
}
