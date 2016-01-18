package com.fabbe50.compressedblocks.init;

import com.fabbe50.compressedblocks.utility.LogHelper;
import cpw.mods.fml.common.ModAPIManager;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;


/**
 * Created by fabbe50 on 15/01/2016.
 */
public class Recipes {
    public static void init() {
        LogHelper.info("Adding Standalone Recipes");
        //GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.endgamiumsword), " s ", " s ", " t ", 's', new ItemStack(ModItems.endgamium), 't', "stickWood"));
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.endgamiumblock), "sss", "sss", "sss", 's', new ItemStack(ModItems.endgamium));
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.endgamiumblockc), "sss", "sss", "sss", 's', new ItemStack(ModBlocks.endgamiumblock));
        LogHelper.info("Done!");
    }
}
