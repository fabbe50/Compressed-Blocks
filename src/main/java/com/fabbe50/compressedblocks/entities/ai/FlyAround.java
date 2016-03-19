package com.fabbe50.compressedblocks.entities.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by fabbe50 on 14/03/2016.
 */
public class FlyAround extends EntityLiving {
    public static ChunkCoordinates spawnPosition;
    private static Entity entity;
    Random rand = new Random();

    public FlyAround(World world, Entity entity) {
        super(world);
        
        this.entity = entity;
    }

    public static void flying () {
        /*if (entity.spawnPosition != null && (!entity.worldObj.isAirBlock(entity.spawnPosition.posX, entity.spawnPosition.posY, entity.spawnPosition.posZ) || entity.spawnPosition.posY < 1)) {
            entity.spawnPosition = null;
        }

        if (entity.spawnPosition == null || entity.rand.nextInt(30) == 0 || entity.spawnPosition.getDistanceSquared((int) entity.posX, (int) entity.posY, (int) entity.posZ) < 4.0F) {
            entity.spawnPosition = new ChunkCoordinates((int) entity.posX + entity.rand.nextInt(7) - entity.rand.nextInt(7), (int) entity.posY + entity.rand.nextInt(6) - 2, (int) entity.posZ + entity.rand.nextInt(7) - entity.rand.nextInt(7));
        }

        double d0 = (double) entity.spawnPosition.posX + 0.5D - entity.posX;
        double d1 = (double) entity.spawnPosition.posY + 0.1D - entity.posY;
        double d2 = (double) entity.spawnPosition.posZ + 0.5D - entity.posZ;
        entity.motionX += (Math.signum(d0) * 0.5D - entity.motionX) * 0.10000000149011612D;
        entity.motionY += (Math.signum(d1) * 0.699999988079071D - entity.motionY) * 0.10000000149011612D;
        entity.motionZ += (Math.signum(d2) * 0.5D - entity.motionZ) * 0.10000000149011612D;
        float f = (float) (Math.atan2(entity.motionZ, entity.motionX) * 180.0D / Math.PI) - 90.0F;
        float f1 = MathHelper.wrapAngleTo180_float(f - entity.rotationYaw);
        entity.moveForward = 0.5F;
        entity.rotationYaw += f1;*/
    }
}
