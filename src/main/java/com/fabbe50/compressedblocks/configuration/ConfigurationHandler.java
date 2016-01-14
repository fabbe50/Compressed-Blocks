package com.fabbe50.compressedblocks.configuration;


import com.fabbe50.compressedblocks.utility.LogHelper;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by fabbe50 on 14/01/2016.
 */
public class ConfigurationHandler {
    public static void init(File configFile) {
        //Create Config-object from Config File
        Configuration configuration = new Configuration(configFile);
        boolean isBeaconBlock = false;
        try {
            //Load Config File
            configuration.load();
            LogHelper.info("Configuration Loaded");

            //Read Properties from Config File
            isBeaconBlock = configuration.get(Configuration.CATEGORY_GENERAL, "isBeaconBlock", true, "Compressed Blocks for Beacons?").getBoolean(true);
        }
        catch (Exception e) {
            //Log Exception!
        }
        finally {
            //Save Config File
            configuration.save();
            LogHelper.info("Configuration Saved");
        }
    }
}
