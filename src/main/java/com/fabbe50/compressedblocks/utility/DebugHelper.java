package com.fabbe50.compressedblocks.utility;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

/**
 * Created by fabbe50 on 03/02/2016.
 */
public class DebugHelper {
    static List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();

    static Iterator<IRecipe> recipeListerator = recipes.iterator();

    public static void init() {
        while (recipeListerator.hasNext())
            FMLLog.info("" + recipeListerator.next().getRecipeOutput());
    }

    public static void getCurrentRelativePath () {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);
    }
}
