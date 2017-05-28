package com.fabbe50.compressedblocks.core.lib;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabbe50 on 23/09/2016.
 */
public class Configs {
    public static String[] entityBlacklist = new String[] {"minecraft:silverfish", "minecraft:chicken", "minecraft:bat", "minecraft:endermite", "minecraft:ghast"};
    public static int[] dimBlacklist = new int[] {1};
    public static List<ResourceLocation> entityBlacklistR = new ArrayList<>();
    public static List<Integer> dimBlacklistR = new ArrayList<>();
    public static boolean isWhitelist = false;

    public static boolean playerIsTrigger = true;
    public static boolean hardcoreRecipes = false;
    public static boolean enableCrossDimensionalTP = false;
    public static boolean compressedTNTSpread = true;
    public static String chestToFill = "minecraft:chest";

    //Vanilla Hooks
    public static boolean vanillaHooks = true;
    public static boolean stackSizes = true;
    public static boolean entityDrops = true;
    public static boolean undrinkableBuckets = true;

    public static void init() {
        parseBlacklist();
        parseDimBlacklist();
    }

    public static void parseBlacklist() {
        for (int i = 0; i < entityBlacklist.length; i++) {
            String[] splitText = entityBlacklist[i].split(":");
            entityBlacklistR.add(new ResourceLocation(splitText[0], splitText[1]));
        }
    }

    public static void parseDimBlacklist() {
        for (int i = 0; i < dimBlacklist.length; i++) {
            dimBlacklistR.add(dimBlacklist[i]);
        }
    }

    public static Block parseChest() {
        return Block.getBlockFromName(chestToFill);
    }
}
