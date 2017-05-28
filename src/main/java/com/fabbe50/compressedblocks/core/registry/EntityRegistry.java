package com.fabbe50.compressedblocks.core.registry;

import com.fabbe50.compressedblocks.CompressedBlocks;
import com.fabbe50.compressedblocks.common.entities.EntityCorgi;
import com.fabbe50.compressedblocks.common.entities.EntitySquidColored;
import com.fabbe50.compressedblocks.core.reference.Reference;
import com.thefifthidiot.tficore.init.TFIEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

/**
 * Created by fabbe50 on 26/09/2016.
 */
public class EntityRegistry {
    public static void init() {
        TFIEntity.createEntity(Reference.modResourceLoc("corgi"), EntityCorgi.class, "corgi", CompressedBlocks.instance, 0, 64, 1, true, 0xedc67d, 0x8f6830);
        TFIEntity.createEntity(Reference.modResourceLoc("squid"), EntitySquidColored.class, "squidcolored", CompressedBlocks.instance, 0, 64, 1, true, 0, 0);
    }
}
