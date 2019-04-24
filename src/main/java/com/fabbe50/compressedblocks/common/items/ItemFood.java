package com.fabbe50.compressedblocks.common.items;

import com.fabbe50.compressedblocks.core.reference.Reference;

/**
 * Created by fabbe50 on 11/07/2016.
 */
public class ItemFood extends net.minecraft.item.ItemFood {
    public ItemFood(int amount, float saturation, boolean isWolfFood, String registryName) {
        super(amount, saturation, isWolfFood);
        this.setRegistryName(Reference.MOD_ID, registryName);
        this.setTranslationKey(this.getRegistryName().toString());
    }
}
