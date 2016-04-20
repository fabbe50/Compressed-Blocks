package com.fabbe50.compressedblocks.init;

import com.fabbe50.compressedblocks.core.handler.ConfigurationHandler;
import com.fabbe50.compressedblocks.core.handler.RecipeHandler;
import com.fabbe50.compressedblocks.core.reference.ModItemLibrary;
import com.fabbe50.compressedblocks.utility.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

/**
 * Created by fabbe50 on 15/01/2016.
 */
public class ModRecipes {



    public static void init() {
        if ((ModItemLibrary.compr8cobble == null || ModItemLibrary.crystalCluster == null || ModItemLibrary.unstableBlock == null ||
                ModItemLibrary.starBlock == null || ModItemLibrary.gaiaIngot == null || ModItemLibrary.brewOfFlowingSpirit == null ||
                ModItemLibrary.iridium == null || ModItemLibrary.enderium == null) && ConfigurationHandler.noSafeRecipe) {
            LogHelper.warn("Mod_Components Missing, recipeEndgamium not registered");
            RecipeHandler.isEndgamiumCompat = false;
        }
        else {
            GameRegistry.addShapelessRecipe(new ItemStack(ModItems.endgamium),
                    new ItemStack(ModItemLibrary.compr8cobble,1,7), new ItemStack(ModItemLibrary.crystalCluster), new ItemStack(ModItemLibrary.unstableBlock,1,5),
                    new ItemStack(ModBlocks.comprpotatoblock,1,7), new ItemStack(ModItemLibrary.starBlock), new ItemStack(ModItemLibrary.gaiaIngot,1,14),
                    new ItemStack(ModItemLibrary.brewOfFlowingSpirit,1,96), new ItemStack(ModItemLibrary.iridium), new ItemStack(ModItemLibrary.enderium,1,12));
            RecipeHandler.isEndgamiumCompat = true;
            LogHelper.info("recipeEndgamium Registered");
        }

        if ((ModItemLibrary.starBlock == null) && ConfigurationHandler.noSafeRecipe) {
            LogHelper.warn("Mod_Components Missing, recipeStarBlock not registered");
            RecipeHandler.isStarblockCompat = false;
        }
        else {
            GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.comprstarblock), "sss", "sss", "sss", 's', new ItemStack(ModItemLibrary.starBlock));
            LogHelper.info("recipeStarBlock Registered");
            RecipeHandler.isStarblockCompat = true;
        }
    }
}
