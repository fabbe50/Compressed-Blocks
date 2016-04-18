package com.fabbe50.compressedblocks.init;

import com.fabbe50.compressedblocks.item.*;
import com.fabbe50.compressedblocks.item.items.*;
import com.fabbe50.compressedblocks.item.tools.ItemModMultitool;
import com.fabbe50.compressedblocks.item.tools.ItemModSword;
import com.fabbe50.compressedblocks.lib.EnumModToolMaterial;
import com.fabbe50.compressedblocks.reference.Reference;
import com.fabbe50.compressedblocks.utility.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;

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
    public static final ItemCB endgamiumstages = new ItemEndgamiumStages();
    public static final ItemCB endgamiummultitool = new ItemModMultitool(EnumModToolMaterial.endgamium);
    public static final ItemCB endgamiumsword = new ItemModSword(EnumModToolMaterial.endgamium);
    public static final ItemCB potatosword = new ItemModSword(EnumModToolMaterial.potato);
    public static final ItemCB missingitem = new ItemMissing();

    public static void init() {
        LogHelper.info("Adding Items");
        GameRegistry.registerItem(endgamium, "endgamium");
        GameRegistry.registerItem(dummyitem, "dummyitem");
        GameRegistry.registerItem(endgamiumnugget, "endgamiumnugget");
        GameRegistry.registerItem(infusedbone, "infusedbone");
        GameRegistry.registerItem(potatobone, "potatobone");
        GameRegistry.registerItem(endgamiumstages, "endgamiumstages");
        GameRegistry.registerItem(endgamiummultitool, "endgamiumtool");
        GameRegistry.registerItem(endgamiumsword, "endgamiumsword");
        GameRegistry.registerItem(potatosword, "potatosword");
        GameRegistry.registerItem(missingitem, "missing");
        LogHelper.info("Done!");
    }
}
