package com.fabbe50.compressedblocks.core.render.entity;

import com.fabbe50.compressedblocks.common.entities.EntitySquidColored;
import net.minecraft.client.model.ModelSquid;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by fabbe on 18/11/2017 - 11:42 AM.
 */
@SideOnly(Side.CLIENT)
public class RenderSquidColored extends RenderLiving<EntitySquidColored> {
    private static final ResourceLocation SQUID_TEXTURES = new ResourceLocation("textures/entity/squid.png");

    public RenderSquidColored(RenderManager renderManager) {
        super(renderManager, new ModelSquid(), 0.7F);
    }

    protected ResourceLocation getEntityTexture(EntitySquidColored entity) {
        return SQUID_TEXTURES;
    }

    protected void applyRotations(EntitySquidColored entityLiving, float pointlessValue, float rotationYaw, float partialTicks) {
        float f = entityLiving.prevSquidPitch + (entityLiving.squidPitch - entityLiving.prevSquidPitch) * partialTicks;
        float f1 = entityLiving.prevSquidYaw + (entityLiving.squidYaw - entityLiving.prevSquidYaw) * partialTicks;
        GlStateManager.translate(0.0F, 0.5F, 0.0F);
        GlStateManager.rotate(180.0F - rotationYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(f1, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(0.0F, -1.2F, 0.0F);
    }

    protected float handleRotationFloat(EntitySquidColored livingBase, float partialTicks) {
        return livingBase.lastTentacleAngle + (livingBase.tentacleAngle - livingBase.lastTentacleAngle) * partialTicks;
    }
}
