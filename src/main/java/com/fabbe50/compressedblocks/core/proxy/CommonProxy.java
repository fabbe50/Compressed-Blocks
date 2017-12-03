package com.fabbe50.compressedblocks.core.proxy;

import com.fabbe50.compressedblocks.core.event.TooltipEvent;
import com.fabbe50.compressedblocks.core.handler.ConfigurationHandler;
import com.fabbe50.compressedblocks.core.lib.CBLibrary;
import com.fabbe50.compressedblocks.core.lib.Configs;
import com.fabbe50.compressedblocks.core.lib.EntityBlacklistStorage;
import com.fabbe50.compressedblocks.core.lib.Payments;
import com.fabbe50.compressedblocks.core.registry.*;

import com.fabbe50.compressedblocks.core.utils.DataFixesManager;
import com.fabbe50.compressedblocks.core.utils.RecipeRemoval;
import com.fabbe50.compressedblocks.core.utils.VanillaHooks;
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
        //ConfigurationHandler.load(event);                               //Initialize Configuration
        ToolMaterialRegistry.init();                                    //Initialize Tool Material
		BlockRegistry.init();	                                        //Initialize Blocks
        BlockRegistry.registerTileEntity();                             //Initialize TileEntities
		ItemRegistry.init();	                                          //Initialize Items
        //EntityRegistry.init();                                          //Initialize Entities
        //CBLibrary.init();                                               //Initialize Special Data for Compressed Blocks
        //PotionRegistry.init();
        //PotionRegistry.subInit();
	}
	
    public void init(FMLInitializationEvent event) {
        //StatsRegistry.init();                                           //Initialize Statistics
        //AchievementRegistry.init();                                     //Initialize Achievements
        TooltipEvent.init();
        //EventRegistry.init();                                           //Initialize Events
        //ModItemRegistry.init();                                         //Initialize Mod Items
        //OreDictRegistry.init();                                         //Initialize OreDictionary
        //ReturnableItemRegistry.init();                                  //Initialize ReturnableItemRegistry
        //RecipeRegistry.init();                                          //Initialize Recipes
        //Payments.init();                                                //Initialize Payments
        //EndgameCraftingRegistry.init();                                 //Initialize Endgame Recipes
    	//SmeltingRegistry.init();                                        //Initialize Smelting Recipes
        //OregenRegistry.init();                                          //Initialize Ore Generation
        //BiomeRegistry.init();                                           //Initialize Biomes //FIXME: Create custom world generator. INFO: NETHER_STONE is generated through ore-veins for now.
        //Configs.init();                                                 //Initialize Configs
        //RecipeRemoval.init();                                           //Remove Recipes
        //RecipeOverrideRegistry.init();                                  //Add Replacements for Recipes
        //DataFixesManager.createFixer();
    }
    
    public void postInit(FMLPostInitializationEvent event) {
        //VanillaHooks.init();
        //EntityBlacklistStorage.init();
    }

    public void onServerStarted(FMLServerStartedEvent event) {
        EventRegistry.onServerStarted();
    }
}
