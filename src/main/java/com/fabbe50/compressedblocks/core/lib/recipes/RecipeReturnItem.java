package com.fabbe50.compressedblocks.core.lib.recipes;

import com.fabbe50.compressedblocks.core.lib.ReturnableItems;
import com.fabbe50.compressedblocks.core.utils.helper.LogHelper;
import com.google.common.collect.Lists;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * Created by fabbe on 09/05/2017.
 */
public class RecipeReturnItem extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
    private final ItemStack recipeOutput;
    public final List<ItemStack> recipeItems;

    public RecipeReturnItem(ItemStack output, List<ItemStack> inputList) {
        this.recipeOutput = output;
        this.recipeItems = inputList;
    }

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        List<ItemStack> list = Lists.newArrayList(this.recipeItems);

        for (int i = 0; i < inv.getHeight(); ++i) {
            for (int j = 0; j < inv.getWidth(); ++j) {
                ItemStack itemstack = inv.getStackInRowAndColumn(j, i);

                if (!itemstack.isEmpty()) {
                    boolean flag = false;

                    for (ItemStack itemstack1 : list) {
                        if (itemstack.getItem() == itemstack1.getItem() && (itemstack1.getMetadata() == 32767 || itemstack.getMetadata() == itemstack1.getMetadata())) {
                            flag = true;
                            list.remove(itemstack1);
                            break;
                        }
                    }

                    if (!flag) {
                        return false;
                    }
                }
            }
        }

        return list.isEmpty();
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        return this.recipeOutput.copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return width > 1 && height > 1;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.recipeOutput;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
        NonNullList<ItemStack> nonnulllist = NonNullList.<ItemStack>withSize(inv.getSizeInventory(), ItemStack.EMPTY);

        for (int i = 0; i < nonnulllist.size(); ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);
            nonnulllist.set(i, getContainerItem(itemstack));
        }

        return nonnulllist;
    }

    private ItemStack getContainerItem(ItemStack stack) {
        ItemStack originalItem = stack;
        if (ReturnableItems.getItems().contains(originalItem.getItem())) {
            return new ItemStack(stack.getItem(), 1, stack.getMetadata());
        }
        if (stack.isItemStackDamageable() && stack.getItemDamage() > 0) {
            LogHelper.info("Stack contains: " + stack);
            stack.attemptDamageItem(1, new Random(), null);
            return stack;
        }
        return ItemStack.EMPTY;
    }
}
