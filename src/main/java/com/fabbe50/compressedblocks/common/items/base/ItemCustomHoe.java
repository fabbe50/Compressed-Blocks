package com.fabbe50.compressedblocks.common.items.base;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemHoe;

/**
 * Created by fabbe on 09/01/2018 - 11:17 AM.
 */
public class ItemCustomHoe extends ItemHoe {
    public ItemCustomHoe(ToolMaterial material, float durabilityModifier, String name, CreativeTabs tab) {
        super(material);
        this.setMaxDamage(Math.round(material.getMaxUses() * durabilityModifier));
        this.setItemName(name);
        this.setCreativeTab(tab);
    }

    private void setItemName(String itemName) {
        this.setRegistryName(itemName);
        this.setUnlocalizedName(this.getRegistryName().toString());
    }
}
