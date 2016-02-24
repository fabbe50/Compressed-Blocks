package com.fabbe50.compressedblocks.entities;

import com.fabbe50.compressedblocks.CompressedBlocks;
import com.fabbe50.compressedblocks.entities.tamables.EntityCorgi;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * Created by fabbe50 on 10/02/2016.
 */
public class ModEntities {
    public static void mainRegistry() {
        registerEntity();
    }

    public static void registerEntity() {
        createEntity(EntityCorgi.class, "corgi", 0xedc67d, 0x8f6830);
    }

    public static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor) {
        int randomID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(entityClass,entityName,randomID);
        EntityRegistry.registerModEntity(entityClass,entityName,randomID, CompressedBlocks.instance,16,1,true);
        EntityRegistry.addSpawn(entityClass,0,0,0, EnumCreatureType.creature, BiomeGenBase.beach);

        createEgg(randomID,solidColor,spotColor);
    }

    private static void createEgg(int id, int solidColor, int spotColor) {
        EntityList.entityEggs.put(id,new EntityList.EntityEggInfo(id,solidColor,spotColor));
    }
}
