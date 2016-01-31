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
        //Recipe Markers
        String r = "sss";

        //ItemStacks
        ItemStack potato = new ItemStack(Items.potato);
        ItemStack cpotato = new ItemStack(ModBlocks.comprpotatoblock);
        ItemStack c2potato = new ItemStack(ModBlocks.comprpotatoblock,1,1);
        ItemStack c3potato = new ItemStack(ModBlocks.comprpotatoblock,1,2);
        ItemStack c4potato = new ItemStack(ModBlocks.comprpotatoblock,1,3);
        ItemStack c5potato = new ItemStack(ModBlocks.comprpotatoblock,1,4);
        ItemStack c6potato = new ItemStack(ModBlocks.comprpotatoblock,1,5);
        ItemStack c7potato = new ItemStack(ModBlocks.comprpotatoblock,1,6);
        ItemStack c8potato = new ItemStack(ModBlocks.comprpotatoblock,1,7);
        ItemStack endgamium = new ItemStack(ModItems.endgamium);
        ItemStack endgamiumb = new ItemStack(ModBlocks.endgamiumblock);
        ItemStack endgamiumbc = new ItemStack(ModBlocks.endgamiumblockc);

        LogHelper.info("Adding Mod Recipes");
        //Standard Shapeless Recipes


        //Standard Shaped Recipes


        //Full Block Recipes
        GameRegistry.addShapedRecipe(endgamiumb, r, r, r, 's', endgamium);
        GameRegistry.addShapedRecipe(endgamiumbc, r, r, r, 's', endgamiumb);
        GameRegistry.addShapedRecipe(cpotato, r, r, r, 's', potato);
        GameRegistry.addShapedRecipe(c2potato, r, r, r, 's', cpotato);
        GameRegistry.addShapedRecipe(c3potato, r, r, r, 's', c2potato);
        GameRegistry.addShapedRecipe(c4potato, r, r, r, 's', c3potato);
        GameRegistry.addShapedRecipe(c5potato, r, r, r, 's', c4potato);
        GameRegistry.addShapedRecipe(c6potato, r, r, r, 's', c5potato);
        GameRegistry.addShapedRecipe(c7potato, r, r, r, 's', c6potato);
        GameRegistry.addShapedRecipe(c8potato, r, r, r, 's', c7potato);

        LogHelper.info("Done!");







        LogHelper.info("Adding Standalone Recipes");
        if (RecipeHandler.isStarblockCompat == false) {/*Temp Recipe*/
            GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.comprstarblock), "sss", "sds", "sss", 's', new ItemStack(Items.nether_star), 'd', new ItemStack(Blocks.diamond_block));
            LogHelper.info("Created Recipe for 'ModBlocks.comprstarblock'");
        }
        else {LogHelper.info("Mod Items Present. Standalone Recipe for 'ModBlocks.comprstarblock' not needed");}
        if (RecipeHandler.isEndgamiumCompat == false) {
            //Temp Recipe
            GameRegistry.addShapedRecipe(new ItemStack(ModItems.endgamium), "sss", "bdb", "ndn", 's', new ItemStack(Items.nether_star), 'b', new ItemStack(Items.blaze_rod), 'd', new ItemStack(Blocks.diamond_block), 'n', new ItemStack(Blocks.nether_brick));
            LogHelper.info("Created Recipe for 'ModItems.endgamium'");
        }
        else {LogHelper.info("Mod Items Present. Standalone Recipe for 'ModItems.endgamium' not needed");}
        LogHelper.info("Done!");
    }
}
