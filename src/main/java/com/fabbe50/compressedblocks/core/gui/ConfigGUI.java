package com.fabbe50.compressedblocks.core.gui;

import com.fabbe50.compressedblocks.core.handler.ConfigurationHandler;
import com.fabbe50.compressedblocks.core.reference.Reference;
import com.google.common.collect.Lists;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.List;
import java.util.Locale;

/**
 * Created by fabbe50 on 23/09/2016.
 */
public class ConfigGUI extends GuiConfig {
    @SuppressWarnings("deprecation")
    public ConfigGUI(GuiScreen parentScreen) {
        super(parentScreen, getConfigElements(), Reference.MOD_ID, false, false, I18n.translateToLocal(String.format("%s.%s", Reference.MOD_ID.toLowerCase(Locale.US), "configgui.title")));
    }

    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> list = Lists.newArrayList();

        list.add(new ConfigElement(ConfigurationHandler.general));
        list.add(new ConfigElement(ConfigurationHandler.vanillaTweaks));

        return list;
    }
}
