package com.fabbe50.compressedblocks.core.registry;

import com.fabbe50.compressedblocks.common.entities.EntityColoredSquid;
import com.fabbe50.compressedblocks.common.entities.EntityCorgi;
import com.fabbe50.compressedblocks.common.tileentities.TileEntityBeaconXray;
import com.fabbe50.compressedblocks.common.tileentities.TileEntitySuperShulkerBox;
import com.fabbe50.compressedblocks.core.model.entity.ModelCorgi;
import com.fabbe50.compressedblocks.core.render.entity.RenderColoredSquid;
import com.fabbe50.compressedblocks.core.render.entity.RenderCorgi;
import com.fabbe50.compressedblocks.core.render.tileentity.TileEntityBeaconXrayRenderer;
import com.fabbe50.compressedblocks.core.render.tileentity.TileEntitySuperShulkerBoxRenderer;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

/**
 * Created by fabbe50 on 26/09/2016.
 */
public class RenderRegistry {
    public static void init() {
        RenderingRegistry.registerEntityRenderingHandler(EntityCorgi.class, renderManager -> new RenderCorgi(renderManager, new ModelCorgi(), 0.3f));
        RenderingRegistry.registerEntityRenderingHandler(EntityColoredSquid.class, RenderColoredSquid::new);
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBeaconXray.class, new TileEntityBeaconXrayRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySuperShulkerBox.class, new TileEntitySuperShulkerBoxRenderer());
    }
}
