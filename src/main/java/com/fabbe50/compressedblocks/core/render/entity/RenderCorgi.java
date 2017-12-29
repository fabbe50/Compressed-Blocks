package com.fabbe50.compressedblocks.core.render.entity;

import com.fabbe50.compressedblocks.common.entities.EntityCorgi;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by fabbe50 on 26/09/2016.
 */
@SideOnly(Side.CLIENT)
public class RenderCorgi extends RenderLiving<EntityCorgi> {

    public RenderCorgi(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super(rendermanagerIn, modelbaseIn, shadowsizeIn);
    }

    protected float handleRotationFloat(EntityCorgi livingBase, float partialTicks) {
        return livingBase.getTailRotation();
    }

    public void doRender(EntityCorgi entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        if (entity.isWet())
        {
            float f = entity.getBrightness() * entity.getShadingWhileWet(partialTicks);
            GlStateManager.color(f, f, f);
        }

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityCorgi entity) {
        return entity.getCorgiType().getResourceLocation();
    }
}
