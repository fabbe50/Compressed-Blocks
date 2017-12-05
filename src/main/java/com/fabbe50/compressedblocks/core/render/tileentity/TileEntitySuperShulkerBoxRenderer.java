package com.fabbe50.compressedblocks.core.render.tileentity;

import com.fabbe50.compressedblocks.common.blocks.BlockSuperShulkerBox;
import com.fabbe50.compressedblocks.common.tileentities.TileEntitySuperShulkerBox;
import com.fabbe50.compressedblocks.core.render.Render;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelShulker;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderShulker;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * Created by fabbe50 on 19/02/2017.
 */
@SideOnly(Side.CLIENT)
public class TileEntitySuperShulkerBoxRenderer extends TileEntitySpecialRenderer<TileEntitySuperShulkerBox> {
    private static final ModelShulker model = new ModelShulker();

    public TileEntitySuperShulkerBoxRenderer() {
    }

    @Override
    public void render(TileEntitySuperShulkerBox te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        renderShulker(te, x, y, z, partialTicks, destroyStage);

        GlStateManager.pushMatrix();
        {
            GlStateManager.translate(0, 0, 0);
            super.render(te, x, y, z, partialTicks, destroyStage, alpha);
        }
        GlStateManager.popMatrix();
    }

    public static void renderShulker(TileEntitySuperShulkerBox box, double x, double y, double z, float partialTicks, int destroyStage) {
        EnumFacing enumfacing = EnumFacing.UP;

        if (box == null)
            box = new TileEntitySuperShulkerBox();

        if (box.hasWorld()) {
            IBlockState iblockstate = box.getWorld().getBlockState(box.getPos());

            if (iblockstate.getBlock() instanceof BlockSuperShulkerBox) {
                enumfacing = (EnumFacing)iblockstate.getValue(BlockSuperShulkerBox.FACING);
            }
        }

        GlStateManager.enableDepth();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);
        GlStateManager.disableCull();

        if (destroyStage >= 0) {
            Render.bindTexture(DESTROY_STAGES[destroyStage]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale(4.0F, 4.0F, 1.0F);
            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        }
        else {
            Render.bindTexture(RenderShulker.SHULKER_ENDERGOLEM_TEXTURE[box.getColor().getMetadata()]);
        }

        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();

        if (destroyStage < 0) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        }

        GlStateManager.translate((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
        GlStateManager.scale(1.0F, -1.0F, -1.0F);
        GlStateManager.translate(0.0F, 1.0F, 0.0F);
        float f = 0.9995F;
        GlStateManager.scale(0.9995F, 0.9995F, 0.9995F);
        GlStateManager.translate(0.0F, -1.0F, 0.0F);

        switch (enumfacing) {
            case DOWN:
                GlStateManager.translate(0.0F, 2.0F, 0.0F);
                GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
            case UP:
            default:
                break;
            case NORTH:
                GlStateManager.translate(0.0F, 1.0F, 1.0F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
                break;
            case SOUTH:
                GlStateManager.translate(0.0F, 1.0F, -1.0F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                break;
            case WEST:
                GlStateManager.translate(-1.0F, 1.0F, 0.0F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
                break;
            case EAST:
                GlStateManager.translate(1.0F, 1.0F, 0.0F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
        }

        model.base.render(0.0625F);
        GlStateManager.translate(0.0F, -box.getProgress(partialTicks) * 0.5F, 0.0F);
        GlStateManager.rotate(270.0F * box.getProgress(partialTicks), 0.0F, 1.0F, 0.0F);
        model.lid.render(0.0625F);
        GlStateManager.enableCull();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        if (destroyStage >= 0) {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }
    }

    @Override
    public boolean isGlobalRenderer(TileEntitySuperShulkerBox te) {
        return true;
    }
}
