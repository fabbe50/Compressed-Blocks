package com.fabbe50.compressedblocks.core.lib.recipes;

import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by fabbe50 on 30/04/2017.
 */
public class EndgameRecipes {
    private final ItemStack recipeOutput;
    public final List<ItemStack> recipeItems;

    public EndgameRecipes(ItemStack output, List<ItemStack> inputList) {
        this.recipeOutput = output;
        this.recipeItems = inputList;
    }

    public ItemStack getRecipeOutput() {
        return this.recipeOutput;
    }
}
