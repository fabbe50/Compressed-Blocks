package com.fabbe50.compressedblocks.common.items.base;

import com.fabbe50.compressedblocks.core.utils.helper.LogHelper;
import com.google.common.collect.Multimap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

/**
 * Created by fabbe on 09/01/2018 - 11:13 AM.
 */
public class ItemCustomSword extends ItemSword {
    private final float damage;
    private float modifier;
    private ToolMaterial material;

    public ItemCustomSword(ToolMaterial material, float durabilityModifier, float damageModifier, String name, CreativeTabs tab) {
        super(material);
        this.material = material;
        this.modifier = damageModifier;
        this.setMaxDamage(Math.round(material.getMaxUses() * durabilityModifier));
        this.damage = 3.0f + material.getAttackDamage();
        this.setItemName(name);
        this.setCreativeTab(tab);
    }

    private void setItemName(String itemName) {
        this.setRegistryName(itemName);
        this.setUnlocalizedName(this.getRegistryName().toString());
    }

    @Override
    public float getAttackDamage() {
        return this.material.getAttackDamage() * this.modifier;
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);

        if (slot == EntityEquipmentSlot.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)Math.round(this.damage * this.modifier), 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
        }

        return multimap;
    }
}
