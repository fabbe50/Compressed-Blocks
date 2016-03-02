package com.fabbe50.compressedblocks.entities.mobspawning;

import com.fabbe50.compressedblocks.entities.tamables.EntityCorgi;
import com.fabbe50.compressedblocks.reference.Reference;
import com.fabbe50.compressedblocks.utility.LogHelper;
import net.minecraft.entity.*;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * Created by fabbe50 on 29/02/2016.
 */
public class SpawnMob extends ItemMonsterPlacer{
    protected static String entityToSpawnName = "";
    protected static String entityToSpawnNameFull = "";
    protected static EntityLiving entityToSpawn = null;
    static EntityCorgi corgi;

    public static Entity spawnEntity(World parWorld, double parX, double parY, double parZ, Entity corgi)
    {
        entityToSpawnName = corgi.toString().substring(6).toLowerCase();

        if (!parWorld.isRemote) // never spawn entity on client side
        {
            entityToSpawnNameFull = Reference.MOB_PREFIX + entityToSpawnName;
            if (EntityList.stringToClassMapping.containsKey(entityToSpawnNameFull)) {
                //DEBUG
                LogHelper.error("Entity not found: " + entityToSpawnName);
                LogHelper.error("Entity not found (full name): " + entityToSpawnNameFull);

                LogHelper.info("EntityList: ");
                return null;
            } else {
                Entity entity = null;

                for (int j = 0; j < 1; ++j) {
                    entity = EntityList.createEntityByID(EntityList.getEntityID(corgi), parWorld);

                    if (entity != null && entity instanceof EntityLivingBase) {
                        EntityLiving entityliving = (EntityLiving) entity;
                        entity.setLocationAndAngles(parX, parY, parZ, MathHelper.wrapAngleTo180_float(parWorld.rand.nextFloat() * 360.0F), 0.0F);
                        entityliving.rotationYawHead = entityliving.rotationYaw;
                        entityliving.renderYawOffset = entityliving.rotationYaw;
                        entityliving.onSpawnWithEgg((IEntityLivingData) null);
                        parWorld.spawnEntityInWorld(entity);
                        entityliving.playLivingSound();
                    }
                }

                return entity;
            }
        }
        else {return null;}
    }
}
