package com.fabbe50.compressedblocks.core.registry;

import com.thefifthidiot.tficore.common.world.gen.GenWorldVeins;
import net.minecraft.init.Blocks;

/**
 * Created by fabbe50 on 13/01/2017.
 */
public class OregenRegistry {
    public static void init() {
        addOverworldOres();
        addNetherOres();
        addEndOres();
    }

    private static void addOverworldOres() {

    }

    private static void addNetherOres() {
        GenWorldVeins.addToNether(BlockRegistry.NETHER_STONE, 16, 64, 6, 10, 120, Blocks.NETHERRACK);
    }

    private static void addEndOres() {

    }
}
