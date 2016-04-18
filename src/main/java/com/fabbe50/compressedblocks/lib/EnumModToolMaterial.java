package com.fabbe50.compressedblocks.lib;

import com.fabbe50.compressedblocks.init.ModItems;
import com.fabbe50.compressedblocks.utility.MathHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by fabbe50 on 07/03/2016.
 */
public enum EnumModToolMaterial {
    endgamium (3,9001,30f,MathHelper.endgamiumbasedamage,35),
    potato (3,15,1f,6,20);

    @Deprecated public Item customCraftingMaterial = null;
    private ItemStack repairMaterial = null;

    /** The level of material this tool can harvest (3 = DIAMOND, 2 = IRON, 1 = STONE, 0 = WOOD/GOLD) */
    private final int harvestLevel;
    /** The number of uses this material allows. (wood = 59, stone = 131, iron = 250, diamond = 1561, gold = 32) */
    private final int maxUses;
    /** The strength of this tool material against blocks which it is effective against. */
    private final float efficiencyOnProperMaterial;
    /** Damage versus entities. */
    private final float damageVsEntity;
    /** Defines the natural enchantability factor of the material. [35 is perfect for only allowing unbreaking on a tool!]*/
    private final int enchantability;

    private EnumModToolMaterial(int harvestLevel, int maxUses, float efficiencyOnProperMaterial, float damageVsEntity, int enchantability)
    {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiencyOnProperMaterial = efficiencyOnProperMaterial;
        this.damageVsEntity = damageVsEntity;
        this.enchantability = enchantability;
    }

    public int getMaxUses() {
        return this.maxUses;
    }
    public float getEfficiencyOnProperMaterial() {
        return this.efficiencyOnProperMaterial;
    }
    public float getDamageVsEntity() {
        return this.damageVsEntity;
    }
    public int getHarvestLevel() {
        return this.harvestLevel;
    }
    public int getEnchantability() {
        return this.enchantability;
    }

    @Deprecated // Use getRepairItemStack below
    public Item repairMaterial()
    {
        switch (this)
        {
            case endgamium: return ModItems.endgamium;
            default:      return customCraftingMaterial;
        }
    }

    public EnumModToolMaterial setRepairItem(ItemStack stack)
    {
        if (this.repairMaterial != null || customCraftingMaterial != null) throw new RuntimeException("Can not change already set repair material");
        if (this == endgamium) throw new RuntimeException("Can not change vanilla tool repair materials");
        this.repairMaterial = stack;
        this.customCraftingMaterial = stack.getItem();
        return this;
    }

    public ItemStack getRepairItemStack()
    {
        if (repairMaterial != null) return repairMaterial;
        Item ret = this.repairMaterial();
        if (ret == null) return null;
        repairMaterial = new ItemStack(ret, 1, net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE);
        return repairMaterial;
    }
}
