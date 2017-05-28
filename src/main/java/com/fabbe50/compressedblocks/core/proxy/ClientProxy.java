package com.fabbe50.compressedblocks.core.proxy;

import com.fabbe50.compressedblocks.core.registry.BlockRegistry;

import com.fabbe50.compressedblocks.core.registry.EventRegistry;
import com.fabbe50.compressedblocks.core.registry.ItemRegistry;
import com.fabbe50.compressedblocks.core.registry.RenderRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;

/*	This is the ClientProxy class, it used for:
 * 		
 * 	Registering:
 * 		particles
 * 		guis
 * 		block models and textures
 * 		item models and textures
 * 
 */
public class ClientProxy extends CommonProxy {
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
        RenderRegistry.init();
	}
	
    public void init(FMLInitializationEvent event) {
    	super.init(event);
    	
    	BlockRegistry.renderInit(); //Register block-rendering
    	ItemRegistry.renderInit(); //Register item-rendering
    }
    
    public void postInit(FMLPostInitializationEvent event) {
    	super.postInit(event);
    }

    public void onServerStarted(FMLServerStartedEvent event) {
		super.onServerStarted(event);

		EventRegistry.onClientStarted();
	}
}