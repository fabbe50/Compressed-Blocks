package com.fabbe50.compressedblocks.common.entities;

import com.thefifthidiot.tficore.utility.LogHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by fabbe on 21/07/2017.
 */
public class EntityPotatoSingularity extends EntityThrowable {
    public static float[] position = new float[] {-15, -10.5f, -7, -3.5f, 0, 3.5f, 7, 10.5f, 15};

    public EntityPotatoSingularity(World worldIn) {
        super(worldIn);
    }

    public EntityPotatoSingularity(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    public EntityPotatoSingularity(World worldIn, double x, double y, double z) {
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
        BlockPos pos = new BlockPos(result.getBlockPos());
        boolean isExplosionDeactivated = false;
        if (isExplosionDeactivated && !hasHit) {
            LogHelper.info(pos);
        }
        if (Loader.isModLoaded("brandonscore") && Loader.isModLoaded("draconicevolution") && !hasHit) {
            com.brandon3055.draconicevolution.blocks.reactor.ProcessExplosion explosion = new com.brandon3055.draconicevolution.blocks.reactor.ProcessExplosion(pos, 150, this.world.getMinecraftServer().worldServerForDimension(this.dimension), 0);
            com.brandon3055.brandonscore.handlers.ProcessHandler.addProcess(explosion);
        }
        if (!hasHit) {
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
