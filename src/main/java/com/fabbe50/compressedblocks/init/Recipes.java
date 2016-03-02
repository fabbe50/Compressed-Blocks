package com.fabbe50.compressedblocks.init;

import com.fabbe50.compressedblocks.handler.RecipeHandler;
import com.fabbe50.compressedblocks.lib.EnumCompressed;
import com.fabbe50.compressedblocks.utility.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;


/**
 * Created by fabbe50 on 15/01/2016.
 */
public class Recipes {
    //Compressed Itemstack Array
    private static ItemStack[] potatoes;

    public static void init() {
        //objectify arrays
        potatoes = new ItemStack[EnumCompressed.count()];

        //Recipe Markers
        String r = "sss";

        //ItemStacks Vanilla
        ItemStack potato = new ItemStack(Items.potato);
        ItemStack bone = new ItemStack(Items.bone);
        ItemStack bakedpotato = new ItemStack(Items.baked_potato);

        //ItemStacks Compressed Blocks
        ItemStack endgamium = new ItemStack(ModItems.endgamium);
        ItemStack endgamiumb = new ItemStack(ModBlocks.endgamiumblock);
        ItemStack endgamiumbc = new ItemStack(ModBlocks.endgamiumblockc);
        ItemStack infusedbone = new ItemStack(ModItems.infusedbone);
        ItemStack potatobone = new ItemStack(ModItems.potatobone);
        ItemStack endgamiumnugget = new ItemStack(ModItems.endgamiumnugget);

        //ItemStack Array Apply
        for (int i = 0; i < potatoes.length; i++) {
            potatoes[i] = new ItemStack(ModBlocks.comprpotatoblock, 1, i);
        }

        LogHelper.info("Adding Mod Recipes");
        //Standard Shapeless Recipes
        GameRegistry.addShapelessRecipe(infusedbone, bone, endgamiumnugget);
        GameRegistry.addShapelessRecipe(potatobone, bakedpotato, infusedbone);
        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.endgamiumblock, 9), endgamiumbc);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.endgamium, 9), endgamiumb);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.endgamiumnugget, 9), endgamium);

        //Standard Shaped Recipes

        //Full Block Recipes
        GameRegistry.addShapedRecipe(endgamium, r, r, r, 's', endgamiumnugget);
        GameRegistry.addShapedRecipe(endgamiumb, r, r, r, 's', endgamium);
        GameRegistry.addShapedRecipe(endgamiumbc, r, r, r, 's', endgamiumb);

        //Full Block Recipes Array
        for (int i = 0; i < potatoes.length; i++) {
            if (i == 0) {
                GameRegistry.addShapedRecipe(potatoes[i], r, r, r, 's', potato);}
            else {
                GameRegistry.addShapedRecipe(potatoes[i], r, r, r, 's', potatoes[i-1]);}
        }

        LogHelper.info("Done!");


        LogHelper.info("Adding Standalone Recipes");
        if (RecipeHandler.isStarblockCompat == false) {//Temp Recipe
            GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.comprstarblock), "sss", "sds", "sss", 's', new ItemStack(Items.nether_star), 'd', new ItemStack(Blocks.diamond_block));
            LogHelper.info("Created Recipe for 'ModBlocks.comprstarblock'");
        } else {
            LogHelper.info("Mod Items Present. Standalone Recipe for 'ModBlocks.comprstarblock' not needed");
        }
        if (RecipeHandler.isEndgamiumCompat == false) {//Temp Recipe
            GameRegistry.addShapedRecipe(new ItemStack(ModItems.endgamium), "sss", "bdb", "ndn", 's', new ItemStack(Items.nether_star), 'b', new ItemStack(Items.blaze_rod), 'd', new ItemStack(Blocks.diamond_block), 'n', new ItemStack(Blocks.nether_brick));
            LogHelper.info("Created Recipe for 'ModItems.endgamium'");
        } else {
            LogHelper.info("Mod Items Present. Standalone Recipe for 'ModItems.endgamium' not needed");
        }
        LogHelper.info("Done!");
    }
}
