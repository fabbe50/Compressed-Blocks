package com.fabbe50.compressedblocks.core.proxy;

import com.fabbe50.compressedblocks.core.handler.ConfigurationHandler;
import com.fabbe50.compressedblocks.core.lib.Configs;
import com.fabbe50.compressedblocks.core.lib.EntityBlacklistStorage;
import com.fabbe50.compressedblocks.core.registry.*;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;

/*	This is the CommonProxy class, it is used for:
 * 
 * 	Registering
 * 		Everything that's supposed to be run server-side, that is all the calculations and such
 */
public class CommonProxy {
	public void preInit(FMLPreInitializationEvent event) {
        ConfigurationHandler.load(event);                               //Initialize Configuration
		BlockRegistry.init();	                                        //Initialize Blocks
        BlockRegistry.registerTileEntity();                             //Initialize TileEntities
		ItemRegistry.init();	                                        //Initialize Items
        ToolMaterialRegistry.init();                                    //Initialize Tool Material
        EntityRegistryCB.init();                                        //Initialize Entities
	}
	
    public void init(FMLInitializationEvent event) {
        StatsRegistry.init();                                           //Initialize Statistics
        AchievementRegistry.init();                                     //Initialize Achievements
        EventRegistry.init();                                           //Initialize Events
        ModItemRegistry.init();                                         //Initialize Mod Items
        OreDictRegistry.init();                                         //Initialize OreDictionary
        RecipeRegistry.init();                                          //Initialize Recipes
    	SmeltingRegistry.init();                                        //Initialize Smelting Recipes
        OregenRegistry.init();                                          //Initialize Ore Generation
        //BiomeRegistry.init();                                           //Initialize Biomes //FIXME: Create custom world generator. INFO: NETHER_STONE is generated through ore-veins for now.
        Configs.parseBlacklist();
    }
    
    public void postInit(FMLPostInitializationEvent event) {
        EntityBlacklistStorage.init();
    }

    public void onServerStarted(FMLServerStartedEvent event) {
        EventRegistry.onServerStarted();
    }
}
