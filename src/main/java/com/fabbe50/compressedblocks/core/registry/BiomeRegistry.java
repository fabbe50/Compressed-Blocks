package com.fabbe50.compressedblocks.core.registry;

import com.fabbe50.compressedblocks.common.world.biome.BiomeDenseHell;
import com.fabbe50.compressedblocks.core.reference.Reference;
import com.fabbe50.compressedblocks.core.utils.helper.LogHelper;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

/**
 * Created by fabbe50 on 13/01/2017.
 */
public class BiomeRegistry {
    public static final Biome DENSE_HELL = new BiomeDenseHell();

    public static void init() {
        //addBiome(DENSE_HELL, "dense_hell", BiomeType.WARM, 5, Type.NETHER); //Can't add biomes to the nether without overriding the WorldProvider
    }

    private static Biome addBiome(Biome biome, String name, BiomeType type, int weight, Type... types) {
        biome.setRegistryName(Reference.MOD_ID, name);
        ForgeRegistries.BIOMES.register(biome);
        LogHelper.info("Biome registered with name: " + biome.getRegistryName().toString());
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addBiome(type, new BiomeEntry(biome, weight));
        BiomeManager.addSpawnBiome(biome);
        return biome;
    }
}
