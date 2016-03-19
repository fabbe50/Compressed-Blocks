package com.fabbe50.compressedblocks.entities.customParticles;

import com.fabbe50.compressedblocks.utility.LogHelper;
import net.minecraft.client.particle.EntityAuraFX;
import net.minecraft.client.particle.EntityFireworkOverlayFX;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by fabbe50 on 08/03/2016.
 */
public class ModEntityFXEndgamium extends EntityFireworkOverlayFX {
    Random rand = new Random();
    Tessellator tesselator = Tessellator.instance;

    public ModEntityFXEndgamium(World parWorld, double parX, double parY, double parZ, double parMotionX, double parMotionY, double parMotionZ)
    {
        super(parWorld, parX, parY, parZ);

        float f1 = ActiveRenderInfo.rotationX;
        float f2 = ActiveRenderInfo.rotationZ;
        float f3 = ActiveRenderInfo.rotationYZ;
        float f4 = ActiveRenderInfo.rotationXY;
        float f5 = ActiveRenderInfo.rotationXZ;
        if (!worldObj.isRemote) {
            try {
                setRBGColorF(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
                setParticleTextureIndex(82);
                particleScale = 1.0F;
                renderParticle(tesselator, 1, f1, f2, f3, f4, f5);
            } catch (Exception e) {
                LogHelper.error(e);
            }
        }
    }
}
