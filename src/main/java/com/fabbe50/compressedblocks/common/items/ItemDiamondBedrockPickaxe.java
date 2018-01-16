package com.fabbe50.compressedblocks.common.items;

import com.fabbe50.compressedblocks.common.items.base.ItemCustomPickaxe;
import com.google.common.collect.Multimap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * Created by fabbe on 09/01/2018 - 11:39 AM.
 */
public class ItemDiamondBedrockPickaxe extends ItemCustomPickaxe {
    public ItemDiamondBedrockPickaxe(ToolMaterial material, float durabilityModifier, float efficiencyMultiplier, String name, CreativeTabs tab) {
        super(material, durabilityModifier, efficiencyMultiplier, name, tab);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (entityIn instanceof EntityPlayer) {
            if (((EntityPlayer) entityIn).getHeldItem(EnumHand.MAIN_HAND).equals(stack)) {
                Potion haste = Potion.REGISTRY.getObject(new ResourceLocation("minecraft", "haste"));
                if (haste != null) {
                    ((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(haste, -1, 2, false, false));
                }
            }
        }
    }
}
