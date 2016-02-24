package com.fabbe50.compressedblocks.proxy;


import com.fabbe50.compressedblocks.entities.tamables.EntityCorgi;
import com.fabbe50.compressedblocks.entities.models.ModelCorgi;
import com.fabbe50.compressedblocks.entities.renders.RenderCorgi;
import cpw.mods.fml.client.registry.RenderingRegistry;

/**
 * Created by fabbe50 on 14/01/2016.
 */
public class ClientProxy extends CommonProxy {
    public static void registerRenderingThings() {
        RenderingRegistry.registerEntityRenderingHandler(EntityCorgi.class, new RenderCorgi(new ModelCorgi(), new ModelCorgi(), 0));
    }
}
