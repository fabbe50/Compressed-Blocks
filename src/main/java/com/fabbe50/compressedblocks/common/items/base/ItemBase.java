package com.fabbe50.compressedblocks.common.items.base;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by fabbe on 03/12/2017 - 3:17 AM.
 */
public class ItemBase extends Item {
    public ItemBase(String itemName, CreativeTabs tab) {
        setItemName(this, itemName);
        this.setCreativeTab(tab);
    }

    public static void setItemName(Item item, String itemName) {
        item.setRegistryName(itemName);
        item.setTranslationKey(item.getRegistryName().toString());
    }
}
