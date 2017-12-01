package com.fabbe50.compressedblocks.core.registry;

import com.fabbe50.compressedblocks.CompressedBlocks;
import com.fabbe50.compressedblocks.common.entities.EntityColoredSquid;
import com.fabbe50.compressedblocks.common.entities.EntityCorgi;
import com.fabbe50.compressedblocks.core.reference.Reference;
import com.fabbe50.compressedblocks.core.render.entity.RenderSingularity;
import com.thefifthidiot.tficore.init.TFIEntity;

/**
 * Created by fabbe50 on 26/09/2016.
 */
public class EntityRegistry {
    public static void init() {
        TFIEntity.createEntity(Reference.modResourceLoc("corgi"), EntityCorgi.class, "corgi", CompressedBlocks.instance, 0, 64, 1, true, 0xedc67d, 0x8f6830);
        TFIEntity.createEntity(Reference.modResourceLoc("csquid"), EntityColoredSquid.class, "csquid", CompressedBlocks.instance, 1, 64, 1, true, 0x00ffff, 0x008b8b);
    }

    public static void renderInit() {
        RenderSingularity.registerRender();
    }
}
