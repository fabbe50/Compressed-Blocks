package com.fabbe50.compressedblocks.plugin.jei;

import com.fabbe50.compressedblocks.core.lib.recipes.EndgameRecipes;
import com.fabbe50.compressedblocks.core.lib.recipes.MiniBeaconRecipes;
import com.fabbe50.compressedblocks.core.lib.recipes.RecipeReturnItem;
import com.fabbe50.compressedblocks.core.registry.BlockRegistry;
import com.fabbe50.compressedblocks.core.registry.JEIHideItemRegistry;
import com.google.common.base.Preconditions;
import mezz.jei.Internal;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.plugins.vanilla.ingredients.item.ItemStackHelper;
import mezz.jei.plugins.vanilla.ingredients.item.ItemStackListFactory;
import mezz.jei.plugins.vanilla.ingredients.item.ItemStackRenderer;
import mezz.jei.runtime.JeiHelpers;
import mezz.jei.startup.StackHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

/**
 * Created by fabbe on 16/06/2017.
 */
@JEIPlugin
public class CB2Plugin implements IModPlugin {
    @Nullable
    private ISubtypeRegistry subtypeRegistry;

    @Override
    public void registerItemSubtypes(ISubtypeRegistry iSubtypeRegistry) {
        this.subtypeRegistry = iSubtypeRegistry;
    }

    @Override
    public void registerIngredients(IModIngredientRegistration iModIngredientRegistration) {
        Preconditions.checkState(this.subtypeRegistry != null);
        StackHelper stackHelper = Internal.getStackHelper();
        ItemStackListFactory itemStackListFactory = new ItemStackListFactory(this.subtypeRegistry);
        iModIngredientRegistration.register(VanillaTypes.ITEM, itemStackListFactory.create(stackHelper), new ItemStackHelper(stackHelper), new ItemStackRenderer());
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        JeiHelpers jeiHelpers = Internal.getHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
        registry.addRecipeCategories(new EndgameCraftingCategory(guiHelper));
        registry.addRecipeCategories(new ReturnCraftingCategory(guiHelper));
        registry.addRecipeCategories(new BeaconCraftingCategory(guiHelper));
    }

    @Override
    public void register(IModRegistry registry) {
        final IJeiHelpers jeiHelpers = registry.getJeiHelpers();

        registry.addRecipes(EndgameCraftingRecipeChecker.getValidRecipes(jeiHelpers), "compressedblocks.fusioncrafting");
        registry.addRecipes(ReturnCraftingRecipeChecker.getValidRecipes(jeiHelpers), "compressedblocks.returncrafting");
        registry.addRecipes(BeaconCraftingRecipeChecker.getValidRecipes(jeiHelpers), "compressedblocks.beaconcrafting");

        registry.handleRecipes(EndgameRecipes.class, (endgameRecipes) -> new EndgameCraftingRecipeWrapper(jeiHelpers, endgameRecipes), "compressedblocks.fusioncrafting");
        registry.handleRecipes(RecipeReturnItem.class, (recipeReturnItem) -> new ReturnCraftingRecipeWrapper(jeiHelpers, recipeReturnItem), "compressedblocks.returncrafting");
        registry.handleRecipes(MiniBeaconRecipes.class, (beaconRecipes) -> new BeaconCraftingRecipeWrapper(jeiHelpers, beaconRecipes), "compressedblocks.beaconcrafting");

        registry.addRecipeCatalyst(new ItemStack(BlockRegistry.FUSIONPEDESTAL), "compressedblocks.fusioncrafting");
        registry.addRecipeCatalyst(new ItemStack(Blocks.CRAFTING_TABLE), "compressedblocks.returncrafting");
        registry.addRecipeCatalyst(new ItemStack(Blocks.CRAFTING_TABLE), "compressedblocks.beaconcrafting");

        JEIHideItemRegistry.init();
        IIngredientBlacklist itemBlacklist = registry.getJeiHelpers().getIngredientBlacklist();
        JEIHideItemRegistry.hide.forEach(itemBlacklist::addIngredientToBlacklist);
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime iJeiRuntime) {

    }
}
