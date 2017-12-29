package com.fabbe50.compressedblocks.plugin.jei;

import com.fabbe50.compressedblocks.core.lib.recipes.EndgameRecipes;
import com.fabbe50.compressedblocks.core.registry.BlockRegistry;
import com.fabbe50.compressedblocks.core.registry.JEIHideItemRegistry;
import com.google.common.base.Preconditions;
import mezz.jei.Internal;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.plugins.vanilla.ingredients.ItemStackHelper;
import mezz.jei.plugins.vanilla.ingredients.ItemStackListFactory;
import mezz.jei.plugins.vanilla.ingredients.ItemStackRenderer;
import mezz.jei.startup.StackHelper;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

/**
 * Created by fabbe on 16/06/2017.
 */
@mezz.jei.api.JEIPlugin
public class JEIPlugin implements IModPlugin {
    @Nullable
    private ISubtypeRegistry subtypeRegistry;
    private IIngredientBlacklist itemBlacklist;

    @Override
    public void registerItemSubtypes(ISubtypeRegistry iSubtypeRegistry) {
        this.subtypeRegistry = iSubtypeRegistry;
    }

    @Override
    public void registerIngredients(IModIngredientRegistration iModIngredientRegistration) {
        Preconditions.checkState(this.subtypeRegistry != null);
        StackHelper stackHelper = Internal.getStackHelper();
        ItemStackListFactory itemStackListFactory = new ItemStackListFactory(this.subtypeRegistry);
        iModIngredientRegistration.register(ItemStack.class, itemStackListFactory.create(stackHelper), new ItemStackHelper(stackHelper), new ItemStackRenderer());
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration iRecipeCategoryRegistration) {
        IJeiHelpers jeiHelpers = iRecipeCategoryRegistration.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
        iRecipeCategoryRegistration.addRecipeCategories(new IRecipeCategory[]{new EndgameCraftingCategory(guiHelper)});
    }

    @Override
    public void register(IModRegistry iModRegistry) {
        IIngredientRegistry ingredientRegistry = iModRegistry.getIngredientRegistry();
        final IJeiHelpers jeiHelpers = iModRegistry.getJeiHelpers();
        iModRegistry.addRecipes(EndgameCraftingRecipeChecker.getValidRecipes(jeiHelpers), "compressedblocks.fusioncrafting");
        iModRegistry.handleRecipes(EndgameRecipes.class, endgameRecipes -> new EndgameCraftingRecipeWrapper(jeiHelpers, endgameRecipes), "compressedblocks.fusioncrafting");
        iModRegistry.addRecipeCatalyst(new ItemStack(BlockRegistry.FUSIONPEDESTAL), new String[]{"compressedblocks.fusioncrafting"});

        JEIHideItemRegistry.init();
        itemBlacklist = iModRegistry.getJeiHelpers().getIngredientBlacklist();
        JEIHideItemRegistry.hide.forEach(itemBlacklist::addIngredientToBlacklist);
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime iJeiRuntime) {

    }
}
