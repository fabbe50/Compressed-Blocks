package com.fabbe50.compressedblocks.core.utils;

import com.fabbe50.compressedblocks.core.lib.Configs;
import com.fabbe50.compressedblocks.core.registry.ModItemRegistry;
import com.thefifthidiot.tficore.utility.helper.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.Loader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by fabbe50 on 12/02/2017.
 */
public class RecipeRemoval {
    /*private static List<Item> forRemoval = new ArrayList<>();
    private static CraftingManager craftingManager = CraftingManager.getInstance();
    private static List<IRecipe> recipes = craftingManager.getRecipeList();
    private static Iterator<IRecipe> recipeOutput = recipes.iterator();

    public static void init() {
        removeFromVanilla();
        removeFromMod();

        remove();
    }

    private static void removeFromVanilla() {
        if (Configs.vanillaHooks && Configs.stackSizes) {
            //removeRecipe(Items.BREWING_STAND);
        }

        removeRecipe(Blocks.STAINED_HARDENED_CLAY);
        removeRecipe(Blocks.STAINED_GLASS);
        removeRecipe(Blocks.WOOL);
    }

    private static void removeFromMod() {
        removeFromEU2();
    }

    private static void removeFromEU2() {
        if (Loader.isModLoaded("extrautils2") && Configs.hardcoreRecipes) {
            removeRecipe(ModItemRegistry.TELEPORTER);
            removeRecipe(ModItemRegistry.EUQUARRY);
        }
    }

    public static void removeRecipe(Block block) {
        removeRecipe(Item.getItemFromBlock(block));
    }

    public static void removeRecipe(Item item) {
        forRemoval.add(item);
        LogHelper.info("Added Recipe " + item.getUnlocalizedName() + " for Removal!");
    }

    private static void remove() {
        while (recipeOutput.hasNext()) {
            ItemStack is = recipeOutput.next().getRecipeOutput();
            if (is != null && forRemoval.contains(is.getItem()))
                recipeOutput.remove();
        };
    }*/
}
