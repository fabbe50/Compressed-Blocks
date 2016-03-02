package com.fabbe50.compressedblocks.init;

import com.fabbe50.compressedblocks.handler.ConfigurationHandler;
import com.fabbe50.compressedblocks.item.*;
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
    public static final ItemCB endgamiumnugget = new ItemEndgamiumNugget();
    public static final ItemCB infusedbone = new ItemInfusedBone();
    public static final ItemCB potatobone = new ItemPotatoBone();
    //public static final ItemCB endgamiumsword = new ItemEndgamiumSword();

    public static void init() {
        LogHelper.info("Adding Items");
        GameRegistry.registerItem(endgamium, "endgamium");
        GameRegistry.registerItem(dummyitem, "dummyitem");
        GameRegistry.registerItem(endgamiumnugget, "endgamiumnugget");
        GameRegistry.registerItem(infusedbone, "infusedbone");
        GameRegistry.registerItem(potatobone, "potatobone");
        //GameRegistry.registerItem(endgamiumsword, "endgamiumsword");
        LogHelper.info("Done!");
    }
}
