package com.fabbe50.compressedblocks.init;

import com.fabbe50.compressedblocks.handler.RecipeHandler;
import com.fabbe50.compressedblocks.utility.LogHelper;
import cpw.mods.fml.common.ModAPIManager;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;


/**
 * Created by fabbe50 on 15/01/2016.
 */
public class Recipes {
    public static void init() {
        LogHelper.info("Adding Mod Recipes");
        //GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.endgamiumsword), " s ", " s ", " t ", 's', new ItemStack(ModItems.endgamium), 't', "stickWood"));
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.endgamiumblock), "sss", "sss", "sss", 's', new ItemStack(ModItems.endgamium));
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.endgamiumblockc), "sss", "sss", "sss", 's', new ItemStack(ModBlocks.endgamiumblock));
        LogHelper.info("Done!");

        LogHelper.info("Adding Standalone Recipes");
        if (RecipeHandler.isStarblockCompat == false) {
            //Temp Recipe
            GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.comprstarblock), "sss", "sds", "sss", 's', new ItemStack(Items.nether_star), 'd', new ItemStack(Blocks.diamond_block));
            LogHelper.info("Created Recipe for 'ModBlocks.comprstarblock'");
        }
        else if (RecipeHandler.isStarblockCompat == true) {
            LogHelper.info("Mod Items Present. Standalone Recipe for 'ModBlocks.comprstarblock' not needed");
        }
        if (RecipeHandler.isEndgamiumCompat == false) {
            //Temp Recipe
            GameRegistry.addShapedRecipe(new ItemStack(ModItems.endgamium), "sss", "bdb", "ndn", 's', new ItemStack(Items.nether_star), 'b', new ItemStack(Items.blaze_rod), 'd', new ItemStack(Blocks.diamond_block), 'n', new ItemStack(Blocks.nether_brick));
            LogHelper.info("Created Recipe for 'ModItems.endgamium'");
        }
        else if (RecipeHandler.isEndgamiumCompat == true) {
            LogHelper.info("Mod Items Present. Standalone Recipe for 'ModItems.endgamium' not needed");
        }
        LogHelper.info("Done!");
    }
}
