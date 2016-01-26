package com.fabbe50.compressedblocks.init;

import com.fabbe50.compressedblocks.handler.RecipeHandler;
import com.fabbe50.compressedblocks.reference.ModItemLibrary;
import com.fabbe50.compressedblocks.utility.LogHelper;
import cpw.mods.fml.common.ModAPIManager;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by fabbe50 on 15/01/2016.
 */
public class ModRecipes {



    public static void init() {
        //if (ModItemLibrary.compr7cobble == null || ModItemLibrary.crystalCluster == null || ModItemLibrary.unstableBlock == null ||
        //        ModItemLibrary.ichorium == null || ModItemLibrary.starBlock == null || ModItemLibrary.gaiaIngot == null ||
        //        ModItemLibrary.brewOfFlowingSpirit == null || ModItemLibrary.iridium == null || ModItemLibrary.tesseract == null) {
        //    LogHelper.warn("Mod_Components Missing, recipeEndgamium not registred");
        //    RecipeHandler.isEndgamiumCompat = false;
        //}
        //else {
            GameRegistry.addShapelessRecipe(new ItemStack(ModItems.endgamium),
                    new ItemStack(ModItemLibrary.compr7cobble), new ItemStack(ModItemLibrary.crystalCluster), new ItemStack(ModItemLibrary.unstableBlock),
                    new ItemStack(ModItemLibrary.ichorium), new ItemStack(ModItemLibrary.starBlock), new ItemStack(ModItemLibrary.gaiaIngot),
                    new ItemStack(ModItemLibrary.brewOfFlowingSpirit), new ItemStack(ModItemLibrary.iridium), new ItemStack(ModItemLibrary.tesseract));
            //RecipeHandler.isEndgamiumCompat = true;
            LogHelper.info("recipeEndgamium Registred");
        //}

        //if (ModItemLibrary.starBlock == null) {
        //    LogHelper.warn("Mod_Components Missing, recipeStarBlock not registred");
        //    RecipeHandler.isStarblockCompat = false;
        //}
        //else {
            GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.comprstarblock), "sss", "sss", "sss", 's', new ItemStack(ModItemLibrary.starBlock));
            LogHelper.info("recipeStarBlock Registred");
            //RecipeHandler.isStarblockCompat = true;
        //}
    }
}
