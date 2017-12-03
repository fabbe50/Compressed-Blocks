package com.fabbe50.compressedblocks.core.utils.helper;

import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by fabbe50 on 30/04/2017.
 */
public class CraftingHelper {
    public static boolean compareItemsFromListedStacks(List<ItemStack> input, List<ItemStack> recipe) {
        int recipeSize = recipe.size();
        int correctInputSize = 0;

        for (ItemStack aRecipe : recipe) {
            for (ItemStack anInput : input) {
                if (ItemStack.areItemStacksEqual(aRecipe, anInput)) {
                    correctInputSize++;
                }
            }
        }

        return correctInputSize == recipeSize;
    }
}
