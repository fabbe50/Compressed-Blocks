package com.fabbe50.compressedblocks.core.registry;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by fabbe50 on 21/09/2016.
 */
public class ToolMaterialRegistry {
    public static Item.ToolMaterial TOOL_ENDGAMIUM;

    public static void init() {
        TOOL_ENDGAMIUM = EnumHelper.addToolMaterial("endgamium", 3, 64000, 20f, 18f, 30);
    }
}
