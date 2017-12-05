package com.fabbe50.compressedblocks.core.registry;

import com.fabbe50.compressedblocks.common.world.gen.GenWorldVeins;
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

    //GenWorldVeins.addToWorld(blockToSpawn, minAmount, maxAmount, chance, minY, maxY, spawnInBlock);

    private static void addOverworldOres() {
        GenWorldVeins.addToOverworld(BlockRegistry.FUSEROCK, 4, 8, 6, 2, 50, Blocks.STONE);
    }

    private static void addNetherOres() {
        GenWorldVeins.addToOverworld(BlockRegistry.FUSEROCKNETHER, 4, 8, 6, 2, 50, Blocks.NETHERRACK);
        //GenWorldVeins.addToNether(BlockRegistry.NETHER_STONE, 32, 128, 6, 10, 120, Blocks.NETHERRACK);
    }

    private static void addEndOres() {
        GenWorldVeins.addToOverworld(BlockRegistry.FUSEROCKNETHER, 4, 8, 6, 2, 50, Blocks.END_STONE);
    }
}
