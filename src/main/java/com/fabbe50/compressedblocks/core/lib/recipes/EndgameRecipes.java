package com.fabbe50.compressedblocks.core.lib.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by fabbe50 on 30/04/2017.
 */
public class EndgameRecipes implements IRecipe{
    private final ItemStack recipeOutput;
    public final List<ItemStack> recipeItems;

    public EndgameRecipes(ItemStack output, List<ItemStack> inputList) {
        this.recipeOutput = output;
        this.recipeItems = inputList;
    }

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        return false;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        return recipeOutput;
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
    }

    public ItemStack getRecipeOutput() {
        return this.recipeOutput;
    }

    public List<ItemStack> getInput() {
        return this.recipeItems;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
        return ForgeHooks.defaultRecipeGetRemainingItems(inv);
    }

    @Override
    public IRecipe setRegistryName(ResourceLocation name) {
        return null;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return null;
    }

    @Override
    public Class<IRecipe> getRegistryType() {
        return null;
    }
}
