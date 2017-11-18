package com.fabbe50.compressedblocks.core.lib.recipes;

import com.fabbe50.compressedblocks.core.registry.BlockRegistry;
import com.fabbe50.compressedblocks.core.registry.ItemRegistry;
import com.thefifthidiot.tficore.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabbe on 17/11/2017 - 10:14 PM.
 */
public class MiniBeaconRecipes implements IRecipe {
    public static List<Item> acceptedItems = new ArrayList<>();

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        int i = 0;
        ItemStack itemstack = ItemStack.EMPTY;
        ItemStack potion = ItemStack.EMPTY;

        for (int j = 0; j < inv.getSizeInventory(); j++) {
            ItemStack itemstack1 = inv.getStackInSlot(j);

            if (!itemstack1.isEmpty()) {
                if (itemstack1.getItem() == ItemRegistry.TRINKET) {
                    if (!itemstack.isEmpty()) {
                        return false;
                    }

                    itemstack = itemstack1;
                } else if (acceptedItems.contains(itemstack1.getItem())) {
                    if (!potion.isEmpty()) {
                        return false;
                    }

                    potion = itemstack1;

                    List<PotionEffect> effect = new ArrayList<>();
                    if (itemstack1.getItem() == ItemRegistry.ITEM_FOOD) {
                        effect.add(new PotionEffect(MobEffects.SATURATION, 0, 0));
                        PotionUtils.appendEffects(potion, effect);
                    } else if (itemstack1.getItem() == Items.SHIELD) {
                        effect.add(new PotionEffect(MobEffects.RESISTANCE, 0, 1));
                        PotionUtils.appendEffects(potion, effect);
                    }
                } else {
                    if (Block.getBlockFromItem(itemstack1.getItem()) != BlockRegistry.COMPRESSED_IRON) {
                        return false;
                    }

                    i++;
                }
            }
        }

        return !itemstack.isEmpty() && itemstack.hasTagCompound() && i > 0;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        int i = 0;
        ItemStack trinket = ItemStack.EMPTY;
        ItemStack potion = ItemStack.EMPTY;

        for (int j = 0; j < inv.getSizeInventory(); j++) {
            ItemStack itemstack1 = inv.getStackInSlot(j);

            if (!itemstack1.isEmpty()) {
                if (itemstack1.getItem() == ItemRegistry.TRINKET) {
                    if (!trinket.isEmpty()) {
                        return ItemStack.EMPTY;
                    }
                    trinket = itemstack1;
                } else if (acceptedItems.contains(itemstack1.getItem())) {
                    if (!potion.isEmpty()) {
                        return ItemStack.EMPTY;
                    }

                    potion = itemstack1;

                    List<PotionEffect> effect = new ArrayList<>();
                    if (itemstack1.getItem() == ItemRegistry.ITEM_FOOD) {
                        effect.add(new PotionEffect(MobEffects.SATURATION, 0, 0));
                        PotionUtils.appendEffects(potion, effect);
                    } else if (itemstack1.getItem() == Items.SHIELD) {
                        effect.add(new PotionEffect(MobEffects.RESISTANCE, 0, 1));
                        PotionUtils.appendEffects(potion, effect);
                    }
                } else {
                    if (Block.getBlockFromItem(itemstack1.getItem()) != BlockRegistry.COMPRESSED_IRON) {
                        return ItemStack.EMPTY;
                    }

                    i++;
                }
            }
        }

        if (!trinket.isEmpty() && trinket.hasTagCompound() && i >= 1) {
            ItemStack itemStack2 = new ItemStack(trinket.getItem(), 1, 3);
            List<PotionEffect> effects = new ArrayList<>();
            effects.addAll(PotionUtils.getEffectsFromStack(trinket));
            effects.addAll(PotionUtils.getEffectsFromStack(potion));
            PotionUtils.appendEffects(itemStack2, effects);
            return itemStack2;
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public int getRecipeSize() {
        return 9;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

        for (int i = 0; i < nonnulllist.size(); ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);

            if (itemstack.getItem() instanceof ItemPotion) {
                ItemStack itemstack1 = new ItemStack(Items.GLASS_BOTTLE, 1, 0);
                nonnulllist.set(i, itemstack1);
                break;
            }
        }

        return nonnulllist;
    }
}
