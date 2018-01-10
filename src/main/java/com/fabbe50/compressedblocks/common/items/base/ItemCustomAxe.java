package com.fabbe50.compressedblocks.common.items.base;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemAxe;

/**
 * Created by fabbe on 09/01/2018 - 11:16 AM.
 */
public class ItemCustomAxe extends ItemAxe {
    public ItemCustomAxe(ToolMaterial material, float durabilityModifier, float efficiencyMultiplier, String name, CreativeTabs tab) {
        super(material);
        this.setMaxDamage(Math.round(material.getMaxUses() * durabilityModifier));
        this.efficiency = material.getEfficiency() * efficiencyMultiplier;
        this.setItemName(name);
        this.setCreativeTab(tab);
    }

    private void setItemName(String itemName) {
        this.setRegistryName(itemName);
        this.setUnlocalizedName(this.getRegistryName().toString());
    }
}
