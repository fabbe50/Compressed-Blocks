package com.fabbe50.compressedblocks.handler;


import com.fabbe50.compressedblocks.reference.Reference;
import com.fabbe50.compressedblocks.utility.LogHelper;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by fabbe50 on 14/01/2016.
 */
public class ConfigurationHandler {
    public static Configuration configuration;

    public static boolean enableDebugItems = false;
    public static boolean areBeaconBlocks = false;
    public static float lightLevelForEndgamium = 1.0f;
    public static float lightLevelForStarblock = 0.85f;

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
        enableDebugItems = configuration.getBoolean("enableDebugItems", Configuration.CATEGORY_GENERAL, false, "Do you want to enable debug items?");
        areBeaconBlocks = configuration.getBoolean("areBeaconBlocks", Configuration.CATEGORY_GENERAL, true, "Can compressed blocks be used for beacons?");
        lightLevelForEndgamium = configuration.getFloat("endgamiumLightLevel", Configuration.CATEGORY_GENERAL, 1.0f, 0.0f, 1.0f, "What light level does an Endgamium Block give?");
        lightLevelForStarblock = configuration.getFloat("comprstarLightLevel", Configuration.CATEGORY_GENERAL, 0.85f, 0.0f, 1.0f, "What light level does a Compressed Nether Star Block Give?");

        if (configuration.hasChanged()){
            configuration.save();
            LogHelper.info("Configuration Saved!");
        }
    }
}
