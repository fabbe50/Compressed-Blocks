package com.fabbe50.compressedblocks.core.registry;

import com.fabbe50.compressedblocks.core.event.CraftingEvent;
import com.fabbe50.compressedblocks.core.event.SmeltingEvent;
import com.fabbe50.compressedblocks.core.tweaks.StackableBuckets;
import com.fabbe50.compressedblocks.core.utils.PlayerTracker;
import com.fabbe50.compressedblocks.core.utils.VanillaHooks;
import com.fabbe50.compressedblocks.core.utils.helper.GameHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

/**
 * Created by fabbe50 on 19/09/2016.
 */
@SuppressWarnings("deprecation")
public class EventRegistry {
    public static void init() {
        MinecraftForge.EVENT_BUS.register(new CraftingEvent());
        MinecraftForge.EVENT_BUS.register(new SmeltingEvent());
    }

    public static void onServerStarted() {
        //MinecraftForge.EVENT_BUS.register(new GameHelper());
        MinecraftForge.EVENT_BUS.register(new StackableBuckets());
        MinecraftForge.EVENT_BUS.register(new PlayerTracker());
        MinecraftForge.EVENT_BUS.register(new VanillaHooks());
    }

    public static void onClientStarted() {

    }
}
