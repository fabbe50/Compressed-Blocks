package com.fabbe50.compressedblocks.plugin.jei;

import com.fabbe50.compressedblocks.core.registry.JEIHideItemRegistry;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

/**
 * Created by fabbe on 16/06/2017.
 */
@mezz.jei.api.JEIPlugin
public class JEIPlugin implements IModPlugin {
    private IIngredientBlacklist itemBlacklist;

    @Override
    public void registerItemSubtypes(ISubtypeRegistry iSubtypeRegistry) {

    }

    @Override
    public void registerIngredients(IModIngredientRegistration iModIngredientRegistration) {

    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration iRecipeCategoryRegistration) {

    }

    @Override
    public void register(IModRegistry iModRegistry) {
        JEIHideItemRegistry.init();
        itemBlacklist = iModRegistry.getJeiHelpers().getIngredientBlacklist();
        JEIHideItemRegistry.hide.forEach(itemBlacklist::addIngredientToBlacklist);
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime iJeiRuntime) {

    }
}
