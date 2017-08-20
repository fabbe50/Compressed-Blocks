package com.fabbe50.compressedblocks.core.render;

import com.fabbe50.compressedblocks.core.render.tileentity.TileEntitySuperShulkerBoxRenderer;
import net.minecraft.client.renderer.block.model.IBakedModel;

/**
 * Created by fabbe on 24/07/2017 - 4:33 PM.
 */
public class RenderSuperShulkerBox extends CustomBakedModel {
    public RenderSuperShulkerBox(IBakedModel model) {
        super(model);
    }

    @Override
    public void render(IBakedModel model) {
        TileEntitySuperShulkerBoxRenderer.renderShulker(null, 0, 0, 0, 0, 0);
    }
}
