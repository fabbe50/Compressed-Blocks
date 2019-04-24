package com.fabbe50.compressedblocks.plugin.jei;

import com.fabbe50.compressedblocks.core.lib.recipes.RecipeReturnItem;
import com.fabbe50.compressedblocks.core.registry.ModRegistry;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.ErrorUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ReturnCraftingRecipeChecker {
    private ReturnCraftingRecipeChecker() {
    }

    public static List<IRecipe> getValidRecipes(final IJeiHelpers jeiHelpers) {
        CraftingRecipeValidator<RecipeReturnItem> endgameRecipeValidator = new CraftingRecipeValidator<RecipeReturnItem>() {
            @Override
            protected IRecipeWrapper getRecipeWrapper(RecipeReturnItem recipe) {
                return new ReturnCraftingRecipeWrapper(jeiHelpers, recipe);
            }

            @Override
            protected int getInputCount(RecipeReturnItem recipe) {
                return getInputCount(recipe.getInput());
            }
        };

        List<IRecipe> recipes = new ArrayList<>(ModRegistry.returnRecipes);
        List<IRecipe> validRecipes = new ArrayList<>(recipes.size());
        Iterator var1 = recipes.iterator();

        while(var1.hasNext()) {
            IRecipe recipe = (IRecipe)var1.next();
            if (recipe instanceof RecipeReturnItem) {
                if (endgameRecipeValidator.isRecipeValid((RecipeReturnItem) recipe)) {
                    validRecipes.add(recipe);
                }
            }
        }

        return validRecipes;
    }

    private abstract static class CraftingRecipeValidator<T extends IRecipe> {
        private static final int INVALID_COUNT = -1;

        public CraftingRecipeValidator() {}

        public boolean isRecipeValid(T recipe) {
            ItemStack recipeOutput = recipe.getRecipeOutput();
            if (recipeOutput != null && !recipeOutput.isEmpty()) {
                int inputCount = this.getInputCount(recipe);
                if (inputCount == -1) {
                    return false;
                } else {
                    String recipeInfo;
                    if (inputCount > 6) {
                        recipeInfo = this.getInfo(recipe);
                        return false;
                    } else if (inputCount == 0) {
                        recipeInfo = this.getInfo(recipe);
                        return false;
                    } else {
                        return true;
                    }
                }
            } else {
                String recipeInfo = this.getInfo(recipe);
                return false;
            }
        }

        private String getInfo(T recipe) {
            IRecipeWrapper recipeWrapper = this.getRecipeWrapper(recipe);
            return ErrorUtil.getInfoFromRecipe(recipe, recipeWrapper);
        }

        protected abstract IRecipeWrapper getRecipeWrapper(T var1);

        protected abstract int getInputCount(T var1);

        protected static int getInputCount(Object[] objectList) {
            return getInputCount(Arrays.asList(objectList));
        }

        protected static int getInputCount(List<?> objectList) {
            int inputCount = 0;
            Iterator var2 = objectList.iterator();

            while(var2.hasNext()) {
                Object input = var2.next();
                if(input instanceof List) {
                    if(((List)input).isEmpty()) {
                        return -1;
                    }

                    ++inputCount;
                } else if(input instanceof ItemStack) {
                    ItemStack itemStack = (ItemStack)input;
                    if(!itemStack.isEmpty()) {
                        ++inputCount;
                    }
                } else if(input != null) {
                    ++inputCount;
                }
            }

            return inputCount;
        }
    }
}
