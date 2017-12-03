package com.fabbe50.compressedblocks.common.items.base;

import com.fabbe50.compressedblocks.common.creativetabs.CBTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by fabbe on 03/12/2017 - 3:17 AM.
 */
public class ItemBase extends Item {
    public ItemBase(String itemName, CreativeTabs tab) {
        setItemName(this, itemName);
        setCreativeTab(tab != null ? tab : CBTab.itemTab);
    }

    public static void setItemName(Item item, String itemName) {
        item.setRegistryName(itemName);
        item.setUnlocalizedName(item.getRegistryName().toString());
    }
}
