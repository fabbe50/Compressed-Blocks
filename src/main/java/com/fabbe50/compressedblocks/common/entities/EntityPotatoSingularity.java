package com.fabbe50.compressedblocks.common.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
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
        if (!hasHit && false) {
            com.brandon3055.draconicevolution.blocks.reactor.ProcessExplosion explosion = new com.brandon3055.draconicevolution.blocks.reactor.ProcessExplosion(pos, 150, this.world.getMinecraftServer().getWorld(this.dimension), 0);
            com.brandon3055.brandonscore.handlers.ProcessHandler.addProcess(explosion);
        }
        hasHit = true;
    }
}
