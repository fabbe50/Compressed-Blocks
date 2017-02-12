package com.fabbe50.compressedblocks.core.lib;

import com.thefifthidiot.tficore.utility.LogHelper;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabbe50 on 23/09/2016.
 */
public class Configs {
    public static String[] entityBlacklist = new String[] {"minecraft:silverfish", "minecraft:chicken", "minecraft:bat", "minecraft:endermite", "minecraft:ghast"};
    public static List<ResourceLocation> entityBlacklistR = new ArrayList<>();
    public static boolean isWhitelist = false;
    public static boolean playerIsTrigger = true;
    public static boolean hardcoreRecipes = true;

    public static void parseBlacklist() {
        for (int i = 0; i < entityBlacklist.length; i++) {
            String[] splitText = entityBlacklist[i].split(":");
            entityBlacklistR.add(new ResourceLocation(splitText[0], splitText[1]));
        }
    }
}
