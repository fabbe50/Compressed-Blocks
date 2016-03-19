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
        ItemStack stick = new ItemStack(Items.stick);

        //ItemStacks Compressed Blocks
        ItemStack endgamium = new ItemStack(ModItems.endgamium);
        ItemStack endgamiumb = new ItemStack(ModBlocks.endgamiumblock);
        ItemStack endgamiumbc = new ItemStack(ModBlocks.endgamiumblockc);
        ItemStack infusedbone = new ItemStack(ModItems.infusedbone);
        ItemStack potatobone = new ItemStack(ModItems.potatobone);
        ItemStack endgamiumnugget = new ItemStack(ModItems.endgamiumnugget);
        ItemStack endgamiumsword = new ItemStack(ModItems.endgamiumsword);
        ItemStack potatoblock = new ItemStack(ModBlocks.potatoblock);

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
        GameRegistry.addShapelessRecipe(new ItemStack(Items.potato, 9), potatoblock);

        //Standard Shaped Recipes
        GameRegistry.addShapedRecipe(endgamiumsword, "e", "e", "b", 'e', endgamium, 'b', infusedbone);

        //Full Block Recipes
        GameRegistry.addShapedRecipe(endgamium, r, r, r, 's', endgamiumnugget);
        GameRegistry.addShapedRecipe(endgamiumb, r, r, r, 's', endgamium);
        GameRegistry.addShapedRecipe(endgamiumbc, r, r, r, 's', endgamiumb);
        GameRegistry.addShapedRecipe(potatoblock, r, r, r, 's', potato);

        //Full Block Recipes Array
        for (int i = 0; i < potatoes.length; i++) {
            if (i == 0) {
                GameRegistry.addShapedRecipe(potatoes[i], r, r, r, 's', potatoblock);}
            else {
                GameRegistry.addShapedRecipe(potatoes[i], r, r, r, 's', potatoes[i-1]);}
        }
        for (int i = 7; i >= 0; i--) {
            if (i == 0) {
                GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.potatoblock, 9), potatoes[i]);
            }
            else {
                GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.comprpotatoblock, 9, i-1), potatoes[i]);
            }
        }

        LogHelper.info("Done!");
    }
}
