package com.fabbe50.compressedblocks.utility;

import com.fabbe50.compressedblocks.utility.LogHelper;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

import java.util.Iterator;
import java.util.List;

/**
 * Created by fabbe50 on 03/02/2016.
 */
public class ItemRecipeRemoval {
    @SuppressWarnings("unchecked")
    public static void RemoveRecipe(Item recipeOut)
    {
        List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
        Iterator<IRecipe> recipe = recipes.iterator();

        while (recipe.hasNext()) {
            ItemStack itemStack = recipe.next().getRecipeOutput();
            if (itemStack != null && itemStack.getItem() == recipeOut)
                recipe.remove();
        }
    }
}
