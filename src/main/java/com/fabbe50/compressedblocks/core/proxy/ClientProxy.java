package com.fabbe50.compressedblocks.core.proxy;


import com.fabbe50.compressedblocks.common.entity.customParticles.ModEntityFXEndgamium;
import com.fabbe50.compressedblocks.common.entity.tamables.corgis.EntityCorgi;
import com.fabbe50.compressedblocks.common.entity.models.ModelCorgi;
import com.fabbe50.compressedblocks.common.entity.renders.RenderCorgi;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;

/**
 * Created by fabbe50 on 14/01/2016.
 */
public class ClientProxy extends ServerProxy {
    @Override
    public void registerRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityCorgi.class, new RenderCorgi(new ModelCorgi(), new ModelCorgi(), 0));
    }

    @Override
    public void registerParticles(Entity theEntity){
        double motionX = theEntity.worldObj.rand.nextGaussian() * 0.02D;
        double motionY = theEntity.worldObj.rand.nextGaussian() * 0.02D;
        double motionZ = theEntity.worldObj.rand.nextGaussian() * 0.02D;
        EntityFX particleMysterious = new ModEntityFXEndgamium(
                theEntity.worldObj,
                theEntity.posX + theEntity.worldObj.rand.nextFloat() * theEntity.width * 2.0F - theEntity.width,
                theEntity.posY + 0.5D + theEntity.worldObj.rand.nextFloat() * theEntity.height,
                theEntity.posZ + theEntity.worldObj.rand.nextFloat() * theEntity.width * 2.0F - theEntity.width,
                motionX,
                motionY,
                motionZ);
        Minecraft.getMinecraft().effectRenderer.addEffect(particleMysterious);
    }
}
