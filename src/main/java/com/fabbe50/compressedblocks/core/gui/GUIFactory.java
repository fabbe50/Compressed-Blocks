package com.fabbe50.compressedblocks.core.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;

import java.util.Set;

/**
 * Created by fabbe50 on 23/09/2016.
 */
public class GUIFactory implements IModGuiFactory {
    @Override
    public void initialize(Minecraft minecraftInstance) {

    }

    @Override
    public boolean hasConfigGui() {
        return false;
    }

    @Override
    public GuiScreen createConfigGui(GuiScreen parentScreen) {
        return null;
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }
}
