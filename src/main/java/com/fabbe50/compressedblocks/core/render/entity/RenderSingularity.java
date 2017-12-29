package com.fabbe50.compressedblocks.core.render.entity;

import com.fabbe50.compressedblocks.common.entities.EntityPotatoSingularity;
import com.fabbe50.compressedblocks.core.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 * Created by fabbe on 22/07/2017.
 */
@SideOnly(Side.CLIENT)
public class RenderSingularity<T extends Entity> extends Render<T>{
    private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/items/potatosingularitye");

    protected final Item item;
    private final RenderItem itemRenderer;

    private RenderSingularity(RenderManager renderManager) {
        super(renderManager);
        //this.item = ItemRegistry.POTATO_SINGULARITY;
        this.item = Items.POTATO;
        this.itemRenderer = Minecraft.getMinecraft().getRenderItem();
    }

    public static void registerRender() {
        RenderingRegistry.registerEntityRenderingHandler(EntityPotatoSingularity.class, RenderSingularity::new);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(T entity) {
        return texture;
    }

    private ItemStack getStackToRender(T entity) {
        return new ItemStack(this.item);
    }

    @Override
    public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate((float)(this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
        this.bindTexture(getEntityTexture(entity));

        if (this.renderOutlines)
        {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }

        this.itemRenderer.renderItem(this.getStackToRender(entity), ItemCameraTransforms.TransformType.GROUND);

        if (this.renderOutlines)
        {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
