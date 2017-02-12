package com.fabbe50.compressedblocks.core.reference;

import net.minecraft.util.ResourceLocation;

public class Reference {
	public static final String MOD_ID = "compressedblocks";
	public static final String MOD_NAME = "Compressed Blocks";
    public static final String BUILD = "@BUILD@";
    public static final String VERSION = "@VERSION@" + BUILD;
    
    public static final String CLIENT_PROXY = "com.fabbe50.compressedblocks.core.proxy.ClientProxy";	//This is the path to the ClientProxy class.
	public static final String COMMON_PROXY = "com.fabbe50.compressedblocks.core.proxy.CommonProxy";	//This is the path to the CommonProxy class.
    public static final String GUIFACTORY = "com.fabbe50.compressedblocks.core.gui.GUIFactory";         //This is the path to the GUIFactory class.

    public static final ResourceLocation MOD_RESOURCES = new ResourceLocation(MOD_ID);

    public static final String TEXTURECORGIDIR = "textures/entity/corgi/";

    public static ResourceLocation modResourceLoc(String name) {
        return new ResourceLocation(MOD_ID, name);
    }
}
