package com.fabbe50.compressedblocks.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by fabbe on 21/05/2017.
 */
public class ItemBowledFood extends ItemFood {
    public ItemBowledFood(int amount, float saturation, boolean isWolfFood, String registryName) {
        super(amount, saturation, isWolfFood, registryName);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        boolean added = player.inventory.addItemStackToInventory(new ItemStack(Items.BOWL, 1, 0));

        if (!added)
            player.dropItem(new ItemStack(Items.BOWL, 1, 0), true);
    }
}
