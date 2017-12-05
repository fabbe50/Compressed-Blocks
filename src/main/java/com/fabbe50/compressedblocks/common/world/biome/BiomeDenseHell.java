package com.fabbe50.compressedblocks.common.world.biome;

import com.fabbe50.compressedblocks.core.registry.BlockRegistry;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeHellDecorator;

/**
 * Created by fabbe50 on 13/01/2017.
 */
public class BiomeDenseHell extends Biome {
    public BiomeDenseHell() {
        super(new BiomeProperties("dense_hell").setTemperature(2.0F).setRainfall(0.0F).setRainDisabled());

        fillerBlock = BlockRegistry.NETHER_STONE.getDefaultState();
        topBlock = BlockRegistry.NETHER_STONE.getDefaultState();

        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityGhast.class, 50, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityPigZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityMagmaCube.class, 4, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityEnderman.class, 1, 4, 4));

        this.decorator = new BiomeHellDecorator();
    }
}
