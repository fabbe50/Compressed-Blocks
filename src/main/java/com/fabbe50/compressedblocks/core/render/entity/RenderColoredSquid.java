package com.fabbe50.compressedblocks.core.render.entity;

import com.fabbe50.compressedblocks.common.entities.EntityColoredSquid;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSquid;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

/**
 * Created by fabbe on 28/11/2017 - 4:24 PM.
 */
public class RenderColoredSquid extends RenderLiving<EntityColoredSquid> {
    public RenderColoredSquid(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelSquid(), 0.7f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityColoredSquid entity) {
        return new ResourceLocation("compressedblocks:textures/entity/squid.png");
    }

    protected void applyRotations(EntityColoredSquid entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        float f = entityLiving.prevSquidPitch + (entityLiving.squidPitch - entityLiving.prevSquidPitch) * partialTicks;
        float f1 = entityLiving.prevSquidYaw + (entityLiving.squidYaw - entityLiving.prevSquidYaw) * partialTicks;
        GlStateManager.translate(0.0F, 0.5F, 0.0F);
        GlStateManager.rotate(180.0F - rotationYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(f1, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(0.0F, -1.2F, 0.0F);
        float[] rgb = entityLiving.getDyeRgb(entityLiving.getInkColor());
        GlStateManager.color(rgb[0], rgb[1], rgb[2]);
    }

    protected float handleRotationFloat(EntityColoredSquid livingBase, float partialTicks) {
        return livingBase.lastTentacleAngle + (livingBase.tentacleAngle - livingBase.lastTentacleAngle) * partialTicks;
    }
}
