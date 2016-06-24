package com.fabbe50.compressedblocks.core.proxy;

import com.fabbe50.compressedblocks.core.registry.BlockRegistry;

import com.fabbe50.compressedblocks.core.registry.ItemRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/*	This is the CommonProxy class, it is used for:
 * 
 * 	Registering
 * 		blocks
 * 		entities
 * 		items
 * 		tile entities
 */
public class CommonProxy {
	public void preInit(FMLPreInitializationEvent event) {
		BlockRegistry.init();	//Initialize Blocks
		ItemRegistry.init();	//Initialize Items
	}
	
    public void init(FMLInitializationEvent event) {
    	//RecipeRegistry.registerRecipe();
    	//SmeltingRegistry.registerSmelting();
    }
    
    public void postInit(FMLPostInitializationEvent event) {
    	
    }
}
