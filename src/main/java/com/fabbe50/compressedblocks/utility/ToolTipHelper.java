package com.fabbe50.compressedblocks.utility;

import com.fabbe50.compressedblocks.reference.Reference;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import org.apache.commons.lang3.text.WordUtils;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by fabbe50 on 19/03/2016.
 */
public class ToolTipHelper {
    private static final Map<String, String> modNames = new HashMap<String, String>();

    public ToolTipHelper () {
        Map<String, ModContainer> modMapping = Loader.instance().getIndexedModList();
        for (Map.Entry<String, ModContainer> mod : modMapping.entrySet()) {
            String lowercaseId = mod.getKey().toLowerCase(Locale.ENGLISH);
            String modName = mod.getValue().getName();
            modNames.put(lowercaseId, modName);
        }
    }

    @Nonnull
    public static String getModName(@Nonnull Item item) {
        ResourceLocation itemResourceLocation = new ResourceLocation(GameData.getItemRegistry().getNameForObject(item));
        String modId = itemResourceLocation.getResourceDomain();
        String lowercaseModId = modId.toLowerCase(Locale.ENGLISH);
        String modName = modNames.get(lowercaseModId);
        if (modName == null) {
            modName = WordUtils.capitalize(modId);
            modNames.put(lowercaseModId, modName);
        }
        return modName;
    }

    @SubscribeEvent
    public void onToolTip(@Nonnull ItemTooltipEvent event) {
        ItemStack itemStack = event.itemStack;
        if (itemStack == null) {
            return;
        }

        Item item = itemStack.getItem();
        if (item == null) {
            return;
        }

        String modName = getModName(item);
        if (modName.equalsIgnoreCase(Reference.MOD_NAME))
            event.toolTip.add(ColorHelper.tooltip + modName);
    }
}
