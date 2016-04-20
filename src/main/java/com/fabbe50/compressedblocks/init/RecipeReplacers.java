package com.fabbe50.compressedblocks.init;

import com.fabbe50.compressedblocks.core.reference.ModItemLibrary;
import com.fabbe50.compressedblocks.utility.ItemRecipeRemoval;
import com.fabbe50.compressedblocks.utility.LogHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by fabbe50 on 05/02/2016.
 */
public class RecipeReplacers {
    //Itemstacks
    static ItemStack chest = new ItemStack(Blocks.chest);
    static ItemStack magenta = new ItemStack(Items.dye, 1, 13);
    static ItemStack purple = new ItemStack(Items.dye, 1, 5);
    static ItemStack glass = new ItemStack(Blocks.glass);
    static ItemStack gold = new ItemStack(Items.gold_ingot);
    static ItemStack lava = new ItemStack(Items.lava_bucket);
    static ItemStack feather = new ItemStack(Items.feather);
    static ItemStack potato = new ItemStack(Items.potato);
    static ItemStack barleyseed = new ItemStack(ModItemLibrary.barley);
    static ItemStack cottonseed = new ItemStack(ModItemLibrary.barley, 1, 1);
    static ItemStack netherwart = new ItemStack(Items.nether_wart);
    static ItemStack carrot = new ItemStack(Items.carrot);
    static ItemStack wheatseed = new ItemStack(Items.wheat_seeds);
    static ItemStack bonemeal = new ItemStack(Items.dye, 1, 15);
    static ItemStack endgamium = new ItemStack(ModItems.endgamium);
    static ItemStack starblock = new ItemStack(ModItemLibrary.starBlock);
    static ItemStack compr8cobble = new ItemStack(ModItemLibrary.compr8cobble, 1, 7);
    static ItemStack diamondBlock = new ItemStack(Blocks.diamond_block);

    //Item ID's
    static Item[] itemNames;

    static String r = "sss";
    static String t = "sis";

    public static void init() {
        addItemsToThing();
        try {
            for (int i = 0; i < itemNames.length; i++) {
                try {
                    ItemRecipeRemoval.RemoveRecipe(itemNames[i]);
                } catch (Exception e) {
                    LogHelper.error(e);
                }
            }
        }
        catch (Exception e) {
            LogHelper.error(e);
        }

        //Call function to add replacements for the removed recipes.
        addRecipeReplacements();
    }

    private static void addRecipeReplacements() {
        if (Loader.isModLoaded("Natura")) {
            //Natura Recipes
            GameRegistry.addShapedRecipe(new ItemStack(ModItemLibrary.potatoBag), r, t, r, 's', potato, 'i', chest);
            GameRegistry.addShapedRecipe(new ItemStack(ModItemLibrary.carrotBag), r, t, r, 's', carrot, 'i', chest);
            GameRegistry.addShapedRecipe(new ItemStack(ModItemLibrary.barleyBag), r, t, r, 's', barleyseed, 'i', chest);
            GameRegistry.addShapedRecipe(new ItemStack(ModItemLibrary.cottonBag), r, t, r, 's', cottonseed, 'i', chest);
            GameRegistry.addShapedRecipe(new ItemStack(ModItemLibrary.wheatBag), r, t, r, 's', wheatseed, 'i', chest);
            GameRegistry.addShapedRecipe(new ItemStack(ModItemLibrary.wartBag), r, t, r, 's', netherwart, 'i', chest);
            GameRegistry.addShapedRecipe(new ItemStack(ModItemLibrary.boneBag), r, t, r, 's', bonemeal, 'i', chest);
        }
        if (Loader.isModLoaded("ExtraUtilities")) {
            //Extra Utilities Recipes
            GameRegistry.addShapedRecipe(new ItemStack(ModItemLibrary.angelRing, 1, 0), "tut", "ere", "yey", 'e', endgamium, 'r', starblock, 't', glass, 'y', compr8cobble, 'u', diamondBlock); //Invisible Wings
            GameRegistry.addShapedRecipe(new ItemStack(ModItemLibrary.angelRing, 1, 1), "tut", "ere", "yey", 'e', endgamium, 'r', starblock, 't', feather, 'y', compr8cobble, 'u', diamondBlock); //Feathery Wings
            GameRegistry.addShapedRecipe(new ItemStack(ModItemLibrary.angelRing, 1, 2), "tui", "ere", "yey", 'e', endgamium, 'r', starblock, 't', magenta, 'i', purple, 'y', compr8cobble, 'u', diamondBlock); //Fairy Wings
            GameRegistry.addShapedRecipe(new ItemStack(ModItemLibrary.angelRing, 1, 3), "tut", "ere", "yey", 'e', endgamium, 'r', starblock, 't', lava, 'y', compr8cobble, 'u', diamondBlock); //Dragon Wings
            GameRegistry.addShapedRecipe(new ItemStack(ModItemLibrary.angelRing, 1, 4), "tut", "ere", "yey", 'e', endgamium, 'r', starblock, 't', gold, 'y', compr8cobble, 'u', diamondBlock); //Golden Wings
        }
        if (Loader.isModLoaded("TConstruct")) {
            //Tinkers' Construct
            GameRegistry.addShapedRecipe(new ItemStack(ModItemLibrary.heartCanist, 1, 5), "sts", "sys", "sts", 's', new ItemStack(ModItemLibrary.heartCanist, 1, 3), 't', new ItemStack(Items.dye, 1, 10), 'y', new ItemStack(ModItemLibrary.starBlock));
            GameRegistry.addShapelessRecipe(new ItemStack(ModItemLibrary.heartCanist, 1, 6), new ItemStack(ModItemLibrary.heartCanist, 1, 4), new ItemStack(ModItemLibrary.heartCanist, 1, 5), new ItemStack(ModItems.endgamium), new ItemStack(ModItemLibrary.starBlock));
        }
    }

    private static void addItemsToThing () {
        itemNames = new Item[8];
        //Natura
        itemNames[0] = ModItemLibrary.barleyBag;
        itemNames[1] = ModItemLibrary.carrotBag;
        itemNames[2] = ModItemLibrary.potatoBag;
        itemNames[3] = ModItemLibrary.wheatBag;
        itemNames[4] = ModItemLibrary.wartBag;
        itemNames[5] = ModItemLibrary.cottonBag;
        itemNames[6] = ModItemLibrary.boneBag;
        itemNames[7] = ModItemLibrary.angelRing;
    }
}
