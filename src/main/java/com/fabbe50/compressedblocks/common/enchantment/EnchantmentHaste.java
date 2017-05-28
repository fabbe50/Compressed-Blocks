package com.fabbe50.compressedblocks.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

/**
 * Created by fabbe on 27/05/2017.
 */
public class EnchantmentHaste extends Enchantment {
    private static final int[] BASE_ENCHANTABILITY = new int[] {1, 5, 5};
    private static final int[] LEVEL_ENCHANTABILITY = new int[] {11, 8, 8};
    private static final int[] THRESHOLD_ENCHANTABILITY = new int[] {20, 20, 20};
    public final int damageType;

    protected EnchantmentHaste(Rarity rarityIn, int damageType, EntityEquipmentSlot[] slots) {
        super(rarityIn, EnumEnchantmentType.WEAPON, slots);
        this.damageType = damageType;
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return BASE_ENCHANTABILITY[this.damageType] + (enchantmentLevel - 1) * LEVEL_ENCHANTABILITY[this.damageType];
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + THRESHOLD_ENCHANTABILITY[this.damageType];
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public String getName() {
        return "compressedblocks.enchantment.haste";
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {
        return !(ench instanceof EnchantmentHaste);
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return stack.getItem() instanceof ItemAxe || super.canApply(stack);
    }


}
