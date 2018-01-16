package com.fabbe50.compressedblocks.common.items;

import com.fabbe50.compressedblocks.common.items.base.ItemCustomAxe;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.tools.Tool;

/**
 * Created by fabbe on 09/01/2018 - 1:51 PM.
 */
public class ItemDiamondBedrockAxe extends ItemCustomAxe {
    ToolMaterial material;
    float effMultiplier;

    public ItemDiamondBedrockAxe(ToolMaterial material, float durabilityModifier, float efficiencyMultiplier, String name, CreativeTabs tab) {
        super(material, durabilityModifier, efficiencyMultiplier, name, tab);
        this.material = material;
        this.effMultiplier = efficiencyMultiplier;
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

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        if (state.getBlock() == Blocks.LOG || state.getBlock() == Blocks.LOG2) {
            return (material.getEfficiency() * effMultiplier) * 5;
        }

        return super.getDestroySpeed(stack, state);
    }
}
