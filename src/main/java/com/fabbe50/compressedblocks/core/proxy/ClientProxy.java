package com.fabbe50.compressedblocks.core.proxy;

import com.fabbe50.compressedblocks.core.reference.Reference;
import com.fabbe50.compressedblocks.core.registry.*;

import com.fabbe50.compressedblocks.core.render.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.util.ResourceLocation;
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
		EntityRegistry.renderInit();

		net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer.instance = new TileEntityItemStackRenderer();
    }
    
    public void postInit(FMLPostInitializationEvent event) {
    	super.postInit(event);
    }

    public void onServerStarted(FMLServerStartedEvent event) {
		super.onServerStarted(event);

		EventRegistry.onClientStarted();
	}
}