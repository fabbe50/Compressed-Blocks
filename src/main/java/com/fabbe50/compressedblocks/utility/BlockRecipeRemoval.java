package com.fabbe50.compressedblocks.utility;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

import java.util.Iterator;
import java.util.List;

/**
 * Created by fabbe50 on 02/03/2016.
 */
public class BlockRecipeRemoval {
    @SuppressWarnings("unchecked")
    public static void RemoveRecipe(Block recipeOut)
    {
        List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
        Iterator<IRecipe> recipe = recipes.iterator();

        while (recipe.hasNext()) {
            ItemStack itemStack = recipe.next().getRecipeOutput();
            if (itemStack != null && itemStack.getItem() == Item.getItemFromBlock(recipeOut))
                recipe.remove();
        }
    }
}
