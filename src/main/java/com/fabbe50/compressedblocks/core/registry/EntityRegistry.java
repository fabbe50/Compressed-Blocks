package com.fabbe50.compressedblocks.core.registry;

import com.fabbe50.compressedblocks.CompressedBlocks;
import com.fabbe50.compressedblocks.common.entities.EntityColoredSquid;
import com.fabbe50.compressedblocks.common.entities.EntityCorgi;
import com.fabbe50.compressedblocks.core.reference.Reference;
import com.fabbe50.compressedblocks.core.render.entity.RenderSingularity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

/**
 * Created by fabbe50 on 26/09/2016.
 */
public class EntityRegistry {
    public static void init() {
        createEntity(Reference.modResourceLoc("corgi"), EntityCorgi.class, "corgi", CompressedBlocks.instance, 0, 64, 1, true, 0xedc67d, 0x8f6830);
        createEntity(Reference.modResourceLoc("csquid"), EntityColoredSquid.class, "csquid", CompressedBlocks.instance, 1, 64, 1, true, 0x00ffff, 0x008b8b);

        addSpawning(EntityColoredSquid.class, 20, 5, 12, EnumCreatureType.WATER_CREATURE, Biomes.OCEAN, Biomes.DEEP_OCEAN, Biomes.FROZEN_OCEAN, Biomes.RIVER, Biomes.FROZEN_RIVER);
        EntitySpawnPlacementRegistry.setPlacementType(EntityColoredSquid.class, EntityLiving.SpawnPlacementType.IN_WATER);
    }

    private static void createEntity(ResourceLocation location, Class<? extends Entity> clazz, String name, Object modInstance, int id, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int eggPrimary, int eggSecondary) {
        net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(location, clazz, name, id, modInstance, trackingRange, updateFrequency, sendsVelocityUpdates, eggPrimary, eggSecondary);
    }

    private static void addSpawning(Class <? extends EntityLiving> entityClass, int weightedProb, int min, int max, EnumCreatureType typeOfCreature, Biome... biomes) {
        net.minecraftforge.fml.common.registry.EntityRegistry.addSpawn(entityClass, weightedProb, min, max, typeOfCreature, biomes);
    }

    public static void renderInit() {
        RenderSingularity.registerRender();
    }
}
