package com.fabbe50.compressedblocks.core.handler;


import com.fabbe50.compressedblocks.core.reference.Reference;
import com.fabbe50.compressedblocks.utility.LogHelper;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by fabbe50 on 14/01/2016.
 */
public class ConfigurationHandler {
    public static Configuration configuration;

    public static boolean noSafeRecipe;
    public static float lightLevelForEndgamium;
    public static float lightLevelForStarblock;
    public static int endgamiumdamagevalue;
    public static int endgamiumextradamage;
    public static boolean pinginchat;
    public static int pingcommandpermission;
    public static boolean noguiblockshavegui;

    public static void init(File configFile) {
        //Create Config-object from Config File
        if (configuration == null) {
            configuration = new Configuration(configFile);
            loadConfiguration();
            LogHelper.info("Config File Created!");
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent (ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
            //Resync Configs
            loadConfiguration();
        }
    }

    public static void loadConfiguration() {
        noSafeRecipe = configuration.getBoolean("noSafeRecipe", Configuration.CATEGORY_GENERAL, true, "Is the game allowed to fall back on a Vanilla Minecraft recipe if it can't find all the mod items?");
        lightLevelForEndgamium = configuration.getFloat("endgamiumLightLevel", Configuration.CATEGORY_GENERAL, 1.0f, 0.0f, 1.0f, "What light level does an Endgamium Block give?");
        lightLevelForStarblock = configuration.getFloat("comprstarLightLevel", Configuration.CATEGORY_GENERAL, 0.85f, 0.0f, 1.0f, "What light level does a Compressed Nether Star Block Give?");
        endgamiumdamagevalue = configuration.getInt("endgamiumdamagevalue", Configuration.CATEGORY_GENERAL, 10, 0, 100, "How much damage should endgamium do? (numbers are in half hearts)");
        endgamiumextradamage = configuration.getInt("endgamiumextradamage", Configuration.CATEGORY_GENERAL, 1000, 0, 100, "How much extra damage does the sword do to mobs? (set to 0 if you want damage to stay the same for all mobs)");
        pinginchat = configuration.getBoolean("pinginchat", Configuration.CATEGORY_GENERAL, false, "Can you type ping in chat for the server to respond pong? (WIP)");
        pingcommandpermission = configuration.getInt("pingcommandpermission", Configuration.CATEGORY_GENERAL, 0, 0, 4, "How high OP-level do you need to use ping-command? '0' means everyone can use it, 4 is only highest tier OP.");
        noguiblockshavegui = configuration.getBoolean("noguiblockhavegui", Configuration.CATEGORY_GENERAL, false, "Will right-clicking a GUI-less block open a GUI that displays some info for that tile?");

        if (configuration.hasChanged()){
            configuration.save();
            LogHelper.info("Configuration Saved!");
        }
    }
}
