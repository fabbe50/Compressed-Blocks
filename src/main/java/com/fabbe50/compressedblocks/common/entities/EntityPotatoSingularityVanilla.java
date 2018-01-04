package com.fabbe50.compressedblocks.common.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by fabbe on 24/07/2017 - 2:50 PM.
 */
public class EntityPotatoSingularityVanilla extends EntityThrowable {
    public static float[] position = new float[] {-15, -10.5f, -7, -3.5f, 0, 3.5f, 7, 10.5f, 15};

    public EntityPotatoSingularityVanilla(World worldIn) {
        super(worldIn);
    }

    public EntityPotatoSingularityVanilla(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    public EntityPotatoSingularityVanilla(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 3) {
            for (int i = 0; i < 8; i++) {
                this.world.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
            }
        }
    }

    private boolean hasHit = false;

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!hasHit && false) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    for (int l = 0; l < 3; l++) {
                        EntityCompressedTNT entityTNT = new EntityCompressedTNT(this.world, (double) ((float) result.getBlockPos().getX() + 0.5F + position[i]), (double) result.getBlockPos().getY() + (l * -5), (double) ((float) result.getBlockPos().getZ() + 0.5F + position[j]), this.getThrower(), 9);
                        this.world.spawnEntity(entityTNT);
                    }
                }
            }
        }
        hasHit = true;
    }
}
