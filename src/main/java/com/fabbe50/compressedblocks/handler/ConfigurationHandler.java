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

    public static boolean areBeaconBlocks = false;

    public static void init(File configFile) {
        //Create Config-object from Config File
        if (configuration == null) {
            configuration = new Configuration(configFile);
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

    public void loadConfiguration() {
        areBeaconBlocks = configuration.getBoolean("areBeaconBlocks", Configuration.CATEGORY_GENERAL, true, "Can compressed blocks be used for beacons?");

        if (configuration.hasChanged()){
            configuration.save();
            LogHelper.info("Configuration Saved!");
        }
    }
}
