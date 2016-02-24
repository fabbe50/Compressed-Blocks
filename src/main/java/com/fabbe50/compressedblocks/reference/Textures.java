package com.fabbe50.compressedblocks.reference;

import net.minecraft.util.ResourceLocation;

/**
 * Created by fabbe50 on 14/01/2016.
 */
public class Textures {
    public static final String RESOURCE_PREFIX = Reference.MOD_ID.toLowerCase() + ":";
    public static final ResourceLocation RESOURCE_PATH_ENTITY = new ResourceLocation(RESOURCE_PREFIX + "textures/entity/");

    public static String corgi (String name) {
        return RESOURCE_PATH_ENTITY + "corgi/corgi_" + name + ".png";
    }
}
