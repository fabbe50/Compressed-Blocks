package com.fabbe50.compressedblocks.core.registry;

import com.fabbe50.compressedblocks.common.world.biome.BiomeDenseHell;
import net.minecraft.world.biome.Biome;

/**
 * Created by fabbe50 on 13/01/2017.
 */
public class BiomeRegistry {
    public static void init() {
        Biome.registerBiome(168, "hell_dense", new BiomeDenseHell((new Biome.BiomeProperties("Hell_dense")).setTemperature(2.5f).setRainfall(0.0f).setRainDisabled()));
    }
}
