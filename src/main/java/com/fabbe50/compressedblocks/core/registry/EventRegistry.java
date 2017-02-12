package com.fabbe50.compressedblocks.core.registry;

import com.fabbe50.compressedblocks.core.event.CraftingEvent;
import com.fabbe50.compressedblocks.core.utils.helper.GameHelper;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by fabbe50 on 19/09/2016.
 */
public class EventRegistry {
    public static void init() {
        MinecraftForge.EVENT_BUS.register(new CraftingEvent());
    }

    public static void onServerStarted() {
        MinecraftForge.EVENT_BUS.register(new GameHelper());
    }
}
