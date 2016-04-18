package com.fabbe50.compressedblocks.init;

import com.fabbe50.compressedblocks.handler.RecipeHandler;
import com.fabbe50.compressedblocks.lib.EnumCompressed;
import com.fabbe50.compressedblocks.utility.LogHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;


/**
 * Created by fabbe50 on 15/01/2016.
 */
public class Recipes {
    //Amount of compressed blocks on one ID
    public static int compressedBlocks = 8;

    //Compressed Itemstack Array
    private static ItemStack[] potatoes;
    private static ItemStack[] gravel;
    private static ItemStack[] sand;
    private static ItemStack[] cobble;
    private static ItemStack[] dirt;

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
        ItemStack gravelblock = new ItemStack(Blocks.gravel);
        ItemStack sandblock = new ItemStack(Blocks.sand);
        ItemStack cobblestone = new ItemStack(Blocks.cobblestone);
        ItemStack dirtblock = new ItemStack(Blocks.dirt);

        //ItemStacks Compressed Blocks
        ItemStack endgamium = new ItemStack(ModItems.endgamium);
        ItemStack endgamiumb = new ItemStack(ModBlocks.endgamiumblock);
        ItemStack endgamiumbc = new ItemStack(ModBlocks.endgamiumblockc);
        ItemStack infusedbone = new ItemStack(ModItems.infusedbone);
        ItemStack potatobone = new ItemStack(ModItems.potatobone);
        ItemStack endgamiumnugget = new ItemStack(ModItems.endgamiumnugget);
        ItemStack endgamiumsword = new ItemStack(ModItems.endgamiumsword);
        ItemStack potatoblock = new ItemStack(ModBlocks.potatoblock);
        ItemStack potatosword = new ItemStack(ModItems.potatosword);

        //ItemStack Array Apply
        for (int i = 0; i < compressedBlocks; i++) {
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
        GameRegistry.addShapedRecipe(potatosword, "e", "e", "b", 'e', new ItemStack(ModBlocks.comprpotatoblock, 1, 3), 'b', stick);

        //Full Block Recipes
        GameRegistry.addShapedRecipe(endgamium, r, r, r, 's', endgamiumnugget);
        GameRegistry.addShapedRecipe(endgamiumb, r, r, r, 's', endgamium);
        GameRegistry.addShapedRecipe(endgamiumbc, r, r, r, 's', endgamiumb);
        GameRegistry.addShapedRecipe(potatoblock, r, r, r, 's', potato);

        //Full Block Recipes Array
        for (int i = 0; i < compressedBlocks; i++) {
            if (i == 0) {
                GameRegistry.addShapedRecipe(potatoes[i], r, r, r, 's', potatoblock);
            }
            else {
                GameRegistry.addShapedRecipe(potatoes[i], r, r, r, 's', potatoes[i-1]);
            }
        }
        for (int i = compressedBlocks - 1; i >= 0; i--) {
            if (i == 0) {
                GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.potatoblock, 9), potatoes[i]);
            }
            else {
                GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.comprpotatoblock, 9, i-1), potatoes[i]);
            }
        }

        LogHelper.info("Done!");

        if (Loader.isModLoaded("ExtraUtilities")) {
            LogHelper.info("Detected mod \"Extra Utilities\"! \r\n Deactivating fallback blocks!");
        }
        else {
            LogHelper.info("Couldn't find mod \"Extra Utilities\"! \r\n Activating fallback blocks!");
            gravel = new ItemStack[EnumCompressed.count()];
            sand = new ItemStack[EnumCompressed.count()];
            cobble = new ItemStack[EnumCompressed.count()];
            dirt = new ItemStack[EnumCompressed.count()];

            //ItemStack Array Apply
            for (int i = 0; i < compressedBlocks; i++) {
                gravel[i] = new ItemStack(ModFallbackBlocks.comprgravelblock, 1, i);
                sand[i] = new ItemStack(ModFallbackBlocks.comprsandblock, 1, i);
                cobble[i] = new ItemStack(ModFallbackBlocks.comprcobbleblock, 1, i);
                dirt[i] = new ItemStack(ModFallbackBlocks.comprdirtblock, 1, i);
            }

            //Full Block Recipes Array
            for (int i = 0; i < compressedBlocks; i++) {
                if (i == 0) {
                    GameRegistry.addShapedRecipe(gravel[i], r, r, r, 's', gravelblock);
                    GameRegistry.addShapedRecipe(sand[i], r, r, r, 's', sandblock);
                    GameRegistry.addShapedRecipe(cobble[i], r, r, r, 's', cobblestone);
                    GameRegistry.addShapedRecipe(dirt[i], r, r, r, 's', dirtblock);
                }
                else {
                    GameRegistry.addShapedRecipe(gravel[i], r, r, r, 's', gravel[i-1]);
                    GameRegistry.addShapedRecipe(sand[i], r, r, r, 's', sand[i-1]);
                    GameRegistry.addShapedRecipe(cobble[i], r, r, r, 's', cobble[i-1]);
                    GameRegistry.addShapedRecipe(dirt[i], r, r, r, 's', dirt[i-1]);
                }
            }
            for (int i = compressedBlocks - 1; i >= 0; i--) {
                if (i == 0) {
                    GameRegistry.addShapelessRecipe(new ItemStack(Blocks.gravel, 9), gravel[i]);
                    GameRegistry.addShapelessRecipe(new ItemStack(Blocks.sand, 9), sand[i]);
                    GameRegistry.addShapelessRecipe(new ItemStack(Blocks.cobblestone, 9), cobble[i]);
                    GameRegistry.addShapelessRecipe(new ItemStack(Blocks.dirt, 9), dirt[i]);
                }
                else {
                    GameRegistry.addShapelessRecipe(new ItemStack(ModFallbackBlocks.comprgravelblock, 9, i-1), gravel[i]);
                    GameRegistry.addShapelessRecipe(new ItemStack(ModFallbackBlocks.comprsandblock, 9, i-1), sand[i]);
                    GameRegistry.addShapelessRecipe(new ItemStack(ModFallbackBlocks.comprcobbleblock, 9, i-1), cobble[i]);
                    GameRegistry.addShapelessRecipe(new ItemStack(ModFallbackBlocks.comprdirtblock, 9, i-1), dirt[i]);
                }
            }
        }
    }
}
