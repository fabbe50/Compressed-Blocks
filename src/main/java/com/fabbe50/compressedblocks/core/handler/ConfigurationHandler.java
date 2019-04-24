package com.fabbe50.compressedblocks.core.handler;

import com.fabbe50.compressedblocks.core.lib.Configs;
import com.fabbe50.compressedblocks.core.reference.Reference;
import com.google.common.collect.Lists;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

/**
 * Created by fabbe50 on 23/09/2016.
 */
public class ConfigurationHandler {
    public static ConfigurationHandler configuration = new ConfigurationHandler();

    static Configuration configFile;

    public static ConfigCategory general;
    public static ConfigCategory vanillaTweaks;

    public static void load(FMLPreInitializationEvent event) {
        configFile = new Configuration(event.getSuggestedConfigurationFile(), Reference.VERSION, false);

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

            prop = configFile.get(cat, "Hardcore Recipes [Not fully implemented]", Configs.hardcoreRecipes);
            prop.setComment("Replaces recipes of some mods to become more difficult to get, therefore prolonging the journey to endgame.");
            Configs.hardcoreRecipes = prop.getBoolean();
            propOrder.add(prop.getName());

            prop = configFile.get(cat, "Crossdimensional Teleportation [EXPERIMENTAL]", Configs.enableCrossDimensionalTP);
            prop.setComment("Enable Experimental Crossdimensional Teleportation (Doesn't work with The End)");
            Configs.enableCrossDimensionalTP = prop.getBoolean();
            propOrder.add(prop.getName());

            prop = configFile.get(cat, "Compressed TNT Spread", Configs.compressedTNTSpread);
            prop.setComment("Should the TNT spawned from compressed TNT spread out?");
            Configs.compressedTNTSpread = prop.getBoolean();
            propOrder.add(prop.getName());

            prop = configFile.get(cat, "Fuserock Strength", Configs.fuseRockStrength);
            prop.setComment("Strength the fuserock explodes with. [TNT is 4, default=6]");
            Configs.fuseRockStrength = prop.getInt();
            propOrder.add(prop.getName());

            prop = configFile.get(cat, "Singularity Crafting Recipe", Configs.singularityCraftable);
            prop.setComment("Enable singularity crafting recipe here. \n I'm not responsible for any destruction or issues caused by this item.");
            Configs.singularityCraftable = prop.getBoolean();
            propOrder.add(prop.getName());
        }

        //Vanilla Tweaks
        {
            String cat = "Vanilla Tweaks";
            List<String> propOrder = Lists.newArrayList();
            vanillaTweaks = configFile.getCategory(cat);

            prop = configFile.get(cat, "Vanilla Tweaks", Configs.vanillaHooks);
            prop.setComment("Turns all tweaks off.");
            Configs.vanillaHooks = prop.getBoolean();
            propOrder.add(prop.getName());

            prop = configFile.get(cat, "Stack Sizes", Configs.stackSizes);
            prop.setComment("Increases stack sizes for different things: boats, buckets, cake etc.");
            Configs.stackSizes = prop.getBoolean();
            propOrder.add(prop.getName());

            prop = configFile.get(cat, "Disable drinking milk from bucket", Configs.undrinkableBuckets);
            prop.setComment("Disable drinking milk from buckets, allows for increased stack size if 'Stack Sizes' are enabled.");
            Configs.undrinkableBuckets = prop.getBoolean();
            propOrder.add(prop.getName());

            prop = configFile.get(cat, "Custom bucket behaviour", Configs.customBucketBehaviour);
            prop.setComment("[EXPERIMENTAL] Setting this to false will allow for stackable buckets, but will also change bucket behaviour.");
            Configs.customBucketBehaviour = prop.getBoolean();
            propOrder.add(prop.getName());
        }

        if(configFile.hasChanged()) {
            configFile.save();
            Configs.parseBlacklist();
        }
    }
}
