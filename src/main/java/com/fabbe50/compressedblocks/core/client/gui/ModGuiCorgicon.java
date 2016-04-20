package com.fabbe50.compressedblocks.core.client.gui;

import com.fabbe50.compressedblocks.common.entity.tamables.corgis.EntityCorgi;
import com.fabbe50.compressedblocks.core.reference.Textures;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/**
 * Created by fabbe50 on 12/04/2016.
 */
public abstract class ModGuiCorgicon extends InventoryEffectRenderer {
    private ResourceLocation defaultBackground = new ResourceLocation(Textures.RESOURCE_PREFIX + "textures/gui/corgicon.png");
    private int guiWidth = 600;
    private int guiHeight = 400;
    private float xSizeFloat;
    private float ySizeFloat;
    private EntityCorgi entity;

    public ModGuiCorgicon(Container container) {
        super(container);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    public void drawDefaultBackground() {
        super.drawDefaultBackground();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(defaultBackground);
        int i = (this.width - 248) / 2;
        int j = (this.height - 166) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, 248, 166);
    }

    public void initGui() {
        this.buttonList.clear();
        byte b0 = -16;
        this.buttonList.add(new GuiButton(1, this.width / 2 - 116, this.height / 2 + 62 + b0, 114, 20, I18n.format(StatCollector.translateToLocal("gui.button.ok"))));
    }

    public void updateScreen() {
        super.updateScreen();
    }

    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 1:
                button.enabled = false;
                this.mc.displayGuiScreen((GuiScreen)null);
                this.mc.setIngameFocus();
        }
    }

    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(field_147001_a);
        int k = this.guiLeft;
        int l = this.guiTop;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        drawEntity(k + 51, l + 75, 30, (float)(k + 51) - this.xSizeFloat, (float)(l + 75 - 50) - this.ySizeFloat, entity);
    }

    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        this.drawDefaultBackground();
        this.xSizeFloat = (float)p_73863_1_;
        this.ySizeFloat = (float)p_73863_2_;
        int k = (this.width - guiWidth) / 2 + 10;
        int l = (this.height - guiHeight) / 2 + 10;
        this.fontRendererObj.drawString(I18n.format(StatCollector.translateToLocal("gui.title.corgicon")), k, l, 2039583);
        GameSettings gamesettings = this.mc.gameSettings;
        this.fontRendererObj.drawString(I18n.format(""), k, l, 5197647);
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    }

    public static void drawEntity(int posX, int posY, int scaleratio, float p_147046_3_, float p_147046_4_, EntityLivingBase p_147046_5_)
    {
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)posX, (float)posY, 50.0F);
        GL11.glScalef((float)(-scaleratio), (float)scaleratio, (float)scaleratio);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        float f2 = p_147046_5_.renderYawOffset;
        float f3 = p_147046_5_.rotationYaw;
        float f4 = p_147046_5_.rotationPitch;
        float f5 = p_147046_5_.prevRotationYawHead;
        float f6 = p_147046_5_.rotationYawHead;
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        p_147046_5_.renderYawOffset = (float)Math.atan((double)(p_147046_3_ / 40.0F)) * 20.0F;
        p_147046_5_.rotationYaw = (float)Math.atan((double)(p_147046_3_ / 40.0F)) * 40.0F;
        p_147046_5_.rotationPitch = -((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F;
        p_147046_5_.rotationYawHead = p_147046_5_.rotationYaw;
        p_147046_5_.prevRotationYawHead = p_147046_5_.rotationYaw;
        GL11.glTranslatef(0.0F, p_147046_5_.yOffset, 0.0F);
        RenderManager.instance.playerViewY = 180.0F;
        RenderManager.instance.renderEntityWithPosYaw(p_147046_5_, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        p_147046_5_.renderYawOffset = f2;
        p_147046_5_.rotationYaw = f3;
        p_147046_5_.rotationPitch = f4;
        p_147046_5_.prevRotationYawHead = f5;
        p_147046_5_.rotationYawHead = f6;
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
}