package com.fabbe50.compressedblocks.core.client.gui;

import com.fabbe50.compressedblocks.common.container.ContainerBlazeGen;
import com.fabbe50.compressedblocks.common.tileentities.TileEntityBlazeGen;
import com.fabbe50.compressedblocks.core.reference.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by fabbe50 on 20/04/2016.
 */
public class GUIBlazeGen extends GuiContainer {

    public static final ResourceLocation background = new ResourceLocation(Reference.MOD_ID, "textures/gui/debug.png");

    public TileEntityBlazeGen blazeGen;

    public GUIBlazeGen (InventoryPlayer inventoryPlayer, TileEntityBlazeGen entity) {
        super(new ContainerBlazeGen(inventoryPlayer, entity));

        this.blazeGen = entity;

        this.xSize = 176;
        this.ySize = 166;
    }

    public void drawGuiContainerForegroundLayer(int par1, int par2) {
        String name = this.blazeGen.hasCustomInventoryName() ? this.blazeGen.getInventoryName() : I18n.format(this.blazeGen.getInventoryName(), new Object[0]);

        this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 130, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
    }
}
