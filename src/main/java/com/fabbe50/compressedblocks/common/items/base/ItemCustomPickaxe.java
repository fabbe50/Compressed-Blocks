package com.fabbe50.compressedblocks.common.items.base;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;

/**
 * Created by fabbe on 07/01/2018 - 6:43 AM.
 */
public class ItemCustomPickaxe extends ItemPickaxe {
    public ItemCustomPickaxe(ToolMaterial material, float durabilityModifier, float efficiencyMultiplier, String name, CreativeTabs tab) {
        super(material);
        this.setMaxDamage(Math.round(material.getMaxUses() * durabilityModifier));
        this.efficiency = material.getEfficiency() * efficiencyMultiplier;
        this.setItemName(name);
        this.setCreativeTab(tab);
    }

    private void setItemName(String itemName) {
        this.setRegistryName(itemName);
        this.setTranslationKey(this.getRegistryName().toString());
    }
}
