package com.fabbe50.compressedblocks.core.proxy;

import com.fabbe50.compressedblocks.common.tileentities.TileEntitySuperShulkerBox;
import com.fabbe50.compressedblocks.core.registry.*;

import com.fabbe50.compressedblocks.core.render.TileEntityItemStackRenderer;
import com.fabbe50.compressedblocks.core.render.tileentity.TileEntitySuperShulkerBoxRenderer;
import net.minecraftforge.fml.client.registry.ClientRegistry;
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
		EntityRegistry.renderInit(); //Register entity-renderering

		ClientRegistry.registerTileEntity(TileEntitySuperShulkerBox.class, "supershulkerbox", new TileEntitySuperShulkerBoxRenderer());

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