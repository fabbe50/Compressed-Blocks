package com.fabbe50.compressedblocks.init;

import com.fabbe50.compressedblocks.handler.ConfigurationHandler;
import com.fabbe50.compressedblocks.item.ItemCB;
import com.fabbe50.compressedblocks.item.ItemDummy;
import com.fabbe50.compressedblocks.item.ItemEndgamium;
import com.fabbe50.compressedblocks.item.ItemEndgamiumSword;
import com.fabbe50.compressedblocks.reference.Reference;
import com.fabbe50.compressedblocks.utility.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created by fabbe50 on 14/01/2016.
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
    public static final ItemCB dummyitem = new ItemDummy();
    public static final ItemCB endgamium = new ItemEndgamium();
    public static final ItemCB endgamiumsword = new ItemEndgamiumSword();

    public static void init() {
        LogHelper.info("Adding Items");
        GameRegistry.registerItem(endgamium, "endgamium");
        GameRegistry.registerItem(endgamiumsword, "endgamiumsword");
        LogHelper.info("Done!");

        if (ConfigurationHandler.enableDebugItems == true){
            LogHelper.info("Adding Debug Items");
            GameRegistry.registerItem(dummyitem, "dummyitem");
        }
    }
}
