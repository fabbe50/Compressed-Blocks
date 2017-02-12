package com.fabbe50.compressedblocks.core.handler;

import com.fabbe50.compressedblocks.core.lib.Configs;
import com.fabbe50.compressedblocks.core.lib.EntityBlacklistStorage;
import com.fabbe50.compressedblocks.core.reference.Reference;
import com.google.common.collect.Lists;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;
import java.util.List;

/**
 * Created by fabbe50 on 23/09/2016.
 */
public class ConfigurationHandler {
    public static ConfigurationHandler configuration = new ConfigurationHandler();

    static Configuration configFile;

    public static ConfigCategory general;

    public static void load(FMLPreInitializationEvent event) {
        configFile = new Configuration(event.getSuggestedConfigurationFile(), "Alpha2", false);

        MinecraftForge.EVENT_BUS.register(configuration);

        syncConfig();
    }

    @SubscribeEvent
    public void update(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(Reference.MOD_ID)) {
            syncConfig();
        }
    }

    public static void syncConfig() {
        Property prop;

        /*General*/
        {
            String cat = "General";
            List<String> propOrder = Lists.newArrayList();
            general = configFile.getCategory(cat);

            prop = configFile.get(cat, "Entity Blacklist", Configs.entityBlacklist);
            prop.setComment("What mob cannot fall through the disappearing block.");
            Configs.entityBlacklist = prop.getStringList();
            propOrder.add(prop.getName());

            prop = configFile.get(cat, "Hardcore Recipes", Configs.hardcoreRecipes);
            prop.setComment("Replaces recipes of some mods to become more difficult to get, therefore prolonging the journey to endgame.");
            Configs.hardcoreRecipes = prop.getBoolean();
            propOrder.add(prop.getName());
        }

        if(configFile.hasChanged()) {
            configFile.save();
            Configs.parseBlacklist();
        }
    }
}
