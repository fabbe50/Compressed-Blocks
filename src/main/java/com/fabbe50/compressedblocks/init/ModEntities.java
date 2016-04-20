package com.fabbe50.compressedblocks.init;

import com.fabbe50.compressedblocks.CompressedBlocks;
import com.fabbe50.compressedblocks.common.entity.tamables.corgis.EntityCorgi;
import com.fabbe50.compressedblocks.core.reference.Reference;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * Created by fabbe50 on 10/02/2016.
 */
public class ModEntities {
    public static void mainRegistry () {
        registerEntity();
    }

    public static void registerEntity() /*Register Mobs Here:*/ {
        createEntity(EntityCorgi.class, Reference.MOB_PREFIX + "corgi", 64, 0xedc67d, 0x8f6830, true);
    }

    public static void createEntity(Class entityClass, String entityName, int viewdistance, int solidColor, int spotColor, boolean createEgg) {
        int randomID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(entityClass,entityName,randomID);
        EntityRegistry.registerModEntity(entityClass,entityName,randomID, CompressedBlocks.instance, viewdistance, 1, true);
        EntityRegistry.addSpawn(entityClass,0,0,0, EnumCreatureType.creature, BiomeGenBase.beach);

        if (createEgg)
            createEgg(randomID,solidColor,spotColor);
    }

    private static void createEgg(int id, int solidColor, int spotColor) {
        EntityList.entityEggs.put(id,new EntityList.EntityEggInfo(id,solidColor,spotColor));
    }
}
