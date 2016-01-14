package com.fabbe50.compressedblocks.init;

import com.fabbe50.compressedblocks.item.ItemCB;
import com.fabbe50.compressedblocks.item.ItemEndgamium;
import com.fabbe50.compressedblocks.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by fabbe50 on 14/01/2016.
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
    public static final ItemCB endgamium = new ItemEndgamium();

    public static void init() {
        GameRegistry.registerItem(endgamium, "endgamium");
    }
}
