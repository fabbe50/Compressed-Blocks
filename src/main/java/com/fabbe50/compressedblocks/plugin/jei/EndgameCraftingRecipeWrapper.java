package com.fabbe50.compressedblocks.plugin.jei;

import com.fabbe50.compressedblocks.core.lib.recipes.EndgameRecipes;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IStackHelper;
import mezz.jei.recipes.BrokenCraftingRecipeException;
import mezz.jei.util.ErrorUtil;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fabbe on 02/12/2017 - 2:41 PM.
 */
public class EndgameCraftingRecipeWrapper extends BlankRecipeWrapper implements IRecipeWrapper {
    private final IJeiHelpers jeiHelpers;
    private final EndgameRecipes recipe;

    public EndgameCraftingRecipeWrapper(IJeiHelpers jeiHelpers, EndgameRecipes recipe) {
        this.jeiHelpers = jeiHelpers;
        this.recipe = recipe;
    }

    @Override
    public void getIngredients(IIngredients iIngredients) {
        IStackHelper stackHelper = this.jeiHelpers.getStackHelper();
        ItemStack recipeOutput = this.recipe.getRecipeOutput();

        try {
            List<List<ItemStack>> inputs = stackHelper.expandRecipeItemStackInputs(this.recipe.getInput());
            iIngredients.setInputLists(ItemStack.class, inputs);
            iIngredients.setOutput(ItemStack.class, recipeOutput);
        } catch (RuntimeException e) {
            String info = ErrorUtil.getInfoFromBrokenCraftingRecipe(this.recipe, this.recipe.getInput(), recipeOutput);
            throw new BrokenCraftingRecipeException(info, e);
        }
    }
}
